package com.myzone.petclinic.services;

import com.myzone.petclinic.models.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService extends CrudService<Owner, Long> {

    Optional<Owner> findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
