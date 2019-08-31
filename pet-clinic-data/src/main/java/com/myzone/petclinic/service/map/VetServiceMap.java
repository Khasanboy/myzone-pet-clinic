package com.myzone.petclinic.service.map;

import com.myzone.petclinic.model.Speciality;
import com.myzone.petclinic.model.Vet;
import com.myzone.petclinic.service.SpecialityService;
import com.myzone.petclinic.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            if (object.getSpecialities() != null)
                object.getSpecialities().forEach(speciality -> {
                    if (speciality.getId() == null) {
                        Speciality saved = specialityService.save(speciality);
                        speciality.setId(saved.getId());
                    }
                });
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Vet> findById(Long id) {
        return super.findById(id);
    }
}
