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

    public static String DEFAULT_ID = "---";

    private final String id;
    private final String name;
    private final int complexity;
    private final Attribute keyAttribute;
    private final Collection<Magic> requiredMagic;
    private final int mentalToPhysicalRatio;
    public Skill(String id, String name, int complexity, Attribute keyAttribute, int mentalToPhysicalRatio, Magic... magic) {
        this.id = id;
        this.name = name;
        this.complexity = complexity;
        this.keyAttribute = keyAttribute;
        this.requiredMagic = Arrays.asList(magic);
        this.mentalToPhysicalRatio = mentalToPhysicalRatio;
    }
    public Skill(String id, String name, boolean isComplex, Attribute keyAttribute, int mentalToPhysicalRatio, Magic... magic) {
        this(id, name, isComplex ? 2 : 1, keyAttribute, mentalToPhysicalRatio, magic);
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

    private String getComplexityString() {
        if (complexity == 1) return "";
        if (complexity == 2) return "(сложный)";
        if (complexity == 4) return "(очень сложный)";
        return "(сложность х " + complexity + ")";
    }

    @Override
    public String toString() {
        return name;// + getComplexityString() + "; " + getKeyAttribute().russianRepresentation() + " ментальный " + mentalToPhysicalRatio + " физический " + (10 - mentalToPhysicalRatio);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Skill) {
            Skill other = (Skill) o;
            return id.equals(other.id);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return id.hashCode();
    }

    public int getMentalToPhysicalRatio() {
        return mentalToPhysicalRatio;
    }
}
