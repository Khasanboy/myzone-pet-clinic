package com.myzone.petclinic.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.myzone.petclinic.models.Speciality;
import com.myzone.petclinic.respositories.SpecialityRepository;
import com.myzone.petclinic.services.SpecialityService;

@Service
@Profile("springdatajpa")
public class SpecialityServiceImpl implements SpecialityService {
    
    private final SpecialityRepository specialityRepository;
    
    public SpecialityServiceImpl(final SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }
    
    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }
    
    @Override
    public Optional<Speciality> findById(Long id) {
        return specialityRepository.findById(id);
    }
    
    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }
    
    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }
    
    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
