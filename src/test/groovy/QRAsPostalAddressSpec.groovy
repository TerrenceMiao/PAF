import geb.spock.GebReportingSpec
import org.paradise.microservice.App
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [App])
@WebIntegrationTest
class QRAsPostalAddressSpec extends GebReportingSpec {

    def "can get to QR As Postal Address home page"() {

        when:
        to QRAsPostalAddressHomePage

        and:
        interact {
        }

        then:
        at QRAsPostalAddressHomePage
    }
}