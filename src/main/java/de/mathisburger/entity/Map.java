package de.mathisburger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Map {

    @Id
    @GeneratedValue
    public long id;

    public String name;

    public String camouflageType;

    @Column(columnDefinition = "TEXT")
    public String description;

    public String arenaId;
}
