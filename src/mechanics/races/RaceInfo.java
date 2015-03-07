package mechanics.races;

import mechanics.character.GameCharacter;
import mechanics.magic.Magic;

import java.util.Collection;

/**
 * User: jamesbrain
 * Date: 21.02.2015
 * Time: 14:03
 */
public abstract class RaceInfo {
    public abstract Collection<Magic> getPossibleMagics();
    public abstract Collection<GameCharacter.AttributeEntry> getDefaultAttributes();
    public abstract String getRaceName();
}
