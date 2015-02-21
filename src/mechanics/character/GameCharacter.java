package mechanics.character;

import mechanics.Attribute;
import mechanics.Skill;
import mechanics.races.Brajagrah;
import mechanics.races.RaceInfo;

import java.util.*;

/**
 * User: jamesbrain
 * Date: 21.02.2015
 * Time: 2:22
 */
public class GameCharacter {

    private HashMap<Attribute, AttributeEntry> attributes;
    private String name;
    private Gender gender;
    private int age;
    private int height;
    private String hair;
    private String skin;
    private int skillPoints;
    private int skillPointsSpent;
    private int attributePoints;
    private Map<Skill, SkillEntry> skills = new HashMap<Skill, SkillEntry>();
    private boolean leftSkillPointsControl;
    private RaceInfo race;
    private int physicalPool;
    private int mentalPool;

    public GameCharacter() {
        this("John Doe", Gender.MALE, 20, 175, "bald", "white", 550, 0, 3, false, new Brajagrah());
    }

    public GameCharacter(String name, Gender gender, Integer age, Integer height, String hair, String skin,
                         int skillPoints, int skillPointsSpent, int attributePoints, boolean leftSkillPointsControl, RaceInfo race) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.hair = hair;
        this.skin = skin;
        this.skillPoints = skillPoints;
        this.skillPointsSpent = skillPointsSpent;
        this.attributePoints = attributePoints;
        this.leftSkillPointsControl = leftSkillPointsControl;
        this.race = race;
        Collection<AttributeEntry> basicAttributes = race.getDefaultAttributes();
        attributes = new HashMap<Attribute, AttributeEntry>();
        for (AttributeEntry attribute: basicAttributes) {
            attributes.put(attribute.attribute, attribute);
        }
    }

    /**
     * Implements logic for '+'/'-' switches in visual representation of skills.
     * @param skill - skill to modify value for
     * @param isIncrease - true if skill must be increased, false otherwise
     */
    public void updateSkill(Skill skill, boolean isIncrease) {
        SkillEntry entry = skills.get(skill);
        assert(entry != null);
        int cost = entry.getChangeCost(isIncrease);
        if (getRemainingSkillPoints() < cost && leftSkillPointsControl || entry.getLevel() == 0 && !isIncrease) {
            return;
        }
        updateSkillPoints(cost);
        updateAttributePool(entry.getSkill().getKeyAttribute().isPhysical(), cost);
        if (isIncrease) {
            entry.increase();
        } else {
            entry.decrease();
        }
    }

    private void updateAttributePool(boolean physical, int cost) {
        if (physical) {
            physicalPool -= cost;
        } else {
            mentalPool -= cost;
        }
    }

    /**
     * Returns string representation of value of a given skill.
     * @param skill
     * @return
     */
    public String getSkillValue(Skill skill) {
        SkillEntry entry = skills.get(skill);
        if (entry == null) {
            //init with empty skill entry
            entry = new SkillEntry(skill);
            skills.put(skill, entry);
        }
        return Integer.toString(entry.getLevel());
    }

    /**
     * Returns string representation of value of a given attribute.
     * @param attribute
     * @return
     */
    public String getAttributeValue(Attribute attribute) {
        return attributes.get(attribute).toString();
    }

    /**
     * Implements logic for '+'/'-' switches in visual representation of aquired attributes operating with attribute pools.
     * @param attribute
     * @param isIncrease
     */
    public void updateAquiredAttribute(Attribute attribute, boolean isIncrease) {
        AttributeEntry entry = attributes.get(attribute);
        if (entry.aquired == 0 && !isIncrease) return;
        int changeCost = entry.getAquiredChangeCost(isIncrease);
        boolean canChange = attribute.isPhysical() ? physicalPool >= changeCost : mentalPool >= changeCost;
        if (canChange) {
            if (attribute.isPhysical()) {
                physicalPool -= changeCost;
            } else {
                mentalPool -= changeCost;
            }
            entry.changeAquired(isIncrease);
        }
    }

    /**
     * Implements logic for '+'/'-' switches in visual representation of innate attributes operating with attribute points.
     * @param attribute
     * @param isIncrease
     */
    public void updateInnateAttribute(Attribute attribute, boolean isIncrease) {
        AttributeEntry entry = attributes.get(attribute);
        if (isIncrease && entry.innate < 0) {
            entry.increaseInnate();
            boolean updatedPhysicalPool = entry.penaltyIsPhysical.pop();
            if (updatedPhysicalPool) {
                physicalPool -= 100;
            } else {
                mentalPool -= 100;
            }
        } else if (isIncrease && attributePoints > 0) {
            attributePoints--;
            entry.increaseInnate();
        } else if (!isIncrease && entry.innate > 0) {
            attributePoints++;
            entry.decreaseInnate();
        } else if (!isIncrease) {
            entry.decreaseInnate();
            boolean updatedPhysicalPool = getPoolToIncreaseInModalDialogue();
            entry.penaltyIsPhysical.push(updatedPhysicalPool);
            if (updatedPhysicalPool) {
                physicalPool += 100;
            } else {
                mentalPool += 100;
            }
        }
    }

    /**
     * Creates a modal dialog to query user which attribute pool must be increased when reducing an attribute below
     * default value.
     * @return true if physical pool must be increase, false if mental pool must be increased
     */
    private boolean getPoolToIncreaseInModalDialogue() {
        //TODO: implement me
        return false;
    }

    //------------GETTERS AND SETTERS----------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public RaceInfo getRace() {
        return race;
    }

    public void setRace(RaceInfo race) {
        this.race = race;
        //TODO: implement change of basic attribute values!!
    }

    private int getRemainingSkillPoints() {
        return skillPoints + calcSkillPointsFromAttributes() - skillPointsSpent;
    }

    private void updateSkillPoints(int cost) {
        skillPointsSpent += cost;
    }

    private int calcSkillPointsFromAttributes() {
        int res = 0;
        for (AttributeEntry entry: attributes.values()) {
            res += entry.skillPointsBonus;
        }
        return res;
    }

    /**
     * User: jamesbrain
     * Date: 21.02.2015
     * Time: 2:19
     */
    public static class SkillEntry {

        private final Skill skill;
        private int level;
        private int miscBonus;

        public SkillEntry(Skill skill) {
            this.skill = skill;
        }

        public int getLevel() {
            return level;
        }

        public void increase() {
            level++;
        }

        public void decrease() {
            level--;
        }

        public int getChangeCost(boolean isIncrease) {
            int res;
            int prevLevel = isIncrease ? level : level - 1;
            if (prevLevel < 5) {
                res = 10;
            } else if (prevLevel < 10) {
                res = 20;
            } else if (prevLevel < 15) {
                res = 40;
            } else if (prevLevel < 20) {
                res = 80;
            } else {
                res = 100;
            }
            return res * (isIncrease ? 1 : -1);
        }

        public Skill getSkill() {
            return skill;
        }
    }

    /**
     * User: jamesbrain
     * Date: 21.02.2015
     * Time: 1:34
     */
    public static class AttributeEntry {

        private static final int ATTRIBUTE_BASE = 10;
        private static final int LEARNING_COEFFICIENT = 30;
        private static final int POOL_STEP = 100;

        public final Attribute attribute;
        private int innate;
        private int basic;
        private int aquired;
        private int skillPointsBonus;
        private int poolThreshold = 100;
        private final Stack<Boolean> penaltyIsPhysical = new Stack<Boolean>();

        public AttributeEntry(Attribute attribute) {
            this(attribute, ATTRIBUTE_BASE);
        }

        public AttributeEntry(Attribute attribute, int value) {
            this.attribute = attribute;
            this.innate = ATTRIBUTE_BASE;
            int difference = value - innate;
            changeDifference(difference);
        }

        public int getValue() {
            return basic + innate + aquired;
        }

        public int getAquiredChangeCost(boolean isIncrease) {
            return isIncrease ? poolThreshold : poolThreshold - POOL_STEP;
        }

        public void increaseInnate() {
            changeDifference(1);
        }

        private void changeDifference(int step) {
            innate += step;
            if (attribute.isLearningAttribute()) {
                skillPointsBonus += LEARNING_COEFFICIENT * step;
            }
        }

        public void decreaseInnate() {
            changeDifference(-11);
        }

        public void changeAquired(boolean isIncrease) {
            aquired += isIncrease ? 1 : -1;
            poolThreshold += isIncrease ? POOL_STEP : -POOL_STEP;
            assert(poolThreshold > 0);
            if (attribute.isLearningAttribute()) {
                skillPointsBonus += isIncrease ? LEARNING_COEFFICIENT : -LEARNING_COEFFICIENT;
            }
        }
    }
}
