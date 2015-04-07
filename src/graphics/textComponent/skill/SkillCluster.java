package graphics.textComponent.skill;

import graphics.ScrollableComponent;
import graphics.textComponent.ChoiceCluster;
import mechanics.Skill;
import mechanics.SkillBase;
import mechanics.character.GameCharacter;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

/**
 * User: Рома Date: 02.04.2015 Time: 5:29
 */
public class SkillCluster extends ChoiceCluster {

	private SkillBase mySkillBase;
	private static Map<String, Skill> nameToSkill;
	private static Vector<String> avaibleSkills;
	private Skill mySkill;
	private GameCharacter myCharacter;

	public SkillCluster(int x, int y, int width, int height, String value, GameCharacter character) {
		super(x, y, width, height, value);
		myCharacter = character;
		mySkillBase = character.getSkillBase();

		// Everything runs in one thread so this is safe
		if (nameToSkill == null) {
			nameToSkill = new HashMap<>();
			avaibleSkills = new Vector<>();
			avaibleSkills.add(Skill.DEFAULT_ID);
			for (Skill skill : mySkillBase.getSkills()) {
				avaibleSkills.add(skill.getName());
				nameToSkill.put(skill.getName(), skill);
			}
			Collections.sort(avaibleSkills);
		}
		choice.setModel(new DefaultComboBoxModel(avaibleSkills));

		choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String name = (String) e.getItem();
				Skill newSkill = nameToSkill.get(name);
				boolean changedAvaibleSkills = false;
				if (mySkill == null || !mySkill.equals(newSkill)) {
					if (mySkill != null) {
						avaibleSkills.add(mySkill.getName());
						changedAvaibleSkills = true;
					}
					myCharacter.dropSkill(mySkill);
					myCharacter.initSkill(newSkill);
					mySkill = newSkill;
				}
				if (!name.equals(Skill.DEFAULT_ID)) {
					avaibleSkills.remove(name);
					changedAvaibleSkills = true;
				}
				if (changedAvaibleSkills) {
					Collections.sort(avaibleSkills);
				}
				container.updateCalculables();
			}
		});
	}

	public Skill getSkill() {
		return mySkill;
	}

	public void attach(ScrollableComponent container) {
		super.attach(container);
		container.add(choice);
	}
}
