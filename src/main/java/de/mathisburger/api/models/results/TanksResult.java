package de.mathisburger.api.models.results;

import de.mathisburger.api.models.datatypes.TankData;

import java.util.Map;

public class TanksResult extends BaseResponse {

    public Map<String, TankData> data;
}
