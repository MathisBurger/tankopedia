package de.mathisburger.api.models.datatypes;

import de.mathisburger.api.models.subtypes.PersonalMissionOperation;

import java.util.Map;

public class PersonalMissionData {

    public String description;

    public int campaign_id;

    public String name;

    public Map<String, PersonalMissionOperation> operations;
}
