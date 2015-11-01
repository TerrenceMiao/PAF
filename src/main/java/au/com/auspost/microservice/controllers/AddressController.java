package au.com.auspost.microservice.controllers;

import au.com.auspost.microservice.Constants;
import au.com.auspost.microservice.dto.PostalAddress;
import au.com.auspost.microservice.model.Address;
import au.com.auspost.microservice.model.Dpid;
import au.com.auspost.microservice.services.PostalAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by terrence on 31/10/15.
 */
@RestController
@RequestMapping(value = Constants.ADDRESS_REQUEST_PATH)
public class AddressController {

    private static final Logger LOG = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private PostalAddressService postalAddressService;

    @RequestMapping(method = {RequestMethod.POST}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Dpid> getDeliveryPointId(@RequestBody @Valid Address address, BindingResult bindingResult) {

        LOG.info("To get a Delivery Point Id ...");

        Dpid dpid = new Dpid();

        dpid.setDpid(postalAddressService.getDpidFromPostalAddress(Constants.MODEL_MAPPER.map(address, PostalAddress.class)));

        return new ResponseEntity<>(dpid, HttpStatus.OK);
    }

    public void setPostalAddressService(PostalAddressService postalAddressService) {
        this.postalAddressService = postalAddressService;
    }

}