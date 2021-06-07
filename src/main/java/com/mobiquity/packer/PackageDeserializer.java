package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Knapsack;
import com.mobiquity.model.KnapsackItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageDeserializer extends Observable implements Observer {

    private Logger logger = LoggerFactory.getLogger(PackageDeserializer.class);
    private final Consumer<Exception> onError;

    public PackageDeserializer(Consumer<Exception> onError) {
        this.onError = onError;
    }

    public int limit(String data) throws ParseException {
        Pattern pattern = Pattern.compile("^(\\p{Digit}+)\\p{Space}*:");
        Matcher matcher = pattern.matcher(data);
        if (matcher.find())
            return Integer.parseInt(matcher.group(1));
        throw new ParseException("Could not parse " + data, 0);
    }

    public List<KnapsackItem> items(String data) throws ParseException, APIException {
        List<KnapsackItem> knapsackItems = new LinkedList<>();
        Pattern pattern = Pattern.compile("[(]([0-9]*),([0-9]*\\.?[0-9]*),\\p{Sc}([0-9]*)[)]");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            if (matcher.groupCount() != 3)
                throw new ParseException("Could not parse " + data, 0);
            KnapsackItem knapsackItem = KnapsackItem.builder()
                    .index(Integer.parseInt(matcher.group(1)))
                    .weight(Double.parseDouble(matcher.group(2)))
                    .cost(Double.parseDouble(matcher.group(3)))
                    .build();
            knapsackItems.add(knapsackItem);
        }
        return knapsackItems;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            Knapsack knapsack = Knapsack.builder()
                    .limit(limit((String) arg))
                    .items(items((String) arg).toArray(KnapsackItem[]::new))
                    .build();
            setChanged();
            notifyObservers(knapsack);
        } catch (APIException | ParseException e) {
            onError.accept(e);
        }
    }
}
