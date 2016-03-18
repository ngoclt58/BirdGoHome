package com.t3h.birdandbomb;

public class Bird {
	private static final int SPEED_MOVE = 10;
	private static final int HEART=3;
	private int x,y;
	
	public Bird(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move() {
		
	}
	
	public void inToaDo() {
		System.out.println("Toa do cua chim: " +x + "- " +y);
	}
}
