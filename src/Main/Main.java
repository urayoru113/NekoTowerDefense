package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener {
	Toolkit tk = Toolkit.getDefaultToolkit();
	int screenSizeX = (int) tk.getScreenSize().getWidth();
	int screenSizeY = (int) tk.getScreenSize().getHeight();
	Clip play;
	//JButton Exit = new JButton(new ImageIcon(""));
	JButton Exit = new JButton("Exit");
	//JButton Multi = new JButton(new ImageIcon(""));
	JButton Multi = new JButton("MultiPlay");
	//JButton Room = new JButton(new ImageIcon(""));
	JButton Room = new JButton("CreateRoom");
	Menu menu = new Menu();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	public Main() {

		init(); // initialize
		// start();
		// multiplay();
	}

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

	private void init() {
		setTitle("Tower Denfense");
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSizeX, screenSizeY);
		setJMenuBar(menu);
		Room.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Room.setBounds(screenSizeX/2-125, 250, 250, 50);
		Room.addActionListener(this);
		Multi.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Multi.setBounds(screenSizeX/2-125, 330, 250, 50);
		Multi.addActionListener(this);
		Exit.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Exit.setBounds(screenSizeX/2-125, 410, 250, 50);
		Exit.addActionListener(this);
		add(Exit);
		add(Multi);
		add(Room);
		setVisible(true);
	}

	private void start() {
		// game panel
		tower tw = new tower();
		tw.setHp(1000);
		tw.setGold(100);

		JPanel pStart = new JPanel();
		pStart.setSize(screenSizeX, screenSizeY);
		add(pStart);
		pStart.setLayout(null);// cancel all set

		// my tower panel
		JPanel pMytower = new JPanel();
		pMytower.setBounds(screenSizeX / 10, screenSizeY * 1 / 3, 200, 500);
		pMytower.setBackground(Color.BLUE);
		pStart.add(pMytower);

		// emegy tower panel
		JPanel pEmeTower = new JPanel();
		pEmeTower.setBounds(screenSizeX * 8 / 10, screenSizeY * 1 / 3, 200, 500);
		pEmeTower.setBackground(Color.BLUE);
		pStart.add(pEmeTower);

		// my hp label
		JLabel jMyHp = new JLabel(tw.getHp() + " / 1000");
		jMyHp.setBounds(screenSizeX * 1 / 10, screenSizeY * 1 / 3 - 60, 200, 30);
		jMyHp.setBackground(Color.red);
		pStart.add(jMyHp);

	}

	public void multiplay() {
		Server S = new Server();/* open server */
		Client C = new Client();/* client connect server */
		S.waitClient();/* server accept client connect */
		C.tran();/* client send HI! to server */
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* wait 3 second */
		S.get();/* server get packet and print */
	}

	class tower {
		private int hp;
		int gold;
		int armLavel;
		int goldLavel;

		public void setHp(int hp) {
			this.hp = hp;
		}

		public int getHp() {
			return hp;
		}

		public void setGold(int gold) {
			this.gold = gold;
		}

		public int getGold() {
			return gold;
		}

		public void armLavel(int armLavel) {
			this.armLavel = armLavel;
		}

		public int getarmLavel() {
			return armLavel;
		}

		public void goldLavel(int goldLavel) {
			this.goldLavel = goldLavel;
		}

		public int getgoldLavel() {
			return goldLavel;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Exit) {
			dispose();
		}
	}
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

/*
 * BackGround setting game Frame
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