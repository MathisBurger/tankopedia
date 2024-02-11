package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DefaultProfileGun {

    @Id
    @GeneratedValue
    public long id;

    public int moveDownArc;

    public int caliber;

    public String name;

    public int weight;

    public int moveUpArc;

    public double fireRate;

    public double dispersion;

    public String tag;

    public int traverseSpeed;

    public double reloadTime;

    public int tier;

    public double aimTime;
}
