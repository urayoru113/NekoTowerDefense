package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextField;
import javax.swing.Timer;

public class Main extends JFrame implements ActionListener, KeyListener, MouseListener {
	Toolkit tk = Toolkit.getDefaultToolkit();
	int screenSizeX = (int) tk.getScreenSize().getWidth();
	int screenSizeY = (int) tk.getScreenSize().getHeight();
	int godMaxPositionX = 0, godMaxIndex = 30;
	int enemyMaxPositionX = 99999, enemyMaxIndex = 30;
	Color myFontColor = Color.blue;
	Color emeFontColor = Color.red;
	String myName = "A";
	String emeName = "B";
	BackgroundPanel pStart;
	ImageIcon myTowerImg = new ImageIcon("src//img//rev_1.png");
	ImageIcon backgroundimg = new ImageIcon("src//img//background.png");
	ImageIcon msgBox = new ImageIcon("src//img//kisakajpg.jpg");
	// JButton Exit = new JButton(new ImageIcon(""));
	JButton Exit = new JButton("Exit");
	// JButton Multi = new JButton(new ImageIcon(""));
	JButton Multi = new JButton("MultiPlay");
	// JButton Room = new JButton(new ImageIcon(""));
	JButton Room = new JButton("CreateRoom");

	// the soldier
	JButton neko;
	// the gold
	JButton goldLabel = new JButton("Gold");

