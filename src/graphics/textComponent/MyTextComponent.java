package graphics.textComponent;
import graphics.Constants;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class MyTextComponent {
	protected Rectangle border;
	protected JTextField textField;
	protected JLabel container;

	public MyTextComponent(int x, int y, int width, int height, String value) {
		border = new Rectangle(x, y, width, height);
		textField = new BorderlessTextField();
		textField.setBounds(x, y, width, height);
		textField.setFont(Constants.FONT);
		textField.setText(value);
	}	
			
	public void attach(JLabel container){
		this.container = container;
		container.add(textField);
	};
	
	public JTextField getTextField(){
		return textField;
	}
}
