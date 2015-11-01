package au.com.auspost.microservice.services;

import au.com.auspost.microservice.configuration.RepositoryConfiguration;
import au.com.auspost.microservice.domain.Locality;
import au.com.auspost.microservice.dto.PostalAddress;
import au.com.auspost.microservice.repositories.DeliveryPointGroupRepository;
import au.com.auspost.microservice.repositories.DeliveryPointRepository;
import au.com.auspost.microservice.repositories.LocalityRepository;
import org.junit.After;
import org.junit.Before;
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
public class PostalAddressServiceTest {

    @Autowired
    private LocalityRepository localityRepository;
    @Autowired
    private DeliveryPointGroupRepository deliveryPointGroupRepository;
    @Autowired
    private DeliveryPointRepository deliveryPointRepository;

    private PostalAddressService postalAddressService = new PostalAddressService.Impl();

    private PostalAddress postalAddress;

    @Before
    public void setUp() throws Exception {

        postalAddressService.setLocalityRepository(localityRepository);
        postalAddressService.setDeliveryPointGroupRepository(deliveryPointGroupRepository);
        postalAddressService.setDeliveryPointRepository(deliveryPointRepository);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetDpidFromPostalAddress() throws Exception {

        String houseNumber1 = "00018";
        String streetName = "Sandlewood";
        String streetType = "Lane";
        String localityName = "POINT COOK";
        String state = "VIC";
        String postcode = "3030";

        String dpid = "51123887";

        postalAddress = new PostalAddress();

        postalAddress.setHouseNumber1(houseNumber1);
        postalAddress.setStreetName(streetName);
        postalAddress.setStreetType(streetType);
        postalAddress.setLocalityName(localityName);
        postalAddress.setState(state);
        postalAddress.setPostcode(postcode);

        assertEquals("Wrong Delivery Point ID (DPID) returned", dpid, postalAddressService.getDpidFromPostalAddress(postalAddress));
    }

    @Test
    public void testGetAllOrderedLocalities() throws Exception {

        List<Locality> localityList = postalAddressService.getAllOrderedLocalities();

        assertNotEquals("Empty Locality list returned", 0, localityList.size());
    }

}