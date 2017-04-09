

import java.awt.Color;
import java.awt.Graphics2D;


public class Bullet extends Sprite{

	private int _speedX=0; 
	private int _speedY=0; // special bullets have this
	
	private boolean _shot;
	
	public static final Color FILL_COLOR=Color.white;
	/**
	 * Create a paddle 
	 */
	public Bullet(int sizeX){
		//set size of paddle from image size 
		setSizeY(sizeX);
		setSizeX(sizeX); // the same!
		_shot = true;
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(FILL_COLOR); //inside
		//draw the bat at xpos, ypos
		g.fillOval(getTopX(), getTopY(), getSizeX(), getSizeY());
		// TODO Auto-generated method stub
	}
	
	public void die(Graphics2D g) {
		g.setColor(Color.black); //inside
		//draw the bat at xpos, ypos
		g.fillOval(getTopX(), getTopY(), getSizeX(), getSizeY());
		// TODO Auto-generated method stub
	}
	
	/**
	 *
	 * @return  Speed to move with each frame
	 */
	
	public int getSpeedX() {
		return _speedX;
	}
	
	
	public int getSpeedY() {
		return _speedY;
	}
	
	public boolean isShot(){
		return _shot;
	}

	/**
	 * @return  Speed to move with each frame
	 */
	public void setSpeedX(int speed) {
		this._speedX = speed;
	}
	
	public void setSpeedY(int speed) {
		this._speedY = speed;
	}
	
	public void shot(){
		_shot = false;
	}
	 
	 public void doMove(boolean move){ // if it can move
		 
			if(move==true) { //move up
				setTopX(getTopX()-_speedX);
				setTopY(getTopY()-_speedY);
			} 
	}

}

