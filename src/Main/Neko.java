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
	private int type,level,alive;
	private int positionX, positionY;
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

	public void setlevel(int level) {
		this.level = level;
	}

	public int getLabel() {
		return level;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionY() {
		return positionY;
	}

}
