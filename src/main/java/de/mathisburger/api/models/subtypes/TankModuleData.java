package de.mathisburger.api.models.subtypes;

import java.util.List;
import java.util.Optional;

public class TankModuleData {

    public String name;

    public Optional<List<Integer>> next_modules;

    public Optional<List<Integer>> next_tanks;

    public boolean is_default;

    public int price_xp;

    public int price_credit;

    public int module_id;

    public String type;
}
