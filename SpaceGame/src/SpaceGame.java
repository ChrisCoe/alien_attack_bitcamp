
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SpaceGame extends GameTemplate{
	
	//create your entities here 
//		private Paddle p1,p2;
//		private Ball ball;
	
	
	// THINGS TO FIX ///
	// Make sure aliens are cleared when game is over. Java runs out of memory. 
	//qq
	//
		private SpaceShip shipM;
		private int _life;
		private ArrayList<Bullet> _bullets;
		
		private ArrayList<Alien> _aliens;
		private ArrayList<Alien2> _aliens2;
		private ArrayList<Alien> _aliensSpd;
		private ArrayList<Alien2> _aliensBulk;
		
		private boolean _playing = false;
		private boolean _start = false; // just another like playing
		private boolean _pause = false;
		private boolean _won = false;
		
		private boolean _wave2 = false;
		private boolean _wave3 = false;
		private boolean _wave4 = false;
		
		private Image _outerSpace = null;
		
		private Clip _laserS;
		
		private int _bgPos; // background position
		private int _score; //scores for players
		private String _wave = "Wave 1";
		
		public static final int STATE_PLAYING=1; //state values
		public static final int STATE_DONE=2;
		
		//public static final Color BG_COL=new Color(100,0,0); //background
		public static final Color WALL_COL=Color.white; //walls
		public static final int WALL_SIZE=3; //wall size
	
		private int gameState; //current state

	/**
	 * Create Frame and Panel
	 */
	public static void main(String[] args){
		SpaceGame game=new SpaceGame();
		createGameFrame(game, 1000, 600);
		game.init();
	}

	/**
	 * Create a Pong game panel
	 */
	public SpaceGame(){
		super();		
	} 
	/**
	 * Initialize game - insert code to set up (not create!) entities
	 */
	public void init(){
		/*
		p1=new Paddle(20,100); // size
		p2=new Paddle(20,100);*/
		
		try {
			_outerSpace = ImageIO.read(new File("SpaceBackGround Full.png"));
		} catch (IOException e) {
		}
		
		/*idk about audio
		 * try {
	        _clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	        		new File("DeathSound.wma"));
	        _clip.open(inputStream);
	        _clip.start();
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
		 */
		
		shipM = new SpaceShip(15,20);
		_life = 10;// health
		_bullets = new ArrayList<Bullet>();
		_aliens = new ArrayList<Alien>();
		_aliens2 = new ArrayList<Alien2>();
		_aliensSpd = new ArrayList<Alien>();
		_aliensBulk = new ArrayList<Alien2>();
		_bgPos = 0; // background position
		
		for(int i = 0; i < 10; i++){
			Alien a = new Alien(10,(int)(Math.random()*5) + 7);
			_aliens.add(a);
			//_aliensSpd.add(a); This makes a copy (not good) there is a connection
		}
		for(int i = 0; i < 10; i++){
			Alien a = new Alien(10,(int)(Math.random()*5) + 7);// I don't think this does anything
			_aliensSpd.add(a);
		}
		for(int i = 0; i < 10; i++){
			Alien2 a = new Alien2();
			_aliens2.add(a);
		}
		for(int i = 0; i < 10; i++){
			Alien2 a = new Alien2();
			_aliensBulk.add(a);
		}
		
		
		_score = -100;
		start();
	}
	 
	/**
	 * (re)start game - reset positions, scores etc
	 */
	public void start(){
		/*
		p1.setSpeed(7);
		p1.setTopX(20); //reset positions
		p1.setTopY(getHeight()/2-p1.getSizeY()/2);
		
		p2.setSpeed(7);
		p2.setTopX(getWidth()-40); //reset positions
		p2.setTopY(getHeight()/2-p1.getSizeY()/2); */
		
		shipM.setSpeed(7);
		shipM.setTopX(10);
		shipM.setTopY(getHeight()/2);
		
		for(Alien a : _aliens){
			a.setTopX(getWidth());
			a.setTopY((int)(Math.random()*(getHeight() - 26)));
			a.setSpeedX((int)(Math.random()*4));
			if(a.getSpeedX() == 0){
				a.setSpeedX(1);
			}
			a.setSpeedY((int)(Math.random()*2) - 1);
		}
		
		//hiding
		for(Alien2 a : _aliens2){
			a.setTopX(getWidth() + 10);
			a.setTopY((int)(Math.random()*(getHeight() - 26)));
			a.setSpeedX((int)(Math.random()*4));
			if(a.getSpeedX() == 0){
				a.setSpeedX(1);
			}
			a.setSpeedY((int)(Math.random()*2) - 1);
		}
		for(Alien a : _aliensSpd){
			a.setTopX(getWidth() + 1);
			a.setTopY((int)(Math.random()*(getHeight() - 26)));
			a.setSpeedX((int)(Math.random()*9));
			if(a.getSpeedX() == 0){
				a.setSpeedX(1);
			}
			a.setSpeedY((int)(Math.random()*3) - 1);
		}
		for(Alien2 a : _aliensBulk){
			a.setTopX(getWidth() + 10);
			a.setTopY((int)(Math.random()*(getHeight() - 26)));
			a.setSpeedX((int)(Math.random()*2));
			if(a.getSpeedX() == 0){
				a.setSpeedX(1);
			}
			a.setSpeedY((int)(Math.random()*7) - 3);
			a.setHealth(3);
		}
		
		/* SOUND at start of game */
		
		
		/*try {
	        _laserS = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	        		new File("Laser-Sound.wav"));
	        _laserS.open(inputStream);
	        _laserS.start(); 
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }*/
		
		
		_playing = true;
	}
	
/**
 * Update the screen - draw the environment, and then call the draw() methods for each sprite
 * @param g graphics object 
 */
	public void updateFrame(Graphics2D g){ 
		
		// Vertical Movement
		if(isAKeyDown(KeyEvent.VK_W) && canMove(shipM,true)) shipM.doMoveV(true);
		else if(isAKeyDown(KeyEvent.VK_S) && canMove(shipM,false)) shipM.doMoveV(false);
		
		if(isAKeyDown(KeyEvent.VK_UP) && canMove(shipM,true)) shipM.doMoveV(true);
		else if(isAKeyDown(KeyEvent.VK_DOWN) && canMove(shipM,false)) shipM.doMoveV(false);
		
		// Horizontal Movement
		if(isAKeyDown(KeyEvent.VK_A) && canMove(shipM,true)) shipM.doMoveH(true);
		else if(isAKeyDown(KeyEvent.VK_D) && canMove(shipM,false)) shipM.doMoveH(false);
		
		if(isAKeyDown(KeyEvent.VK_LEFT) && canMove(shipM,true)) shipM.doMoveH(true);
		else if(isAKeyDown(KeyEvent.VK_RIGHT) && canMove(shipM,false)) shipM.doMoveH(false);

		
		// Do not fire bullets if game is over
		if(_life <= 0 && _bullets != null){
			_bullets.clear();
		}
		//Reload
		if(isAKeyDown(KeyEvent.VK_SPACE) && _bullets.size() < 9 && !_pause){ // set limit
			Bullet b = new Bullet(7);
			b.setTopX(shipM.getTopX() + 5);
			b.setTopY(shipM.getTopY() + 5);
			
			b.setSpeedX(-15);
			b.setSpeedY(0);
			_bullets.add(b);
		}
		
		//Add Aliens
		
		if(_aliens != null && _start && !_pause){ // spawn once the game begins
			
			if(Math.random()*100 > 99){
				Alien a  = new Alien(10,10);
				a.setTopX(getWidth());
				a.setTopY((int)(Math.random()*(getHeight() - 26)));
				a.setSpeedX((int)(Math.random()*4));
				if(a.getSpeedX() == 0){
					a.setSpeedX(1);
				}
				a.setSpeedY((int)(Math.random()*2) - 1);
				_aliens.add(a);
			}
		}
		if(_aliens2 != null && !_wave2 && !_pause){ // do not spawn if paused

			if(Math.random()*100 > 99){ // chance of respawning
				Alien2 a  = new Alien2();
				a.setTopX(getWidth() + 10);
				a.setTopY((int)(Math.random()*(getHeight() - 26)));
				a.setSpeedX((int)(Math.random()*4));
				if(a.getSpeedX() == 0){
					a.setSpeedX(1);
				}
				a.setSpeedY((int)(Math.random()*4) - 1);
				_aliens2.add(a);
			}
		}
		if(_aliensSpd != null && _wave3 && !_pause){
			
			if(Math.random()*1000 > 999){ // chance of respawning
				Alien a  = new Alien(10,10);
				a.setTopX(getWidth() + 1);
				a.setTopY((int)(Math.random()*(getHeight() - 26)));
				a.setSpeedX((int)(Math.random()*9));
				if(a.getSpeedX() == 0){
					a.setSpeedX(1);
				}
				a.setSpeedY((int)(Math.random()*3) - 1);
				_aliensSpd.add(a);
			}
		}
		if(_aliensBulk != null && _wave3 && !_pause){// lol or _start
			if(Math.random()*100 > 99.5){ // chance of respawning
				Alien2 a  = new Alien2();
				a.setTopX(getWidth() + 10);
				a.setTopY((int)(Math.random()*(getHeight() - 26)));
				a.setSpeedX((int)(Math.random()*2));
				if(a.getSpeedX() == 0){
					a.setSpeedX(1);
				}
				a.setSpeedY((int)(Math.random()*7) - 3);
				a.setHealth(3);
				_aliensBulk.add(a);
			} 
		}
		
		
		
		
		//Remove Dead Aliens
		
		for(int i = 0; _aliens != null && i < _aliens.size()&& i > 0 ; i++){
			Alien a = _aliens.get(i);
			if(a.getTopX() > getWidth() + 1){
				_aliens.remove(i);
				i--;
			}
			// aliens that passed
			if(a.getTopX() < 0){
				_aliens.remove(i);
				i--;
			}
		}
		
		for(int i = 0; _aliens2 != null && i < _aliens2.size()  && i > 0 ; i++){
			Alien2 a = _aliens2.get(i);
			if(a.getTopX() > getWidth() + 1){
				_aliens2.remove(i);
				i--;
			}
			if(a.getTopX() < 0){
				_aliens.remove(i);
				i--;
			}
		}
		
		for(int i = 0; _aliensSpd != null && i < _aliensSpd.size() && i > 0; i++){
			Alien a = _aliensSpd.get(i);
			if(a.getTopX() > getWidth() + 1){
				_aliensSpd.remove(i);
				i--;
			}
			if(a.getTopX() < 0){
				_aliens.remove(i);
				i--;
			}
		}
		
		for(int i = 0; _aliensBulk != null && i < _aliensBulk.size()  && i > 0 ; i++){
			Alien2 a = _aliensBulk.get(i);
			if(a.getTopX() > getWidth() + 1){
				_aliensBulk.remove(i);
				i--;
			}
			if(a.getTopX() < 0){ // this 
				_aliens.remove(i);
				i--;
			}
		}
		
		// Check hit

		for(int j = 0; _bullets != null && j < _bullets.size() ; j++ ){
			for(int i = 0; _aliens != null && i < _aliens.size() ; i++){
				if(i < _aliens.size() && j > 0){
					Alien a = _aliens.get(i);
					Bullet b = _bullets.get(j);
					
					if(a != null && b != null){
						//g.setColor(Color.white);
						//g.drawRect(a.getTopX() +  3, a.getTopY(), 10, 6);
						if(b.getTopX() - 12 <= a.getTopX()  && b.getTopX() + 12 >= a.getTopX()
								&& b.getTopY() + 15 >= a.getTopY() && b.getTopY() - 25<= a.getTopY()){
							a.setSpeedX(-12);
							a.setSpeedY(-1);
							a.kill();
							_bullets.remove(j); 
							_score = _score + 100;
							j--;
						}
					}
				}
				
			}
			
			for(int i = 0; _aliens2 != null && i < _aliens2.size()   ; i++){
				if(i < _aliens2.size() && j > 0){ 
					Alien2 a = _aliens2.get(i);
					Bullet b = _bullets.get(j);
					
					if(a != null && b != null){
						if(b.getTopX() - 12 <= a.getTopX()  && b.getTopX() + 30 >= a.getTopX()
								&& b.getTopY() + 25 >= a.getTopY() && b.getTopY() - 45<= a.getTopY()){
							a.setSpeedX(a.getSpeedX() + 5);
							a.setSpeedY(-1);
							a.damage();
							_bullets.remove(j); 
							_score = _score + 200;
							j--;
							if(a.getHealth() <= 0){
								a.setSpeedX(-12);
								a.setSpeedY(-1);
							}
						}
					}
				}
				
			}
			
			for(int i = 0; _aliensSpd != null && i < _aliensSpd.size() ; i++){
				if(i < _aliensSpd.size() && j > 0){ 
					Alien a = _aliensSpd.get(i);
					Bullet b = _bullets.get(j);
					
					if(a != null && b != null){
						if(b.getTopX() - 13 <= a.getTopX()  && b.getTopX() + 15 >= a.getTopX()
								&& b.getTopY() + 10 >= a.getTopY() && b.getTopY() - 25 <= a.getTopY()){
							a.setSpeedX(-12);
							a.setSpeedY(-1);
							a.kill();
							_bullets.remove(j); 
							_score = _score + 500;
							j--;
						}
					}
				}
				
			}
			
			for(int i = 0; _aliensBulk != null && i < _aliensBulk.size()   ; i++){
				if(i < _aliensBulk.size() && j > 0){ 
					Alien2 a = _aliensBulk.get(i);
					Bullet b = _bullets.get(j);
					
					if(a != null && b != null){
						if(b.getTopX() - 12 <= a.getTopX()  && b.getTopX() + 30 >= a.getTopX()
								&& b.getTopY() + 45 >= a.getTopY() && b.getTopY() - 65<= a.getTopY()){
							
							if(a.getSpeedX() > 0){
								a.setSpeedX(a.getSpeedX() - 1);
							}
							a.setSpeedY(-1);
							if(a.getHealth() == 1){
								a.setSpeedY(-5);
							}
							a.damage();
							_bullets.remove(j); 
							_score = _score + 1000;
							j--;
							if(a.getHealth() <= 0){
								a.setSpeedX(-12);
								a.setSpeedY(-1);
							}
						}
					}
				}
			}	
		}
				
		//Move bullets (Shoot)
		if(_bullets != null && _pause == false){
			for(Bullet b: _bullets){
				if(b != null){
					
					/*
					 * try {
				        _laserS = AudioSystem.getClip();
				        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
				        		new File("Laser-Sound.wav"));
				        _laserS.open(inputStream);
				       // _laserS.start();
				      } catch (Exception e) {
				        System.err.println(e.getMessage());
				      }
					 */
					
					/* if we want sound */
					/*if(b.isShot()){
						/_laserS.start();
					}*/
					
					if(_life > 0){ // looks cooler when bullet is shown hovering
						b.doMove(true);
						b.shot();
					} // it looks like not when we take out this if statement
		
				}
			}
		}
		
		
		drawBoard(g);
		drawSprites(g);
		drawResults(g);
		
		if(isAKeyDown(KeyEvent.VK_P)){
			_pause = !_pause;
		}
		
		if(_pause){
			drawPause(g);
		} else{
			if(isAKeyDown(KeyEvent.VK_SPACE)){
				_start = true;
			}
			if(_start){
				//Move aliens
				
				if(_aliens != null){
			
					for(Alien a: _aliens){
						if(a.getTopY()<=WALL_SIZE+a.getSpeedY() || a.getTopY() >= getHeight() - 25 + a.getSpeedY()){
							a.setSpeedY(a.getSpeedY()*-1);
						}
						if(_won){
							a.setSpeedX(-1);
							a.setSpeedY(0);
						}
						if(a.getTopX() < 1 && !a.isThrough() && _life > 0){
							_life--;
							a.passShip();
						}
						a.doMove(true);
					}
				}
				
				if(_wave2){
					if(_aliens2 != null){
						
						for(Alien2 a: _aliens2){
							if(a.getTopY()<=WALL_SIZE+a.getSpeedY() || a.getTopY() >= getHeight() - 25 + a.getSpeedY()){
								a.setSpeedY(a.getSpeedY()*-1);
							}
							if(_won){
								a.setSpeedX(-1);
								a.setSpeedY(0);
							}
							if(a.getTopX() < 1 && !a.isThrough() && _life > 0){
								_life--;
								a.passShip();
							}
							a.doMove(true);
						}
					}
				}
				
				if(_wave3){
					if(_aliensSpd != null){
						
						for(Alien a: _aliensSpd){
							if(a.getTopY()<=WALL_SIZE+a.getSpeedY() || a.getTopY() >= getHeight() - 25 + a.getSpeedY()){
								a.setSpeedY(a.getSpeedY()*-1);
							}
							if(_won){
								a.setSpeedX(-1);
								a.setSpeedY(0);
							}
							if(a.getTopX() < 1 && !a.isThrough() && _life > 0){
								_life--;
								a.passShip();
							}
							a.doMove(true);
						}
					}
					
				}
				
				if(_wave4){
					if(_aliensBulk != null){
						
						for(Alien2 a: _aliensBulk){
							if(a.getTopY()<=WALL_SIZE+a.getSpeedY() || a.getTopY() >= getHeight() - 25 + a.getSpeedY()){
								a.setSpeedY(a.getSpeedY()*-1);
							}
							if(_won){
								a.setSpeedX(-1);
								a.setSpeedY(0);
							}
							if(a.getTopX() < 1 && !a.isThrough() && _life > 0){
								_life--;
								a.passShip();
							}
							a.doMove(true);
						}
					}
				}
				
			} else{
				drawIntro(g);
			}
		}
		
	}
	
	/** Test whether a player can move up or down
	*@param p player to test
	@param moveUpOrDown trying to move up or down
	*/
	public boolean canMove(SpaceShip p,boolean moveUpOrDown){ 
		if(moveUpOrDown){
			if(p.getTopY()<=WALL_SIZE+p.getSpeed()) return false; 
			else return true;
		}
		
		/* you can't go past the board on the right. */
		if(p.getTopX() > getWidth()){
			return false;
		}
		if(p.getTopY()+p.getSizeY()>=getHeight()-WALL_SIZE-p.getSpeed())return false;
		else return true;
		
	}

	/**
	 * Stuff to draw when we're playing
	 * @param g
	 */
	private void drawSprites(Graphics2D g){
		/*
		p1.draw(g);
		p2.draw(g);*/
		if(_playing){
			shipM.draw(g);
			for(Bullet b : _bullets){
				b.draw(g);
			}
			for(Alien a : _aliens){
				a.draw(g);
			}
			
			if(_wave2){
				for(Alien2 a : _aliens2){
					a.draw(g);
				}
			}
			if(_wave3){
				for(Alien a : _aliensSpd){
					a.draw2(g);
				}
			}
			if(_wave4){
				for(Alien2 a : _aliensBulk){
					a.draw2(g);
				}
			}
		}
		
	}

	/*
	 * Draw Background
	 */
	private  void drawBoard(Graphics2D g){
		g.setColor(new Color(1,3,60)); 
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//use mod to repeat
		if(_bgPos > -5100){
			g.drawImage(_outerSpace,_bgPos,65,this);
			//g.drawImage(_outerSpace,_bgPos + 5100,65,this);
		} else{
			_bgPos = 0;
		}
		
		if(Math.random()*10 > 5){//5
			_bgPos = _bgPos - 3;
		}
		//5272
		
		//draw top and bottom walls
		g.setColor(Color.white); 
		g.fillRect(0, 0, getWidth(), WALL_SIZE);
		g.fillRect(0, getHeight()-WALL_SIZE, getWidth(), WALL_SIZE);	
	}
	
	/**
	 * Draw the Pong board background
	 */
	private void drawResults(Graphics2D g){
		Color fill = Color.white;
		g.setColor(fill); 
		
		//Score Board
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("PolloShip Score:  " + _score, getWidth()/5 - 10, 30);
		
		g.drawString("PolloShip Life:  " + _life, getWidth()/5 - 10, 50);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
		//boolean won = false;
		
		if(_life <= 0){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("YOU LOSE!" , getWidth()/2 - 300 , getHeight()/2 - 10);
			g.drawString("Press the SPACE BAR to play again " , getWidth()/2 - 200 , getHeight()/2 + 100);
			drawPlayAgain(g);
		} 
		else{
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			
			if(_score >= 20000){
				_wave = "Wave 2";_wave2 = true;
			}
			if(_score >= 50000){
				_wave = "Wave 3"; _wave3 = true;
			}
			if(_score >= 100000){
				_wave = "Wave 4"; _wave4 = true;
			}
			if(_score >= 1000000){// 1,000,000
				g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
				g.drawString("CONGRATULATIONS, YOU WIN!  Pollo" , getWidth()/2 - 340 , getHeight()/2 - 10);
				
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g.drawString("Press the SPACE BAR to play again " , getWidth()/2 - 200 , getHeight()/2 + 100);
				// close the game to start again
				_won = true;
			}
		
			g.drawString(_wave , getWidth()/2 - 300 , getHeight()/2 - 200);
		}
	}
	private void drawPlayAgain(Graphics2D g){
		if(isAKeyDown(KeyEvent.VK_SPACE)) {
		_life = 10;
		Color fill = Color.white;
		g.setColor(fill); 
		
		//Score Board
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("PolloShip Score:  " + _score, getWidth()/5 - 10, 30);
		
		g.drawString("PolloShip Life:  " + _life, getWidth()/5 - 10, 50);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
		init();
		}
		
	}
	
	private void drawIntro(Graphics2D g){
		g.setColor(Color.white);
		g.setFont(new Font("Book Antiqua", Font.BOLD, 45));
		g.drawString("Alien Attack!" , getWidth()/2 - 150 , getHeight()/2 - 170);
		g.setFont(new Font("Book Antiqua", Font.BOLD, 20));
		g.drawString("Press SPACEBAR to Play" , getWidth()/2 - 130, getHeight()/2 - 50);
		g.drawString("Shoot ... Spacebar" , getWidth()/2 - 100, getHeight()/2 + 10);
		g.drawString("Move ... W/S or Up Key/ Down key " , getWidth()/2 - 190, getHeight()/2 + 30);
		g.drawString("Pause ... P Key" , getWidth()/2 - 90, getHeight()/2 + 50);
		g.drawString("Shoot enough Aliens to pass each wave" , getWidth()/2 - 200, getHeight()/2 + 70);
		g.drawString("Restart the Game if you don't" , getWidth()/2 - 160, getHeight()/2 + 90);
		
		g.setFont(new Font("Book Antiqua", Font.BOLD, 60));
		g.drawString("Reach 1,000,000 points to WIN!" , getWidth()/2 - 425
				, getHeight()/2 + 200);
		
	}
	
	private void drawPause(Graphics2D g){
		g.setColor(Color.white);
		g.setFont(new Font("Book Antiqua", Font.BOLD, 35));
		g.drawString("Pause" , getWidth()/2 - 70, getHeight()/2);
		
		g.drawString("Press P to Unpause" , getWidth()/2 - 150 , getHeight()/2 + 50);
	}
}
