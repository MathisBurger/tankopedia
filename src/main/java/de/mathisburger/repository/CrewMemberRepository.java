package de.mathisburger.repository;

import de.mathisburger.entity.CrewMember;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CrewMemberRepository implements PanacheRepositoryBase<CrewMember, Integer> {
}
