package flappyBird;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Column {
	
	private Random random;
	protected ArrayList<Rectangle> columns;
	private static flappyFrame f;
	public Rectangle bird; //add an Image instead of a rectangle, a bird
	public Image bird0;
	public int ticks, yMotion, score;
	boolean started, gameOver = false ;
	Image b;
	int speed;
	int highest = 0; 
	
	Column(){
		f = new flappyFrame();
		bird = new Rectangle((f.getW()/2)-100, (f.getH()/2)-100, 25, 25);
		bird0 = new ImageIcon("bird.png").getImage();
		random = new Random();
		columns = new ArrayList<Rectangle>();
		b= new ImageIcon("bird.png").getImage();
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

	}
		
	void addColumn(boolean start){
		int gap = 400;
		int w = 100;
		int h = 100 + random.nextInt(300);
		int bottomh = 20 + random.nextInt(200);
		
		while(bottomh + 80 > h){
		if(h>80){
				h = 100 + random.nextInt(300);
			}else if(bottomh>80){
			bottomh = 20 + random.nextInt(100);
			}
		}
		
		if(start){
			columns.add(new Rectangle(flappyFrame.W + w+ columns.size()*300, flappyFrame.H-h-100-bottomh, w, h));
			columns.add(new Rectangle(flappyFrame.W+w+(columns.size() - 1)*300, 138, w, flappyFrame.H-h-gap-50-bottomh+100));
		}else{
			columns.add(new Rectangle(columns.get(columns.size()-1).x+600, flappyFrame.H-h-100-bottomh,w,h));
			columns.add(new Rectangle(columns.get(columns.size()-1).x, 138, w, flappyFrame.H-  h-gap-50-bottomh+100));

		}
		
	}
	void paintColumn(Graphics g, Rectangle column){
		g.setColor(Color.orange);
		g.fillRect(column.x, column.y, column.width, column.height);
		g.setColor(Color.orange.darker());
		g.fillRect(column.x+10, column.y+10, column.width-20, column.height-20);
		g.setColor(Color.orange.darker().darker());
		g.fillRect(column.x-3, column.y+column.height-40, column.width+6, 40);
		g.fillRect(column.x-3, column.y, column.width+6, 40);
		g.setColor(Color.orange.darker().darker().darker());
		g.drawRect(column.x-3, column.y+column.height-40, column.width+6, 40);
		g.drawRect(column.x-3, column.y, column.width+6, 40);
		
	}

	public void paintBird(Graphics g) {
		g.setColor(Color.white.darker());
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		g.drawImage(bird0, bird.x, bird.y, null);
	}
	
	public void performAction() {
		speed = 10;
		ticks++;
		if (started && !gameOver){
			gameOver = false;
			for (int i = 0; i < columns.size(); i++){
				Rectangle column = columns.get(i);
				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15){
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++){
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0){
					columns.remove(column);

					if (column.y == 138){
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;

			for (Rectangle column : columns){
				if (column.y == 138 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10){
					if (bird.y > flappyFrame.H - 130 || bird.y < 138){
						gameOver = true;
						
					}else{
						score++;
					}
				}


				if (column.intersects(bird)){
					gameOver = true;

					if (bird.x <= column.x){
						bird.x = column.x - bird.width;
					}
					else{
						if (column.y != 0){
							//bird.y = column.y - bird.height;
						}else if (bird.y < column.height){
							bird.y = column.height;
						}
					}
				}
			}

			if (bird.y > flappyFrame.H - 130 || bird.y < 138){
				gameOver = true;
				
			}

			if (bird.y + yMotion >= flappyFrame.H - 120){
				bird.y = flappyFrame.H - 120 - bird.height;
				gameOver = true;
			}
		}
	}

	public void Checker(Graphics g) {
		g.setColor(Color.yellow);
		if (!started){ 
			g.drawString("Press space to start!", 75, flappyFrame.H / 2 - 50);
		}
		if(gameOver && gameOver == true){
			g.drawString("Game Over!",flappyFrame.W /2-350, flappyFrame.H/2);
		}
		
		if(started){
			g.drawString(String.valueOf(score), flappyFrame.W / 2 - 25, flappyFrame.H-40);
		}
		
		if(highest == 0 ){
			highest = score;
		}
		
		if(highest < score){
			highest = score;
		}
		if(gameOver){
			g.drawString("Highest Score: ",flappyFrame.W /2-400, flappyFrame.H/2+100);
			g.drawString(String.valueOf(highest), flappyFrame.W /2+310, flappyFrame.H/2+100);
		}
		
	}

	public void Jump() {
		if (!started){
			started = true;
		}
		if(gameOver){
			bird = new Rectangle((f.getW()/2)-100, (f.getH()/2)-100, 25, 25);
			columns.clear();
			yMotion = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}
		if (!started){
			started = true;
		}else if (!gameOver){
			if (yMotion > 0){
				yMotion = 0;
			}
			yMotion -= 10;
		}
	}
}
