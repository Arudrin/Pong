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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;


public class Pong extends JPanel {
    
        Ball ball = new Ball(this);
        Racquet1 racquet1 = new Racquet1(this, 440);
        Racquet1 racquet2 = new Racquet1(this, 20);
        int speed = 1;
        public int option;
        
       
        @Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
                g2d.setColor(Color.PINK);
                ball.paint(g2d);
                g2d.setColor(Color.BLACK);
                racquet1.paint(g2d);
                racquet2.paint(g2d);
                
                g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(racquet2.getScore()), 10, 30);
                g2d.drawString(String.valueOf(racquet1.getScore()), 10, 465);

	}
        
        
	public Pong() {
		addKeyListener(new KeyListener() { //keylistener for racquet 1
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet1.keyReleased(e);
                               
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet1.keyPressed(e);
                                
			}
		});
                
                addKeyListener(new KeyListener() { // for racquet 2
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet2.keyReleased(e);
                               
			}

			@Override
			public void keyPressed(KeyEvent e) { // added the if statements here since if I put it in the Racquet class
							     // two racquets move instead.
			
                            if (e.getKeyCode() == KeyEvent.VK_A  )
                                racquet2.xa = -speed;
                            if (e.getKeyCode() == KeyEvent.VK_D)
                                racquet2.xa = speed;
                                
			}
		});
		setFocusable(true);
	}
	
	private void move() { // moves all sprites
		ball.move();
		racquet1.move();
                racquet2.move();
	}
        
       public void gameOver() {
                      option = JOptionPane.showConfirmDialog(this, "TRY AGAIN?", 
                              "A NOOB JUST LOST",  JOptionPane.YES_NO_OPTION) ; 
                         
                      if(option == JOptionPane.NO_OPTION){
                          System.exit(ABORT);
                      }
                      
	}

	public static void main(String[] args) throws InterruptedException {
            
            
		JFrame frame = new JFrame("Mini Tennis"); // Creates JFrame object called frame with a title Mini Tennis
                Pong pong = new Pong();
                frame.add(pong);
                
                frame.setResizable(false); // no maximizing
		frame.setSize(450, 500); 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes the program stop running after its closed.
                
                
                while (true) {
                    
			pong.move();
			pong.repaint();
			Thread.sleep(10);
                        
                        
                        
		}
	}
        
        
  
}
