package mechanics;

import mechanics.magic.Magic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Рома
 * Date: 21.03.2015
 * Time: 22:38
 */
public class SkillBase {
    private final Set<Skill> skills;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Skill skill : skills) {
            builder.append(skill.toString()).append("\n");
        }
        return builder.toString();
    }

    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    public void addSkill(Skill newSkill) {
        if (skills.contains(newSkill)) {
            warning("Can't add " + newSkill + ": there already is a skill with such ID");
        } else {
            skills.add(newSkill);
        }
    }

    public SkillBase(final Skill... skills) {
        this.skills = new HashSet<Skill>();
        for (Skill skill : skills) {
            if (this.skills.contains(skill)) {
                warning("Skill duplication: " + skill);
            }
            this.skills.add(skill);
        }
    }

    protected void warning(String message) {
        System.err.println(message);
    }

    public static Skill combatSkill(String id, String combatTraditionName, String discipline1, String discipline2,
                                    String discipline3, int mentalToPhysicalRatio) {
        return new Skill(id, "Бой(" + combatTraditionName + ": " + discipline1 + ", " + discipline2 + ", " + discipline3 + ")",
                true, Attribute.DEXTERITY, mentalToPhysicalRatio);
    }

    public static Skill commonSkill(String id, String name, int mentalToPhysicalRatio, Attribute attribute) {
        return new Skill(id, name, false, attribute, mentalToPhysicalRatio);
    }

    public static Skill difficultSkill(String id, String name, int mentalToPhysicalRatio, Attribute attribute, Magic... magic) {
        return new Skill(id, name, true, attribute, mentalToPhysicalRatio, magic);
    }

    public static final SkillBase ATARAX_BASE = new SkillBase(
       combatSkill("COMBAT_GUARD", "стражник", "дубина", "копьё", "доспех", 2),
            combatSkill("COMBAT_SWORDSMAN", "мечник", "одноручный меч", "щит", "доспех", 2),
            combatSkill("COMBAT_ASSASSIN", "убийца", "кинжал", "арбалет", "уклонение", 3),
            combatSkill("COMBAT_SPEARMAN", "копейщик", "копьё", "одноручный меч", "доспех", 2),
            combatSkill("COMBAT_SKIRMISHER", "застрельщик", "лук", "дротик", "доспех", 2),
            combatSkill("COMBAT_ARCHER", "лучник", "лук", "одноручный меч", "доспех", 2),
            combatSkill("COMBAT_RANGER", "следопыт", "лук", "одноручный меч", "уклонение", 2),
            combatSkill("COMBAT_MONK", "монах", "безоружный", "посох", "уклонение", 4),
            combatSkill("COMBAT_DUELIST", "дуэлист", "одноручный меч", "кинжал", "уклонение", 2),
            combatSkill("COMBAT_KNIGHT", "рыцарь", "двуручный меч", "копьё", "доспех", 2),
            //commonSkill("INITIATIVE", "инициатива", 5, )
            difficultSkill("MENTAL_RESISTANCE", "психическая сопротивляемость", 10, Attribute.WILL),
            difficultSkill("PHYSICAL_RESISTANCE", "физическая сопротивляемость", 0, Attribute.VITALITY),
            commonSkill("ACROBATICS", "акробатика", 3, Attribute.DEXTERITY),
            commonSkill("ATHLETICS_LIGHT", "атлетика", 1, Attribute.STRENGTH),
            difficultSkill("MOVEMENT", "перемещение", 2, Attribute.VITALITY),
            commonSkill("LOCKPICKING", "взлом", 7, Attribute.DEXTERITY),
            commonSkill("PICKPOCKET", "ловкость рук", 4, Attribute.DEXTERITY),
            commonSkill("MASKING", "маскировка", 7, Attribute.CHARISMA),
            commonSkill("DETECTING", "наблюдательность", 7, Attribute.PERCEPTION),
            commonSkill("SEARCH", "поиск", 8, Attribute.INTELLECT),
            commonSkill("SNEAK", "скрытность", 3, Attribute.DEXTERITY),
            commonSkill("POISONS", "яды", 6, Attribute.INTELLECT),
            commonSkill("RIDING", "верховая езда", 4, Attribute.DEXTERITY),
            commonSkill("SURVIVALIST", "выживание", 5, Attribute.VITALITY),
            commonSkill("CARTOGRAPHY", "картография", 9, Attribute.PERCEPTION),
            difficultSkill("MEDICINE", "медицина", 8, Attribute.INTELLECT),
            commonSkill("HISTORY", "история", 10, Attribute.INTELLECT),
            commonSkill("TEOLOGY", "теология", 10, Attribute.INTELLECT),
            difficultSkill("BLACKSMITH", "кузнец", 4, Attribute.VITALITY),
            commonSkill("BOWCRAFTING", "лучное дело", 4, Attribute.DEXTERITY),
            commonSkill("POTTERY", "гончарное дело", 4, Attribute.DEXTERITY),
            commonSkill("MINING", "шахтёр", 4, Attribute.STRENGTH),
            commonSkill("TRADING", "торговля", 8, Attribute.CHARISMA),
            commonSkill("CARPENTRY", "плотник", 4, Attribute.STRENGTH),
            commonSkill("COOKERY", "кулинария", 6, Attribute.PERCEPTION),
            commonSkill("FARMING", "фермерство", 3, Attribute.STRENGTH),
            commonSkill("JEWELRYCRAFTING", "ювелир", 7, Attribute.PERCEPTION),
            difficultSkill("TALK", "разговор", 8, Attribute.CHARISMA),
            commonSkill("LEADERSHIP", "лидерство", 8, Attribute.CHARISMA),
            commonSkill("LITERATURE", "литература", 10, Attribute.INTELLECT),
            commonSkill("MUSIC", "музыка", 8, Attribute.PERCEPTION),
            commonSkill("TEACHING", "обучение", 10, Attribute.CHARISMA),
            //this is Atarax-related stuff!!
            difficultSkill("CONCENTRATION", "концентрация", 8, Attribute.WILL),
            difficultSkill("ARCANE", "академическая магия", 9, Attribute.INTELLECT, Magic.ARCANE),
            difficultSkill("NECROMANCY", "некромантия", 8, Attribute.INTELLECT, Magic.NECROMANCY),
            difficultSkill("RUNES", "руническая магия", 9, Attribute.INTELLECT, Magic.RUNIC),
            difficultSkill("VITAL MAGIC", "магия жизни", 7, Attribute.WILL, Magic.VITAL),
            difficultSkill("ELEMENTAL MAGIC", "элементальная магия", 9, Attribute.WILL, Magic.ELEMENTAL),
            difficultSkill("ACHEMY", "алхимия", 8, Attribute.INTELLECT, Magic.ALCHEMY),
            difficultSkill("KOBOLD_MECHANICS", "механика", 7, Attribute.INTELLECT)
    );
}
