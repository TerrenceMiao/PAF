package org.paradise.microservice

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.paradise.microservice.api.ApiDpidSpec
import org.paradise.microservice.api.ApiStreetTypeSpec
import org.paradise.microservice.api.ApiSuburbSpec
import spock.lang.Specification

/**
 * Created by terrence on 26/12/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses([ApiDpidSpec.class, ApiStreetTypeSpec.class, ApiSuburbSpec])
class AllSpecSuite extends Specification {
}
