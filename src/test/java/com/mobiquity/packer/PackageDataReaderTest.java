package com.mobiquity.packer;

import com.mobiquity.base.KnapsackTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

class PackageDataReaderTest extends KnapsackTestBase {

    private Logger logger = LoggerFactory.getLogger(PackageDataReaderTest.class);

    private PackageDataReader packageDataReader;

    @Mock
    private PackageDeserializer packageDeserializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        packageDataReader = new PackageDataReader();
        packageDataReader.addObserver(packageDeserializer);
    }

    @Test
    void givenPackageDataReader_whenReadExampleInput_thenPackageDeserializerShouldCall4Times() {
        Assertions.assertDoesNotThrow(() -> {
            try (InputStream in = getResourceAsStream("example_input")) {
                Assertions.assertNotNull(in);
                packageDataReader.readAll(in);
                Mockito.verify(packageDeserializer, Mockito.times(4))
                        .update(Mockito.any(), Mockito.any());
                Mockito.verify(packageDeserializer, Mockito.times(1))
                        .update(Mockito.any(), Mockito.argThat("8 : (1,15.3,€34)"::equals));
            }
        });
    }

    @Test
    void givenPackageDataReader_whenReadExampleInput_thenPackageDeserializerShouldExamineTheDataAgainstTheRegex() {
        Assertions.assertDoesNotThrow(() -> {
            try (InputStream in = getResourceAsStream("example_input")) {
                Assertions.assertNotNull(in);
                packageDataReader.readAll(in);
                Mockito.verify(packageDeserializer, Mockito.times(4))
                        .update(Mockito.any(), Mockito.any());
            }
        });
    }
}