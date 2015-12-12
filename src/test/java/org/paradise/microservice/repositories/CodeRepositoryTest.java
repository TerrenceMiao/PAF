package org.paradise.microservice.repositories;

import org.paradise.microservice.configuration.RepositoryConfiguration;
import org.paradise.microservice.domain.Code;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by miaot on 13/10/2015.
 *
 * Must come with @WebAppConfiguration annotation otherwise test will fail cause by racing condition. Exception like:
 *
 * Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type
 * [org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping] found for dependency
 * [collection of org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping]: expected at least 1 bean
 * which qualifies as autowire candidate for this dependency. Dependency annotations: {}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class CodeRepositoryTest {

    @Autowired
    private CodeRepository codeRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindByTypeItem() {

        List<Code> codeList = codeRepository.findByTypeItem("FLOOR");

        assertEquals("Wrong list size", 1, codeList.size());

        assertEquals("Wrong Code Type Id", "FLT", codeList.get(0).getTypeId());
        assertEquals("Wrong Code Type Item", "FLOOR", codeList.get(0).getTypeItem());
        assertEquals("Wrong Code Type Item Abbr", "FL", codeList.get(0).getTypeItemAbbr());
        assertEquals("Wrong Code Type Action Code", "V", codeList.get(0).getTypeActnCode());
    }

    @Test
    public void testSaveReadCode() {

        // get original data rows count
        long originalCodeCount = codeRepository.count();

        // setup code
        Code code = new Code();
        code.setRecordActnCode("I");
        code.setTypeId("FLT");
        code.setTypeItem("BASEMENT");
        code.setTypeItemAbbr("B");
        code.setTypeActnCode("V");

        // save code, verify has ID value after save
        // null before save
        assertNull(code.getId());
        codeRepository.save(code);
        // not null after save
        assertNotNull(code.getId());

        // fetch from DB
        Code fetchedCode = codeRepository.findOne(code.getId());

        // should not be null
        assertNotNull(fetchedCode);

        // should equal
        assertEquals(code.getId(), fetchedCode.getId());
        assertEquals(code.getRecordActnCode(), fetchedCode.getRecordActnCode());
        assertEquals(code.getTypeId(), fetchedCode.getTypeId());
        assertEquals(code.getTypeItem(), fetchedCode.getTypeItem());
        assertEquals(code.getTypeItemAbbr(), fetchedCode.getTypeItemAbbr());
        assertEquals(code.getTypeActnCode(), fetchedCode.getTypeActnCode());

        // update description and save
        fetchedCode.setTypeItem("B");
        codeRepository.save(fetchedCode);

        // get from DB, should be updated
        Code fetchedUpdatedCode = codeRepository.findOne(fetchedCode.getId());
        assertEquals(fetchedCode.getTypeItem(), fetchedUpdatedCode.getTypeItem());

        // verify count of products in DB
        long codeCount = codeRepository.count();
        assertEquals(codeCount, originalCodeCount + 1);

        // get all codes, list should only have one
        Iterable<Code> codes = codeRepository.findAll();

        int count = 0;

        for (Code c : codes) {
            count++;
        }

        assertEquals(count, originalCodeCount + 1);
    }

}