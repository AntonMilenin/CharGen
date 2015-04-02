package graphics.textComponent.attribute;

import graphics.ScrollableComponent;
import graphics.textComponent.NumberCluster;
import mechanics.Attribute;
import mechanics.character.GameCharacter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Рома
 * Date: 16.03.2015
 * Time: 23:21
 */
public class AquiredAttributeNumberCluster extends NumberCluster {
    private final Attribute attribute;
    private final GameCharacter gameCharacter;

    public AquiredAttributeNumberCluster(int x, int y, int width, int height,
                                         final GameCharacter character, final Attribute attribute) {
        super(x, y, width, height, "0");
        this.gameCharacter = character;
        this.attribute = attribute;
        addPlusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.updateAquiredAttribute(attribute, true);
                container.updateCalculables();
            }
        });

        addMinusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.updateAquiredAttribute(attribute, false);
                container.updateCalculables();
            }
        });
    }

    @Override
    public void update() {
        this.textField.setText(gameCharacter.getAquiredAttribute(attribute));
    }
}
