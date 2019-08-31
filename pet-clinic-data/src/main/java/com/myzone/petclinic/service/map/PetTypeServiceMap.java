package com.myzone.petclinic.service.map;

import com.myzone.petclinic.model.PetType;
import com.myzone.petclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<PetType> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
