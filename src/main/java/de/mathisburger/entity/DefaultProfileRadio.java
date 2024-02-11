package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DefaultProfileRadio {

    @Id
    @GeneratedValue
    public long id;

    public int tier;

    public int signalRange;

    public String tag;

    public String name;

    public int weight;
}

