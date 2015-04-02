package graphics.textComponent;
import graphics.Constants;
import graphics.ScrollableComponent;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class MyTextComponent implements MyUpdatable {
	protected Rectangle border;
	protected JTextField textField;
	protected ScrollableComponent container;

    public MyTextComponent(int x, int y, int width, int height, String value, boolean isFocusable) {
        border = new Rectangle(x, y, width, height);
        textField = new BorderlessTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(Constants.FONT);
        textField.setText(value);
        textField.setFocusable(isFocusable);
    }

	public MyTextComponent(int x, int y, int width, int height, String value) {
		this(x, y, width, height, value, true);
	}
			
	public void attach(ScrollableComponent container){
		this.container = container;
		container.add(textField);
	}

    /**
     * Updates values of the component to comply with underlying source of data (for example,
     * {@link mechanics.character.GameCharacter GameCharacter})
     */
    public void update() {}
	
	public JTextField getTextField(){
		return textField;
	}
}
