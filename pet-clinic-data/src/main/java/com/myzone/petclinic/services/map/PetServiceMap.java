package com.myzone.petclinic.services.map;

import com.myzone.petclinic.models.Pet;
import com.myzone.petclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return super.findById(id);
    }
}
