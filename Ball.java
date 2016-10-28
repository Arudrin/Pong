/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;


/**
 *
 * @author Aldrin
 */
public class Ball {
    
        private static final int DIAMETER = 30;
        int x = 225;
	int y = 250;
	int xa = 0;
	int ya = 1;
        int score1 = 0; // added this in an effort to fix the scoring error in README, but it did not work
        int score2 = 0;//
	private Pong pong;

	public Ball(Pong pong) {
		this.pong = pong;
	}
        
        public Ball(Pong pong, int ya, int y, int score1, int score2) { //constructor for it to change its position depending on who last scored. And stores previous scores.
		this.pong = pong;
                this.ya = ya;
                this.y = y;
                this.score1 = score1;
                this.score2 = score2;
	}
        

	void move() {
		if (x + xa < 0)
                { 
			xa = pong.speed;
                }
                else if (x + xa > pong.getWidth() - DIAMETER)
                {
			xa = -pong.speed;
                }
                else if (y + ya < 0)
                {
                        score1++; // adds score if it gets past
			pong.racquet1.setScore(score1);
                        if( pong.racquet1.getScore() == 3) // if score gets to 3 then its game over
                        {
                            pong.repaint(); // repaint so that it display final score before ending the game
                            pong.gameOver();
			    //resets score and everything just in case "gameOver()" doesn't end the game.
                            pong.racquet1.setScore(0);
                            pong.racquet2.setScore(0);
                            pong.racquet1.resetPos();
                            pong.racquet2.resetPos();
                            
                        }
                        //resets ball and speed
                        pong.ball = new Ball(pong, -pong.speed, 450, score1, score2);
                        pong.speed = 1;
                        
                        
                }
                else if (y + ya > pong.getHeight() - DIAMETER)
                {
			//same logic above
                        score2++;
			pong.racquet2.setScore(score2);
                        if( pong.racquet2.getScore() == 3)
                        {
                           pong.repaint();
                           pong.gameOver();
                           pong.racquet1.setScore(0);
                           pong.racquet2.setScore(0);
                           
                            pong.racquet1.resetPos();
                            pong.racquet2.resetPos();
                        }
                         pong.ball = new Ball(pong, pong.speed, 30, score1, score2);
                         pong.speed = 1;
                }
                else if(collision1()) //racquet1
                {
                        xa = - pong.speed; // reverses direction after hitting
			ya = -pong.speed;
			y = pong.racquet1.getTopY() - DIAMETER;
                        if(pong.speed < 5){ // I limited the max speed since its hard to play w/ higer speeds
                            pong.speed++; // increases speed everytime ball collides
                        }
                     
		}
                else if(collision2()){ //racquet2 same logic above
                    
                    xa = pong.speed;
                    ya = pong.speed;
                    y = pong.racquet2.getTopY() + DIAMETER;
                     if(pong.speed < 5){
                            pong.speed++;
                        }
                }
                
           
			//moves after x and y values have been finalized.
		x = x + xa; 
		y = y + ya;
	}

	private boolean collision1() {
		return pong.racquet1.getBounds().intersects(getBounds());
	}
        
        private boolean collision2() {
		return pong.racquet2.getBounds().intersects(getBounds());
	}
        
        

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
    
}
