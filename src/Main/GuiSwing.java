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

public class GuiSwing {
	Menu menu;
	Clip play;
	GuiSwing() {
		menu = new Menu();
	}
	public void Gamesound() throws Exception {
		File file = new File("music//k19.wav");
		// ���o�����Ҫ���J��y
		AudioInputStream sound = AudioSystem.getAudioInputStream(file);
		// �N���o����J��d�b�J�O����Clip
		DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		// ���o���w��Clip�����
		play = (Clip) AudioSystem.getLine(info);
		play.open(sound);
		play.start();
		play.loop(-1);
	}
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
	class ButtonNetwork extends JButton {
		Image im;

		public ButtonNetwork(Image im) {
			this.im = im;
			this.setOpaque(true);
		}

		// Draw the back ground.
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
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
