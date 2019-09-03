package com.myzone.petclinic.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.myzone.petclinic.models.Vet;
import com.myzone.petclinic.respositories.VetRepository;
import com.myzone.petclinic.services.VetService;

@Service
@Profile("springdatajpa")
public class VetServiceImpl implements VetService {
    
    private final VetRepository vetRepository;
    
    public VetServiceImpl(final VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }
    
    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }
    
    @Override
    public Optional<Vet> findById(Long id) {
        return vetRepository.findById(id);
    }
    
    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }
    
    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }
    
    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
