package org.paradise.microservice.bdd

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.paradise.microservice.bdd.api.ApiDpidSpec
import org.paradise.microservice.bdd.api.ApiStreetTypeSpec
import org.paradise.microservice.bdd.api.ApiSuburbSpec
import spock.lang.Specification

/**
 * Created by terrence on 26/12/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses([ApiDpidSpec.class, ApiStreetTypeSpec.class, ApiSuburbSpec])
class AllSpecSuite extends Specification {
}
