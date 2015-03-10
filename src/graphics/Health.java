package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Health extends JPanel {
	private final int R = 40;
	private int[] health = new int[] { 4, 3, 2, 1 };
	private int[] baseHealth = new int[] { 4, 3, 2, 1 };
	private int[] healthValues = new int[] { 4, 3, 2, 1 };

	Health(int vit) {
		setVitality(vit);
		setBackground(Color.WHITE);
		setFont(Constants.SMALL_FONT);
	}

	public void setVitality(int vit) {
		System.out.println(vit);
		for (int i = 0; i < health.length; i++) {
			//TODO: fix formula
			health[i] = baseHealth[i] + (vit + baseHealth[i] - 11 )/4;
			healthValues[i] = i * 5 + vit - 10;
		}
		healthValues[0] = 1; 
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
	}

}
