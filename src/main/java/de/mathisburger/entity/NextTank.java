package de.mathisburger.entity;

import jakarta.persistence.*;

@Entity
public class NextTank {

    @Id
    @GeneratedValue
    public int id;

    @ManyToOne
    public Tank nextTank;

    public int priceXp;
}
