package com.myzone.petclinic.bootstrap;

import com.myzone.petclinic.model.Owner;
import com.myzone.petclinic.model.Vet;
import com.myzone.petclinic.service.OwnerService;
import com.myzone.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("GLenani");

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Vetter");
        vet1.setLastName("VetterSet");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("SetVet");
        vet2.setLastName("SetCAD");
        vetService.save(vet2);
    }
}
