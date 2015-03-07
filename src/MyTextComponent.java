import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.JTextField;


public class MyTextComponent {
	protected Rectangle border;
	protected int dx, dy;
	protected JTextField textField;
	protected ImagePanel container;

	MyTextComponent(int x, int y, int width, int height, String value) {
		border = new Rectangle(x, y, width, height);
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
		textField.setBounds(border.x + dx, border.y + dy, border.width, border.height);		
	};
	
	public void repaint(Rectangle repaintArea){
		Rectangle realBounds = new Rectangle(border.x, border.y, border.width, border.height);
		if (realBounds.intersects(repaintArea)) repaint();
	};
	
	protected void repaint() {
		textField.repaint();
	};
	
	public void attach(ImagePanel container){
		this.container = container;
		container.add(textField);
	};
	
	public JTextField getTextField(){
		return textField;
	}
}
