import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImagePanel extends JPanel {
	private final int R = 50;
	private int xDiff = 0;
	private int yDiff = 0;

	double scaleFactor = 1;

	int counter = 0;

	private HashMap<String, MyTextComponent> clusters = new HashMap<>();
	private int[] health = new int[] { 4, 3, 2, 1 };

	private BufferedImage image;
	private boolean imagesLoaded;

	private boolean chackBrightness(int x, int y) {
		try {
			int color = image.getRGB(x, y);

			int red = (color >>> 16) & 0xFF;
			int green = (color >>> 8) & 0xFF;
			int blue = (color >>> 0) & 0xFF;

			float luminance = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
			return (luminance <= 0.8f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	public void move(int x, int y) {
		xDiff = x;
		yDiff = y;
		for (MyTextComponent cluster : clusters.values()) {
			cluster.move(x, y);
		}
		repaint();
	}

	private int[] findPoly(int x, int y) {
		int myX = (int) (x / scaleFactor) - xDiff;
		int myY = (int) (y / scaleFactor) - yDiff;
		System.out.println("" + myX + " " + myY + " " + xDiff + " " + yDiff);
		int[] poly = new int[4];
		int i = myX;
		while (--i > 0) {
			if (chackBrightness(i, myY)) {
				break;
			}
		}
		poly[0] = i;
		i = myY;
		while (--i > 0) {
			if (chackBrightness(myX, i)) {
				break;
			}
		}
		poly[1] = i;
		i = myX;
		while (++i < image.getWidth()) {
			if (chackBrightness(i, myY)) {
				break;
			}
		}
		poly[2] = i - poly[0];
		i = myY;
		while (++i < image.getHeight()) {
			if (chackBrightness(myX, i)) {
				break;
			}
		}
		poly[3] = i - poly[1];
		System.out.println("clusters.put(\"???\" ,new NumberCluster("
				+ (poly[0] + 1) + ", " + (poly[1] + 1) + ", " + (poly[2] - 2)
				+ ", " + (poly[3] - 2) + ", \"10\"));");
		NumberCluster cluster = new NumberCluster(poly[0] + 1, poly[1] + 1,
				poly[2] - 2, poly[3] - 2, "10");
		clusters.put("" + counter++, cluster);

		cluster.attach(this);
		return poly;
	}

	public void run() {
		super.setBounds(0, 0, 800, 600);
		super.setLayout(null);

		try {
			image = ImageIO.read(ImagePanel.class
					.getResourceAsStream("/images/atarax_sheet_2_1.png"));
		    Graphics2D bGr = image.createGraphics();
		    bGr.drawImage(image.getScaledInstance(Constants.WIDTH, Constants.HEIGHT - 120, Image.SCALE_SMOOTH), 0, 0, null);
		    bGr.dispose();
			imagesLoaded = true;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
//				findPoly(e.getX(), e.getY());
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
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setVisible(true);
		addTextFields();
	}

	@Override
	public void paint(Graphics g) {if (g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;

			g2.setRenderingHint(
			    RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}
		if (imagesLoaded) {

			g.drawImage(
					image, xDiff, yDiff,
					null);
		} else {
			g.drawString("Image didn't load", 5, Constants.FONT_SIZE);
		}
		drawHealth(g);
		Rectangle bounds = this.getBounds();
		Rectangle realBounds = new Rectangle(bounds.x - xDiff, bounds.y - yDiff,bounds.width, bounds.height );
		System.out.println(realBounds);
		for (MyTextComponent c : clusters.values()) {
			c.repaint(realBounds);
		}
	}

	private void addTextFields() {
		MyTextComponent name = new MyTextComponent(100, 150, 717, 60, "Дребон");
		clusters.put("characterName", name);

		MyTextComponent playerName = new MyTextComponent(817, 150, 376, 60,
				"Дребон");
		clusters.put("playerName", playerName);

		ChoiceCluster species = new ChoiceCluster(100, 240, 360, 60, "---");
		clusters.put("species", species);
		species.add("Drebon");
		species.add("Дребон");
		
		ChoiceCluster race = new ChoiceCluster(460, 240, 360, 60, "---");
		clusters.put("race", race);
		race.add("Drebon");
		race.add("Дребон");

		MyTextComponent exp = new MyTextComponent(820, 240, 360, 60, "650");
		exp.attach(this);		
		clusters.put("exp", exp);		

		ChoiceCluster magic = new ChoiceCluster(100, 327, 180, 60, "None");
		magic.add("Arcane");
		magic.add("Elemental");
		clusters.put("magic", magic);		

		NumberCluster age = new NumberCluster(280, 327, 180, 60, "30");
		clusters.put("age", age);

		ChoiceCluster gender = new ChoiceCluster(460, 327, 180, 60, "---");
		gender.add("Male");
		gender.add("Female");
		clusters.put("gender", gender);		
		
		clusters.put(Constants.STRENGTH, new NumberCluster(321, 540, 76, 46, "10"));
		clusters.put(Constants.DEXTERETY, new NumberCluster(321, 588, 76, 46, "10"));
		clusters.put(Constants.SPEED, new NumberCluster(321, 637, 76, 46, "10"));
		clusters.put(Constants.VITALITY, new NumberCluster(321, 686, 76, 45, "10"));
		clusters.put(Constants.PERCEPTION, new NumberCluster(321, 734, 76, 46, "10"));
		clusters.put(Constants.WILLPOWER, new NumberCluster(321, 783, 76, 46, "10"));
		clusters.put(Constants.INTELLIGENCE, new NumberCluster(321, 832, 76, 45, "10"));
		clusters.put(Constants.CHARISMA, new NumberCluster(321, 880, 76, 46, "10"));

		int[] skillY = new int[] { 1048, 1104, 1161, 1217, 1274, 1330, 1387,
				1444, 1500, 1557, 1613, 1670, 1726, 1783, 1839, 1896, 1952,
				2009, 2065, 2122, 2178 };
		for (int i = 0; i < skillY.length; i++) {

			clusters.put(Constants.SKILL + i, new ChoiceCluster(100, skillY[i], 376,
					53, "acrobatics"));

			clusters.put(Constants.SKILL + i + Constants.STAT, new ChoiceCluster(478,
					skillY[i], 69, 53, Constants.DEXTERETY.substring(0, 3)));	
			((ChoiceCluster) clusters.get(Constants.SKILL + i + Constants.STAT)).setFont(new Font("Dialog", Font.PLAIN, 28));
			clusters.put(Constants.SKILL + i + Constants.RANK, new NumberCluster(549,
					skillY[i], 69, 53, "10"));

			clusters.put(Constants.SKILL + i + Constants.STAT_BONUS, new MyUnfocusableText(620,
					skillY[i], 68, 53, "10"));
		}

		for (MyTextComponent c : clusters.values()) {
			c.attach(this);
		}

		for (int i = 0; i <health.length; i++) {
			String value;
			if (i == 0) {
				value = "1";
			} else {
				value = i * 5 + "";
			}
			MyUnfocusableText text = new MyUnfocusableText(1020 + R/2 + ((int) (Math.sqrt(3)*R*i/2)) + xDiff, 533 + (i - 1) * R/2 + yDiff, 25, 25, value, true);
			text.getTextField().setHorizontalAlignment(JTextField.CENTER);
			text.attach(this);
			clusters.put("lifeText" + i, text);
		}

	}

	public void drawHealth(Graphics g) {
		int x = 1032;
		int y = 533;
		for (int i = 0; i <health.length; i++) {
			for (int j = 0; j < health[i]; j++ ) {
				g.drawOval(x + ((int) (Math.sqrt(3)*R*i/2)) + xDiff, y + (2*j + i) * R/2 + yDiff, R, R);
			}
		}

	}
}
