package com.mobiquity.model;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackItemTest {

    /**
     * Testing knapsack builder to create an instance
     */
    @Test
    void givenBuilderPattern_whenCreateNewInstance_thenPropertiesShouldSetCorrect() throws APIException {
        KnapsackItem item = KnapsackItem.builder()
                .index(1).weight(40).cost(10).build();
        assertEquals(item.getIndex(), 1);
        assertEquals(item.getWeight(), 40);
        assertEquals(item.getCost(), 10);
    }

    /**
     * Knapsack item index might be in valid range
     * It can not be negative, any try to create an instance with negative index
     * faces with APIException
     */
    @Test
    void givenKnapsackItemBuilder_whenIndexIsLessThanZero_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            KnapsackItem item = KnapsackItem.builder().index(-1).build();
        });
    }

    /**
     * Knapsack item index might be in valid range
     * It can not be more than 15, any try to create an instance with index greater than 15
     * faces with APIException
     */
    @Test
    void givenKnapsackItemBuilder_whenIndexIsMoreThanFifteen_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            KnapsackItem item = KnapsackItem.builder().index(16).build();
        });
    }

    /**
     * Knapsack item weight might be in valid range
     * It can not be negative, any try to create an instance with negative weight
     * faces with APIException
     */
    @Test
    void givenKnapsackItemBuilder_whenWeightIsLessThanZero_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            KnapsackItem item = KnapsackItem.builder().weight(-1).build();
        });
    }

    /**
     * Knapsack item weight might be in valid range
     * It the valid range is between 0 to 100, any try to create an instance with weight more than 100
     * faces with APIException
     */
    @Test
    void givenKnapsackItemBuilder_whenWeightIsMoreThanHundred_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            KnapsackItem item = KnapsackItem.builder().weight(101).build();
        });
    }

    /**
     * Knapsack item weight might be in valid range
     * It can not be negative, any try to create an instance with negative weight
     * faces with APIException
     */
    @Test
    void givenKnapsackItemBuilder_whenCostIsLessThanZero_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            KnapsackItem item = KnapsackItem.builder().cost(-1).build();
        });
    }

    /**
     * Knapsack item weight might be in valid range
     * It the valid range is between 0 to 100, any try to create an instance with weight more than 100
     * faces with APIException
     */
    @Test
    void givenKnapsackItemBuilder_whenCostIsMoreThanHundred_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            KnapsackItem item = KnapsackItem.builder().cost(101).build();
        });
    }

}