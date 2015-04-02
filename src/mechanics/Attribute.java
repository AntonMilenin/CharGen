package mechanics;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * User: jamesbrain
 * Date: 21.02.2015
 * Time: 2:05
 */
public enum Attribute {
    STRENGTH, DEXTERITY, SPEED, VITALITY, PERCEPTION, WILL, INTELLECT, CHARISMA;

    public static Collection<Attribute> getLearningAttributes() {
        return Arrays.asList(VITALITY, INTELLECT, WILL, PERCEPTION);
    }

    public boolean isLearningAttribute() {
        return getLearningAttributes().contains(this);
    }

    public boolean isPhysical() {
        return this == STRENGTH || this == DEXTERITY || this == SPEED || this == VITALITY;
    }

    public String russianRepresentation() {
        switch (this) {
            case STRENGTH: return "сила";
            case DEXTERITY: return "ловкость";
            case SPEED: return "скорость";
            case VITALITY: return "выносливость";
            case PERCEPTION: return "восприятие";
            case WILL: return "сила воли";
            case INTELLECT: return "интеллект";
            case CHARISMA: return "харизма";
            default: throw new RuntimeException("Unknown attribute " + this);
        }
    }

    public String getShortName() {
        switch (this) {
            case STRENGTH: return "STR";
            case DEXTERITY: return "DEX";
            case SPEED: return "SPD";
            case VITALITY: return "VIT";
            case PERCEPTION: return "PRT";
            case WILL: return "WP";
            case INTELLECT: return "INT";
            case CHARISMA: return "CHA";
            default: throw new RuntimeException("Unknown attribute " + this);
        }
    }

    /**
     * This is used to make sure visual representation does not depend on order of elements in enum.
     * @return
     */
    public int getIndex() {
        switch (this) {
            case STRENGTH: return 0;
            case DEXTERITY: return 1;
            case SPEED: return 2;
            case VITALITY: return 3;
            case PERCEPTION: return 4;
            case WILL: return 5;
            case INTELLECT: return 6;
            case CHARISMA: return 7;
            default: throw new RuntimeException("Unknown attribute " + this);
        }
    }
}
