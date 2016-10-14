package org.paradise.microservice.api

import org.paradise.microservice.Constants

/**
 * Created by terrence on 25/12/2015.
 */
class ApiStreetTypeSpec extends ApiAbstractSpec {

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
        response.responseData.streetTypes[0].streetType == "ACCS"

        and: "Correct street type of last record"
        response.responseData.streetTypes[totalNumberOfStreetTypes - 1].streetType == "WYND"
    }

}
