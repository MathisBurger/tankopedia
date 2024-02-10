package de.mathisburger.resources;

import de.mathisburger.api.WotApiClient;
import de.mathisburger.api.models.results.PersonalInfoResult;
import de.mathisburger.api.models.results.PlayerAchievementsResult;
import de.mathisburger.api.models.results.PlayerSearchResult;
import de.mathisburger.api.models.results.PlayerTanksResult;
import de.mathisburger.config.WargamingConfig;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@GraphQLApi
public class AccountResource {

    @Inject
    WargamingConfig config;

    @Inject
    @RestClient
    WotApiClient apiClient;

    @Query
    public PlayerSearchResult searchPlayer(String search) {
        return apiClient.searchPlayer(config.applicationID(), search);
    }

    @Query
    public PersonalInfoResult personalInfo(String accountId) {
        return apiClient.personalInfo(config.applicationID(), accountId);
    }

    @Query
    public PlayerTanksResult playerTanks(String accountId) {
        return apiClient.playersTanks(config.applicationID(), accountId);
    }

    @Query
    public PlayerAchievementsResult playerAchievements(String accountId) {
        return apiClient.playerAchievements(config.applicationID(), accountId);
    }

}
