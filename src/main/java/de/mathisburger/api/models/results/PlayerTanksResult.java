package de.mathisburger.api.models.results;

import de.mathisburger.api.models.subtypes.PlayersTank;

import java.util.List;
import java.util.Map;

public class PlayerTanksResult extends BaseResponse {

    public Map<String, List<PlayersTank>> data;
}
