package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Observable;

public class PackageDataReader extends Observable {

    private Logger logger = LoggerFactory.getLogger(PackageDataReader.class);

    public void readAll(InputStream in) throws APIException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank())
                    continue;

                setChanged();
                notifyObservers(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new APIException("File not found!");
        }
    }
}
