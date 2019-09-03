package com.myzone.petclinic.respositories;

import java.util.Optional;

import com.myzone.petclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    
    Optional<Owner> findByLastName(String lastName);
    
}
