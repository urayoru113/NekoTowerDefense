package Main;

import java.awt.Image;
/**
 * the standard neko(Soldier pattern)
 * @author jack
 *
 */
public class Neko {
	private int hp,mp,kb;
	private int moveSpeed,backSpeed;
	private int type,level;
	Image image;
	Neko(){
		
		
	}
	Neko(int type){
		
	}
	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHp() {
		return hp;
	}

	public void level(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
