package com.mobiquity.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.exception.APIException;

public class KnapsackItem {
    private final int index; // optional
    private final double weight; // optional
    private final double cost; // optional

    private KnapsackItem(Builder builder) {
        this.index = builder.index;
        this.weight = builder.weight;
        this.cost = builder.cost;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getIndex() {
        return index;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
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

    public static class Builder {
        private int index; // optional
        private double weight; // optional
        private double cost; // optional

        public Builder index(int index) {
            this.index = index;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder cost(double cost) {
            this.cost = cost;
            return this;
        }

        public KnapsackItem build() throws APIException {
            KnapsackItem item = new KnapsackItem(this);
            validate(item);
            return item;
        }

        private void validate(KnapsackItem item) throws APIException {
            if (item.getIndex() < 0)
                throw new APIException("Index could not be negative!");

            if (item.getCost() > 100)
                throw new APIException("Cost could not be more than 100!");
        }
    }
}
