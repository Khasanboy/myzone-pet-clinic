package com.myzone.petclinic.bootstrap;

import com.myzone.petclinic.models.*;
import com.myzone.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.petService = petService;
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
        Owner savedOwner1 = ownerService.save(owner1);

        Pet pet = new Pet();
        pet.setName("Mikes Pet");
        pet.setType(savedPetType);
        pet.setBirthDate(LocalDate.now());
        pet.setOwner(savedOwner1);
        pet = petService.save(pet);
        savedOwner1.getPets().add(pet);

        ownerService.save(savedOwner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("GLenani");
        owner2.setAddress("Lala 28/38");
        owner2.setCity("Tartu");
        owner2.setTelephone("98787893636");
        Owner savedOwner2 = ownerService.save(owner2);

        Pet pet1 = new Pet();
        pet1.setName("Fionas Cat");
        pet1.setType(savedCat);
        pet1.setBirthDate(LocalDate.now());
        pet1.setOwner(savedOwner2);
        pet1 = petService.save(pet1);
        savedOwner2.getPets().add(pet1);

        ownerService.save(savedOwner2);

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

        Visit visit=new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Dog visit");
        visit.setPet(pet);
        visitService.save(visit);

        Visit visit1 = new Visit();
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Cat visit");
        visit1.setPet(pet1);
        visitService.save(visit1);
    }
}
