package com.mobiquity.packer;

import com.mobiquity.model.KnapsackItem;

import java.util.ArrayList;
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

    private List<KnapsackItem> calc(double W, int n) {
        if (n == 0 || W == 0)
            return Collections.emptyList();

        // If weight of the nth item is more than Knapsack capacity W,
        // then this item cannot be included in the optimal solution
        if (items[n - 1].getWeight() > W) {
            return calc(W, n - 1);
        }

        // Find knapsack items consider that the n(th) item is in the selected items

        List<KnapsackItem> including = new ArrayList<>();
        Collections.addAll(including, items[n - 1]);
        including.addAll(calc(W - items[n - 1].getWeight(), n - 1));

        // Find knapsack items consider that the n(th) item is not in the selected items
        List<KnapsackItem> notIncluding = calc(W, n - 1);

        if (including.stream().mapToDouble(KnapsackItem::getCost).sum() == notIncluding.stream().mapToDouble(KnapsackItem::getCost).sum()) {
            if (including.stream().mapToDouble(KnapsackItem::getWeight).sum() < notIncluding.stream().mapToDouble(KnapsackItem::getWeight).sum()) {
                return including;
            }
        }

        if (including.stream().mapToDouble(KnapsackItem::getCost).sum() > notIncluding.stream().mapToDouble(KnapsackItem::getCost).sum()) {
            return including;
        }

        return notIncluding;
    }

    public double getWeight() {
        return weight;
    }

    public KnapsackItem[] getItems() {
        return items;
    }
}
