package mechanics.races;

import mechanics.magic.Magic;

import java.util.Collection;

/**
 * User: jamesbrain
 * Date: 21.02.2015
 * Time: 14:02
 */
public abstract class Human extends RaceInfo {
    public Collection<Magic> getPossibleMagics() {
        return Magic.getDefaultMagics;
    }
}
