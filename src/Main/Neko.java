package Main;

import java.awt.Image;

/**
 * the standard neko(Soldier pattern)
 * 
 * @author jack
 *
 */
public class Neko {
	private int hp, mp, kb;
	private int moveSpeed, backSpeed;
	private int type, level, alive;
	private int positionX;
	private int heigth, width;
	Image image;

	Neko() {

	}

	Neko(int type) {
		if (type == 1) {
			hp = 100;
			moveSpeed = 1;
			this.type = type;
			level = 1;
			positionX = 0;
			width = 0;
			heigth = 0;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
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

	public int getMoveSpeed() {
		return moveSpeed;
	}
}