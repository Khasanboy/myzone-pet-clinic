package com.myzone.petclinic.service;

import java.util.Optional;
import java.util.Set;

import com.myzone.petclinic.model.Vet;

public interface VetService {
    
    Optional<Vet> findById(Long id);
    
    Vet save(Vet vet);
    
    Set<Vet> findAll();
}
