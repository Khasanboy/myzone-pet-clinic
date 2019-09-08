package com.myzone.petclinic.services.map;

import com.myzone.petclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerServiceMapTest {


    OwnerServiceMap ownerServiceMap;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(1L).build());

    }

    @Test
    void findByLastName() {
        ownerServiceMap.save(Owner.builder().id(2L).lastName("Muxa").build());

        assertThat(ownerServiceMap.findByLastName("Muxa")).isNotEmpty();
    }

    @Test
    void findAll() {
        assertThat(ownerServiceMap.findAll()).hasSize(1);
    }

    @Test
    void findById() {
        assertThat(ownerServiceMap.findById(1L)).isNotEmpty();
    }

    @Test
    void saveWithId() {
        Owner owner = Owner.builder().id(3L).build();
        Owner saved = ownerServiceMap.save(owner);

        assertThat(saved.getId()).isEqualTo(3L);
    }

    @Test
    void saveWithoutId() {
        Owner owner = Owner.builder().build();
        Owner saved = ownerServiceMap.save(owner);

        assertThat(saved.getId()).isEqualTo(2L);
    }

    @Test
    void delete() {
        Owner owner = ownerServiceMap.save(Owner.builder().id(2L).build());
        assertThat(ownerServiceMap.findAll()).hasSize(2);
        ownerServiceMap.delete(owner);
        assertThat(ownerServiceMap.findById(2L)).isEmpty();
        assertThat(ownerServiceMap.findAll()).hasSize(1);
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(1L);
        assertThat(ownerServiceMap.findAll()).hasSize(0);
    }
}