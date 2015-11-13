package com.boole.web.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 10/27/2015.
 */
@Controller
public class ContactController {

    @RequestMapping("/contacts")
    public String getContactsHome() {
        return "contacts";
    }

    /*@RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/boole";
    }*/
}
