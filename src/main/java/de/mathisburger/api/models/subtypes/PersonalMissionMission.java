package de.mathisburger.api.models.subtypes;

import java.util.Map;

public class PersonalMissionMission {

    public int min_level;

    public String name;

    public String hint;

    public String[] tags;

    public int mission_id;

    public int set_id;

    public String description;

    public Map<String, PersonalMissionMissionRewards> rewards;
}
