package Main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GuiSwing {
	Menu menu;

	GuiSwing() {
		menu = new Menu();
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
}
