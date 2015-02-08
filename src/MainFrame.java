import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

public class MainFrame extends JFrame {
	static JScrollBar horizontal, vertical;
	ImagePanel panel = new ImagePanel();

	public static void main(String[] args) {

		DisplayMode displayMode;

		if (args.length == 3) {
			displayMode = new DisplayMode(Integer.parseInt(args[0]),
					Integer.parseInt(args[1]), Integer.parseInt(args[2]),
					DisplayMode.REFRESH_RATE_UNKNOWN);
		} else {
			displayMode = new DisplayMode(1280, 1024, 16,
					DisplayMode.REFRESH_RATE_UNKNOWN);
		}

		MainFrame test = new MainFrame();
		test.run(displayMode);
	}

	public void run(DisplayMode displayMode) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBounds(0, 0, 400, 300);
		setLayout(new BorderLayout());
		panel.run();
		add(panel, BorderLayout.CENTER);
		add(horizontal = new JScrollBar(Scrollbar.HORIZONTAL, 0,
				Constants.WIDTH / 10, 0, Constants.WIDTH), BorderLayout.SOUTH);
		add(vertical = new JScrollBar(Scrollbar.VERTICAL, 0,
				Constants.HEIGHT / 10, 0, Constants.HEIGHT), BorderLayout.EAST);
		horizontal.setUnitIncrement(10);
		horizontal.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				panel.move(-e.getValue(), -vertical.getValue());
			}
		});
		vertical.setUnitIncrement(10);
		vertical.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				panel.move(-horizontal.getValue(), -e.getValue());
			}
		});
		
		this.addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				  int notches = e.getWheelRotation();
				  vertical.setValue(vertical.getValue() + notches*30);
				
			}
			
		});

		setVisible(true);
	}

	@Override
	public void validate() {
		super.validate();
		int hor = Constants.WIDTH - panel.getWidth();
		horizontal.setValues(horizontal.getValue(), hor / 10, 0, hor);
		int ver = Constants.HEIGHT - panel.getHeight();
		vertical.setValues(vertical.getValue(), ver / 10, 0, ver);
	}

}
