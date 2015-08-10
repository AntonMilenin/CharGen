package mechanics.races;

import mechanics.magic.Magic;

import java.util.Collection;
import java.util.List;

/**
 * User: Рома
 * Date: 13.06.2015
 * Time: 1:21
 */
public interface Species {
    public Collection<Magic> getPossibleMagics();
    public List<RaceInfo> getRaces();
    public String speciesName();
}
