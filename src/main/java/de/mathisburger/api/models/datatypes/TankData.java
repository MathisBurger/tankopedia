package de.mathisburger.api.models.datatypes;

import de.mathisburger.api.models.subtypes.CrewMemberData;
import de.mathisburger.api.models.subtypes.DefaultProfile;
import de.mathisburger.api.models.subtypes.TankImagesData;
import de.mathisburger.api.models.subtypes.TankModuleData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TankData {

    public boolean is_wheeled;

    public int[] radios;

    public boolean is_premium;

    public String tag;

    public TankImagesData images;

    public int tank_id;

    public int[] suspensions;

    /**
     * TODO: How to handle this
     */
    public int[] provisions;

    public int[] engines;

    public List<CrewMemberData> crew;

    public String type;

    public int[] guns;

    public String description;

    public String short_name;

    public boolean is_premium_igr;

    public Map<String, Integer> next_tanks;

    public Map<String, TankModuleData> modules_tree;

    public String nation;

    public int tier;

    public Map<String, Integer> prices_xp;

    public boolean is_gift;

    public String name;

    public Optional<Integer> price_gold;

    public Optional<Integer> price_credit;

    public int[] turrets;

    public DefaultProfile default_profile;
}
