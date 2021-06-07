package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.KnapsackItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

class KnapsackAlgorithmTest {

    private KnapsackAlgorithm knapsackAlgorithm;

    public static final String KNAPSACK_ITEMS = "(1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

    @Mock
    private Consumer<Boolean> errorParsing;

    @BeforeEach
    void setUp() throws APIException, ParseException {
        MockitoAnnotations.initMocks(this);

        PackageDeserializer packageDeserializer = new PackageDeserializer(errorParsing);
        List<KnapsackItem> knapsackItems = packageDeserializer.items(KNAPSACK_ITEMS);
        knapsackAlgorithm = new KnapsackAlgorithm(packageDeserializer.limit(KNAPSACK_ITEMS), knapsackItems.toArray(KnapsackItem[]::new));
    }

    @Test
    void givenKnapsackAlgorithm_whenCalculateItemsInPackage_thenSumOfWeightShouldNotExceedThePackageLimit() {
        List<KnapsackItem> items = knapsackAlgorithm.calc();
        assertThat(items.stream().mapToDouble(KnapsackItem::getWeight).sum())
                .isLessThanOrEqualTo(knapsackAlgorithm.getWeight());
    }
}