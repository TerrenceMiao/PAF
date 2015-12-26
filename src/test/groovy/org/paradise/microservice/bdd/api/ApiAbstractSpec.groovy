package org.paradise.microservice.bdd.api

import groovyx.net.http.RESTClient
import org.paradise.microservice.App
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by terrence on 27/12/2015.
 */
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [App])
@WebIntegrationTest
abstract class ApiAbstractSpec extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080")
}
