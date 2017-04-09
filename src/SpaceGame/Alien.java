package SpaceGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Alien extends Sprite{

	private Image _fire;
	private Image _alienSpd;
	private Image _Noah;
	private Image _Nathan;
	private Image _dog;
	private int _speedX = 0;
	private int _speedY=0;
	int _size;
	public static final Color FILL_COLOR=Color.white;
	
	private boolean _killed;
	private boolean _through;
	
	
	public Alien(int sizeX, int sizeY){ // triangle with equal sides
		setSizeX(sizeX);
		setSizeY(sizeY);
		
//		try {
//			_Noah = ImageIO.read(new File("Noah's Face.jpg"));
//		} catch (IOException e) {
//		}
//		try {
//			_Nathan = ImageIO.read(new File("Nathan's Face.jng"));
//		} catch (IOException e) {
//		}
		
		try {
			_dog = ImageIO.read(new File("doggy4Game.gif"));
		} catch (IOException e) {
		}
		
		try {
			_fire = ImageIO.read(new File("Fire2.png"));
		} catch (IOException e) {
		}
		
		try {
			_alienSpd = ImageIO.read(new File("Alien Speed.png"));
		} catch (IOException e) {
		}
		
		_through = false;
		_killed = false;
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.green); //inside
		//draw the bat at xpos, ypos
		
		
//		int duck = (int) (Math.random()*10);
//		if(duck > 5){
//			g.drawImage(_Nathan,getTopX(), getTopY(), null);
//		} 
//		
//		if(duck <= 5){
//			g.drawImage(_Noah,getTopX(), getTopY(), null);
//		}
		
		//g.drawImage(_dog, getTopX(), getTopY(), null);
		
		g.fillRect(getTopX(), getTopY(), getSizeX(), getSizeY());
		g.fillPolygon(new int[]{getTopX() + 6, getTopX() + 9, getTopX() + 5},
				new int[]{getTopY() , getTopY() - 2, getTopY()}, 3);
		g.fillPolygon(new int[]{getTopX() + getSizeX(), getTopX() + getSizeX()
				+ 20, getTopX() + getSizeX()},
				new int[]{getTopY() + 6, getTopY() + 6, getTopY() + 3}, 3);
		g.fillOval(getTopX(),getTopY() + getSizeY() - 1, 21, 14); // was - 6
		 
		if(_killed){
			g.drawImage(_fire,getTopX(), getTopY(), null);
		}
	}
	
	public void die(Graphics2D g){
		g.setColor(Color.black); //inside
		//draw the bat at xpos, ypos

//		g.fillRect(getTopX(), getTopY(), getSizeX(), getSizeY());
//		g.fillPolygon(new int[]{getTopX() + 6, getTopX() + 9, getTopX() + 5},
//				new int[]{getTopY() , getTopY() - 2, getTopY()}, 3);
//		g.fillPolygon(new int[]{getTopX() + getSizeX(), getTopX() + getSizeX()
//				+ 20, getTopX() + getSizeX()},
//				new int[]{getTopY() + 6, getTopY() + 6, getTopY() + 3}, 3);
//		g.fillOval(getTopX(),getTopY() + getSizeY() - 1, 21, 14); // was - 6
	}
	
	public void kill(){
		_killed = true;
	}
	public void passShip(){
		_through = true;
	}
	public boolean isThrough(){
		return _through;
	}

	public int getSpeedX() {
		return _speedX;
	}
	
	public int getSpeedY() {
		return _speedY;
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
	 
	 public void doMove(boolean move){ // if it can move
			if(move==true) { //move up
				setTopX(getTopX()-_speedX);
				setTopY(getTopY()-_speedY);
			} 
	}
	public void draw2(Graphics2D g) {
		g.drawImage(_alienSpd,getTopX(), getTopY(), null);
		if(_killed){
			g.drawImage(_fire,getTopX(), getTopY(), null);
		}
	}


}
