import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public int x, y;
	public int vx, vy;
	public int radius;
	private Color c = new Color(255, 0, 255);
	public double a;
	
	public Player() {
		
		//make radius kind of big for now
		
		radius = 30;
		x = 800/2-radius;
		y = 600/2-radius;
		
	}
	


	public void updatePos(int pvx, int pvy) {
		x -= pvx;
		y -= pvy;
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
	

	public void eat(double newRadius) {
        a = (Math.PI * radius * radius) + (Math.PI * newRadius * newRadius);
        newRadius = Math.sqrt(a/Math.PI);
        radius = (int) newRadius;
    }

	public void paint(Graphics g) {
		
		update();
		g.setColor(c);
		g.fillOval(x, y, radius*2, radius*2);
		
	
	}


public void update() {

}




}
