package de.mathisburger.api.models.subtypes;

import java.util.Map;

public class PersonalMissionOperation {

    public String name;

    public int missions_in_set;

    public String image;

    public int sets_count;

    public int operation_id;

    public int next_id;

    public int sets_to_next;

    public int description;

    public PersonalMissionReward reward;

    public Map<String, PersonalMissionMission> missions;
}
