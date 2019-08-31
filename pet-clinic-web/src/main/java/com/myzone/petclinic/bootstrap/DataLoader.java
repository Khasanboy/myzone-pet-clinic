package com.myzone.petclinic.bootstrap;

import com.myzone.petclinic.model.*;
import com.myzone.petclinic.service.OwnerService;
import com.myzone.petclinic.service.PetTypeService;
import com.myzone.petclinic.service.SpecialityService;
import com.myzone.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0)
            loadData();
    }

    private void loadData() {
        PetType type = new PetType();
        type.setName("Dog");
        PetType savedPetType = petTypeService.save(type);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Majaka 17/24");
        owner1.setCity("Tallinn");
        owner1.setTelephone("398098230909");

        Pet pet = new Pet();
        pet.setName("Mikes Pet");
        pet.setPetType(type);
        pet.setBirthDate(LocalDate.now());
        pet.setOwner(owner1);
        owner1.getPets().add(pet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("GLenani");
        owner2.setAddress("Lala 28/38");
        owner2.setCity("Tartu");
        owner2.setTelephone("98787893636");

        Pet pet1 = new Pet();
        pet1.setName("Fionas Cat");
        pet1.setPetType(cat);
        pet1.setBirthDate(LocalDate.now());
        pet1.setOwner(owner2);
        owner2.getPets().add(pet1);

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Vetter");
        vet1.setLastName("VetterSet");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("SetVet");
        vet2.setLastName("SetCAD");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);
    }
}
