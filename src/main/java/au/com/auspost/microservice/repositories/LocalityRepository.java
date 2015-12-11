package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.domain.Locality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by terrence on 28/10/15.
 */
public interface LocalityRepository extends CrudRepository<Locality, Integer> {

    String FIND_BY_LOCALITY_NAME_QUERY = "SELECT l FROM Locality l WHERE l.localityName = :localityName";

    String FIND_BY_LOCALITY_NAME_AND_STATE_AND_POSTCODE_QUERY = "SELECT l FROM Locality l WHERE l.localityName = :localityName AND l.state = :state AND l.postcode = :postcode";

    String FIND_ALL_ORDERED_LOCALITIES_QUERY = "SELECT l FROM Locality l ORDER BY l.localityName";

    @Query(FIND_BY_LOCALITY_NAME_QUERY)
    List<Locality> findByLocalityName(@Param("localityName") String localityName);

    @Query(FIND_BY_LOCALITY_NAME_AND_STATE_AND_POSTCODE_QUERY)
    List<Locality> findByLocalityNameAndStateAndPostcode(@Param("localityName") String localityName, @Param("state") String state, @Param("postcode") String postcode);

    @Query(FIND_ALL_ORDERED_LOCALITIES_QUERY)
    List<Locality> findAllOrderedLocalities();

}
