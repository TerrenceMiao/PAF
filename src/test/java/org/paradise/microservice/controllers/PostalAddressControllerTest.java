package org.paradise.microservice.controllers;

import org.paradise.microservice.configuration.RepositoryConfiguration;
import org.paradise.microservice.model.Address;
import org.paradise.microservice.model.Dpid;
import org.paradise.microservice.model.StreetTypes;
import org.paradise.microservice.model.Suburbs;
import org.paradise.microservice.repositories.DeliveryPointGroupRepository;
import org.paradise.microservice.repositories.DeliveryPointRepository;
import org.paradise.microservice.repositories.LocalityRepository;
import org.paradise.microservice.services.PostalAddressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by terrence on 31/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class PostalAddressControllerTest {

    @Autowired
    private LocalityRepository localityRepository;
    @Autowired
    private DeliveryPointGroupRepository deliveryPointGroupRepository;
    @Autowired
    private DeliveryPointRepository deliveryPointRepository;

    private PostalAddressController postalAddressController = new PostalAddressController();

    private PostalAddressService postalAddressService = new PostalAddressService.Impl();

    @Before
    public void setUp() throws Exception {

        postalAddressService.setLocalityRepository(localityRepository);
        postalAddressService.setDeliveryPointGroupRepository(deliveryPointGroupRepository);
        postalAddressService.setDeliveryPointRepository(deliveryPointRepository);

        postalAddressController.setPostalAddressService(postalAddressService);
    }

    @Test
    public void testGetDeliveryPointId() throws Exception {

        String houseNumber1 = "00018";
        String streetName = "Sandlewood";
        String streetType = "Lane";
        String suburb = "POINT COOK";
        String state = "VIC";
        String postcode = "3030";

        String dpid = "51123887";

        Address address = new Address();
        address.setHouseNumber1(houseNumber1);
        address.setStreetName(streetName);
        address.setStreetType(streetType);
        address.setLocalityName(suburb);
        address.setState(state);
        address.setPostcode(postcode);

        BindingResult bindingResult = new BeanPropertyBindingResult(address, "address");

        ResponseEntity<Dpid> dpidResponseEntity = postalAddressController.getDeliveryPointId(address, bindingResult);

        assertEquals("Wrong Delivery Point ID (DPID) returned", dpid, dpidResponseEntity.getBody().getDpid());
    }

    @Test
    public void testGetAllOrderedLocalities() throws Exception {

        ResponseEntity<Suburbs> suburbsResponseEntity = postalAddressController.getAllOrderedLocalities();

        assertNotEquals("Empty Locality list returned", 0, suburbsResponseEntity.getBody().getSuburbList().size());
    }

    @Test
    public void testGetAllStreetType() throws Exception {

        ResponseEntity<StreetTypes> streetTypesResponseEntity = postalAddressController.getAllStreetType();

        assertNotEquals("Empty Street Type list returned", 0, streetTypesResponseEntity.getBody().getStreetTypeList().size());
    }

}