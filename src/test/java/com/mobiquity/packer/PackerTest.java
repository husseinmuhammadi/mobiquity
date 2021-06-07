package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

class PackerTest {
    private Logger logger = LoggerFactory.getLogger(PackerTest.class);

    @Test
    void name1() throws APIException, URISyntaxException {
        URI uri = getClass().getClassLoader().getResource("example_input").toURI();
        String pack = Packer.pack(uri.getPath());
        logger.info(pack);
    }
}