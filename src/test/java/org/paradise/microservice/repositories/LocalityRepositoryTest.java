package org.paradise.microservice.repositories;

import org.paradise.microservice.configuration.RepositoryConfiguration;
import org.paradise.microservice.domain.Locality;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by terrence on 31/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class LocalityRepositoryTest {

    @Autowired
    private LocalityRepository localityRepository;

    @Test
    public void testFindByLocalityName() throws Exception {

        String localityName = "POINT COOK";

        List<Locality> localityList = localityRepository.findByLocalityName(localityName);

        assertEquals("Wrong list size", 1, localityList.size());

        assertEquals("Wrong Locality Id", "00012220", localityList.get(0).getLocalityId());
        assertEquals("Wrong Locality Name", localityName, localityList.get(0).getLocalityName());
        assertEquals("Wrong Postcode", "3030", localityList.get(0).getPostcode());
        assertEquals("Wrong State", "VIC", localityList.get(0).getState());
        assertEquals("Wrong Locality Did", "", localityList.get(0).getLocalityDid());
    }

    @Test
    public void testFindByLocalityNameAndStateAndPostcode() throws Exception {

        String localityName = "KEW";
        String state = "VIC";
        String postcode = "3101";

        List<Locality> localityList = localityRepository.findByLocalityNameAndStateAndPostcode(localityName, state, postcode);

        assertEquals("Wrong list size", 1, localityList.size());

        assertEquals("Wrong Locality Id", "00003419", localityList.get(0).getLocalityId());
        assertEquals("Wrong Locality Name", localityName, localityList.get(0).getLocalityName());
        assertEquals("Wrong State", state, localityList.get(0).getState());
        assertEquals("Wrong Postcode", postcode, localityList.get(0).getPostcode());
        assertEquals("Wrong Locality Did", "", localityList.get(0).getLocalityDid());

        state = "NSW";
        postcode = "2439";

        localityList = localityRepository.findByLocalityNameAndStateAndPostcode(localityName, state, postcode);

        assertEquals("Wrong list size", 1, localityList.size());

        assertEquals("Wrong Locality Id", "00009711", localityList.get(0).getLocalityId());
        assertEquals("Wrong Locality Name", localityName, localityList.get(0).getLocalityName());
        assertEquals("Wrong State", state, localityList.get(0).getState());
        assertEquals("Wrong Postcode", postcode, localityList.get(0).getPostcode());
        assertEquals("Wrong Locality Did", "28009711", localityList.get(0).getLocalityDid());
    }

    @Test
    public void testFindAllOrderedLocalities() throws Exception {

        List<Locality> localityList = localityRepository.findAllOrderedLocalities();

        assertNotEquals("Empty list returned", 0, localityList.size());
    }

}