	JLabel[] msgLabel = new JLabel[10];
	JLabel jMyTowerHp;
	JLabel jMyGold;
	JTextField inputText = new JTextField("");
	Menu menu = new Menu();
	Clip play;
	Image bufferImage;
	Arms God = new Arms();
	Arms enemy = new Arms();
	Timer msgCheck = new Timer(5, this);// set counter
	Timer anime = new Timer(5, this);// set counte
	Timer nekomove = new Timer(30, this);// set move time
	Timer collistion = new Timer(5, this);
	String[] strMsg = { "", "", "", "", "", "", "", "", "", "" };// communication
																	// message
	Server S;
	Client C;
	BackgroundPanel myTower, eneTower, kisaka;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	Main() {
		// initialize
		setTitle("Tower Denfense");
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSizeX, screenSizeY);
		setJMenuBar(menu);
		Room.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Room.setBounds(screenSizeX / 2 - 125, 250, 250, 50);
		Room.addActionListener(this);
		Multi.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Multi.setBounds(screenSizeX / 2 - 125, 330, 250, 50);
		Multi.addActionListener(this);
		Exit.setFont(new Font("SansSerif", Font.PLAIN, 18));
		Exit.setBounds(screenSizeX / 2 - 125, 410, 250, 50);
		Exit.addActionListener(this);
		add(Exit);
		add(Multi);
		add(Room);
		setVisible(true);
		start();
	}

	public void paintFight(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 100000, 100000);
		g.setColor(Color.RED);
		for (int i = 0; i < God.soldier.size(); i++)
			g.fillRect(0 + God.soldier.get(i).getPositionX(), 0, 20, 20);
		g.setColor(Color.BLACK);
		for (int i = 0; i < enemy.soldier.size(); i++)
			g.fillRect(enemy.soldier.get(i).getPositionX() - enemy.soldier.get(i).getWidth(), 0, 20, 20);
		g.setColor(Color.BLACK);
	}

	int i = 0;

	@Override
	public void update(Graphics g)// double buffer
	{
		bufferImage = createImage(screenSizeX / 10 * 6, screenSizeY * 12 / 17);
		Graphics gBuffer = bufferImage.getGraphics();
		if (gBuffer != null)
			paintFight(gBuffer);
		else
			paint(g);
		gBuffer.dispose();
		g.drawImage(bufferImage, screenSizeX / 5, 80, null);
	}

	void Gamesound() throws Exception {
		File file = new File("");
		AudioInputStream sound = AudioSystem.getAudioInputStream(file);
		DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		play = (Clip) AudioSystem.getLine(info);
		play.open(sound);
		play.start();
		play.loop(-1);
	}

	private void start() {
		// game panel
		pStart = new BackgroundPanel(backgroundimg.getImage(), screenSizeX, screenSizeY);
		pStart.setSize(screenSizeX, screenSizeY);
		pStart.setLayout(null);// cancel all set
		kisaka = new BackgroundPanel(msgBox.getImage(), screenSizeX * 1 / 4, screenSizeY * 22 / 9 / 12);
		kisaka.setBounds(screenSizeX * 8 / 11, screenSizeY * 13 / 18, screenSizeX / 4, screenSizeY * 22 / 9 / 12);
		// my tower panel
		myTower = new BackgroundPanel(myTowerImg.getImage(), 440 / 2, 451 / 2);
		if (!(myTowerImg.getImage() == null))
			System.out.println("fuxk");
		myTower.setBounds(screenSizeX / 20, screenSizeY * 1 / 3, 440 / 2, 451 / 2);
		// myTower.setBackground(Color.BLACK);
		// enemy tower panel
		eneTower = new BackgroundPanel(myTowerImg.getImage(), 440 / 2, 451 / 2);
		eneTower.setBounds(screenSizeX * 16 / 20, screenSizeY * 1 / 3, 440 / 2, 451 / 2);

		// my hp label
		jMyTowerHp = new JLabel(God.getHp() + " / 1000");
		jMyTowerHp.setBounds(screenSizeX * 1 / 10, screenSizeY * 1 / 3 - 60, 200, 30);
		jMyTowerHp.setBackground(Color.red);
		pStart.add(jMyTowerHp);

		// my gold label
		jMyGold = new JLabel("Gold: " + God.getGold());
		jMyGold.setBounds(screenSizeX * 1 / 10, screenSizeY * 1 / 3 - 120, 200, 30);
		jMyGold.setBackground(Color.red);
		pStart.add(jMyGold);
		// pStart.add(myTower);
		// pStart.add(eneTower);

		// make battle button
		neko = new JButton("Neko");
		neko.setBounds(0, screenSizeY * 7 / 9, screenSizeX * 1 / 9, screenSizeY * 2 / 9);
		neko.addActionListener(this);
		pStart.add(neko);
		// make a communication Label
		for (int i = 0; i < 10; i++) {
			msgLabel[i] = new JLabel(strMsg[i]);
			msgLabel[i].setBounds(screenSizeX * 8 / 11, screenSizeY * 13 / 18 + screenSizeY * 2 / 9 * i / 12,
					screenSizeX * 1 / 4, screenSizeY * 2 / 9 / 12);
			pStart.add(msgLabel[i]);
		}
		inputText.setBounds(screenSizeX * 8 / 11, screenSizeY * 7 / 9 + screenSizeY * 2 / 9 * 7 / 12,
				screenSizeX * 1 / 4, screenSizeY * 2 / 9 / 12);
		inputText.addKeyListener(this);
		pStart.add(kisaka);
		pStart.add(inputText);
	}

	/*
	 * butoon action event and socket connect (non-Javadoc)
	 *
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 ********************************************************************************/
	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == Exit) {
			dispose();
		}
		if (e.getSource() == Room) {
			this.setLayout(null);
			remove(Multi);
			remove(Exit);
			remove(Room);
			this.getContentPane().add(pStart);
			this.paintComponents(getGraphics());

			multiplay();
			msgCheck.start();
			anime.start();
			nekomove.start();
			collistion.start();
		}

		/*
		 * action 0 remove soldier,1 move, 2 attack, 3 attack tower
		 ******************************************************************************/

		if (e.getSource() == nekomove) {
			System.out.println("GODsize:" + God.soldier.size() + "godMaxPositionX:" + godMaxPositionX);
			System.out.println("enemysize:" + enemy.soldier.size() + "enemyMaxPositionX:" + enemyMaxPositionX);

				if (God.soldier.size() == 0) {
					godMaxIndex = 30;
					godMaxPositionX = 0;
				}

				if (enemy.soldier.size() == 0) {

					enemyMaxIndex = 30;
					enemyMaxPositionX = screenSizeX * 6 / 10;

				}
				/* save the most front soldier information */
				for (int i = 0; i < God.soldier.size(); i++)
					if (godMaxPositionX < God.soldier.get(i).getPositionX() + God.soldier.get(i).getWidth()) {
						godMaxPositionX = God.soldier.get(i).getPositionX() + God.soldier.get(i).getWidth();
						godMaxIndex = i;
					}
				for (int i = 0; i < enemy.soldier.size(); i++)
					if (enemyMaxPositionX > enemy.soldier.get(i).getPositionX()) {
						enemyMaxPositionX = enemy.soldier.get(i).getPositionX();
						enemyMaxIndex = i;
					}
			/* my soldier action */
			for (int i = 0; i < God.soldier.size(); i++) {
				synchronized (this) {
					if (enemy.soldier.size() == 0)
						God.soldier.get(i).setAction(1);

					if (God.soldier.get(i).getPositionX() + God.soldier.get(i).getHitRange() >= enemyMaxPositionX) {
						God.soldier.get(i).setAction(2);
					}

					if (God.soldier.get(i).getHp() <= 0) {
						God.soldier.get(i).setAction(0);

					}
					if (God.soldier.get(i).getAction() == 0) {
						God.soldier.remove(i);
						C.send("Neko", "Remove", i);
						continue;
					}
					/* move */
					if (God.soldier.get(i).getAction() == 1) {
						God.soldier.get(i)
								.setPositionX(God.soldier.get(i).getPositionX() + God.soldier.get(i).getMoveSpeed());
						C.send("Neko", i, "Position", God.soldier.get(i).getPositionX());
					}
					/* attack */
					if (God.soldier.get(i).getAction() == 2) {
						/* attack normal 1, skill 2 ... */
						C.send("Neko", enemyMaxIndex, "Attack", 1);
					}
				}
			}

			/* enemy soldier action */

			for (int i = 0; i < enemy.soldier.size(); i++) {
				synchronized (this) {
					if (God.soldier.size() == 0)
						enemy.soldier.get(i).setAction(1);

					if (enemy.soldier.get(i).getPositionX() - enemy.soldier.get(i).getHitRange() <= godMaxPositionX) {
						enemy.soldier.get(i).setAction(2);
					}

					if (enemy.soldier.get(i).getAction() == 1) {
						enemy.soldier.get(i).setPositionX(
								enemy.soldier.get(i).getPositionX() - enemy.soldier.get(i).getMoveSpeed());
					}
				}
			}

		}

		if (e.getSource() == msgCheck) {
			synchronized (this) {
				String s;
				if (!(S.message() == "")) {
					communication(emeName + ": " + S.message());
					msgLabel[8].setForeground(emeFontColor);
					S.resetMsg();
				}
				if (S.getSoldier() > 0) {
					enemy.addSoldier(S.getSoldier());
					enemy.soldier.get(enemy.soldier.size() - 1).setPositionX(
							screenSizeX * 6 / 10 - enemy.soldier.get(enemy.soldier.size() - 1).getWidth());
					S.resetSoldier();
				}
				if (S.getIndex() != -1) {
					if (godMaxIndex != 30) {
						if (S.getHp() != -1)
							enemy.soldier.get(S.getIndex()).setHp(S.getHp());
						if (S.getPosition() != -1)
							enemy.soldier.get(S.getIndex()).setPositionX(screenSizeX * 6 / 10 - S.getPosition()
									- enemy.soldier.get(S.getIndex()).getWidth());

						if (S.getAction() == 2) {

							God.soldier.get(godMaxIndex).setHp(
									God.soldier.get(godMaxIndex).getHp() - enemy.soldier.get(S.getIndex()).getDamage());
						}
						if (S.getAction() == 0) {
							System.out.println("i dead");
							enemy.soldier.get(S.getIndex()).setPositionX(screenSizeX * 6 / 10);
							enemy.soldier.remove(S.getIndex());
						}
						S.resetSoldierStatus();
					}
				}
			}
		}
		if (e.getSource() == anime) {
			update(getGraphics());
		}

		if (e.getSource() == neko) {
			God.addSoldier(1);
			C.send("Neko", "Add", 1);
		}
	}

	public void multiplay() {
		S = new Server();/* open server */
		C = new Client();/* client connect server */
		S.waitClient();/* server accept client connect */
		C.sendpkg();
		S.sendpkg();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		/* set message to emery */
		if (e.getKeyCode() == KeyEvent.VK_ENTER && !inputText.getText().isEmpty()) {
			msgLabel[9].setForeground(myFontColor);
			communication(myName + ": " + inputText.getText());
			C.send("Message", inputText.getText());
			inputText.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* update communication label */
	public void communication(String str) {
		for (int i = 1; i < 10; i++) {
			strMsg[i - 1] = strMsg[i];
			msgLabel[i - 1].setForeground(msgLabel[i].getForeground());
			msgLabel[i - 1].setText(strMsg[i - 1]);
		}
		strMsg[9] = str;
		msgLabel[9].setText(strMsg[9]);
	}

}

/*
 * MenuBar-->
 *********************************/
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
	private Image img;
	private int sizeX;
	private int sizeY;

	public BackgroundPanel(Image img, int sizeX, int sizeY) {
		this.img = img;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.setOpaque(true);
		System.out.println("hi");
	}

	// Draw the back ground.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(img, 0, 0, sizeX, sizeY, this);
	}
}