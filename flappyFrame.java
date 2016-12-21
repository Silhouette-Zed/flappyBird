package flappyBird;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class flappyFrame extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	protected static int W;
	protected static int H;
	private flappyPanel flappyP;
	public Image bird0;
	flappyFrame(){}

	flappyFrame(flappyPanel fPanel){
		flappyP = fPanel;
		fPanel.setVisible(true);
		addKeyListener(this);
		add(fPanel);
		Timer timer = new Timer(20, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		flappyP.actionBridge();
		flappyP.repaint();
	}
	
	void createBird(){
		bird0 = new ImageIcon("bird.png").getImage();
		getGraphics().drawImage(bird0,(getW()/2)-100, (getH()/2)-100, null);
	}
	
	Image returnBird(){
		return bird0;
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			flappyP.jump();
		}	
	}

	public void keyTyped(KeyEvent e) {}

	public void setW(int w) {
		flappyFrame.W = w; 
	}
	
	public int getW() {
		return W; 
	}

	public void setH(int h) {
		flappyFrame.H = h; 
	}
	public int getH() {
		return H;

	}
}
