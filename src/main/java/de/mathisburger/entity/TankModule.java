package de.mathisburger.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class TankModule {

    @Id
    public Integer id;

    @ManyToMany
    public List<TankModule> nextModules;

    @ManyToMany(mappedBy = "nextModules")
    public List<TankModule> previousModules;

    @ManyToMany(mappedBy = "previousModules")
    public List<Tank> nextTanks;

    public boolean isDefault;

    public int priceXp;

    public int priceCredit;

    public String type;

    @ManyToMany(mappedBy = "radios", cascade = CascadeType.ALL)
    public List<Tank> tankRadios;

    @ManyToMany(mappedBy = "suspensions", cascade = CascadeType.ALL)
    public List<Tank> tankSuspensions;

    @ManyToMany(mappedBy = "engines", cascade = CascadeType.ALL)
    public List<Tank> tankEngines;

    @ManyToMany(mappedBy = "guns", cascade = CascadeType.ALL)
    public List<Tank> tankGuns;

    @ManyToMany(mappedBy = "turrets", cascade = CascadeType.ALL)
    public List<Tank> tankTurrets;
}
