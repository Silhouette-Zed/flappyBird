package flappyBird;

import java.awt.Color;

import javax.swing.JFrame;

public class flappyMain {

	static int W=1200;
	static int H=1000;
	static flappyPanel flappyPanel;
	static flappyFrame flappyFrame;
	
	public static void main(String[] args) {

		flappyPanel = new flappyPanel();
		flappyFrame flappyFrame = new flappyFrame(flappyPanel);
		flappyFrame.setTitle("Flappy Bird Game");
		flappyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		flappyFrame.setResizable(false);
		flappyFrame.setW(W);
		flappyFrame.setH(H);
		flappyFrame.setSize(W,H);
		flappyFrame.setVisible(true);
	}

}









