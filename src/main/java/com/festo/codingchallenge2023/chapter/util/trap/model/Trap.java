package com.festo.codingchallenge2023.chapter.util.trap.model;

import java.util.List;

public class Trap {

    public List<Long> leftWeightList;
    public List<Long> rightWeightList;

    public Integer id;

    public Trap(List<Long> leftWeightList, List<Long> rightWeightList, Integer id) {
        this.leftWeightList = leftWeightList;
        this.rightWeightList = rightWeightList;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
