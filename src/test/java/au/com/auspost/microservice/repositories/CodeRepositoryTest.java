package au.com.auspost.microservice.repositories;

import au.com.auspost.microservice.autoconfigure.RepositoryConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by miaot on 13/10/2015.
 *
 * Must come with @WebAppConfiguration annotation otherwise test will fail cause by racing condition. Exception like:
 *
 * Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type
 * [org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping] found for dependency
 * [collection of org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping]: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class CodeRepositoryTest {

    private CodeRepository codeRepository;

    @Autowired
    public void setCodeRepository(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReadCode() {

    }

}