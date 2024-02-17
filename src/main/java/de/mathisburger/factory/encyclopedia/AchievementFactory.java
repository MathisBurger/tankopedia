package de.mathisburger.factory.encyclopedia;

import de.mathisburger.api.WotApiClient;
import de.mathisburger.api.models.datatypes.AchievementData;
import de.mathisburger.config.WargamingConfig;
import de.mathisburger.entity.Achievement;
import de.mathisburger.repository.AchievementRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Collection;

@ApplicationScoped
public class AchievementFactory {

    @Inject
    WargamingConfig config;

    @Inject
    AchievementRepository achievementRepository;

    @Inject
    EntityManager entityManager;

    @Inject
    @RestClient
    WotApiClient apiClient;

    @Transactional
    public void clearAchievementData() {
        for (Achievement achievement : this.achievementRepository.listAll()) {
            this.entityManager.remove(achievement);
        }
    }

    @Transactional
    public void reloadAchievements() {
        Collection<AchievementData> achievements = this.apiClient.achievements(config.applicationID()).data.values();
        for (AchievementData data : achievements) {
            Achievement achievement = new Achievement();
            achievement.internalName = data.name;
            achievement.outdated = data.outdated;
            achievement.section = data.section;
            achievement.sectionOrder = data.section_order;
            achievement.imageBig = data.image_big;
            achievement.heroInfo = data.hero_info;
            achievement.name = data.name_i18n;
            //achievement.order = data.order;
            achievement.type = data.type;
            achievement.image = data.image;
            achievement.condition = data.condition;
            achievement.description = data.description;
            this.entityManager.persist(achievement);
        }
    }
}
