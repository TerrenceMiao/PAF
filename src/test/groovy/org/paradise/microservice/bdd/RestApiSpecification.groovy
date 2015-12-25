package org.paradise.microservice.bdd

import groovyx.net.http.RESTClient
import org.paradise.microservice.Constants
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by terrence on 25/12/2015.
 */
class RestApiSpecification extends Specification {

    RESTClient restClient = new RESTClient("http://localhost:8080")

    @Unroll("Check matches #expectedPostcode, #expectedState, #expectedState")
    def 'Check ALL suburbs have been returned'() {

        given:
        int totalNumberOfSuburbs = 15790

        when:
        def response = restClient.get(path: Constants.SUBURBS_REQUEST_PATH)

        then:
        response.status == 200

        and:
        response.responseData.suburbs.size == totalNumberOfSuburbs

        response.responseData.suburbs[0].postcode == "2850"
        response.responseData.suburbs[0].state == "NSW"
        response.responseData.suburbs[0].suburb == "AARONS PASS"

        response.responseData.suburbs[totalNumberOfSuburbs - 1].postcode == "6536"
        response.responseData.suburbs[totalNumberOfSuburbs - 1].state == "WA"
        response.responseData.suburbs[totalNumberOfSuburbs - 1].suburb == "ZUYTDORP"

//        and:
//        response.responseData.suburbs.postcode == expectedPostcode
//        response.responseData.suburbs.state == expectedState
//        response.responseData.suburbs.suburb == expectedSuburb

//        where:
//        expectedPostcode | expectedState | expectedSuburb
//        2851             | "NSW"         | "AARONS PASS"
//        ...
//        6536             | "WA"          | "ZUYTDORP"
    }

    def 'Check ALL street types have been returned'() {

        given:
        int totalNumberOfStreetTypes = 147

        when:
        def response = restClient.get(path: Constants.STREETS_REQUEST_PATH)

        then:
        response.status == 200

        and:
        response.responseData.streetTypes.size == totalNumberOfStreetTypes

        response.responseData.streetTypes[0].streetType == "Accs"

        response.responseData.streetTypes[totalNumberOfStreetTypes - 1].streetType == "Wynd"
    }

    def 'Check correct DPID has been returned by a postal address'() {

        given:
        String dpid = "51123887"

        when:
        def response = restClient.post(path: Constants.ADDRESS_REQUEST_PATH,
                requestContentType: 'application/json',
                body: ["houseNumber": "00018",
                       "streetName": "Sandlewood",
                       "streetType": "Lane",
                       "suburb": "POINT COOK",
                       "state": "VIC",
                       "postcode": "3030"])

        then:
        response.status == 200

        and:
        response.responseData.dpid == dpid
    }

}
