package graphics.textComponent.attribute;

import graphics.ScrollableComponent;
import graphics.textComponent.MyTextComponent;
import graphics.textComponent.MyUpdatable;
import graphics.textComponent.NumberCluster;
import mechanics.Attribute;
import mechanics.character.GameCharacter;

import java.awt.*;

/**
 * User: Рома
 * Date: 31.03.2015
 * Time: 20:55
 */
public class AttributeElement implements MyUpdatable {

    private final NumberCluster aquiredCluster;
    private final NumberCluster innateCluster;
    private final MyTextComponent baseCluster;

    private final GameCharacter myCharacter;
    private final Attribute myAttribute;

    public AttributeElement(int attributeY, int innateX, int aquiredX, int baseX, int width, int height, Attribute attribute, GameCharacter character) {
        myCharacter = character;
        myAttribute = attribute;
        innateCluster = new InnateAttributeNumberCluster(innateX, attributeY, width, height, myCharacter, myAttribute);
        aquiredCluster = new AquiredAttributeNumberCluster(aquiredX, attributeY, width, height, myCharacter, myAttribute);
        baseCluster = new NumberCluster(baseX, attributeY, width, height, myCharacter.getAttributeBaseValue(attribute), false) {
            @Override
            public void update() {
                this.getTextField().setText(myCharacter.getAttributeBaseValue(myAttribute));
            }
        };
    }

    @Override
    public void update() {
        aquiredCluster.update();
        innateCluster.update();
        baseCluster.update();
    }

    @Override
    public void attach(ScrollableComponent container) {
        aquiredCluster.attach(container);
        innateCluster.attach(container);
        baseCluster.attach(container);
    }
}
