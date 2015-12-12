package org.paradise.microservice.services;

import org.paradise.microservice.domain.Code;
import org.paradise.microservice.repositories.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by terrence on 26/10/15.
 */
public interface CodeService {

    List<Code> getCodeListByTypeItem(String typeItem);

    void setCodeRepository(CodeRepository codeRepository);


    @Service
    class Impl implements CodeService {

        @Autowired
        private CodeRepository codeRepository;

        @Override
        public List<Code> getCodeListByTypeItem(String typeItem) {

            return codeRepository.findByTypeItem(typeItem);
        }

        public void setCodeRepository(CodeRepository codeRepository) {

            this.codeRepository = codeRepository;
        }
    }

}
