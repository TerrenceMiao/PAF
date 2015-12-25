package org.paradise.microservice.bdd.api

import groovyx.net.http.RESTClient
import org.paradise.microservice.App
import org.paradise.microservice.Constants
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Created by terrence on 25/12/2015.
 */
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [App])
@WebIntegrationTest
class ApiStreetTypeSpec extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080")

    def 'Check ALL street types have been returned'() {

        given: "An API client"
        int totalNumberOfStreetTypes = 147

        when: "Invoke to retrieve ALL unique street types of Postal Address"
        def response = restClient.get(path: Constants.STREETS_REQUEST_PATH)

        then: "HTTP status OK (200)"
        response.status == 200

        and: "Correct number of ALL street types returned"
        response.responseData.streetTypes.size == totalNumberOfStreetTypes

        and: "Correct street type of 1st record"
        response.responseData.streetTypes[0].streetType == "Accs"

        and: "Correct street type of last record"
        response.responseData.streetTypes[totalNumberOfStreetTypes - 1].streetType == "Wynd"
    }

}
