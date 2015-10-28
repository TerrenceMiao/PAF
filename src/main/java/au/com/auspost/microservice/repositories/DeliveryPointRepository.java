package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.domain.DeliveryPoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by terrence on 28/10/15.
 */
public interface DeliveryPointRepository extends CrudRepository<DeliveryPoint, Integer> {

    String FIND_BY_DELIVY_POINT_GROUP_ID_AND_HOUSE_NBR_1_QUERY
            = "SELECT d FROM DeliveryPoint d WHERE d.delivyPointGroupId = :delivyPointGroupId and d.houseNbr1 = :houseNbr1";

    @Query(FIND_BY_DELIVY_POINT_GROUP_ID_AND_HOUSE_NBR_1_QUERY)
    List<DeliveryPoint> findByDelivyPointGroupIdAndHouseNbr1(@Param("delivyPointGroupId") String delivyPointGroupId,
                                                             @Param("houseNbr1") String houseNbr1);
    
}
