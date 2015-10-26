package au.com.auspost.microservice.services;

import au.com.auspost.microservice.configuration.RepositoryConfiguration;
import au.com.auspost.microservice.domain.Code;
import au.com.auspost.microservice.repositories.CodeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by terrence on 26/10/15.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class CodeServiceTest {

    private CodeService codeService = new CodeService.Impl();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetCodeListByTypeItem() throws Exception {

        List<Code> codeList = codeService.getCodeListByTypeItem("FLOOR");

        assertEquals("Wrong list size", 1, codeList.size());

        assertEquals("Wrong Code Type Id", "FLT", codeList.get(0).getTypeId());
        assertEquals("Wrong Code Type Item", "FLOOR", codeList.get(0).getTypeItem());
        assertEquals("Wrong Code Type Item Abbr", "FL", codeList.get(0).getTypeItemAbbr());
        assertEquals("Wrong Code Type Action Code", "V", codeList.get(0).getTypeActnCode());
    }

}