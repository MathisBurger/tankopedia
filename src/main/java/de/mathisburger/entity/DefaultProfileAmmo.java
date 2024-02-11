package de.mathisburger.entity;

import jakarta.persistence.*;

@Entity
public class DefaultProfileAmmo {

    @Id
    @GeneratedValue
    public long id;

    public int[] penetration;

    @OneToOne(cascade = CascadeType.ALL)
    public DefaultProfileStun stun;

    public String type;

    public int[] damage;
}
