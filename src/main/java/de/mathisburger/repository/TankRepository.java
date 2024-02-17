package de.mathisburger.repository;

import de.mathisburger.entity.Tank;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TankRepository implements PanacheRepositoryBase<Tank, Integer> {

    public Tank findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Tank> getTanksByNation(String nation) {
        return find("nation", nation).list();
    }

    public List<Tank> getAllPremium() {
        return find("premium", true).list();
    }

    public List<Tank> getAllOfType(String type) {
        return find("type", type).list();
    }

    public List<Tank> getAllByTier(int tier) {
        return find("tier", tier).list();
    }
}
