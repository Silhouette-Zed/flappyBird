package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class flappyPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Column c;
	
	flappyPanel(){
		super();
		System.out.println("1");
		c = new Column();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image image  = new ImageIcon("bg1.jpg").getImage();
		g.drawImage(image, 0, 0, null);
		draw(g);
		draw(g);
	}
	
	public void draw(Graphics g){
		c.paintBird(g); 
		
		for (Rectangle column : c.columns){
			c.paintColumn(g, column);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
		
		c.Checker(g);
	}
	
	public void jump(){
		c.Jump();
	}
	public void actionBridge() {
		c.performAction();
	}
}
