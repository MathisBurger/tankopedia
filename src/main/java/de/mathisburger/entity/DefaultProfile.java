package de.mathisburger.entity;

import jakarta.persistence.*;

@Entity
public class DefaultProfile {

    @Id
    @GeneratedValue
    public int id;

    @OneToOne(cascade = CascadeType.ALL)
    public DefaultProfileModules modules;

    @OneToOne(cascade = CascadeType.ALL)
    public DefaultProfileGun gun;

    @OneToOne(cascade = CascadeType.ALL)
    public DefaultProfileTurret turret;

    @OneToOne(cascade = CascadeType.ALL)
    public DefaultProfileRadio radio;

    @OneToOne(cascade = CascadeType.ALL)
    public DefaultProfileAmmo ammo;

    public int maxAmmo;

    public int weight;

    public int hp;

    public int hullWeight;

    public int speedForward;

    public int hullHp;

    public int speedBackward;

    public int maxWeight;
}
