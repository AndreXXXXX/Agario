import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food {
   public int x, y;
   public int radius;
   private Color color;
   public double a;

   public Food() {
    x = (int)(Math.random()*2000);
    y = (int)(Math.random()*2000);
 
    color = new Color(0, 255, 0);
    radius = 15;

   }
	public void updatePos(int pvx, int pvy) {
		x -= pvx;
		y -= pvy;
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
        a = (Math.PI * radius * radius) + 1;
        newRadius = a;
        radius = (int) newRadius;
    }
	
public void paint(Graphics g) {

        //call method that helps with updating vars
   update();

       //call fillOval here
       g.fillOval(x, y, radius, radius);
       g.setColor(color);



   }
public void update() {

}



}
