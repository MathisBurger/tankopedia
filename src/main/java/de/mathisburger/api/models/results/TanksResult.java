package de.mathisburger.api.models.results;

import de.mathisburger.api.models.datatypes.ErrorType;
import de.mathisburger.api.models.datatypes.TankData;

import java.util.Map;
import java.util.Optional;

public class TanksResult extends BaseResponse {

    public Optional<Map<String, TankData>> data;

    public Optional<ErrorType> error;
}
