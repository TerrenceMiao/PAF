package au.com.auspost.microservice.controllers;

import au.com.auspost.microservice.Comment;
import au.com.auspost.microservice.JavaScriptEngine;
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

    static final List<Comment> comments = new CopyOnWriteArrayList<>();

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JavaScriptEngine javaScriptEngine;

    @PostConstruct
    void init() {

        comments.addAll(Arrays.asList(
                new Comment("Pete Hunt", "This is one comment"),
                new Comment("Jordan Walke", "This is *another* comment")));
    }

    @RequestMapping("/")
    String hello(Model model) throws JsonProcessingException {

        String markup = javaScriptEngine.invokeFunction("renderOnServer", String::valueOf, comments);
        String initialData = objectMapper.writeValueAsString(comments);
        model.addAttribute("markup", markup);
        model.addAttribute("initialData", initialData);

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    List<Comment> getComments() {

        return comments;
    }

    @ResponseBody
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    List<Comment> postComments(@RequestBody Comment comment) {

        comments.add(comment);

        return comments;
    }

}
