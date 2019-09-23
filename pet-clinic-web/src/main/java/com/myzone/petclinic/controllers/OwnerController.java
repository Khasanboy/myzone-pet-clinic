package com.myzone.petclinic.controllers;

import com.myzone.petclinic.models.Owner;
import com.myzone.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindResult, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (results.isEmpty()) {
            bindResult.rejectValue("lastName", "notFound", "notFound");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    /*
    @RequestMapping(value = {"", "/index"})
    public String index(Model model) {

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
     */

    @RequestMapping(value = "/find")
    public String find(Model model) {
        model.addAttribute("owner", Owner.builder().id(1L).build());
        return "owners/findOwners";
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

    @GetMapping(value = "/new")
    public String createOwnerForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(value = "/new")
    public String processCreateForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            var savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping(value = "/{ownerId}/edit")
    public String createEditOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        Optional<Owner> owner = ownerService.findById(ownerId);
        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("owner", Owner.builder().build());
        }
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(value = "/{ownerId}/edit")
    public String processEditOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") Long ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(ownerId);
            var savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}
