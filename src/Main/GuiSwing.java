package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**
 * GUI Component
 * setting
 * @author jack
 *
 */
public class GuiSwing {
	Menu menu;
	Clip play;
	GuiSwing() {
		menu = new Menu();
	}
	/*
	 * background Music player
	 */
	public void Gamesound() throws Exception {
		File file = new File("");
		//
		AudioInputStream sound = AudioSystem.getAudioInputStream(file);
		//
		DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		//
		play = (Clip) AudioSystem.getLine(info);
		play.open(sound);
		play.start();
		play.loop(-1);
	}
	/*
	 * TooLBar setting
	 *****************/
	class Menu extends JMenuBar {
		JMenu system = new JMenu("System");
		JMenuItem i1 = new JMenuItem("Save");
		JMenuItem i2 = new JMenuItem("Load");
		JMenuItem i3 = new JMenuItem("High Score");
		JMenuItem i4 = new JMenuItem("Exit");

		Menu() {
			system.add(i1);
			system.add(i2);
			system.addSeparator();
			system.add(i3);
			system.add(i4);
			add(system);
			setOpaque(false);
			setBackground(null);
			setVisible(true);
		}
	}
	/*
	 * BackGround setting
	 * game Frame
	 ********************/
	class BackgroundPanel extends JPanel {
		Image im;

		public BackgroundPanel(Image im) {
			this.im = im;
			this.setOpaque(true);
		}

		// Draw the back ground.
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	/*
	 * Button
	 * to choose Multiplayer mode
	 ****************************/
	class ButtonMulti extends JButton {
		Image im;

		public ButtonMulti(Image im) {
			this.im = im;
			this.setOpaque(true);
		}

		// Draw the back ground.
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	/*
	 *Button 
	 *to choose solo mode
	 ********************/
	class ButtonSolo extends JButton {
		Image im;

		public ButtonSolo(Image im) {
			this.im = im;
			this.setOpaque(true);
		}

		// Draw the back ground.
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	/*
	 * Button
	 * exit this game
	 ****************/
	class ButtonExit extends JButton {
		Image im;

		public ButtonExit(Image im) {
			this.im = im;
			this.setOpaque(true);
		}

		// Draw the back ground.
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
