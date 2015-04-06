package graphics.textComponent;
import graphics.Constants;
import graphics.ScrollableComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class NumberCluster extends MyTextComponent {
	private JButton plusButton, minusButton;

    public NumberCluster(int x, int y, int width, int height, String value) {
        this(x, y, width, height, value, true);
    }

    public NumberCluster(int x, int y, int width, int height, String value, boolean hasChangeButtons) {
        super(x, y, width, height, value);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setFocusable(false);
		int buttonLength = height / 2;
        textField.setText(value);
        plusButton = new JButton();
        plusButton.setText("+");
        plusButton.setMargin(new Insets(0, 0, 0, 0));
        plusButton.setFont(new Font("Dialog", Font.PLAIN,
                Constants.FONT_SIZE / 2));
        plusButton.setVisible(false);
        plusButton.setBackground(Color.white);
        plusButton.setBounds(x + width - buttonLength, y, buttonLength, buttonLength);
        minusButton = new JButton();
        minusButton.setText("-");
        minusButton.setVisible(false);
        minusButton.setBounds(x + width - buttonLength, y + buttonLength,
                buttonLength, buttonLength);
        minusButton.setBackground(Color.white);
        minusButton.setMargin(new Insets(0, 0, 0, 0));
        minusButton.setFont(new Font("Dialog", Font.PLAIN, Constants.FONT_SIZE / 2));
        if (hasChangeButtons) {
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
            plusButton.addMouseListener(listener);

            minusButton.addMouseListener(listener);
        } else {
            hideButtons();
        }
    }

	private void hideButtons() {
		plusButton.setVisible(false);
		minusButton.setVisible(false);
		textField.setBounds(border.x, border.y , border.width, border.height);
	}

	private void showButtons() {
		plusButton.setVisible(true);
		minusButton.setVisible(true);
		textField.setBounds(border.x , border.y, border.width - border.height / 2, border.height);
	}

    public void addPlusListener(ActionListener listener) {
        plusButton.addActionListener(listener);
    }

    public void addMinusListener(ActionListener listener) {
        minusButton.addActionListener(listener);
    }

	@Override
	public void attach(ScrollableComponent container) {
		super.attach(container);
		container.add(plusButton);
		container.add(minusButton);
	}
}
