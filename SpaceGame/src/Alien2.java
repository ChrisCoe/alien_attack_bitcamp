

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Alien2 extends Sprite{

	private Image _fire;
	
	private Image _ship;
	private Image _shipDam;
	
	private Image _bulk;
	private Image _bulkHit;
	private Image _bulkHurt;
	
	private int _speedX = 0;
	private int _speedY=0;
	int _size;
	
	public static final Color FILL_COLOR=Color.white;
	
	private int _health;
	private boolean _through;
	
	public Alien2(){ 
		
		try {
			_fire = ImageIO.read(new File("Fire2.png"));
		} catch (IOException e) {
		}
		
		try {
			_ship = ImageIO.read(new File("Alien2.png"));
		} catch (IOException e) {
		}
		
		try {
			_shipDam = ImageIO.read(new File("Alien2 Hurt.png"));
		} catch (IOException e) {
		}
		
		try {
			_bulk = ImageIO.read(new File("Alien2 Slow.png"));
		} catch (IOException e) {
		}
		try {
			_bulkHit = ImageIO.read(new File("Alien Slow Hit.png"));// this is right
		} catch (IOException e) {
		}
		try {
			_bulkHurt = ImageIO.read(new File("Alien2 Slow Hurt.png"));
		} catch (IOException e) {
		}
		
		_health = 2;
		_through = false;
	}
	@Override
	public void draw(Graphics2D g) {

		if(_health == 2){
			g.drawImage(_ship,getTopX(), getTopY(), null);
		} else{
			if(_health <= 1){
				g.drawImage(_shipDam,getTopX(), getTopY(), null);
			}
			if(_health == 0){
				g.drawImage(_fire,getTopX(), getTopY(), null);
			}
		}
		
		if(this.getSizeX() < 0){
			_through = true;
		}
	}
	
	

	public void damage(){
		_health--;
	}
	
	public void passShip(){
		_through = true;
	}
	public boolean isThrough(){
		return _through;
	}
	
	public void setHealth(int h){
		_health = h;
	}
	public int getHealth(){
		return _health;
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
		
		if(_health == 3 ){
			g.drawImage(_bulk ,getTopX(), getTopY(), null);
		} else{
			if(_health == 2 ){
				g.drawImage(_bulkHit,getTopX(), getTopY(), null);
			} else{
				if(_health <= 1 ){
					g.drawImage(_bulkHurt,getTopX(), getTopY(), null);
				}
			}
		}
		
		if(_health <= 0){
			g.drawImage(_fire,getTopX(), getTopY(), null);
		}
		
		
	}


}

