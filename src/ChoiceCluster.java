import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChoiceCluster extends MyTextComponent {
	Choice choice;
	boolean showed = false;

	ChoiceCluster(int x, int y, int width, int height, String value) {
		super(x, y, width, height, value);
		System.out.println(x);
		System.out.println(y);
		System.out.println(width);
		System.out.println(height);
//		MouseListener listener = new MouseListener() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				if (!showed)
//					showChoice();
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// if (showed) hideChoice();
//			}
//		};
//		MouseListener listener0 = new MouseListener() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// if (!showed) showChoice();
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				if (showed)
//					hideChoice();
//			}
//		};
		textField.setVisible(false);
		choice = new Choice();
		choice.setBounds(x, y, width, height);
		choice.setForeground(Color.BLACK);
//		choice.addMouseListener(listener0);
		choice.setFont(Constants.FONT);
		choice.setVisible(true);
		choice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				textField.setText((String) e.getItem());

			}
		});
		choice.addItem(value);
//		textField.addMouseListener(listener);
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
		choice.setBounds(x + dx, y + dy, width, height);
	}

	@Override
	public void repaint() {
		super.repaint();
		choice.repaint();
	}

	@Override
	public void attach(Container container) {
		super.attach(container);
		container.add(choice);
	}
}
