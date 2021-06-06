package com.mobiquity.model;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnapsackTest {

    private static KnapsackItem create(Integer index) {
        try {
            return KnapsackItem.builder().index(index).build();
        } catch (APIException e) {
        }
        return null;
    }

    @Test
    void givenBuilderPattern_whenCreateNewInstance_thenPropertiesShouldCorrect() {
        Knapsack knapsack = Knapsack.builder()
                .limit(40).build();
        assertEquals(knapsack.getLimit(), 40);
    }

    /**
     * Knapsack limit could not be negative
     */
    @Test
    void givenKnapsackBuilder_whenLimitIsLessThanZero_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            Knapsack knapsack = Knapsack.builder()
                    .limit(-1).build();
        });
    }

    /**
     * Knapsack limit valid range is between 0 to 100
     */
    @Test
    void givenKnapsackBuilder_whenLimitIsOutOfBound_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            Knapsack knapsack = Knapsack.builder()
                    .limit(120).build();
        });
    }

    /**
     * There might be up to 15 items you need to choose from
     */
    @Test
    void givenKnapsackBuilder_whenNumberOfKnapsankIsMoreThanFifteen_thenAPIExceptionMightRaise() {
        Assertions.assertThrows(APIException.class, () -> {
            Knapsack knapsack = Knapsack.builder().limit(40)
                    .items(IntStream.range(1, 20).boxed()
                            .map(KnapsackTest::create)
                            .toArray(KnapsackItem[]::new)
                    ).build();
        });
    }
}