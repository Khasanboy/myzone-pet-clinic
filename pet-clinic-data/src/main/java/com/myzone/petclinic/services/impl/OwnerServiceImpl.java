package com.myzone.petclinic.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.myzone.petclinic.models.Owner;
import com.myzone.petclinic.respositories.OwnerRepository;
import com.myzone.petclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerServiceImpl implements OwnerService {
    
    private final OwnerRepository ownerRepository;
    
    public OwnerServiceImpl(final OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    
    @Override
    public Optional<Owner> findByLastName(final String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
    
    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }
    
    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }
    
    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }
    
    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }
    
    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
