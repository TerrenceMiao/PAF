package org.paradise.microservice.api

import org.paradise.microservice.Constants
import spock.lang.Unroll

/**
 * Created by terrence on 25/12/2015.
 */
class ApiSuburbSpec extends ApiAbstractSpec {

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

}
