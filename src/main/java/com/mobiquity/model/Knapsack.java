package com.mobiquity.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;
import java.util.List;

public class Knapsack {
    private final double limit;
    private final List<KnapsackItem> items;

    public Knapsack(Builder builder) {
        this.limit = builder.limit;
        items = builder.items;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException ignored) {
        }
        return "";
    }

    public double getLimit() {
        return limit;
    }

    public List<KnapsackItem> getItems() {
        return items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private double limit;
        private List<KnapsackItem> items;

        public Builder() {
            this.items = new LinkedList<>();
        }

        public Builder limit(double limit) {
            this.limit = limit;
            return this;
        }

        public Builder owners(KnapsackItem... items) {
            for (KnapsackItem item : items)
                this.items.add(item);
            return this;
        }

        public Knapsack build() {
            return new Knapsack(this);
        }
    }
}
