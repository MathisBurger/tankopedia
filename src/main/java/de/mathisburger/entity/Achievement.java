package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Achievement {

    @Id
    @GeneratedValue
    public long id;

    public String internalName;

    public boolean outdated;

    public String section;

    public int sectionOrder;

    public String imageBig;

    public String heroInfo;

    public String name;

    //public int order;

    public String type;

    public String image;

    public String condition;

    public String description;
}
