package de.mathisburger.resources;

import de.mathisburger.api.WotApiClient;
import de.mathisburger.api.models.results.AchievementResult;
import de.mathisburger.api.models.results.PersonalMissionsResult;
import de.mathisburger.config.WargamingConfig;
import de.mathisburger.entity.Map;
import de.mathisburger.repository.MapRepository;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@GraphQLApi
public class HandThroughResource {

    @Inject
    WargamingConfig config;

    @Inject
    @RestClient
    WotApiClient apiClient;

    @Inject
    MapRepository mapRepository;

    @Query
    public AchievementResult achievements() {
        return this.apiClient.achievements(config.applicationID());
    }

    @Query
    public PersonalMissionsResult personalMissions() {
        return this.apiClient.personalMissions(config.applicationID());
    }

    @Query
    public List<Map> maps() {
        return this.mapRepository.listAll();
    }
}
