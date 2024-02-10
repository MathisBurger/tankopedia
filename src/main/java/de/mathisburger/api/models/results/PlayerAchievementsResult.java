package de.mathisburger.api.models.results;

import de.mathisburger.api.models.datatypes.PlayerAchievements;

import java.util.Map;

public class PlayerAchievementsResult extends BaseResponse {

    public Map<String, PlayerAchievements> data;
}
