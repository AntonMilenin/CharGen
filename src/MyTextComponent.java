import java.awt.Container;

import javax.swing.JTextField;


public class MyTextComponent {
	protected int x, y, width, height, dx, dy;
	protected JTextField textField;

	MyTextComponent(int x, int y, int width, int height, String value) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		dx = 0;
		dy = 0;
		textField = new BorderlessTextField();
		textField.setBounds(x, y, width, height);
		textField.setFont(Constants.FONT);
		textField.setText(value);
	}	
	
	public void move(int dx, int dy) {
		this.dx = dx;
		this.dy= dy;
		textField.setBounds(x + dx, y + dy, width, height);		
	};
	
	public void repaint() {
		textField.repaint();
	};
	
	public void attach(Container container){
		container.add(textField);
	};
	
	public JTextField getTextField(){
		return textField;
	}
}
