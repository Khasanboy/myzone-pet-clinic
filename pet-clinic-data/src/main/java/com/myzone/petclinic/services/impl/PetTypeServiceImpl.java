package com.myzone.petclinic.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.myzone.petclinic.models.PetType;
import com.myzone.petclinic.respositories.PetTypeRepository;
import com.myzone.petclinic.services.PetTypeService;

@Service
@Profile("springdatajpa")
public class PetTypeServiceImpl implements PetTypeService {
    
    private final PetTypeRepository petTypeRepository;
    
    public PetTypeServiceImpl(final PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }
    
    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }
    
    @Override
    public Optional<PetType> findById(Long id) {
        return petTypeRepository.findById(id);
    }
    
    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }
    
    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }
    
    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
