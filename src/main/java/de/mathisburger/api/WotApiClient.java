package de.mathisburger.api;
import de.mathisburger.api.models.results.*;
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

    @GET
    @Path("/encyclopedia/vehicles/")
    TanksResult tanks(@QueryParam("application_id") String applicationId, @QueryParam("page_no") int pageNo);

    @GET
    @Path("/encyclopedia/arenas/")
    MapResult maps(@QueryParam("application_id") String applicationId);

    @GET
    @Path("/encyclopedia/achievements/")
    AchievementResult achievements(@QueryParam("application_id") String applicationId);
}
