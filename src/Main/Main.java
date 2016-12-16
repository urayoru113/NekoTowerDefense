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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
	ImageIcon myTowerImg = new ImageIcon("src//img//N300_neko-slime.png");
	ImageIcon eneTowerImg = new ImageIcon("src//img//N300_neko-slime_phixr.png");
	ImageIcon backgroundimg = new ImageIcon("src//img//background.png");
	ImageIcon msgBox = new ImageIcon("src//img//kisakajpg.jpg");
	ImageIcon[] myCat = new ImageIcon[3];
	ImageIcon[] eneCat = new ImageIcon[3];
	// JButton Exit = new JButton(new ImageIcon(""));
	JButton Exit = new JButton("Exit");
	// JButton Multi = new JButton(new ImageIcon(""));
	JButton Multi = new JButton("MultiPlay");
	// JButton Room = new JButton(new ImageIcon(""));
	JButton Room = new JButton("CreateRoom");

	// the soldier
	JButton[] neko = new JButton[3];
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
	Timer anime = new Timer(5, this);// set counte
	Timer nekomove = new Timer(30, this);// set move time
	Timer publicEvent = new Timer(100, this);
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
		// setUndecorated(true);
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
		readImage();
	}

	public void paintFight(Graphics g) {
		g.drawImage(backgroundimg.getImage(), -screenSizeX / 5, 0, screenSizeX, screenSizeY, this);
		for (int i = 0; i < God.soldier.size(); i++)
			g.drawImage(myCat[God.soldier.get(i).getImage()].getImage(), God.soldier.get(i).getPositionX(),
					screenSizeY * 9 / 17 - God.soldier.get(i).getHeigth(), God.soldier.get(i).getWidth(),
					God.soldier.get(i).getHeigth(), this);
		for (int i = 0; i < enemy.soldier.size(); i++)
			g.drawImage(eneCat[enemy.soldier.get(i).getImage()].getImage(), enemy.soldier.get(i).getPositionX(),
					screenSizeY * 9 / 17 - enemy.soldier.get(i).getHeigth(), enemy.soldier.get(i).getWidth(),
					enemy.soldier.get(i).getHeigth(), this);
		jMyTowerHp.setText(God.getHp() + " / 1000");
		jMyGold.setText("Gold: " + God.getGold());
	}

	int i = 0;

	@Override
	public void update(Graphics g)// double buffer
	{
		bufferImage = createImage(screenSizeX / 10 * 6, screenSizeY * 12 / 17 - 30);
		Graphics gBuffer = bufferImage.getGraphics();
		if (gBuffer != null)
			paintFight(gBuffer);
		else
			paint(g);
		gBuffer.dispose();
		g.drawImage(bufferImage, screenSizeX / 5, 49, null);
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

	void readImage() {
		/* monster image */
		myCat[1] = new ImageIcon("src//img//anpo_1_phixr.png");
		eneCat[1] = new ImageIcon("src//img//anpo_1.png");
		myCat[2] = new ImageIcon("src//img//heishi.png");
		eneCat[2] = new ImageIcon("src//img//heishi.png");
		/* button image */
		neko[1].setIcon(new ImageIcon("src//img//images.jpg"));
		neko[2].setIcon(new ImageIcon("src//img//images1.jpg"));

	}

	private void start() {
		// game panel
		pStart = new BackgroundPanel(backgroundimg.getImage(), screenSizeX, screenSizeY);
		pStart.setSize(screenSizeX, screenSizeY);
		pStart.setLayout(null);// cancel all set
		kisaka = new BackgroundPanel(msgBox.getImage(), screenSizeX * 1 / 4, screenSizeY * 22 / 9 / 12);
		kisaka.setBounds(screenSizeX * 8 / 11, screenSizeY * 13 / 18, screenSizeX / 4, screenSizeY * 22 / 9 / 12);
		// my tower panel
		myTower = new BackgroundPanel(myTowerImg.getImage(), 150, 150);
		myTower.setBounds(screenSizeX / 20, screenSizeY * 1 / 3, 150, 150);
		// myTower.setBackground(Color.BLACK);
		// enemy tower panel
		eneTower = new BackgroundPanel(eneTowerImg.getImage(), 150, 150);
		eneTower.setBounds(screenSizeX * 16 / 20, screenSizeY * 1 / 3, 150, 150);

		// my hp label
		jMyTowerHp = new JLabel(God.getHp() + " / 1000");
		jMyTowerHp.setForeground(Color.RED);
		jMyTowerHp.setBounds(screenSizeX * 1 / 10, screenSizeY * 1 / 3 - 60, 200, 30);
		pStart.add(jMyTowerHp);

		// my gold label
		jMyGold = new JLabel("Gold: " + God.getGold());
		jMyGold.setForeground(Color.YELLOW);
		jMyGold.setBounds(screenSizeX * 1 / 10, screenSizeY * 1 / 3 - 120, 200, 30);
		jMyGold.setBackground(Color.red);
		pStart.add(jMyGold);
		pStart.add(myTower);
		pStart.add(eneTower);

		// make battle button

		for (int i = 1; i < 3; i++) {
			neko[i] = new JButton();
			neko[i].setContentAreaFilled(false);
			neko[i].setBounds(25 + screenSizeX * 1 / 9 * (i - 1), screenSizeY * 13 / 18, screenSizeX * 1 / 9,
					screenSizeY * 2 / 9);
			neko[i].addActionListener(this);
			pStart.add(neko[i]);
		}
		// make a communication Label
		for (int i = 0; i < 10; i++) {
			msgLabel[i] = new JLabel(strMsg[i]);
			msgLabel[i].setBounds(screenSizeX * 8 / 11, screenSizeY * 13 / 18 + screenSizeY * 2 / 9 * i / 12,
					screenSizeX * 1 / 4, screenSizeY * 2 / 9 / 12);
			pStart.add(msgLabel[i]);
		}
		inputText.setBounds(screenSizeX * 8 / 11, screenSizeY * 7 / 9 + screenSizeY * 2 / 9 * 7 / 12,
				screenSizeX * 1 / 4, screenSizeY * 2 / 9 / 12 + 5);
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
			anime.start();
			publicEvent.start();
			nekomove.start();
		}

		/* public event */
		if (e.getSource() == publicEvent) {
			God.setGold(God.getGold() + 70);
		}

		/*
		 * action 0 remove soldier,1 move, 2 attack, 3 attack tower
		 ******************************************************************************/

		if (e.getSource() == nekomove) {

			System.out.println("GODsize:" + God.soldier.size() + "godMaxPositionX:" + godMaxPositionX);
			System.out.println("enemysize:" + enemy.soldier.size() + "enemyMaxPositionX:" + enemyMaxPositionX);
			if (God.getHp() <= 0) {
				anime.stop();
				nekomove.stop();
				S.closeSocket();
				dispose();
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
				{
					if (enemy.soldier.size() == 0)
						God.soldier.get(i).setAction(1);

					if (God.soldier.get(i).getPositionX() + God.soldier.get(i).getWidth()
							+ God.soldier.get(i).getHitRange() >= enemyMaxPositionX) {
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
						if (enemyMaxIndex != 30)
							C.send("Neko", enemyMaxIndex, "Attack", 1);
						if (enemy.soldier.size() == 0)
							C.send("Tower", "Hp", God.soldier.get(i).getDamage());
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
		if (e.getSource() == anime) {
			update(getGraphics());
		}

		if (e.getSource() == neko[1]) {
			if (God.getGold() >= 300) {
				God.addSoldier(1);
				God.setGold(God.getGold() - 300);
				C.send("Neko", "Add", 1);
			}
		}
		if (e.getSource() == neko[2]) {
			if (God.getGold() >= 500) {
				God.addSoldier(2);
				God.setGold(God.getGold() - 500);
				C.send("Neko", "Add", 2);
			}
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

	class Server {
		/* initialize var */
		private ServerSocket server;
		private Socket socket;
		private final int port = 8888;
		private String[] getMsg;
		private String Msg = "";
		private int Hp = 0, Position = 0, Index = 0, TowerDamage = 0;

		/* open server */
		public Server() {
			try {
				server = new ServerSocket(port);
			} catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
			if (server != null) {
				System.out.println("start server");
			}

		}

		public void sendpkg() {
			new Thread(new Runnable() {
				DataInputStream input;
				DataOutputStream output;

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						try {
							input = new DataInputStream(socket.getInputStream());
						} catch (Exception e) {
							continue;
						}
						try {

							while (input != null) {
								synchronized (this) {
									decode(input.readUTF());
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.exit(1);
							e.printStackTrace();

						}
					}
				}
			}).start();
		}

		/* decode packet */
		public void decode(String packet) {
			String[] s = packet.split("&9o");
			for (String g : s)
				System.out.println(g);
			for (String getMsg : s) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.getMsg = getMsg.split("&8o");
				/*
				 * for (String j : this.getMsg) System.out.println(j);
				 */
				switch (this.getMsg[0]) {
				case "Message":
					Msg = this.getMsg[1];
					communication(emeName + ": " + S.message());
					msgLabel[8].setForeground(emeFontColor);
					break;
				case "Neko":
					switch (this.getMsg[1]) {
					case "Add":
						enemy.addSoldier(Integer.parseInt(this.getMsg[2]));
						enemy.soldier.get(enemy.soldier.size() - 1).setPositionX(
								screenSizeX * 6 / 10 - enemy.soldier.get(enemy.soldier.size() - 1).getWidth());
						break;
					case "Remove":
						Index = Integer.parseInt(this.getMsg[2]);
						enemy.soldier.remove(Index);
						break;
					default:
						Index = Integer.parseInt(this.getMsg[1]);
						switch (this.getMsg[2]) {
						// HP
						case "Hp":
							Hp = Integer.parseInt(this.getMsg[3]);
							enemy.soldier.get(Index).setHp(Hp);
							break;
						// position
						case "Position":
							Position = Integer.parseInt(this.getMsg[3]);
							enemy.soldier.get(Index).setPositionX(
									screenSizeX * 6 / 10 - Position - enemy.soldier.get(Index).getWidth());
							break;
						case "Attack":
							God.soldier.get(godMaxIndex)
									.setHp(God.soldier.get(godMaxIndex).getHp() - enemy.soldier.get(Index).getDamage());
							break;

						}
						break;
					}
					break;
				case "Tower":
					switch (this.getMsg[1]) {
					case "Hp":
						TowerDamage = Integer.parseInt(this.getMsg[2]);
						God.setHp(God.getHp() - TowerDamage);
						break;
					case "Level":
						break;
					}
				}
			}
		}

		public String message() {
			return Msg;
		}

		public void resetMsg() {
			Msg = "";
		}

		/* wait client connect */
		public void waitClient() {
			try {
				System.out.println("wait connect");
				socket = server.accept();
				System.out.println("Server connect successed");
				System.out.println("gethost : InetAddress = " + socket.getInetAddress());
				server.close();
			} catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}

		/* get way */

		/* close server socket */
		public void closeSocket() {
			try {
				socket.close();
			} catch (java.io.IOException e) {
				System.out.println(e.getMessage());
			}
		}

		/* close server */
		public void closeServer() {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.exit(1);
				e.printStackTrace();
			}
		}
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
