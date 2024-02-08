package de.mathisburger.resources;

import de.mathisburger.api.WotApiClient;
import de.mathisburger.api.models.PlayerSearchResult;
import de.mathisburger.config.WargamingConfig;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Arrays;

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

}
