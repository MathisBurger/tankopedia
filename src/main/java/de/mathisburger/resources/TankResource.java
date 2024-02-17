package de.mathisburger.resources;

import de.mathisburger.config.WargamingConfig;
import de.mathisburger.entity.Tank;
import de.mathisburger.repository.TankRepository;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class TankResource {

    @Inject
    WargamingConfig config;

    @Inject
    TankRepository tankRepository;

    @Query
    public List<Tank> tanks() {
        return this.tankRepository.listAll();
    }

    @Query
    public Tank getTankByName(String name) {
        return this.tankRepository.findByName(name);
    }

    @Query
    public Tank getTankById(int id) {
        return this.tankRepository.findById(id);
    }

    @Query
    public List<Tank> getTanksByNation(String nation) {
        return this.tankRepository.getTanksByNation(nation);
    }

    @Query
    public List<Tank> getAllPremiumTanks() {
        return this.tankRepository.getAllPremium();
    }

    @Query
    public List<Tank> getAllTanksOfType(String type) {
        return this.tankRepository.getAllOfType(type);
    }

    @Query
    public List<Tank> getAllTanksOfTier(int tier) {
        return this.tankRepository.getAllByTier(tier);
    }
}
