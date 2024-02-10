package de.mathisburger.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Set;

@Entity
public class CrewMember {

    @Id
    @GeneratedValue
    public int id;

    public String memberId;

    @ElementCollection
    public Set<String> roles;

}
