package org.paradise.microservice.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by terrence on 21/11/15.
 */
public class StringFormatterTest {

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPadLeftWithZeroFour() throws Exception {

        assertEquals("Incorrect string padding", "00002", StringFormatter.padLeftWithZero("2", 5));
    }

    @Test
    public void testPadLeftWithZeroThree() throws Exception {

        assertEquals("Incorrect string padding", "00018", StringFormatter.padLeftWithZero("18", 5));
    }

    @Test
    public void testPadLeftWithZeroOne() throws Exception {

        assertEquals("Incorrect string padding", "03040", StringFormatter.padLeftWithZero("3040", 5));
    }

    @Test
    public void testPadLeftWithoutZero() throws Exception {

        assertEquals("Incorrect string padding", "10050", StringFormatter.padLeftWithZero("10050", 5));
    }

    @Test
    public void testPadLeftExtraLongString() throws Exception {

        assertEquals("Incorrect string padding", "100050", StringFormatter.padLeftWithZero("100050", 5));
    }

}