package org.paradise.microservice.services;

import org.paradise.microservice.configuration.RepositoryConfiguration;
import org.paradise.microservice.domain.Locality;
import org.paradise.microservice.dto.PostalAddress;
import org.paradise.microservice.repositories.DeliveryPointGroupRepository;
import org.paradise.microservice.repositories.DeliveryPointRepository;
import org.paradise.microservice.repositories.LocalityRepository;
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
    public void testGetDpidFromPostalAddressOneSuburb() throws Exception {

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
    public void testGetDpidFromPostalAddressTwoSameSuburbs() throws Exception {

        String houseNumber1 = "00006";
        String streetName = "Scott";
        String streetType = "St";
        String localityName = "KEW";
        String state = "VIC";
        String postcode = "3101";

        String dpid = "49670273";

        postalAddress = new PostalAddress();

        postalAddress.setHouseNumber1(houseNumber1);
        postalAddress.setStreetName(streetName);
        postalAddress.setStreetType(streetType);
        postalAddress.setLocalityName(localityName);
        postalAddress.setState(state);
        postalAddress.setPostcode(postcode);

        assertEquals("Wrong Delivery Point ID (DPID) returned", dpid, postalAddressService.getDpidFromPostalAddress(postalAddress));

        houseNumber1 = "00009";
        streetName = "Glen Haven";
        streetType = "Dr";
        state = "NSW";
        postcode = "2439";

        dpid = "47499803";

        postalAddress.setHouseNumber1(houseNumber1);
        postalAddress.setStreetName(streetName);
        postalAddress.setStreetType(streetType);
        postalAddress.setState(state);
        postalAddress.setPostcode(postcode);

        assertEquals("Wrong Delivery Point ID (DPID) returned", dpid, postalAddressService.getDpidFromPostalAddress(postalAddress));
    }

    @Test
    public void testGetAllOrderedLocalities() throws Exception {

        List<Locality> localityList = postalAddressService.getAllOrderedLocalities();

        assertNotEquals("Empty Locality list returned", 0, localityList.size());
    }

    @Test
    public void testFindAllStreetType() throws Exception {

        List<String> streetTypeList = postalAddressService.getAllStreetType();

        assertNotEquals("Empty list returned", 0, streetTypeList.size());
    }

}