package de.mathisburger.api.models.subtypes;

import java.util.Optional;

public class DefaultProfileAmmo {

    public int[] penetration;

    public Optional<DefaultProfileStun> stun;

    public String type;

    public int[] damage;
}
