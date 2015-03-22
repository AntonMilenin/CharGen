package graphics;

import graphics.textComponent.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ScrollableComponent extends JLabel implements Scrollable, MouseMotionListener {
	int maxUnitIncrement = 10;
	BufferedImage image;
	int counter = 0;

	private HashMap<String, MyTextComponent> clusters = new HashMap<>();

	ScrollableComponent(ImageIcon image) {
		super(image);
		setAutoscrolls(true); // enable synthetic drag events
		addMouseMotionListener(this); // handle mouse drags
		addTextFields();
		try {
			this.image = ImageIO.read(ScrollableComponent.class.getResourceAsStream(Constants.IMAGE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		final Health health = new Health(10);
		health.setBounds(1033, 495, 500, 280);
		this.add(health);

		final JTextField vit = clusters.get(Constants.VITALITY).getTextField();
		vit.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}

			public void removeUpdate(DocumentEvent e) {
			}

			public void insertUpdate(DocumentEvent e) {
				health.setVitality(Integer.parseInt(vit.getText()));
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				// findPoly(e.getX(), e.getY());
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
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		scrollRectToVisible(r);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		int currentPosition = 0;
		if (orientation == SwingConstants.HORIZONTAL) {
			currentPosition = visibleRect.x;
		} else {
			currentPosition = visibleRect.y;
		}

		// Return the number of pixels between currentPosition
		// and the nearest tick mark in the indicated direction.
		if (direction < 0) {
			int newPosition = currentPosition - (currentPosition / maxUnitIncrement) * maxUnitIncrement;
			return (newPosition == 0) ? maxUnitIncrement : newPosition;
		} else {
			return ((currentPosition / maxUnitIncrement) + 1) * maxUnitIncrement - currentPosition;
		}
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		if (orientation == SwingConstants.HORIZONTAL) {
			return visibleRect.width - maxUnitIncrement;
		} else {
			return visibleRect.height - maxUnitIncrement;
		}
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	private void addTextFields() {
		final int charLeftBound = 100;
		final int charRightBound = 1590;
		final int charBotBound = 152;
		final int playerLeftBound = 845;
		final int speciesBotBound = 240;
		final int raceLeftBound = 474;
		final int skillPointsLeftBound = 660;
		final int physicalPoolLeftBound = 1095;
		final int mentalPoolLeftBound = 1342;
		final int statLeftBound = 338;

		MyTextComponent name = new MyTextComponent(charLeftBound, charBotBound, playerLeftBound - charLeftBound, 55,
				"Дребон");

		clusters.put(Constants.characterNameId, name);
		MyTextComponent playerName = new MyTextComponent(playerLeftBound, charBotBound, charRightBound - charBotBound,
				55, "Potato");

		clusters.put(Constants.playerNameId, playerName);
		ChoiceCluster species = new ChoiceCluster(charLeftBound, speciesBotBound, raceLeftBound - charLeftBound, 55,
				"---");
		clusters.put(Constants.speciesId, species);
		species.add("Drebon");
		species.add("Дребон");

		ChoiceCluster race = new ChoiceCluster(raceLeftBound, speciesBotBound, skillPointsLeftBound - raceLeftBound,
				55, "---");
		clusters.put(Constants.raceId, race);
		race.add("Drebon");
		race.add("Дребон");

		MyTextComponent skillPoints = new MyTextComponent(skillPointsLeftBound, speciesBotBound, playerLeftBound
				- skillPointsLeftBound, 55, "650");
		skillPoints.attach(this);
		clusters.put(Constants.skillPointsId, skillPoints);

		MyTextComponent attributePoints = new MyTextComponent(playerLeftBound, speciesBotBound, physicalPoolLeftBound
				- playerLeftBound, 55, "3");
		attributePoints.attach(this);
		clusters.put(Constants.attributePointsId, attributePoints);

		MyTextComponent physicalPool = new MyTextComponent(physicalPoolLeftBound, speciesBotBound, mentalPoolLeftBound
				- physicalPoolLeftBound, 55, "0");
		physicalPool.attach(this);
		clusters.put(Constants.physicalPoolId, physicalPool);

		MyTextComponent mentalPool = new MyTextComponent(mentalPoolLeftBound, speciesBotBound, charRightBound
				- mentalPoolLeftBound, 55, "0");
		mentalPool.attach(this);
		clusters.put(Constants.mentalPoolId, mentalPool);

		clusters.put(Constants.STRENGTH, new NumberCluster(statLeftBound, 532, 76, 46, "10"));
		clusters.put(Constants.DEXTERETY, new NumberCluster(statLeftBound, 581, 76, 46, "10"));
		clusters.put(Constants.SPEED, new NumberCluster(statLeftBound, 630, 76, 46, "10"));
		clusters.put(Constants.VITALITY, new NumberCluster(statLeftBound, 678, 76, 45, "10"));
		clusters.put(Constants.PERCEPTION, new NumberCluster(statLeftBound, 727, 76, 46, "10"));
		clusters.put(Constants.WILLPOWER, new NumberCluster(statLeftBound, 776, 76, 46, "10"));
		clusters.put(Constants.INTELLIGENCE, new NumberCluster(statLeftBound, 824, 76, 45, "10"));
		clusters.put(Constants.CHARISMA, new NumberCluster(statLeftBound, 873, 76, 46, "10"));

		int[] skillY = new int[] { 1040, 1097, 1153, 1210, 1266, 1323, 1379, 1436, 1492, 1549, 1606, 1662, 1719, 1775,
				1832, 1888, 1945, 2001, 2058, 2114, 2171 };
		for (int i = 0; i < skillY.length; i++) {

			clusters.put(Constants.SKILL + i, new ChoiceCluster(100, skillY[i], 392, 54, "acrobatics"));

			clusters.put(Constants.SKILL + i + Constants.STAT, new ChoiceCluster(495, skillY[i], 76, 54,
					Constants.DEXTERETY.substring(0, 3)));
			((ChoiceCluster) clusters.get(Constants.SKILL + i + Constants.STAT)).setFont(new Font("Dialog", Font.PLAIN,
					28));
			clusters.put(Constants.SKILL + i + Constants.RANK, new NumberCluster(574, skillY[i], 75, 53, "10"));

			clusters.put(Constants.SKILL + i + Constants.STAT_BONUS,
					new MyUnfocusableText(653, skillY[i], 75, 53, "10"));
		}

		for (MyTextComponent c : clusters.values()) {
			c.attach(this);
		}
	}

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

	private int[] findPoly(int x, int y) {
		int myX = x;
		int myY = y;// legacy from scaling
		System.out.println("" + myX + " " + myY);
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
		System.out.println("clusters.put(\"???\" ,new NumberCluster(" + (poly[0] + 1) + ", " + (poly[1] + 1) + ", "
				+ (poly[2] - 2) + ", " + (poly[3] - 2) + ", \"10\"));");
		NumberCluster cluster = new NumberCluster(poly[0] + 1, poly[1] + 1, poly[2] - 2, poly[3] - 2, "10");
		clusters.put("" + counter++, cluster);

		cluster.attach(this);
		return poly;
	}

	public void saveAsImage() {
		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		for (MyTextComponent c: clusters.values()){
			if (c.getClass() == ChoiceCluster.class) {
				((ChoiceCluster) c).hideChoice(); 
			}
		}
		printAll(g2d);
		for (MyTextComponent c: clusters.values()){
			if (c.getClass() == ChoiceCluster.class) {
				((ChoiceCluster) c).showChoice(); 
			}
		}
		g2d.dispose();
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String name = file.getName();
			if (name.lastIndexOf('.') == -1 || !name.substring(name.lastIndexOf('.')).equalsIgnoreCase("png")) {
				file = new File(file.toString() + ".png");
			}
			try {
				ImageIO.write(img, "png", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
