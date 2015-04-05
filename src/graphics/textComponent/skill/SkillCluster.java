package graphics.textComponent.skill;

import graphics.Constants;
import graphics.ScrollableComponent;
import graphics.textComponent.MyComboBoxRenderer;
import graphics.textComponent.MyTextComponent;
import mechanics.Skill;
import mechanics.SkillBase;
import mechanics.character.GameCharacter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Рома
 * Date: 02.04.2015
 * Time: 5:29
 */
public class SkillCluster extends MyTextComponent {

    protected JComboBox<String> choice;
    private SkillBase mySkillBase;
    private Map<String, Skill> nameToSkill = new HashMap<String, Skill>();
    private Skill mySkill;
    private GameCharacter myCharacter;

    public SkillCluster(int x, int y, int width, int height, String value, GameCharacter character) {
        super(x, y, width, height, value);
        myCharacter = character;
        mySkillBase = character.getSkillBase();

        textField.setVisible(false);
        choice = new JComboBox<>();
        choice.setBounds(x, y, width, height);
        choice.setForeground(Color.BLACK);
        choice.setFont(Constants.FONT);
        choice.setBackground(Color.WHITE);
        choice.setVisible(true);
		choice.setRenderer(new MyComboBoxRenderer(choice));
        choice.addItem(value);

        for (Skill skill: mySkillBase.getSkills()) {
            System.out.println(skill.getName());
            choice.addItem(skill.getName());
            nameToSkill.put(skill.getName(), skill);
        }

        choice.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                String name = (String) e.getItem();
                textField.setText(name);
				choice.setToolTipText(name);
                Skill newSkill = nameToSkill.get(name);
                if (mySkill == null || !mySkill.equals(newSkill)) {
                    myCharacter.dropSkill(mySkill);
                    myCharacter.initSkill(newSkill);
                    mySkill = newSkill;
                }
                container.updateCalculables();
            }
        });
    }

    public Skill getSkill() {
        return mySkill;
    }

    public void attach(ScrollableComponent container){
        super.attach(container);
        container.add(choice);
    }
}
