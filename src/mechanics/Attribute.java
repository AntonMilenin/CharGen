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
    STRENGTH, DEXTERITY, SPEED, VITALITY, INTELLECT, CHARISMA, WILL, PERCEPTION;

    public static Collection<Attribute> getLearningAttributes() {
        return Arrays.asList(VITALITY, INTELLECT, WILL, PERCEPTION);
    }

    public boolean isLearningAttribute() {
        return getLearningAttributes().contains(this);
    }

    public boolean isPhysical() {
        return this == STRENGTH || this == DEXTERITY || this == SPEED || this == VITALITY || this == PERCEPTION;
    }

//    public static int getPoolThreshold(int poolValue, final boolean isIncrease) {
//        int threshold = 100;
//        int prevThreshold = 100;
//        while (poolValue > threshold) {
//            prevThreshold = threshold;
//            threshold += threshold + 100;
//        }
//        return isIncrease ? threshold : prevThreshold;
//    }

//    public static final int ATTRIBUTE_BASE = 10;
//    public static final int LEARNING_COEFFICIENT = 30;
}
