package de.mathisburger.repository;

import de.mathisburger.entity.TankModule;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TankModuleRepository implements PanacheRepositoryBase<TankModule, Integer> {
}
