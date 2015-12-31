package org.paradise.functional

import geb.spock.GebReportingSpec
import org.paradise.functional.pages.QRAsPostalAddressHomePage
import org.paradise.microservice.App
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [App])
@WebIntegrationTest
class QRAsPostalAddressSpec extends GebReportingSpec {

    def "can get to QR As Postal Address home page"() {

        given:
        QRAsPostalAddressHomePage qrAsPostalAddressHomePage = to QRAsPostalAddressHomePage

        when:
        qrAsPostalAddressHomePage.generateQRCode("Terrence", "6", "Scott", "St", "KEW VIC 3101");

        then:
        at QRAsPostalAddressHomePage

        assert $("div", class: "postalAddressList").text().contains("Terrence")
        assert $("div", class: "postalAddressList").text().contains("6 Scott St, KEW VIC 3101")
    }

}