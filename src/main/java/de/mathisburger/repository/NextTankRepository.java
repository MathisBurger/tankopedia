package de.mathisburger.repository;

import de.mathisburger.entity.NextTank;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NextTankRepository implements PanacheRepositoryBase<NextTank, Integer> {
}
