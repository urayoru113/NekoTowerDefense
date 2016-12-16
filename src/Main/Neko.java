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
	private int type, action, skill;
	private int positionX, positionY, hitTime, hitCd;
	private int heigth, width;
	private int hitRange;
	private int img;
	Neko() {

	}

	Neko(int kind,int level) {
		action = 1;
		type = kind;
		positionX = 0;
		if (kind == 1) {
			hp = 100 + level*50;
			moveSpeed = 1;
			damage = 5;
			width = 130;
			heigth = 165;
			hitRange = 10;
			defense = 1;
			hitTime = 30;// Timer
			hitCd = 30;// CD
			img = 1;
		}
		if (kind == 2) {
			hp = 150;
			moveSpeed = 2;
			damage = 10;
			width = 201;
			heigth = 175;
			hitRange = 15;
			defense = 1;
			hitTime = 30;// Timer
			hitCd = 30;// CD
			img = 2;
		}
	}

	public int getImage(){
		return img;
	}
	public void setImage(int img){
		this.img = img;
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