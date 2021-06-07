package com.mobiquity.packer;

import com.mobiquity.model.Knapsack;
import com.mobiquity.model.KnapsackItem;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        result.sort(Comparator.comparingInt(KnapsackItem::getIndex));
        List<Integer> index = result.stream().map(KnapsackItem::getIndex).collect(Collectors.toList());

        if (!result.isEmpty())
            onFindResult.accept(StringUtils.join(index, ","));
        else
            onFindResult.accept("-");
    }
}
