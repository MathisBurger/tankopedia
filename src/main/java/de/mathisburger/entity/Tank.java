package de.mathisburger.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Tank {

    @Id
    public int id;

    @ManyToMany
    public List<TankModule> previousModules;

    public boolean isWheeled;

    public boolean isPremium;

    public String tag;

    public String smallIcon;

    public String contourIcon;

    public String bigIcon;

    public String type;

    @Column(columnDefinition = "TEXT")
    public String description;

    public String shortName;

    public boolean isPremiumIgr;

    public String nation;

    public int tier;

    public boolean isGift;

    public String name;

    public Integer priceGold;

    public Integer priceCredit;

    @OneToMany
    public List<NextTank> nextTanks;

    @OneToMany
    public List<LastTank> lastTanks;

    @OneToMany
    public List<CrewMember> crewMembers;

    @ManyToMany
    @JoinTable(
            name = "tank_radio",
            joinColumns = { @JoinColumn(name = "tank_id") },
            inverseJoinColumns = { @JoinColumn(name = "radio_id") }
    )
    public List<TankModule> radios;

    @ManyToMany
    @JoinTable(
            name = "tank_suspension",
            joinColumns = { @JoinColumn(name = "tank_id") },
            inverseJoinColumns = { @JoinColumn(name = "suspension_id") }
    )
    public List<TankModule> suspensions;

    @ManyToMany
    @JoinTable(
            name = "tank_engine",
            joinColumns = { @JoinColumn(name = "tank_id") },
            inverseJoinColumns = { @JoinColumn(name = "engine_id") }
    )
    public List<TankModule> engines;

    @ManyToMany
    @JoinTable(
            name = "tank_gun",
            joinColumns = { @JoinColumn(name = "tank_id") },
            inverseJoinColumns = { @JoinColumn(name = "gun_id") }
    )
    public List<TankModule> guns;

    @ManyToMany
    @JoinTable(
            name = "tank_turret",
            joinColumns = { @JoinColumn(name = "tank_id") },
            inverseJoinColumns = { @JoinColumn(name = "turret_id") }
    )
    public List<TankModule> turrets;
}
