package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DefaultProfileTurret {

    @Id
    @GeneratedValue
    public long id;

    public String name;

    public int weight;

    public int viewRange;

    public int hp;

    public String tag;

    public Integer traverseSpeed;

    public int traverseRightArc;

    public int tier;

    public int traverseLeftArc;
}

