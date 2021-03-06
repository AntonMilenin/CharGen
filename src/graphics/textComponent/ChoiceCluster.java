package graphics.textComponent;

import graphics.Constants;
import graphics.ScrollableComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class ChoiceCluster extends MyTextComponent {
	protected JComboBox<String> choice;
	boolean showed = false;

	public ChoiceCluster(int x, int y, int width, int height, String value) {
		super(x, y, width, height, value);
		textField.setVisible(false);
		choice = new JComboBox<>();
		choice.setBounds(x, y, width, height);
		choice.setForeground(Color.BLACK);
		choice.setFont(Constants.FONT);
		choice.setBackground(Color.WHITE);
		choice.setRenderer(new MyComboBoxRenderer(choice));
		choice.setVisible(true);
		choice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				textField.setText((String) e.getItem());
				choice.setToolTipText((String) e.getItem());
			}
		});
		choice.addItem(value);
	}

    public void update() {
        getTextField().setText((String)choice.getSelectedItem());
    }

	public void add(String value) {
		choice.addItem(value);
	}

	public void showChoice() {
		showed = true;
		choice.setVisible(true);
		textField.setVisible(false);
	}

	public void hideChoice() {
		showed = false;
		choice.setVisible(false);
		textField.setVisible(true);
	}

	@Override
	public void attach(ScrollableComponent container) {
		super.attach(container);
		container.add(choice);
	}

	public void setFont(Font font) {
		choice.setFont(font);
	}
}
