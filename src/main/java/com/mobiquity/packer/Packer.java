package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Packer {

    private static Logger logger = LoggerFactory.getLogger(Packer.class);
    private PackageDataReader packageDataReader;
    private PackageDeserializer packageDeserializer;
    private PackageAnalyzer packageAnalyzer;
    private List<Exception> exceptions = new ArrayList<>();
    private StringBuilder builder = new StringBuilder();

    private Packer() {
        packageDataReader = new PackageDataReader();
        packageDeserializer = new PackageDeserializer(this::onError);
        packageDataReader.addObserver(packageDeserializer);
        packageAnalyzer = new PackageAnalyzer(this::onFindResult);
        packageDeserializer.addObserver(packageAnalyzer);
    }

    public static String pack(String filePath) throws APIException {
        try (FileInputStream in = new FileInputStream(filePath)) {
            Packer packer = new Packer();
            packer.packageDataReader.readAll(in);
            if (!packer.exceptions.isEmpty())
                throw new APIException("Error exists", packer.exceptions.get(0));
            return packer.builder.toString();
        } catch (IOException e) {
            throw new APIException("Error reading file", e);
        }
    }

    private void onError(Exception e) {
        logger.error(e.getMessage(), e);
        exceptions.add(e);
    }

    private void onFindResult(String result) {
        builder.append(result).append(System.lineSeparator());
    }
}
