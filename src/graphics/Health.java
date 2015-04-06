package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import mechanics.HealthGenerator;

public class Health extends JPanel {
	private final int R = 40;
	private int[] health = new int[] { 4, 3, 2, 1 };
	private int[] healthValues = new int[] { 1, 5, 10, 15 };
	
	Health(int vit) {
		setVitality(vit);
		setBackground(Color.WHITE);
		setFont(Constants.SMALL_FONT);
	}

	public void setVitality(int vit) {
		health = HealthGenerator.generateHealthTriangle(vit);
		healthValues = HealthGenerator.generateHealthValues(vit);
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int x = 0;
		int y = -10;
		for (int i = 0; i < health.length; i++) {
			g.drawString("" + healthValues[i], 15 + ((int) (Math.sqrt(3) * R * i / 2)), y + 3 + (i + 1) * R / 2);
			for (int j = 0; j < health[i]; j++) {
				g.drawOval(x + ((int) (Math.sqrt(3) * R * i / 2)), y + 30 + (2 * j + i) * R / 2, R, R);
			}
		}
		g.drawString("" + healthValues[4], 15 + ((int) (Math.sqrt(3) * R * 2)), y - 3 + 6 * R / 2);
	}

}
