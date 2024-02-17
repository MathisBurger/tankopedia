package de.mathisburger.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class PersonalMissionOperation {

    @Id
    public int id;

    public String name;

    public int missionsInSet;

    public String image;

    public int setsCount;

    @OneToOne
    public PersonalMissionOperation nextOperation;

    public int setsToNext;

    public String description;

    public Integer rewardSlots;

    @ElementCollection
    public List<Integer> rewardTanks;
}
