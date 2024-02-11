package de.mathisburger.repository;

import de.mathisburger.entity.Achievement;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AchievementRepository implements PanacheRepositoryBase<Achievement, Long> {
}
