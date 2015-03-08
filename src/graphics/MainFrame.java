package graphics;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	public static void main(String[] args) {
		MainFrame test = new MainFrame();
		test.run();
	}

	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		java.net.URL imgURL = MainFrame.class.getResource(Constants.IMAGE_PATH);
		if (imgURL == null) {
			System.out.println("broken link to file");
			System.exit(1);
		} else {
			System.out.println("URL generated");
		}
		ImageIcon icon = new ImageIcon(imgURL);
		ScrollableComponent panel = new ScrollableComponent(icon);
		JScrollPane pictureScrollPane = new JScrollPane(panel);
		pictureScrollPane.setPreferredSize(new Dimension(300, 250));
		add(pictureScrollPane);
		pack();
		setVisible(true);
	}
}
