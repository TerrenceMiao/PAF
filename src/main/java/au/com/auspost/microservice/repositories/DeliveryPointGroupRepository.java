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

    String FIND_BY_LOCALITY_ID_AND_STREET_NAME_AND_STREET_TYPE_QUERY
            = "SELECT d FROM DeliveryPointGroup d WHERE d.localityId = :localityId AND d.streetName = :streetName AND d.streetType = :streetType";

    @Query(FIND_BY_LOCALITY_ID_AND_STREET_NAME_AND_STREET_TYPE_QUERY)
    List<DeliveryPointGroup> findByLocalityIdAndStreetNameAndStreetType(@Param("localityId") String localityId,
                                                                        @Param("streetName") String streetName,
                                                                        @Param("streetType") String streetType);

}
