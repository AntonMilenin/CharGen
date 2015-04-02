package graphics.textComponent;

import graphics.ScrollableComponent;

/**
 * User: Рома
 * Date: 30.03.2015
 * Time: 22:28
 */
public interface MyUpdatable {
    public void update();

    public void attach(ScrollableComponent container);
}