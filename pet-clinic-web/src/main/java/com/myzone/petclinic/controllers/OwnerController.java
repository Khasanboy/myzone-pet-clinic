package com.myzone.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {

    @RequestMapping(value = {"/owners", "/owners/index"})
    public String index(){
        return "owners/index";
    }

}