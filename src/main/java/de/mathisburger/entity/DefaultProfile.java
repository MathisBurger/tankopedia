package de.mathisburger.entity;

import jakarta.persistence.*;

@Entity
public class DefaultProfile {

    @Id
    @GeneratedValue
    public int id;

    @OneToOne
    public DefaultProfileModules modules;

    @OneToOne
    public DefaultProfileGun gun;

    @OneToOne
    public DefaultProfileTurret turret;

    @OneToOne
    public DefaultProfileRadio radio;

    @OneToOne
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
