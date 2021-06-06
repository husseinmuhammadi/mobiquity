package com.mobiquity.base;

import java.io.InputStream;

public class KnapsackTestBase {
    protected InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    protected ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
