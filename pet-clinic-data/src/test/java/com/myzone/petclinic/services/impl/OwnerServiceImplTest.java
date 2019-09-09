package com.myzone.petclinic.services.impl;

import com.myzone.petclinic.models.Owner;
import com.myzone.petclinic.respositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceImpl ownerService;

    Owner testOwner;

    @BeforeEach
    void setUp() {
        testOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        //given
        Owner sm = Owner.builder().lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(anyString())).thenReturn(Optional.of(sm));
        //when
        Optional<Owner> smith = ownerService.findByLastName(LAST_NAME);

        //then
        assertThat(smith).isNotEmpty();
        assertThat(smith.get().getLastName()).isEqualTo(LAST_NAME);
        verify(ownerRepository).findByLastName(anyString());
    }

    @Test
    void findAll() {

        Owner ow1 = Owner.builder().id(1L).build();
        Owner ow2 = Owner.builder().id(2L).build();
        Set<Owner> set = new HashSet<>();
        set.add(ow1);
        set.add(ow2);

        when(ownerRepository.findAll()).thenReturn(set);

        Set<Owner> findAll = ownerService.findAll();

        assertThat(findAll).hasSize(2);
    }

    @Test
    void findById() {

        when(ownerRepository.findById(any())).thenReturn(Optional.of(testOwner));

        Optional<Owner> foundById = ownerService.findById(1L);

        assertThat(foundById).isNotEmpty();
        assertThat(foundById.get().getId()).isEqualTo(1L);
    }

    @Test
    void save() {

        when(ownerRepository.save(any())).thenReturn(testOwner);

        Owner saved = ownerService.save(testOwner);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isEqualTo(1L);
    }

    @Test
    void delete() {
        ownerService.delete(testOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(anyLong());
        verify(ownerRepository).deleteById(anyLong());
    }
}