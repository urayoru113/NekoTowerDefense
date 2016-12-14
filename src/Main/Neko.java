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
	private int moveSpeed, backSpeed, hitMove;
	private int type, level, action, skill;
	private int positionX, positionY, hitTime, hitCd;
	private int heigth, width;
	private int hitRange;
	private Image image;

	Neko() {

	}

	Neko(int kind) {
		if (kind == 1) {
			action = 1;// 0 stop // 1 move // 2 attack
			hp = 100;
			moveSpeed = 1;
			type = kind;
			level = 1;
			damage = 10;
			positionX = 0;
			width = 0;
			heigth = 0;
			hitRange = 30;
			defense = 1;
			hitTime = 30;// Timer
			hitCd = 30;// CD
		}
	}

	public int getHitMove() {
		return hitMove;
	}

	public void setHitMove(int hitMove) {
		this.hitMove = hitMove;
	}

	public void setHitTime(int hitTime) {
		this.hitTime = hitTime;
	}

	public int getHitTime() {
		return hitTime;
	}

	public int getHitCd() {
		return getHitCd();
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
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
	public void setPositionY(int positionX) {
		this.positionY = positionY;
	}

	public int getPositionY() {
		return positionY;
	}
		
	public int getMoveSpeed() {
		return moveSpeed;
	}
	
	/*hit damage*/
	public void setDamage(int damage){
		this.damage = damage;
	}
	public int getDamage(){
		return damage;
	}

}
