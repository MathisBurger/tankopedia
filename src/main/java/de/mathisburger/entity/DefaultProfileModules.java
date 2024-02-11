package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DefaultProfileModules {

    @Id
    @GeneratedValue
    public long id;

    public int gunId;

    public int suspensionId;

    public Integer turretId;

    public int radioId;

    public int engineId;
}
