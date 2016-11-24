package Main;
import java.awt.Button;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Main extends JFrame implements ActionListener {
//aaaHI
	Toolkit tk = Toolkit.getDefaultToolkit();
	int screenSizeX = (int) tk.getScreenSize().getWidth();
	int screenSizeY = (int) tk.getScreenSize().getHeight();
	Menu menu = new Menu();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	Main(){
		setTitle("Tower Denfense");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSizeX, screenSizeY);
		setJMenuBar(menu);
		//start();
		JButton bt = new JButton();
		add(bt);
		//multiplay();
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