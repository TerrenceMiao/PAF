package org.paradise.microservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.paradise.microservice.controllers.PostalAddressControllerTest;
import org.paradise.microservice.repositories.CodeRepositoryTest;
import org.paradise.microservice.repositories.DeliveryPointGroupRepositoryTest;
import org.paradise.microservice.repositories.DeliveryPointRepositoryTest;
import org.paradise.microservice.repositories.LocalityRepositoryTest;
import org.paradise.microservice.services.CodeServiceTest;
import org.paradise.microservice.services.PostalAddressServiceTest;
import org.paradise.microservice.utils.StringFormatterTest;

/**
 * Created by terrence on 23/12/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostalAddressControllerTest.class,
        CodeRepositoryTest.class,
        DeliveryPointGroupRepositoryTest.class,
        DeliveryPointRepositoryTest.class,
        LocalityRepositoryTest.class,
        CodeServiceTest.class,
        PostalAddressServiceTest.class,
        StringFormatterTest.class
})
public class AllTestSuite {

}
