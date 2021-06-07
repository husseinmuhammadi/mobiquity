package com.mobiquity.packer;

import com.mobiquity.model.Knapsack;
import com.mobiquity.model.KnapsackItem;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;

public class PackageAnalyzer implements Observer {

    private final Consumer<String> onFindResult;

    public PackageAnalyzer(Consumer<String> onFindResult) {
        this.onFindResult = onFindResult;
    }

    @Override
    public void update(Observable o, Object arg) {
        Knapsack knapsack = (Knapsack) arg;

        KnapsackAlgorithm knapsackAlgorithm = new KnapsackAlgorithm(knapsack.getLimit(), knapsack.getItems().toArray(KnapsackItem[]::new));
        List<KnapsackItem> result = knapsackAlgorithm.calc();

        if (!result.isEmpty())
            onFindResult.accept(StringUtils.join(result, ","));
        else
            onFindResult.accept("-");
    }
}
