package de.mathisburger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PersonalMissionRewardItem {

    @Id
    @GeneratedValue
    public long id;

    public int itemId;

    public int amount;
}
