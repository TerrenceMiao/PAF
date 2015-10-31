package au.com.auspost.microservice.controllers;

import au.com.auspost.microservice.configuration.RepositoryConfiguration;
import au.com.auspost.microservice.model.Address;
import au.com.auspost.microservice.repositories.DeliveryPointGroupRepository;
import au.com.auspost.microservice.repositories.DeliveryPointRepository;
import au.com.auspost.microservice.repositories.LocalityRepository;
import au.com.auspost.microservice.services.PostalAddressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;

/**
 * Created by terrence on 31/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class AddressControllerTest {

    @Autowired
    private LocalityRepository localityRepository;
    @Autowired
    private DeliveryPointGroupRepository deliveryPointGroupRepository;
    @Autowired
    private DeliveryPointRepository deliveryPointRepository;

    private AddressController addressController = new AddressController();

    private PostalAddressService postalAddressService = new PostalAddressService.Impl();

    @Before
    public void setUp() throws Exception {

        postalAddressService.setLocalityRepository(localityRepository);
        postalAddressService.setDeliveryPointGroupRepository(deliveryPointGroupRepository);
        postalAddressService.setDeliveryPointRepository(deliveryPointRepository);

        addressController.setPostalAddressService(postalAddressService);
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

        assertEquals("Wrong Delivery Point ID (DPID) returned", dpid, addressController.getDeliveryPointId(address, bindingResult));
    }

}