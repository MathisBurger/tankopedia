package de.mathisburger.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class PersonalMissionMission {

    @Id
    public int id;

    public int minLevel;

    public String name;

    public String hint;

    public int maxLevel;

    @ElementCollection
    public List<String> tags;

    public int setId;

    public String description;

    @OneToMany
    public List<PersonalMissionReward> rewards;
}
