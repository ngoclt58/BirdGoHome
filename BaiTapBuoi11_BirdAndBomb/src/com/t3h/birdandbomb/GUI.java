package com.t3h.birdandbomb;

import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GUI extends JFrame{
	private MyPanel myPanel;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 450;
	private String move = "";
	public GUI() {
		initGUI();
		initComps();
		addComps();
		exit();
		music();
	}

	private void music() {
		Sound_sdjv sound=new Sound_sdjv("D:/ahihi.wav");
		sound.start();
	}

	private void exit() {
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dispose();
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					move = "left";
					myPanel.moveLeft();
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					move = "right";
					myPanel.moveRight();
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					move = "left";
					myPanel.moveUp();
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					move = "right";
					myPanel.moveDown();
				}
			}
		};
		addKeyListener(keyAdapter);
	}

	private void initComps() {
		myPanel = new MyPanel();
	}

	private void addComps() {
		add(myPanel);
	}

	private void initGUI() {
		setTitle("Bird and Bomb!");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		
		setLayout(new CardLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
