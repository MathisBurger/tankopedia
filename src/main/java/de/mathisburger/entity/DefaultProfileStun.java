package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DefaultProfileStun {

    @Id
    @GeneratedValue
    public long id;

    public double[] duration;
}
