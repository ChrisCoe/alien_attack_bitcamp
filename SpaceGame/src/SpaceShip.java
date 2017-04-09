

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpaceShip extends Sprite {
	private int _speedY = 0; int _speedX = 0;
	private Image _pic;
	int _size;
	public static final Color FILL_COLOR=Color.white;
	
	public SpaceShip(int sizeX, int sizeY){ // triangle with equal sides
		setSizeX(sizeX);
		setSizeY(sizeY);
		
		try {
			_pic = ImageIO.read(new File("doggy4Game.gif"));
		} catch (IOException e) {
		}
	}
	@Override
	public void draw(Graphics2D g) {
		
		/*g.drawImage(_pic,getTopX() - 73, getTopY() - 40, null);
		g.setColor(Color.PINK); //inside */
		//draw the bat at xpos, ypos

		g.fillRect(getTopX(), getTopY(), getSizeX(), getSizeY());
		g.fillPolygon(new int[]{getTopX() + 5, getTopX() + 10, getTopX() + 15},
				new int[]{getTopY() , getTopY() - 7, getTopY()}, 3);
		g.fillPolygon(new int[]{getTopX() + getSizeX(), getTopX() + getSizeX()
				+ 20, getTopX() + getSizeX()},
				new int[]{getTopY() + 3, getTopY() + 6, getTopY() + 12}, 3);
		g.fillOval(getTopX(),getTopY() + getSizeY() - 12, 21, 14);
		
	}
	

	/**
	 *
	 * @return  Speed to move with each frame
	 */
	public int getSpeed() {
		return _speedY;
	}
	
	/**
	 * @return  Speed to move with each frame
	 */
	public void setSpeed(int speed) {
		this._speedY = speed;
		this._speedX = speed;
	}
	 
	 public void doMoveV(boolean moveUpOrDown){
		 
			if(moveUpOrDown==true) { //move up
				setTopY(getTopY()-_speedY);
			} else {
				setTopY(getTopY()+_speedY); //move down
			}
	}
	 public void doMoveH(boolean moveleftOrRight){
		 
			if(moveleftOrRight==true) { //move left
				setTopX(getTopX()-_speedX);
			} else {
				setTopX(getTopX()+_speedX); //move right
			}
	}
		

}
