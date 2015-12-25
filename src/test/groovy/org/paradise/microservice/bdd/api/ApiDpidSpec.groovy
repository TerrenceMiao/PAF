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
class ApiDpidSpec extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080")

    def 'Check correct DPID has been returned by postal address - 18 Sandlewood Lane, Point Cook VIC 3030'() {

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

    def 'Check correct DPID has been returned by postal address - 18 Middle Park Dr, Point Cook VIC 3030'() {

        given: "An API client"
        String dpid = "48926331"

        when: "Invoke to retrieve DPID of a Postal Address"
        def response = restClient.post(path: Constants.ADDRESS_REQUEST_PATH,
                requestContentType: 'application/json',
                body: ["houseNumber": "00018",
                       "streetName": "Middle Park",
                       "streetType": "Dr",
                       "suburb": "POINT COOK",
                       "state": "VIC",
                       "postcode": "3030"])

        then: "HTTP status OK (200)"
        response.status == 200

        and: "Correct DPID of the postal address queried"
        response.responseData.dpid == dpid
    }

    def 'Check correct DPID has been returned by postal address - 12 Monomeath Ave, Toorak VIC 3142'() {

        given: "An API client"
        String dpid = "35227901"

        when: "Invoke to retrieve DPID of a Postal Address"
        def response = restClient.post(path: Constants.ADDRESS_REQUEST_PATH,
                requestContentType: 'application/json',
                body: ["houseNumber": "00012",
                       "streetName": "Monomeath",
                       "streetType": "Ave",
                       "suburb": "TOORAK",
                       "state": "VIC",
                       "postcode": "3142"])

        then: "HTTP status OK (200)"
        response.status == 200

        and: "Correct DPID of the postal address queried"
        response.responseData.dpid == dpid
    }

    def 'Check correct DPID has been returned by postal address - 111 Bourke St, Melbourne VIC 3000'() {

        given: "An API client"
        String dpid = "32815985"

        when: "Invoke to retrieve DPID of a Postal Address"
        def response = restClient.post(path: Constants.ADDRESS_REQUEST_PATH,
                requestContentType: 'application/json',
                body: ["houseNumber": "00111",
                       "streetName": "Bourke",
                       "streetType": "St",
                       "suburb": "MELBOURNE",
                       "state": "VIC",
                       "postcode": "3000"])

        then: "HTTP status OK (200)"
        response.status == 200

        and: "Correct DPID of the postal address queried"
        response.responseData.dpid == dpid
    }

    def 'Check correct DPID has been returned by postal address - 80 Collins St, Melbourne VIC 3000'() {

        given: "An API client"
        String dpid = "31515566"

        when: "Invoke to retrieve DPID of a Postal Address"
        def response = restClient.post(path: Constants.ADDRESS_REQUEST_PATH,
                requestContentType: 'application/json',
                body: ["houseNumber": "00080",
                       "streetName": "Collins",
                       "streetType": "St",
                       "suburb": "MELBOURNE",
                       "state": "VIC",
                       "postcode": "3000"])

        then: "HTTP status OK (200)"
        response.status == 200

        and: "Correct DPID of the postal address queried"
        response.responseData.dpid == dpid
    }

}
