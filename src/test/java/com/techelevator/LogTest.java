package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LogTest {

    @Test
    public void checkLogFileAndAppend() {
        Log.log("Hello");
        Log.log("Hello again");
    }
}
