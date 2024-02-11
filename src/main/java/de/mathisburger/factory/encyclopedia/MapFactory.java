package de.mathisburger.factory.encyclopedia;

import de.mathisburger.api.WotApiClient;
import de.mathisburger.api.models.results.MapResult;
import de.mathisburger.config.WargamingConfig;
import de.mathisburger.entity.Map;
import de.mathisburger.repository.MapRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MapFactory {

    @Inject
    WargamingConfig config;

    @Inject
    MapRepository mapRepository;

    @Inject
    EntityManager entityManager;

    @Inject
    @RestClient
    WotApiClient apiClient;

    @Transactional
    public void clearMapData() {
        for (Map map : this.mapRepository.listAll()) {
            this.entityManager.remove(map);
        }
    }

    @Transactional
    public void reloadMaps() {
        MapResult result = this.apiClient.maps(config.applicationID());
        for (de.mathisburger.api.models.datatypes.Map mapData : result.data.values()) {
            Map map = new Map();
            map.name = mapData.name_i18n;
            map.camouflageType = mapData.camouflage_type;
            map.description = mapData.description;
            map.arenaId = mapData.arena_id;
            this.entityManager.persist(map);
        }
    }
}
