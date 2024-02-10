package de.mathisburger.repository;

import de.mathisburger.entity.Tank;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TankRepository implements PanacheRepositoryBase<Tank, Integer> {
}
