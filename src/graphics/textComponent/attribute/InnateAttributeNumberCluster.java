package graphics.textComponent.attribute;

import graphics.textComponent.NumberCluster;
import mechanics.Attribute;
import mechanics.character.GameCharacter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Рома
 * Date: 16.03.2015
 * Time: 22:57
 */
public class InnateAttributeNumberCluster extends NumberCluster {
    private final Attribute attribute;
    private final GameCharacter gameCharacter;

    public InnateAttributeNumberCluster(int x, int y, int width, int height,
                                        final GameCharacter character, final Attribute attribute) {
        super(x, y, width, height, character.getInnateAttributeValue(attribute));
        this.attribute = attribute;
        this.gameCharacter = character;
        addPlusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.updateInnateAttribute(attribute, true);
                container.updateCalculables();
            }
        });

        addMinusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.updateInnateAttribute(attribute, false);
                container.updateCalculables();
            }
        });
    }

    @Override
    public void update() {
        this.textField.setText(gameCharacter.getInnateAttributeValue(attribute));
    }
}
