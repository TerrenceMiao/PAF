package au.com.auspost.microservice.repository;

import au.com.auspost.microservice.domain.Code;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by miaot on 13/10/2015.
 */
public interface CodeRepository extends CrudRepository<Code, String> {
}
