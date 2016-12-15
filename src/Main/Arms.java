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
	private int []soldierLevel={1,1};//
	private int goldLevel;//
	private int times;
	ArrayList<Neko>soldier;//add soldier(neko) into arrayList
	//initialize
	Arms() {
		soldier = new ArrayList<>();
		gold=100;
		hp=1000;
		goldLevel=1;
		times=0;
	}

	Arms(String race) {

	}
	//add soldier
	public void addSoldier(int type) {
		times++;
		soldier.add(new Neko(type));
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


	public void goldLevel(int goldLevel) {
		this.goldLevel = goldLevel;
	}

	public int getgoldLevel() {
		return goldLevel;
	}
}