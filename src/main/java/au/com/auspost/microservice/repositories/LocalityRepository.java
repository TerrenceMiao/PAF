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

    @Query(FIND_BY_LOCALITY_NAME_QUERY)
    List<Locality> findByLocalityName(@Param("localityName") String localityName);

}
