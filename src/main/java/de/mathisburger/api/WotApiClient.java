package de.mathisburger.api;
import de.mathisburger.api.models.results.PersonalInfoResult;
import de.mathisburger.api.models.results.PlayerAchievementsResult;
import de.mathisburger.api.models.results.PlayerSearchResult;
import de.mathisburger.api.models.results.PlayerTanksResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "wot-api")
public interface WotApiClient {
    @GET
    @Path("/account/list/")
    PlayerSearchResult searchPlayer(@QueryParam("application_id") String applicationId, @QueryParam("search") String search);

    @GET
    @Path("/account/info/")
    PersonalInfoResult personalInfo(@QueryParam("application_id") String applicationId, @QueryParam("account_id") String accountId);

    @GET
    @Path("/account/tanks/")
    PlayerTanksResult playersTanks(@QueryParam("application_id") String applicationId, @QueryParam("account_id") String accountId);

    @GET
    @Path("/account/achievements/")
    PlayerAchievementsResult playerAchievements(@QueryParam("application_id") String applicationId, @QueryParam("account_id") String accountId);

}
