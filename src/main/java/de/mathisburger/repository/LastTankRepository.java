package de.mathisburger.repository;

import de.mathisburger.entity.LastTank;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LastTankRepository implements PanacheRepositoryBase<LastTank, Integer> {
}
