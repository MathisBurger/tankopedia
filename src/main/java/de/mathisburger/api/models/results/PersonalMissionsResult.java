package de.mathisburger.api.models.results;

import de.mathisburger.api.models.datatypes.PersonalMissionData;

import java.util.Map;

public class PersonalMissionsResult extends BaseResponse {

    public Map<String, PersonalMissionData> data;
}
