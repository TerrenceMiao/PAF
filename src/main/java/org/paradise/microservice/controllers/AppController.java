package org.paradise.microservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.TypeToken;
import org.paradise.microservice.Constants;
import org.paradise.microservice.JavaScriptEngine;
import org.paradise.microservice.domain.Locality;
import org.paradise.microservice.dto.PostalAddress;
import org.paradise.microservice.dto.PropertiesFilterMixIn;
import org.paradise.microservice.model.Suburb;
import org.paradise.microservice.services.PostalAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by terrence on 7/11/15.
 */
@Controller
public class AppController {

    private static CopyOnWriteArrayList<PostalAddress> postalAddressList;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JavaScriptEngine javaScriptEngine;

    @Autowired
    private PostalAddressService postalAddressService;

    @PostConstruct
    void init() {

        initiatePostalAddressList();
    }

    @RequestMapping("/")
    String hello(Model model) throws JsonProcessingException {

        List<String> streetTypeList = postalAddressService.getAllStreetType();

        List<Locality> localityList = postalAddressService.getAllOrderedLocalities();
        // Define the target type
        Type targetListType = new TypeToken<List<Suburb>>() { }.getType();
        List<Suburb> suburbList = Constants.MODEL_MAPPER.map(localityList, targetListType);

        String markup = javaScriptEngine.invokeFunction("renderOnServer", String::valueOf, reversePostalAddressList(), streetTypeList, suburbList);
        String initialData = objectMapper.writeValueAsString(reversePostalAddressList());

        model.addAttribute("markup", markup);
        model.addAttribute("initialData", initialData);
        model.addAttribute("streetTypeList", streetTypeList);
        model.addAttribute("suburbList", suburbList);

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/postaladdress", method = RequestMethod.GET)
    List<PostalAddress> getPostalAddressList(@RequestParam(value = "filter", defaultValue = "on") String filter) {

        if ("off".equalsIgnoreCase(filter)) {
            // turn off the properties filter
        } else {
            objectMapper.addMixIn(PostalAddress.class, PropertiesFilterMixIn.class);
        }

        return reversePostalAddressList();
    }

    @ResponseBody
    @RequestMapping(value = "/postaladdress", method = RequestMethod.POST)
    List<PostalAddress> addPostalAddressToList(@RequestBody PostalAddress postalAddress) {

        String dpid = postalAddressService.getDpidFromPostalAddress(postalAddress);

        if (!dpid.isEmpty()) {
            postalAddress.setDpid(dpid);
            postalAddressList.add(postalAddress);
        }

        return reversePostalAddressList();
    }

    @ResponseBody
    @RequestMapping(value = "/reset", method = RequestMethod.PUT)
    void resetPostalAddressList() {

        initiatePostalAddressList();
    }

    private static List<PostalAddress> reversePostalAddressList() {

        List<PostalAddress> copyPostAddressList = new ArrayList<>(postalAddressList);

        Collections.reverse(copyPostAddressList);

        return copyPostAddressList;
    }

    private void initiatePostalAddressList() {

        postalAddressList = new CopyOnWriteArrayList<>();

        postalAddressList.addAll(Arrays.asList(
                        new PostalAddress("32815985", "Pyotr Smirnov", "111", "Bourke", "St", "Melbourne", "VIC", "3000"),
                        new PostalAddress("31515566", "Johnny Walker", "80", "Collins", "St", "Melbourne", "VIC", "3000"))
        );
    }

}
