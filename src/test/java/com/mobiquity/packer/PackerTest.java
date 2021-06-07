package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

class PackerTest {
    private Logger logger = LoggerFactory.getLogger(PackerTest.class);

    @Test
    void givenPacker_whenFilesWithFourEntry_thenResultShouldMatch() throws APIException, URISyntaxException {
        URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource("example_input")).toURI();
        String result = Packer.pack(uri.getPath());
        Assertions.assertThat(result.split(System.lineSeparator())).contains("4", "-", "2,7", "8,9");
    }

    @Test
    void givenPacker_whenReadFileWithInvalidFormat_thenApiException() {
        Assertions.assertThatThrownBy(() -> {
            URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource("invalid_input")).toURI();
            String result = Packer.pack(uri.getPath());
        }).isInstanceOf(APIException.class);
    }
}