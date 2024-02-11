package de.mathisburger.repository;

import de.mathisburger.entity.DefaultProfile;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultProfileRepository implements PanacheRepositoryBase<DefaultProfile, Integer> {
}
