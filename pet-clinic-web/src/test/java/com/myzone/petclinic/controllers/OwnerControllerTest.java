package com.myzone.petclinic.controllers;

import com.myzone.petclinic.models.Owner;
import com.myzone.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    MockMvc mockMvc;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> ownerSet;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }
/*
    @Test
    void index() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }
*/
    @Test
    void find() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        Owner owner1 = Owner.builder().address("Lalala").city("Tallinn").id(1L).firstName("KUka").lastName("Pupa").telephone("090989808").build();
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(owner1));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        Owner owner1 = Owner.builder().address("Lalala").city("Tallinn").id(1L).firstName("KUka").lastName("Pupa").telephone("090989808").build();
        Owner owner2 = Owner.builder().address("Lalala").city("Tallinn").id(2L).firstName("Luka").lastName("Pupa").telephone("090989808").build();
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(owner1, owner2));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void findOwnerById() throws Exception {
        Owner owner = Owner.builder().address("Lalala").city("Tallinn").id(1L).firstName("KUka").lastName("Pupa").telephone("090989808").build();

        when(ownerService.findById(anyLong())).thenReturn(Optional.of(owner));

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));

    }
}