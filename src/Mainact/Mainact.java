package Mainact;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mainact extends JFrame implements ActionListener {

	Toolkit tk = Toolkit.getDefaultToolkit();
	int screenSizeX = (int) tk.getScreenSize().getWidth();
	int screenSizeY = (int) tk.getScreenSize().getHeight();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Mainact();
	}
	public Mainact(){
		init(); //initialize
		start();
		multiplay();
	}

	private void init() {
		setTitle("game");
		setSize(screenSizeX, screenSizeY);
		setVisible(true);
	}

	private void start(){
		//game panel

		tower tw = new tower();
		tw.setHp(1000);
		tw.setGold(100);

		JPanel pStart = new JPanel();
		pStart.setSize(screenSizeX, screenSizeY);
		add(pStart);
		pStart.setLayout(null);//cancel all set

		//my tower panel
		JPanel pMytower = new JPanel();
		pMytower.setBounds(screenSizeX / 10, screenSizeY * 1 / 3, 200, 500);
		pMytower.setBackground(Color.BLUE);
		pStart.add(pMytower);

		//emegy tower panel
		JPanel pEmeTower = new JPanel();
		pEmeTower.setBounds(screenSizeX * 8 / 10, screenSizeY * 1 / 3, 200, 500);
		pEmeTower.setBackground(Color.BLUE);
		pStart.add(pEmeTower);

		//my hp label
		JLabel jMyHp = new JLabel(tw.getHp() + " / 1000");
		jMyHp.setBounds(screenSizeX * 1 / 10, screenSizeY * 1 / 3 - 60, 200, 30);
		jMyHp.setBackground(Color.red);
		pStart.add(jMyHp);
	}

	public void multiplay(){
		Server S = new Server();
		System.out.println(S.cntmsg);

	}

	class tower{
		private int hp;
				int gold;
				int armLavel;
				int goldLavel;

		public void setHp(int hp){
			this.hp = hp;
		}
		public int getHp() {
			return hp;
		}

		public void setGold(int gold){
			this.gold = gold;
		}

		public int getGold() {
			return gold;
		}

		public void armLavel(int armLavel){
			this.armLavel = armLavel;
		}
		public int getarmLavel() {
			return armLavel;
		}

		public void goldLavel(int goldLavel){
			this.goldLavel = goldLavel;
		}
		public int getgoldLavel() {
			return goldLavel;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
