package com.myzone.petclinic.controllers;

import com.myzone.petclinic.models.Owner;
import com.myzone.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = {"", "/index"})
    public String index(Model model) {

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping(value = "/find")
    public String find() {
        return "notimplemented";
    }

    @GetMapping(value = "/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        Optional<Owner> owner = ownerService.findById(ownerId);
        if (owner.isPresent()) {
            modelAndView.addObject(owner.get());
        }

        return modelAndView;
    }

}
