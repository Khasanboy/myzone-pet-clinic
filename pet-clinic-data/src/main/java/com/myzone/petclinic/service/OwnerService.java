package com.myzone.petclinic.service;

import java.util.Optional;
import java.util.Set;

import com.myzone.petclinic.model.Owner;

public interface OwnerService {
    
    Optional <Owner> findById(Long id);
    
    Owner save(Owner owner);
    
    Set<Owner> findAll();
    
    Optional<Owner> findByLastName(String lastName);
}
