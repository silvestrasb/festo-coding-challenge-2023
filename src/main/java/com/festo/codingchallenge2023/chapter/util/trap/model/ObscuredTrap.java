package com.festo.codingchallenge2023.chapter.util.trap.model;

import java.util.List;

public class ObscuredTrap {
    public List<Long> leftWeightList;
    public Integer noOfWeightsOnTheRight;
    public Integer id;

    public ObscuredTrap(List<Long> leftWeightList, Integer noOfWeightsOnTheRight, Integer id) {
        this.leftWeightList = leftWeightList;
        this.noOfWeightsOnTheRight = noOfWeightsOnTheRight;
        this.id = id;
    }
}