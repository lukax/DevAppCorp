package br.uff.ic.devappcorp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    // inject via application.properties
    @Value("${app.name}")
    private String message = "App";

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
