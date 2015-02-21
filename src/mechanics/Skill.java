package mechanics;

import mechanics.magic.Magic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * User: jamesbrain
 * Date: 21.02.2015
 * Time: 2:01
 */
public class Skill {

    private final String name;
    private final int complexity;
    private final Attribute keyAttribute;
    private final Collection<Magic> requiredMagic;
    public Skill(String name, boolean isComplex, Attribute keyAttribute, Magic... magic) {
        this.name = name;
        this.complexity = isComplex ? 2 : 1;
        this.keyAttribute = keyAttribute;
        this.requiredMagic = Arrays.asList(magic);
    }

    public String getName() {
        return name;
    }

    public int getComplexity() {
        return complexity;
    }

    public Attribute getKeyAttribute() {
        return keyAttribute;
    }

    public Collection<Magic> getRequiredMagic() {
        return Collections.unmodifiableCollection(requiredMagic);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Skill) {
            Skill other = (Skill) o;
            return other.getName().equals(getName()) && other.getKeyAttribute() == getKeyAttribute();
        } else {
            return false;
        }
    }
}
