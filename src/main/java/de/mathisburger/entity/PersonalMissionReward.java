package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class PersonalMissionReward {

    @Id
    @GeneratedValue
    public long id;

    public Integer berths;

    public Integer premium;

    public Integer free_xp;

    @OneToMany
    public List<PersonalMissionRewardItem> items;
}
