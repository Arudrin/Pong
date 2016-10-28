/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

/**
 *
 * @author Aldrin
 */
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;




public class Racquet1 {
    
       
      
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	int x = 225;
        private int y = 0;
	int xa = 0;
      
	private Pong pong;
        private int score;

	public Racquet1(Pong pong, int y) {
		this.pong = pong;
                this.y = y;
                score = 0;
	}

	public void move() {
		if (x + xa > 0 && x + xa < pong.getWidth()-60)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, WIDTH, HEIGHT);
               
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -pong.speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = pong.speed;
                
                
                
                
                
	}
        
        public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public int getTopY() {
            
            return y;
            
	}
        
        public int getScore(){
            return score;
        }
        
        public void setScore(int score){
            this.score = score;
        }
        
        public void resetPos(){
            x = 225;
        }
}