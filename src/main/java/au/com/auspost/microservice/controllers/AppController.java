package au.com.auspost.microservice.controllers;

import au.com.auspost.microservice.JavaScriptEngine;
import au.com.auspost.microservice.dto.PostalAddress;
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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by terrence on 7/11/15.
 */
@Controller
public class AppController {

    private static final List<PostalAddress> POSTAL_ADDRESS_LIST = new CopyOnWriteArrayList<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JavaScriptEngine javaScriptEngine;

    @PostConstruct
    void init() {

        POSTAL_ADDRESS_LIST.addAll(Arrays.asList(
                new PostalAddress("Pyotr Smirnov", "111", "Bourke", "St", "Melbourne", "VIC", "3000"),
                new PostalAddress("Johnny Walker", "80", "Collins", "St", "Melbourne", "VIC", "3000")));
    }

    @RequestMapping("/")
    String hello(Model model) throws JsonProcessingException {

        String markup = javaScriptEngine.invokeFunction("renderOnServer", String::valueOf, POSTAL_ADDRESS_LIST);
        String initialData = objectMapper.writeValueAsString(POSTAL_ADDRESS_LIST);

        model.addAttribute("markup", markup);
        model.addAttribute("initialData", initialData);

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/postaladdress", method = RequestMethod.GET)
    List<PostalAddress> getPostalAddressList() {

        return POSTAL_ADDRESS_LIST;
    }

    @ResponseBody
    @RequestMapping(value = "/postaladdress", method = RequestMethod.POST)
    List<PostalAddress> addPostalAddressToList(@RequestBody PostalAddress postalAddress) {

        POSTAL_ADDRESS_LIST.add(postalAddress);

        return POSTAL_ADDRESS_LIST;
    }

}
