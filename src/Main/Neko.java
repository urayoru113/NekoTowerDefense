package Main;

import java.awt.Image;

/**
 * the standard neko(Soldier pattern)
 * 
 * @author jack
 *
 */
public class Neko {
	private int hp, mp, kb, damage, defense;
	private int moveSpeed, backSpeed;
	private int type, level, alive;
	private int positionX;
	private int heigth, width;
	private int hitRange;
	private Image image;

	Neko() {

	}

	Neko(int kind) {
		if (kind == 1) {
			hp = 100;
			moveSpeed = 1;
			type = type;
			level = 1;
			positionX = 0;
			width = 0;
			heigth = 0;
			hitRange = 5;
			defense = 1;
		}
	}

	public int getHitRange() {
		return hitRange;
	}

	public void setHitRange(int hitRange) {
		this.hitRange = hitRange;
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

	public void move() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				positionX -= 10;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}
}
