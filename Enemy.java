import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	
	//instance variables
	private int x, y; //location
	private int radius;//radius
	private int vx, vy; //movement variables
	private double sx, sy;
	private Color color; //color
	private double a;
	
	//add the constructor header and body
	//give initial values for x, y, and a radius of 50
	
	
	public Enemy() {
		
		//randomize the location of every
		//Enemy object to be in the 800x600 frame
		
		x = (int)(Math.random()*1500);
		y = (int)(Math.random()*1500);
		vx = (int)(Math.random()*7)-3;
		vy = (int)(Math.random()*7)-3;
		radius = (int)(Math.random()*40)+10;
		
		//randomize enemy color
		
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);
		color = new Color(red, green, blue);
		
		//speed
		
		if (radius <= 10) {
            sx = 5;
            sy = 5;
        }
        if (radius <= 20) {
            sx = 4;
            sy = 4;
        }
        if (radius <= 30) {
            sx = 3;
            sy = 3;
        }
        if (radius <= 40) {
            sx = 2;
            sy = 2;
        }

		
		//randomize vx and vy to be nonzero between [-3 3]
		
		while(vx == 0) {
			vx = (int)(Math.random()*7)-3;
		}
		while (vy == 0) {
			vy = (int)(Math.random()*7)-3;
		}
		
		if ( x >= 2000) {
			vx *= -1;
		}
		
		if ( y >= 2000) {
			vy *= -1;
		}
	}
	
	//getters and setters
	
	public int getX() {
		return x;
	}	
	public void setX(int newX) {
		x = newX;
	}	
	public void setY(int newY) {
		y = newY;
	}	
	public int getRadius() {
		return radius;
	}
	
	
	public boolean collide(Enemy e2) {
		//need center of objects
		int x1 = this.x + radius/2;
        int y1 = this.y + radius/2;
		
		int x2 = e2.x + radius;
		int y2 = e2.y + radius/2;

		//find the distance
		double d = Math.hypot(x1-x2,  y1-y2);
		
		if (d < (this.radius+e2.radius)) {
		return true;
		} return false;
	}
	
	public boolean collide(Food f) {
		//need center of objects
		int x1 = this.x + radius/2;
        int y1 = this.y + radius/2;
		
		int x2 = f.x + radius;
		int y2 = f.y + radius/2;

		//find the distance
		double d = Math.hypot(x1-x2,  y1-y2);
		
		if (d < (this.radius+f.radius)) {
		return true;
		} return false;
	}
	
	public boolean collide(Player p) {
		//need center of objects
		int x1 = this.x + radius/2;
        int y1 = this.y + radius/2;
		
		int x2 = p.x + radius;
		int y2 = p.y + radius/2;

		//find the distance
		double d = Math.hypot(x1-x2,  y1-y2);
		
		if (d < (this.radius+p.radius)) {
		return true;
		} return false;
	}
	public void eat(double newRadius) {
        a = (Math.PI * radius * radius) + (Math.PI * newRadius * newRadius);
        newRadius = Math.sqrt(a/Math.PI);
        radius = (int) newRadius;
    }
	
	
	public void updatePos(int pvx, int pvy) {
		x -= pvx;
		y -= pvy;
	}
	
	
	public void paint(Graphics g) {
		
		update(); //call method that helps with updating vars
		
		
		//call fillOval here
		g.fillOval(x, y, radius*2, radius*2);
		g.setColor(color);
	


	}


	/* anything that updates the variables of this object */
	public void update() {
		x += vx;
		y += vy;
		
	}


	

}
