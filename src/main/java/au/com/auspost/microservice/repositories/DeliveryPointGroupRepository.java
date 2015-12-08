package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.domain.DeliveryPointGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by terrence on 28/10/15.
 */
public interface DeliveryPointGroupRepository extends CrudRepository<DeliveryPointGroup, Integer> {

    String FIND_BY_LOCALITY_ID_AND_STREET_NAME_AND_STREET_TYPE_QUERY = "SELECT dpg FROM DeliveryPointGroup dpg "
            + "WHERE dpg.localityId = :localityId AND dpg.streetName = :streetName AND dpg.streetType = :streetType";

    String FIND_ALL_STREET_TYPE_QUERY = "SELECT DISTINCT CONCAT(UCASE(MID(dpg.streetType,1,1)),LCASE(MID(dpg.streetType,2))) as streetType FROM DeliveryPointGroup dpg "
            + "WHERE dpg.streetType != '' ORDER BY streetType";

    @Query(FIND_BY_LOCALITY_ID_AND_STREET_NAME_AND_STREET_TYPE_QUERY)
    List<DeliveryPointGroup> findByLocalityIdAndStreetNameAndStreetType(@Param("localityId") String localityId,
                                                                        @Param("streetName") String streetName,
                                                                        @Param("streetType") String streetType);
    @Query(FIND_ALL_STREET_TYPE_QUERY)
    List<String> findAllStreetType();

}
