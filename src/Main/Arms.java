package Main;

import java.util.ArrayList;
/**
 * player can choose different kinds of arms
 * @author jack
 *
 */
public class Arms {
	ArrayList<Integer> soldier;
	tower tower;
	Arms()
	{
		tower = new tower();
	}
	Arms(String race)
	{
		
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

}
