package org.paradise.microservice.bdd

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
class ApiDpidSpec extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080")

    def 'Check correct DPID has been returned by a postal address'() {

        given: "An API client"
        String dpid = "51123887"

        when: "Invoke to retrieve DPID of a Postal Address"
        def response = restClient.post(path: Constants.ADDRESS_REQUEST_PATH,
                requestContentType: 'application/json',
                body: ["houseNumber": "00018",
                       "streetName": "Sandlewood",
                       "streetType": "Lane",
                       "suburb": "POINT COOK",
                       "state": "VIC",
                       "postcode": "3030"])

        then: "HTTP status OK (200)"
        response.status == 200

        and: "Correct DPID of the postal address queried"
        response.responseData.dpid == dpid
    }

}
