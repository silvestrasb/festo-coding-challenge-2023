package com.festo.codingchallenge2023.chapter.util.general.service;

import com.festo.codingchallenge2023.chapter.util.general.model.Fraction;

public class MathFractionUtil {

    public static Fraction addTwoFractions(Fraction x, Fraction y) {
        Long numerator = (x.numerator() * y.denominator()) + (y.numerator() * x.denominator());
        Long denominator = x.denominator() * y.denominator();
        return new Fraction(numerator, denominator);
    }

    public static boolean areFractionsEqual(Fraction x, Fraction y) {
        return (x.numerator() * y.denominator()) == (y.numerator() * x.denominator());
    }
}
