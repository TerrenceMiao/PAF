package org.paradise.microservice.bdd

import groovyx.net.http.RESTClient
import org.paradise.microservice.App
import org.paradise.microservice.Constants
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by terrence on 25/12/2015.
 */
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [App])
@WebIntegrationTest
class RestApiSpecification extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080")

    @Unroll("Check matches #expectedPostcode, #expectedState, #expectedState")
    def 'Check ALL suburbs have been returned'() {

        given: "An API client"
        int totalNumberOfSuburbs = 15790

        when: "Invoke to retrieve ALL unique suburbs of Postal Address"
        def response = restClient.get(path: Constants.SUBURBS_REQUEST_PATH)

        then: "HTTP status OK (200)"
        response.status == 200

        and: "Correct number of ALL suburbs returned"
        response.responseData.suburbs.size == totalNumberOfSuburbs

        and: "Correct Postcode, State and Suburb for 1st record"
        response.responseData.suburbs[0].postcode == "2850"
        response.responseData.suburbs[0].state == "NSW"
        response.responseData.suburbs[0].suburb == "AARONS PASS"

        and: "Correct Postcode, State and Suburb for last record"
        response.responseData.suburbs[totalNumberOfSuburbs - 1].postcode == "6536"
        response.responseData.suburbs[totalNumberOfSuburbs - 1].state == "WA"
        response.responseData.suburbs[totalNumberOfSuburbs - 1].suburb == "ZUYTDORP"

//        and: "Look for Postcode, State and Suburb list"
//        response.responseData.suburbs.postcode == expectedPostcode
//        response.responseData.suburbs.state == expectedState
//        response.responseData.suburbs.suburb == expectedSuburb

//        where: "Expected Postcode, State and Suburb list returned"
//        expectedPostcode | expectedState | expectedSuburb
//        2851             | "NSW"         | "AARONS PASS"
//        ...
//        6536             | "WA"          | "ZUYTDORP"
    }

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
