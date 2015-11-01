package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.configuration.RepositoryConfiguration;
import au.com.auspost.microservice.domain.Locality;
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
    public void testFindAllOrderedLocalities() throws Exception {

        List<Locality> localityList = localityRepository.findAllOrderedLocalities();

        assertNotEquals("", 0, localityList.iterator());
    }

}