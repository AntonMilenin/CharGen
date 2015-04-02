package graphics.textComponent.skill;

import graphics.ScrollableComponent;
import graphics.textComponent.ChoiceCluster;
import graphics.textComponent.MyTextComponent;
import graphics.textComponent.MyUpdatable;
import graphics.textComponent.NumberCluster;
import mechanics.Skill;
import mechanics.character.GameCharacter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Рома
 * Date: 01.04.2015
 * Time: 0:03
 */
public class SkillElement implements MyUpdatable {

    private final GameCharacter myCharacter;
    private ScrollableComponent myContainer;

    private final SkillCluster skillCluster;
    private final MyTextComponent statComponent;
    private final NumberCluster rankComponent;
    private final MyTextComponent statBonusComponent;
    //private final MyTextComponent miscComponent;
    private final MyTextComponent totalComponent;
    private final MyTextComponent typeComponent;

    public SkillElement(int skillY, int skillX, int skillWidth, int statX, int rankX, int statBonusX, int miscX,
                        int totalX, int typeX, int width, int height, GameCharacter character) {
        myCharacter = character;
        skillCluster = new SkillCluster(skillX, skillY, skillWidth, height, Skill.DEFAULT_ID, myCharacter);
        statComponent = new MyTextComponent(statX, skillY, width, height, Skill.DEFAULT_ID){
            public void update() {
                Skill skill = skillCluster.getSkill();
                if (skill != null) {
                    getTextField().setText(skill.getKeyAttribute().getShortName());
                }
            }
        };
        statComponent.getTextField().setFont(new Font("Dialog", Font.PLAIN, 28));
        rankComponent = new NumberCluster(rankX, skillY, width, height, Skill.DEFAULT_ID){
            public void update() {
                Skill skill = skillCluster.getSkill();
                if (skill != null) {
                    getTextField().setText(Integer.toString(myCharacter.getSkillValue(skill)));
                }
            }
        };
        rankComponent.addPlusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Skill skill = skillCluster.getSkill();
                if (skill != null) {
                    myCharacter.updateSkill(skill, true);
                    myContainer.updateCalculables();
                }
            }
        });
        rankComponent.addMinusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Skill skill = skillCluster.getSkill();
                if (skill != null) {
                    myCharacter.updateSkill(skill, false);
                    myContainer.updateCalculables();
                }
            }
        });
        statBonusComponent = new MyTextComponent(statBonusX, skillY, width, height, Skill.DEFAULT_ID) {
            public void update() {
                Skill skill = skillCluster.getSkill();
                if (skill != null) {
                    getTextField().setText(Integer.toString(myCharacter.getAttributeBonusValue(skill.
                            getKeyAttribute())));
                }
            }
        };
        totalComponent = new MyTextComponent(totalX, skillY, width, height, Skill.DEFAULT_ID) {
            public void update() {
                Skill skill = skillCluster.getSkill();
                if (skill != null) {
                    getTextField().setText(Integer.toString(myCharacter.getSkillValue(skill) + myCharacter.getAttributeBonusValue(skill.
                            getKeyAttribute())));
                }
            }
        };
        typeComponent = new MyTextComponent(typeX, skillY, width, height, Skill.DEFAULT_ID) {
            public void update() {
                Skill skill = skillCluster.getSkill();
                if (skill != null) {
                    getTextField().setText(skill.getMentalToPhysicalRatio() + "/" + (10 - skill.getMentalToPhysicalRatio()));
                }
            }
        };
        typeComponent.getTextField().setFont(new Font("Dialog", Font.PLAIN, 28));
    }

    @Override
    public void update() {
        skillCluster.update();
        statComponent.update();
        rankComponent.update();
        statBonusComponent.update();
//        miscComponent.update();
        totalComponent.update();
        typeComponent.update();
    }

    @Override
    public void attach(ScrollableComponent container) {
        myContainer = container;
        typeComponent.attach(container);
        skillCluster.attach(container);
        statComponent.attach(container);
        rankComponent.attach(container);
        statBonusComponent.attach(container);
//        miscComponent.attach(container);
        totalComponent.attach(container);
    }
}
