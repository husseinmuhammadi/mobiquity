package com.mobiquity.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackTest {

    @Test
    void givenBuilderPattern_whenCreateNewInstance_PropertiesShouldCorrect() {
        KnapsackItem item = KnapsackItem.builder()
                .index(1).weight(40).cost(10).build();
        assertEquals(item.getIndex() , 1);
        assertEquals(item.getWeight(), 40);
        assertEquals(item.getCost(), 10);
    }
}