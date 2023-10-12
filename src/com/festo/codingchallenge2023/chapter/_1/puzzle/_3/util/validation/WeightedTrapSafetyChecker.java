package com.festo.codingchallenge2023.chapter._1.puzzle._3.util.validation;

import com.festo.codingchallenge2023.chapter._1.puzzle._3.model.Fraction;
import com.festo.codingchallenge2023.chapter._1.puzzle._3.model.Trap;

import java.util.List;
import java.util.stream.Stream;

/**
 * This checker uses these rules to determine the safety of a given trap:
 * <p>
 * Equality: Both sides of the scale must contain the same number of objects.
 * Equality: Both sides of the scale must carry exactly the same weight.
 * Diversity: All objects on the scale must have different weights. No two objects may have the same weight."
 */
public class WeightedTrapSafetyChecker {

    public static boolean isSafe(Trap trap) {

        return compliesWithNumberEqualityRule(trap.leftWeightList, trap.rightWeightList) &&
                compliesWithWeightEqualityRule(trap.leftWeightList, trap.rightWeightList) &&
                compliesWithDiversityRule(trap.leftWeightList, trap.rightWeightList);
    }

    private static boolean compliesWithNumberEqualityRule(List<Long> sideOne, List<Long> sideTwo) {
        return sideOne.size() == sideTwo.size();
    }

    private static boolean compliesWithWeightEqualityRule(List<Long> sideOne, List<Long> sideTwo) {
        Fraction sumOfSideOneFractions = sideOne.stream()
                .map(weight -> new Fraction(1L, weight))
                .reduce(new Fraction(1L, 1L), WeightedTrapSafetyChecker::addTwoFractions);

        Fraction sumOfSideTwoFractions = sideTwo.stream()
                .map(weight -> new Fraction(1L, weight))
                .reduce(new Fraction(1L, 1L), WeightedTrapSafetyChecker::addTwoFractions);

        return areFractionsEqual(sumOfSideOneFractions, sumOfSideTwoFractions);
    }

    private static boolean compliesWithDiversityRule(List<Long> sideOne, List<Long> sideTwo) {
        return Stream.concat(sideOne.stream(), sideTwo.stream())
                .distinct().count() == sideOne.size() + sideTwo.size();
    }

    /**
     * Calculation of fractions should be done in a separate class. (Single responsibility principle.)
     */
    private static Fraction addTwoFractions(Fraction x, Fraction y) {
        Long numerator = (x.numerator() * y.denominator()) + (y.numerator() * x.denominator());
        Long denominator = x.denominator() * y.denominator();
        return new Fraction(numerator, denominator);
    }

    private static boolean areFractionsEqual(Fraction x, Fraction y) {
        return (x.numerator() * y.denominator()) == (y.numerator() * x.denominator());
    }
}
