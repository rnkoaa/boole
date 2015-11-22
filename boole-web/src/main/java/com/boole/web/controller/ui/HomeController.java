package com.boole.web.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 10/22/2015.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "index";
    }

    @RequestMapping(value = "/discover", method = RequestMethod.GET)
    public String discoverMovies() {
        return "index";
    }

  /*  @RequestMapping(value = "/stores*//**")
     public String redirect() {
     return "forward:/";
     }*/
}
