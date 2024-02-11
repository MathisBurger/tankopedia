package de.mathisburger.factory;

import de.mathisburger.factory.encyclopedia.MapFactory;
import de.mathisburger.factory.encyclopedia.TankFactory;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EncyclopediaFactory {

    @Inject
    TankFactory tankFactory;

    @Inject
    MapFactory mapFactory;



    @Scheduled(cron = "{wargaming.schedule}")
    void reloadEncyclopedia() {
        this.tankFactory.removeAllTankData();
        this.tankFactory.reloadTanks();
        System.out.println("Imported tanks");

        this.mapFactory.clearMapData();
        this.mapFactory.reloadMaps();
        System.out.println("Imported maps");

        // TODO: Implement consumables with relation to tanks
        // TODO: Implement accievements
        // TODO: Implement maps
        // TODO: Implement personal missions
        // TODO: Implement personal reserves
        // TODO: Implement modules
        // TODO: Implement badges
        // TODO: Implement crew qualifications
        // TODO: Implement crew skills

        System.out.println("Import done!");
    }
}
