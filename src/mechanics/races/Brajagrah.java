package mechanics.races;

import mechanics.Attribute;
import mechanics.character.GameCharacter.AttributeEntry;

import java.util.*;

/**
 * User: Рома
 * Date: 21.02.2015
 * Time: 21:52
 */
public class Brajagrah extends Human {
    @Override
    public Collection<AttributeEntry> getDefaultAttributes() {

        List<AttributeEntry> res = new LinkedList<>();
        res.add(new AttributeEntry(Attribute.CHARISMA, 10));
        res.add(new AttributeEntry(Attribute.DEXTERITY, 10));
        res.add(new AttributeEntry(Attribute.INTELLECT, 9));
        res.add(new AttributeEntry(Attribute.PERCEPTION, 10));
        res.add(new AttributeEntry(Attribute.SPEED, 10));
        res.add(new AttributeEntry(Attribute.STRENGTH, 10));
        res.add(new AttributeEntry(Attribute.VITALITY, 11));
        res.add(new AttributeEntry(Attribute.WILL, 10));
        assert(res.size() == Attribute.values().length);
        return res;
    }

    @Override
    public String getRaceName() {
        return "Brajagrah";
    }
}
