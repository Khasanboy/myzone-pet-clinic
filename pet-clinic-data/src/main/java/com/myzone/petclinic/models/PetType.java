package com.myzone.petclinic.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity{

    @Column(name = "name")
    private String name;

}