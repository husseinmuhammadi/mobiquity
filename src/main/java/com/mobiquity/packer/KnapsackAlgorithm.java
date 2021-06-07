package com.mobiquity.packer;

import com.mobiquity.model.KnapsackItem;

import java.util.Collections;
import java.util.List;

public class KnapsackAlgorithm {

    private final double weight;
    private final KnapsackItem[] items;

    public KnapsackAlgorithm(double weight, KnapsackItem[] items) {
        this.weight = weight;
        this.items = items;
    }


    public List<KnapsackItem> calc() {
        return calc(weight, items.length);
    }

    private List<KnapsackItem> calc(double w, int n) {
        return Collections.emptyList();
    }

    public double getWeight() {
        return weight;
    }

    public KnapsackItem[] getItems() {
        return items;
    }
}
