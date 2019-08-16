package com.myzone.petclinic.service;

import java.util.Optional;
import java.util.Set;

import com.myzone.petclinic.model.Pet;

public interface PetService {

    Optional<Pet> findById(Long id);
    
    Pet save(Pet pet);
    
    Set<Pet> findAll();

}
