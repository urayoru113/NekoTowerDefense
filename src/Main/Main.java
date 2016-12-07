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
import javax.swing.Timer;

public class Main extends JFrame implements ActionListener,KeyListener,MouseListener {
	Toolkit tk = Toolkit.getDefaultToolkit();
	int screenSizeX = (int) tk.getScreenSize().getWidth();
	int screenSizeY = (int) tk.getScreenSize().getHeight();
	// JButton Exit = new JButton(new ImageIcon(""));
	JButton Exit = new JButton("Exit");
	// JButton Multi = new JButton(new ImageIcon(""));
	JButton Multi = new JButton("MultiPlay");
	// JButton Room = new JButton(new ImageIcon(""));
	JButton Room = new JButton("CreateRoom");
	Menu menu = new Menu();
	Clip play;
	Image bufferImage;
	Arms God=new Arms();
	Arms enermy = new Arms();
	Timer test;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	public Main() {
		init(); // initialize
		start();
		test = new Timer(1000,this);
		//test.addActionListener(this);
		test.start();
		Graphics g = this.getGraphics();
		while(true){
			update(g);
		}
		// multiplay();
	}
	int i = 0;
	public void paintFight(Graphics g) {
		g.drawLine(0 , 120 , screenSizeX * 6 / 10, i++);
		g.drawString("HI XD 你好 "+i,0 , 120);
		
	}

	public void update(Graphics g)// double buffer
	{
		bufferImage = createImage(screenSizeX / 10 * 6, screenSizeY*7/9);
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

	private void init() {
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
		//add(Exit);
		// add(Multi);
		// add(Room);
		setVisible(true);
	}

	private void start() {
		// game panel
		JPanel pStart = new JPanel();
		pStart.setSize(screenSizeX, screenSizeY);
		add(pStart);
		pStart.setLayout(null);// cancel all set

		// my tower panel
		JPanel pMytower = new JPanel();
		pMytower.setBounds(screenSizeX / 10, screenSizeY * 1 / 3, screenSizeX /10, screenSizeY * 4 / 9);
		pMytower.setBackground(Color.BLUE);
		pStart.add(pMytower);

		// emegy tower panel
		JPanel pEmeTower = new JPanel();
		pEmeTower.setBounds(screenSizeX * 8 / 10, screenSizeY * 1 / 3, screenSizeX / 10, screenSizeY * 4 / 9);
		pEmeTower.setBackground(Color.BLUE);
		pStart.add(pEmeTower);

		// my hp label
		JLabel jMyHp = new JLabel(God.getHp() + " / 1000");
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
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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