package com.myzone.petclinic.service;

import com.myzone.petclinic.model.Owner;

import java.util.Optional;

public interface OwnerService extends CrudService<Owner, Long> {
    
    Optional<Owner> findByLastName(String lastName);
}
