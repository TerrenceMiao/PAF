package org.paradise.functional.pages

import geb.Page

/**
 * Created by terrence on 31/12/2015.
 */
class QRAsPostalAddressHomePage extends Page {

    static at = { title.startsWith("QR as Postal Address") }

    static content = {

        addressee(wait: true) { $("#addressee") }
        houseNumber1(wait: true) { $("#houseNumber1") }
        streetName(wait: true) { $("#streetName") }
        streetType(wait: true) { $("#streetType") }
        localityNameStatePostcode(wait: true) { $("#localityNameStatePostcode") }

        // hidden fields
        localityName(wait: true) { $("#localityName") }
        state(wait: true) { $("#state") }
        postcode(wait: true) { $("#postcode") }

        generateQRCodeButton(to: QRAsPostalAddressHomePage) { $("#generateQRCode") }
    }

    QRAsPostalAddressHomePage generateQRCode(String addresseeValue, String houseNumber1Value, String streetNameValue,
                                             String streetTypeValue, String localityNameStatePostcodeValue) {

        addressee.value(addresseeValue)
        houseNumber1.value(houseNumber1Value)
        streetName.value(streetNameValue)

        streetType.value(streetTypeValue)
        localityNameStatePostcode.value(localityNameStatePostcodeValue)

        generateQRCodeButton.click()

        return browser.page
    }

}
