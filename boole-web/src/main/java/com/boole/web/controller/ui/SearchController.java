package com.boole.web.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/8/15.
 */
@Controller
public class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String sayHello() {
        return "search";
    }
}
