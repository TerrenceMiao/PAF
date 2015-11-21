package au.com.auspost.microservice.controllers;

import au.com.auspost.microservice.JavaScriptEngine;
import au.com.auspost.microservice.dto.PostalAddress;
import au.com.auspost.microservice.services.PostalAddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
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

        String markup = javaScriptEngine.invokeFunction("renderOnServer", String::valueOf, reversePostalAddressList());
        String initialData = objectMapper.writeValueAsString(reversePostalAddressList());

        model.addAttribute("markup", markup);
        model.addAttribute("initialData", initialData);

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/postaladdress", method = RequestMethod.GET)
    List<PostalAddress> getPostalAddressList() {

        return reversePostalAddressList();
    }

    @ResponseBody
    @RequestMapping(value = "/postaladdress", method = RequestMethod.POST)
    List<PostalAddress> addPostalAddressToList(@RequestBody PostalAddress postalAddress) {

        postalAddress.setDpid(postalAddressService.getDpidFromPostalAddress(postalAddress));

        postalAddressList.add(postalAddress);

        return reversePostalAddressList();
    }

    @ResponseBody
    @RequestMapping(value = "/reset", method = RequestMethod.PUT)
    void resetPostalAddressList() {

        initiatePostalAddressList();
    }

    private static List<PostalAddress> reversePostalAddressList() {

        CopyOnWriteArrayList<PostalAddress> copyPostAddressList = (CopyOnWriteArrayList<PostalAddress>) postalAddressList.clone();

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
