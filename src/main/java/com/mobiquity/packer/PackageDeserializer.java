package com.mobiquity.packer;

import com.mobiquity.model.Knapsack;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

public class PackageDeserializer extends Observable implements Observer {

    private Logger logger = LoggerFactory.getLogger(PackageDeserializer.class);

    public static final String PATTERN = "^\\d*\\s*:(\\p{Space}*[(]([0-9]*),([0-9]*\\.?[0-9]*),\\p{Sc}([0-9]*)[)])+";

    public boolean match(String data) {
        throw  new NotImplementedException();
    }

    public static Knapsack unpack(String data) {
        throw  new NotImplementedException();
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.info("{}", arg);
    }
}
