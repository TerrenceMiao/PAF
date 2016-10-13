package org.paradise.microservice.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.Date;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Created by terrence on 11/10/2016.
 */
public class UselessDtoTest {

    private String aString = "I'm a string";
    private Integer anInteger = 99;
    private Boolean aBoolean = Boolean.FALSE;

    private String hidden = "Hide";
    private String notHidden = "Not Hide";

    private DateTime aDateTime = new DateTime(DateTimeZone.UTC);

    @Test
    public void testClone() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        // try to ignore properties during serialization / marshalling
        objectMapper.addMixIn(UselessDto.class, UselessDtoPropertiesFilterMixIn.class);

        // but Disable USE_ANNOTATIONS overrides ALL / ANY annotations including MixIn
        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);

        objectMapper.registerModule(new JodaModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        UselessDto uselessDto = generateUselessDto();
        UselessDto clonedUselessDto = null;

        long startTime = new Date().getTime();

        int loop = 100000;

        for (int i = 0; i < loop; i++) {
            String json = objectMapper.writeValueAsString(uselessDto);
            clonedUselessDto = objectMapper.readValue(json, UselessDto.class);
        }

        long endTime = new Date().getTime();

        long timeTaken = endTime - startTime;

        System.out.println("Time taken to complete " + loop + " times serializations: " + timeTaken + "ms");

        assertEquals("Cloning should be SAME", uselessDto.getaString(), clonedUselessDto.getaString());
        assertEquals("Cloning should be SAME", uselessDto.getAnInteger(), clonedUselessDto.getAnInteger());
        assertEquals("Cloning should be SAME", uselessDto.getaBoolean(), clonedUselessDto.getaBoolean());
        assertEquals("Cloning should be SAME", uselessDto.getHidden(), clonedUselessDto.getHidden());
        assertEquals("Cloning should be SAME", uselessDto.getNotHidden(), clonedUselessDto.getNotHidden());
        assertEquals("Cloning should be SAME", uselessDto.getaDateTime(), clonedUselessDto.getaDateTime());
    }

    private UselessDto generateUselessDto() {

        UselessDto uselessDto = new UselessDto();

        uselessDto.setaString(aString);
        uselessDto.setAnInteger(anInteger);
        uselessDto.setaBoolean(aBoolean);
        uselessDto.setHidden(hidden);
        uselessDto.setNotHidden(notHidden);
        uselessDto.setaDateTime(aDateTime);

        return uselessDto;
    }

}