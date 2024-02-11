package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class DefaultProfileAmmo {

    @Id
    @GeneratedValue
    public long id;

    public int[] penetration;

    @OneToOne
    public DefaultProfileStun stun;

    public String type;

    public int[] damage;
}
