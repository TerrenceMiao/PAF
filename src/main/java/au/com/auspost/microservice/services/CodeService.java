package au.com.auspost.microservice.services;

import au.com.auspost.microservice.domain.Code;
import au.com.auspost.microservice.repositories.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by terrence on 26/10/15.
 */
public interface CodeService {

    List<Code> getCodeListByTypeItem(String typeItem);

    @Service
    class Impl implements CodeService {

        @Autowired
        private CodeRepository codeRepository;

        @Override
        public List<Code> getCodeListByTypeItem(String typeItem) {

            return codeRepository.findByTypeItem(typeItem);
        }
    }

}
