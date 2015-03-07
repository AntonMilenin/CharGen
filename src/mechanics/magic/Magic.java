package mechanics.magic;

import mechanics.Skill;

import java.util.Arrays;
import java.util.Collection;

/**
 * User: jamesbrain
 * Date: 21.02.2015
 * Time: 14:05
 *
 * A class representing a type of magic possibly weildable by a character. It is a class and not a Enum to allow
 * pluggability. (For instance, for fangedcat's magics)
 */
public class Magic {
    private final String name;

    public Magic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static final Magic ELEMENTAL = new Magic("Elemental");
    public static final Magic RUNIC = new Magic("Runic");
    public static final Magic VITAL = new Magic("Vital");
    public static final Magic ARCANE = new Magic("Arcane");
    public static final Magic NECROMANCY = new Magic("Necromancy");
    public static final Magic ALCHEMY = new Magic("Alchemy");
    public static final Collection<Magic> getDefaultMagics = Arrays.asList(ELEMENTAL, RUNIC, VITAL, ARCANE, NECROMANCY,
            ALCHEMY);
}
