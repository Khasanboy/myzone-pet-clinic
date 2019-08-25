package com.myzone.petclinic.service.map;

import com.myzone.petclinic.model.Owner;
import com.myzone.petclinic.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Optional<Owner> findByLastName(String lastName) {
        return Optional.empty();
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
