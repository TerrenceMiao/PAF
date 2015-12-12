package org.paradise.microservice.repositories;

import org.paradise.microservice.domain.Code;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by miaot on 13/10/2015.
 */
public interface CodeRepository extends CrudRepository<Code, Integer> {

    String FIND_BY_TYPE_ITEM_QUERY = "SELECT c FROM Code c WHERE c.typeItem = :typeItem";

    @Query(FIND_BY_TYPE_ITEM_QUERY)
    List<Code> findByTypeItem(@Param("typeItem") String typeItem);

}
