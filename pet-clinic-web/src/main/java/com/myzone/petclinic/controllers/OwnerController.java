package com.myzone.petclinic.controllers;

import com.myzone.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = {"", "/index"})
    public String index(Model model){

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping(value = "/find")
    public String find(){
        return "notimplemented";
    }

}
