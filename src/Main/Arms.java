package Main;

import java.util.ArrayList;

/**
 * player can choose different kinds of arms
 * 
 * @author jack
 *
 */
public class Arms {
	private int hp;
	private int gold;
	private int armLavel;
	private int goldLavel;
	private ArrayList<Integer> soldier;// max number of soldier

	Arms() {
		soldier = new ArrayList<>();
		gold=100;
		hp=1000;
	}

	Arms(String race) {

	}

	public void addSoldier(int type) {

	}

	public void removeSoldier(int position) {

	}

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
