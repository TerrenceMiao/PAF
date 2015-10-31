package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.configuration.RepositoryConfiguration;
import au.com.auspost.microservice.domain.DeliveryPoint;
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
public class DeliveryPointRepositoryTest {

    @Autowired
    private DeliveryPointRepository deliveryPointRepository;

    @Test
    public void testFindByDelivyPointGroupIdAndHouseNbr1() throws Exception {

        String delivyPointGroupId = "00783106";
        String houseNbr1 = "00018";

        List<DeliveryPoint> deliveryPointList
                = deliveryPointRepository.findByDelivyPointGroupIdAndHouseNbr1(delivyPointGroupId, houseNbr1);

        assertEquals("Wrong list size", 1, deliveryPointList.size());

        assertEquals("Wrong Delivery Point Id", "51123887", deliveryPointList.get(0).getDelivyPointId());
        assertEquals("Wrong Delivery Point Group Id", delivyPointGroupId, deliveryPointList.get(0).getDelivyPointGroupId());
        assertEquals("Wrong House Nbr 1", houseNbr1, deliveryPointList.get(0).getHouseNbr1());
        assertEquals("Wrong House Nbr Sfx 1", "", deliveryPointList.get(0).getHouseNbrSfx1());
        assertEquals("Wrong House Nbr 2", "00000", deliveryPointList.get(0).getHouseNbr2());
        assertEquals("Wrong House Nbr Sfx 2", "", deliveryPointList.get(0).getHouseNbrSfx2());
        assertEquals("Wrong Flat Unit Type", "", deliveryPointList.get(0).getFlatUnitType());
        assertEquals("Wrong Flat Unit Nbr", "", deliveryPointList.get(0).getFlatUnitNbr());
        assertEquals("Wrong Floor Level Type", "", deliveryPointList.get(0).getFloorLevelType());
        assertEquals("Wrong Floor Level Nbr", "", deliveryPointList.get(0).getFloorLevelNbr());
        assertEquals("Wrong Lot Nbr", "", deliveryPointList.get(0).getLotNbr());
        assertEquals("Wrong Postal Delivery Nbr", "00000", deliveryPointList.get(0).getPostalDeliveryNbr());
        assertEquals("Wrong Postal Delivery Nbr Pfx", "", deliveryPointList.get(0).getPostalDeliveryNbrPfx());
        assertEquals("Wrong Postal Delivery Nbr Sfx", "", deliveryPointList.get(0).getPostalDeliveryNbrSfx());
        assertEquals("Wrong Primary Point Ind", "R", deliveryPointList.get(0).getPrimaryPointInd());
    }
}