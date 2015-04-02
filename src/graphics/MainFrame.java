package graphics;

import mechanics.SkillBase;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import mechanics.character.GameCharacter;
import mechanics.character.Gender;
import mechanics.character.TempStats;
import mechanics.races.Brajagrah;

public class MainFrame extends JFrame implements Serializable {
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
		final ScrollableComponent panel = new ScrollableComponent(icon);
		JScrollPane pictureScrollPane = new JScrollPane(panel);
		pictureScrollPane.setPreferredSize(new Dimension(300, 250));
		add(pictureScrollPane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem resetItem = new JMenuItem("Reset(nie)");
		fileMenu.add(resetItem);

		JMenuItem saveItem = new JMenuItem("Save as image");
		fileMenu.add(saveItem);
		
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ee) {
				panel.saveAsImage();
			}
		});

		JMenuItem saveAsXMLItem = new JMenuItem("Save as XML");
		fileMenu.add(saveAsXMLItem);

		saveAsXMLItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ee) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						String name = file.getName();
						if (name.lastIndexOf('.') == -1 || !name.substring(name.lastIndexOf('.')).equalsIgnoreCase("xml")) {
							file = new File(file.toString() + ".xml");
						}
						XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
						e.writeObject(new GameCharacter("John Doe", Gender.FEMALE, 201, 170, "bald", "white", 555, 0,
								30, false, new Brajagrah(), 2, 5, new TempStats(), SkillBase.ATARAX_BASE));
						e.close();
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		JMenuItem loadItem = new JMenuItem("Load charactrer");
		fileMenu.add(loadItem);
		loadItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ee) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
						GameCharacter result = (GameCharacter) d.readObject();
						// TODO: add file compatibility check and messaging in case of exception
						d.close();
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		JMenuItem questionItem = new JMenuItem("Ask a question");
		fileMenu.add(questionItem);
		questionItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Mental pool", "Physical pool", "Cancel" };
				String message = "Where are you want to put new points?";
				String title = "We are getting retard here!";
				System.out.println(JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[2]));
			}
		});
		pack();
		setVisible(true);
	}

	public class PersistentTime implements Serializable {
		private int time;

		public PersistentTime() {
			time = 12;
		}

		public int getTime() {
			return time;
		}
	}
}
