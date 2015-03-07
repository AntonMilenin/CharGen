import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class ChoiceCluster extends MyTextComponent {
	JComboBox choice;
	boolean showed = false;

	ChoiceCluster(int x, int y, int width, int height, String value) {
		super(x, y, width, height, value);
		System.out.println(x);
		System.out.println(y);
		System.out.println(width);
		System.out.println(height);
		textField.setVisible(false);
		choice = new JComboBox();
		choice.setBounds(x, y, width, height);
		choice.setForeground(Color.BLACK);
		choice.setFont(Constants.FONT);
		choice.setBackground(Color.WHITE);
		choice.setVisible(true);
		choice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				textField.setText((String) e.getItem());

			}
		});
		choice.addItem(value);
	}

	public void add(String value) {
		choice.addItem(value);
	}

	private void showChoice() {
		showed = true;
		choice.setVisible(true);
		textField.setVisible(false);
	}

	private void hideChoice() {
		showed = false;
		choice.setVisible(false);
		textField.setVisible(true);
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		choice.setBounds(border.x + dx, border.y + dy, border.width, border.height);
	}

	@Override
	protected void repaint() {
		super.repaint();
		choice.repaint();
	}

	@Override
	public void attach(ImagePanel container) {
		super.attach(container);
		container.add(choice);
	}
	
	public void setFont(Font font){
		choice.setFont(font);		
	}
}
