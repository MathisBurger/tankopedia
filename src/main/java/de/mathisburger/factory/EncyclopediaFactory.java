package de.mathisburger.factory;

import de.mathisburger.factory.encyclopedia.AchievementFactory;
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

    @Inject
    AchievementFactory achievementFactory;

    @Scheduled(cron = "{wargaming.schedule}")
    void reloadEncyclopedia() {
        this.tankFactory.removeAllTankData();
        this.tankFactory.reloadTanks();
        System.out.println("Imported tanks");

        this.mapFactory.clearMapData();
        this.mapFactory.reloadMaps();
        System.out.println("Imported maps");

        //this.achievementFactory.clearAchievementData();
        //this.achievementFactory.reloadAchievements();
        System.out.println("Imported achievements");

        System.out.println("Import done!");
    }
}
