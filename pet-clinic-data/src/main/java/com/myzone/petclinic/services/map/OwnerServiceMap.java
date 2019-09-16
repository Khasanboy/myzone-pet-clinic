package com.myzone.petclinic.services.map;

import com.myzone.petclinic.models.Owner;
import com.myzone.petclinic.models.Pet;
import com.myzone.petclinic.services.OwnerService;
import com.myzone.petclinic.services.PetService;
import com.myzone.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Optional<Owner> findByLastName(String lastName) {

        return map.values().stream()
                .filter(owner -> owner.getLastName() != null)
                .filter(owner -> owner.getLastName().equals(lastName))
                .findFirst();
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

        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getType() != null) {
                        if (pet.getType().getId() == null) {
                            pet.setType(petTypeService.save(pet.getType()));
                        }
                    } else {
                        throw new RuntimeException("Pet type is required");
                    }
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
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
