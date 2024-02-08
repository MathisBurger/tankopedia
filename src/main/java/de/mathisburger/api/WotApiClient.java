package de.mathisburger.api;
import de.mathisburger.api.models.PlayerSearchResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "wot-api")
public interface WotApiClient {
    @GET
    @Path("/account/list/")
    PlayerSearchResult searchPlayer(@QueryParam("application_id") String applicationId, @QueryParam("search") String search);
}
