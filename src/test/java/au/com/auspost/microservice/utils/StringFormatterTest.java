package au.com.auspost.microservice.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by terrence on 21/11/15.
 */
public class StringFormatterTest {

    @Test
    public void testPadLeftWithZero() throws Exception {

        assertEquals("Incorrect string padding", "00002", StringFormatter.padLeftWithZero("2", 5));
        assertEquals("Incorrect string padding", "00018", StringFormatter.padLeftWithZero("18", 5));
        assertEquals("Incorrect string padding", "03040", StringFormatter.padLeftWithZero("3040", 5));
        assertEquals("Incorrect string padding", "10050", StringFormatter.padLeftWithZero("10050", 5));
    }

}