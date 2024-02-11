package de.mathisburger.repository;

import de.mathisburger.entity.Map;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MapRepository implements PanacheRepositoryBase<Map, Long> {
}
