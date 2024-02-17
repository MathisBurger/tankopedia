package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PersonalMission {

    @Id
    public int id;

    public String name;

    public String description;


}
