import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Driver extends JPanel implements MouseListener, ActionListener{
	
	//Create an array of enemies
	Enemy[] enemies = new Enemy[100];
	Food[] food = new Food[400];
	//
	Player p = new Player();
	
	
	public void paint(Graphics g) {
		
		super.paintComponent(g); //proper redrawing of the entire screen
		
				
		//find mx, my
		int mx = MouseInfo.getPointerInfo().getLocation().x;
		int my = MouseInfo.getPointerInfo().getLocation().y;
		
		//find px, py
		//must be the center of the Player object not the corner
		int px = p.x + p.radius;
		int py = p.y + p.radius;
		
		double theta = Math.atan((mx - px)/(my - py));
		
		p.vx = (int) ((100/p.radius)*Math.sin(theta)) + 1;
		p.vy = (int) ((100/p.radius)*Math.cos(theta)) + 1;
		
		p.vx = Math.abs(p.vx);
		p.vy = Math.abs(p.vy);
		
		//fix direction
		//need to switch direction of sign
		//if the mouse is to the left of the player object
		if (mx < px + p.radius) { 
			p.vx *= -1;
		}
		
		//when does player need to go up
		//any time mouse is above player
		if (my < py + p.radius) { 
			p.vy *= -1;
		}
		
		System.out.println(p.vx + ":" + p.vy);
				
		p.paint(g);

		
		//traverse the enemies array and invoke the paint method 
		//of each Enemy object
		
		//using a for-each loop (enhanced for loop)
		for(Enemy en : enemies) { //for every Enemy en in enemies
			
			en.updatePos(p.vx, p.vy);
			en.paint(g);
		}
		
		for(Food fo : food) {
			
			fo.updatePos(p.vx, p.vy);
			fo.paint(g);
		}
		
		//collision between player and food
		
//		for (int i = 0; i < food.length; i++) {
//			Player p = new Player();
//		    Food f = food[i];
			
//			if(p.collide(f));
			
//			p.eat(f.getRadius());
//			 f.setX((int)(Math.random()*(20000))+10000);
//			 f.setY((int)(Math.random()*(20000))+10000);
//		}
		
		//collision between player and enemy
		
//		for (int i = 0; i < enemies.length; i++) {
//			Enemy e = enemies[i];
//			Player p = new Player();
			
//			if (e.collide(p)) {

//               if (e.getRadius() > p.getRadius()) {
                	
//                	e.eat(p.getRadius());
                	
//                	 p.setX((int)(Math.random()*(20000))+10000);
 //                    p.setY((int)(Math.random()*(20000))+10000);
                     
                     //grow bigger
                    

//			}else {
            	
            	//make smaller cell disappear
				
//				p.eat(e.getRadius());
            	
//            	e.setX((int)(Math.random()*(20000))+10000);
//                e.setY((int)(Math.random()*(20000))+10000);
               
                //grow bigger

                

//               }
//		}
//		}
		
		
		//collision between food and enemies
		
		for (int i = 0; i < enemies.length; i++) {
			for (int j = i + 1; j < food.length; j++) {
				Enemy e = enemies[i];
				Food f = food[j];
				
				//if enemies and food collide
				//the enemy gets bigger
				//the food disappears
				
				if(e.collide(f)) {
					
					e.eat(f.getRadius());
					 f.setX((int)(Math.random()*(20000))+10000);
					 f.setY((int)(Math.random()*(20000))+10000);
				}
			}
		}
		
		//collision between enemies and enemies
		
		for (int i = 0; i < enemies.length; i++) {
            for (int j = i + 1; j < enemies.length; j++) {
                Enemy e1 = enemies[i];
                Enemy e2 = enemies[j];
                
                //if 2 enemies collide, print collide
                //make bigger cell bigger
                //make smaller cell disappear

                if(e1.collide(e2)) {
            System.out.println("collide");
            
                    //make smaller cell disappear
            
                    if (e1.getRadius() > e2.getRadius()) {
                    	 e2.setX((int)(Math.random()*(20000))+10000);
                         e2.setY((int)(Math.random()*(20000))+10000);
                         
                         //grow bigger
                         
                         e1.eat(e2.getRadius());
                        


                } else {
                	
                	//make smaller cell disappear
                	
                	e1.setX((int)(Math.random()*(20000))+10000);
                    e1.setY((int)(Math.random()*(20000))+10000);
                    
                    //grow bigger
                    
                    e2.eat(e1.getRadius());
                    

                    }
                }
                }
        }
		}
		
	

	

	public Driver(){
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800,600);
		frame.add(this);
		
		/* add 50  Enemies */
		for(int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy(); //creates an object each iteration //add it to location i
		}
		for(int i = 0; i < food.length; i++) {
            food[i] = new Food(); //creates an object each iteration //add it to location i
        }
		//

	
		Timer t = new Timer(16, this); //chose swing library for import
		t.start();
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("here");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint(); //Timer will invoke this method which then refreshes the screen
				   // for the "animation"
	}
	
}
