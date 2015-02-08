import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class NumberCluster extends MyTextComponent {
	private JButton plusButton, minusButton;

	NumberCluster(int x, int y, int width, int height, String value) {
		super(x, y, width, height, value);
		textField.setHorizontalAlignment(JTextField.CENTER);
		int buttonLength = height / 2;
		MouseListener listener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				showButtons();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hideButtons();
			}
		};
		textField.addMouseListener(listener);
		textField.setText(value);
		plusButton = new JButton();
		plusButton.setText("+");
		plusButton.setMargin(new Insets(0, 0, 0, 0));
		plusButton.setFont(new Font("Dialog", Font.PLAIN,
				Constants.FONT_SIZE / 2));
		plusButton.setVisible(false);
		plusButton.setBackground(Color.white);
		plusButton.setBounds(x + width - buttonLength, y, buttonLength,
				buttonLength);
		plusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(String.valueOf(Integer.parseInt(textField
						.getText()) + 1));
			}
		});
		plusButton.addMouseListener(listener);
		minusButton = new JButton();
		minusButton.setText("-");
		minusButton.setVisible(false);
		minusButton.setBounds(x + width - buttonLength, y + buttonLength,
				buttonLength, buttonLength);
		minusButton.setBackground(Color.white);
		minusButton.setMargin(new Insets(0, 0, 0, 0));
		minusButton.setFont(new Font("Dialog", Font.PLAIN,
				Constants.FONT_SIZE / 2));

		minusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(String.valueOf(Integer.parseInt(textField
						.getText()) - 1));
			}
		});
		minusButton.addMouseListener(listener);

	}

	private void hideButtons() {
		plusButton.setVisible(false);
		minusButton.setVisible(false);
		textField.setBounds(x + dx, y + dy, width, height);
	}

	private void showButtons() {		
		plusButton.setVisible(true);
		minusButton.setVisible(true);
		textField.setBounds(x + dx, y + dy, width - height / 2, height);
	}

	public void attach(Container container) {
		super.attach(container);
		container.add(plusButton);
		container.add(minusButton);
	}

	public void repaint() {
		super.repaint();
		plusButton.repaint();
		minusButton.repaint();
	}

	public void move(int dx, int dy) {
		super.move(dx, dy);
		int buttonLength = height / 2;
		plusButton.setBounds(x + width - buttonLength + dx, y + dy,
				buttonLength, buttonLength);
		minusButton.setBounds(x + width - buttonLength + dx, y + buttonLength
				+ dy, buttonLength, buttonLength);
	}
}
