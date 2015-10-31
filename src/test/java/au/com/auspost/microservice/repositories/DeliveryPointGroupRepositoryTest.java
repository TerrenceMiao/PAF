package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.configuration.RepositoryConfiguration;
import au.com.auspost.microservice.domain.DeliveryPointGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by terrence on 31/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class DeliveryPointGroupRepositoryTest {

    @Autowired
    private DeliveryPointGroupRepository deliveryPointGroupRepository;

    @Test
    public void testFindByLocalityIdAndStreetNameAndStreetType() throws Exception {

        String localityId = "00012220";
        String streetName = "Sandlewood";
        String streetType = "Lane";

        List<DeliveryPointGroup> deliveryPointGroupList
                = deliveryPointGroupRepository.findByLocalityIdAndStreetNameAndStreetType(localityId, streetName, streetType);

        assertEquals("Wrong list size", 1, deliveryPointGroupList.size());

        assertEquals("Wrong Delivery Point Group Id", "00783106", deliveryPointGroupList.get(0).getDelivyPointGroupId());
        assertEquals("Wrong Locality Id", localityId, deliveryPointGroupList.get(0).getLocalityId());
        assertEquals("Wrong Street Name", streetName.toUpperCase(), deliveryPointGroupList.get(0).getStreetName());
        assertEquals("Wrong Street Type", streetType.toUpperCase(), deliveryPointGroupList.get(0).getStreetType());
        assertEquals("Wrong Street Sfx", "", deliveryPointGroupList.get(0).getStreetSfx());
        assertEquals("Wrong Postal Delivery Type", "", deliveryPointGroupList.get(0).getPostalDeliveryType());
        assertEquals("Wrong Delivery Point Group Did", "", deliveryPointGroupList.get(0).getDelivyPointGroupDid());
    }

}