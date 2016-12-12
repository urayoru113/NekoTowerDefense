package Main;
import java.util.ArrayList;

/**
 * player can choose different kinds of arms
 * 
 * @author jack
 *
 *******************************************/
public class Arms {
	private int hp;//Tower(player) hp
	private int gold;
	private int armLevel;//
	private int goldLevel;//
	private ArrayList<Neko>soldier = new ArrayList<>();;//add soldier(neko) into arrayList
	//initialize
	Arms() {
		gold=100;
		hp=1000;
		goldLevel=1;
		armLevel=1;
	}

	Arms(String race) {

	}
	//add soldier
	public void addSoldier(int type) {
		//|soldier
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

	public void armLevel(int armLevel) {
		this.armLevel = armLevel;
	}

	public int getarmLevel() {
		return armLevel;
	}

	public void goldLevel(int goldLevel) {
		this.goldLevel = goldLevel;
	}

	public int getgoldLevel() {
		return goldLevel;
	}
}
