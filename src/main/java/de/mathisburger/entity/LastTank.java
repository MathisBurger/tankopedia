package de.mathisburger.entity;

import jakarta.persistence.*;

@Entity
public class LastTank {

    @Id
    @GeneratedValue
    public int id;

    public int costXp;

    @ManyToOne(cascade = CascadeType.ALL)
    public Tank lastTank;
}
