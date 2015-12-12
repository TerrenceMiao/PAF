package org.paradise.microservice.controllers;

import org.paradise.microservice.Constants;
import org.paradise.microservice.domain.Locality;
import org.paradise.microservice.dto.PostalAddress;
import org.paradise.microservice.model.Address;
import org.paradise.microservice.model.Dpid;
import org.paradise.microservice.model.StreetType;
import org.paradise.microservice.model.StreetTypes;
import org.paradise.microservice.model.Suburb;
import org.paradise.microservice.model.Suburbs;
import org.paradise.microservice.services.PostalAddressService;
import org.modelmapper.TypeToken;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by terrence on 31/10/15.
 */
@RestController
public class PostalAddressController {

    private static final Logger LOG = LoggerFactory.getLogger(PostalAddressController.class);

    @Autowired
    private PostalAddressService postalAddressService;

    @RequestMapping(value = Constants.ADDRESS_REQUEST_PATH, method = {RequestMethod.POST}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Dpid> getDeliveryPointId(@RequestBody @Valid Address address, BindingResult bindingResult) {

        LOG.info("To get a Delivery Point Id ...");

        Dpid dpid = new Dpid();

        dpid.setDpid(postalAddressService.getDpidFromPostalAddress(Constants.MODEL_MAPPER.map(address, PostalAddress.class)));

        return new ResponseEntity<>(dpid, HttpStatus.OK);
    }

    @RequestMapping(value = Constants.SUBURBS_REQUEST_PATH, method = {RequestMethod.GET}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Suburbs> getAllOrderedLocalities() {

        LOG.info("To get all ORDERED suburbs ...");

        List<Locality> localityList = postalAddressService.getAllOrderedLocalities();

        // Define the target type
        Type targetListType = new TypeToken<List<Suburb>>() { }.getType();

        List<Suburb> suburbList = Constants.MODEL_MAPPER.map(localityList, targetListType);

        Suburbs suburbs = new Suburbs();
        suburbs.setSuburbList(suburbList);

        return new ResponseEntity<>(suburbs, HttpStatus.OK);
    }

    @RequestMapping(value = Constants.STREETS_REQUEST_PATH, method = {RequestMethod.GET}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StreetTypes> getAllStreetType() {

        LOG.info("To get all street type ...");

        List<StreetType> streetTypeList = new ArrayList<>();

        for (String st : postalAddressService.getAllStreetType()) {
            StreetType streetType = new StreetType();
            streetType.setStreet(st);
            streetTypeList.add(streetType);
        }

        StreetTypes streetTypes = new StreetTypes();
        streetTypes.setStreetTypeList(streetTypeList);

        return new ResponseEntity<>(streetTypes, HttpStatus.OK);
    }

    public void setPostalAddressService(PostalAddressService postalAddressService) {
        this.postalAddressService = postalAddressService;
    }

}