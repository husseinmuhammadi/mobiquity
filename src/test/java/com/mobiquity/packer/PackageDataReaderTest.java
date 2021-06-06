package com.mobiquity.packer;

import com.mobiquity.base.KnapsackTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

class PackageDataReaderTest extends KnapsackTestBase {

    private Logger logger = LoggerFactory.getLogger(PackageDataReaderTest.class);
    private PackageDataReader packageDataReader;

    @BeforeEach
    void setUp() {
        packageDataReader = new PackageDataReader();
    }

    @Test
    void givenPackageDataReader_whenParseExampleInput_thenItShouldBeParsed() {
        Assertions.assertDoesNotThrow(() -> {
            try (InputStream in = getResourceAsStream("example_input")) {
                Assertions.assertNotNull(in);
                packageDataReader.readAll(in);
            }
        });
    }
}