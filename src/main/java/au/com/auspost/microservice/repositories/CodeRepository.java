package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.domain.Code;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by miaot on 13/10/2015.
 */
public interface CodeRepository extends CrudRepository<Code, Integer> {

    List<Code> findByTypeItem(String typeItem);

}
