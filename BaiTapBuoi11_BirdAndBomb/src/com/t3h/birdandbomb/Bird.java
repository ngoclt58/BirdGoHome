package com.t3h.birdandbomb;

public class Bird {
	
	private int x,y;
	
	public Bird(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public void move() {
		
	}
	
	public void inToaDo() {
		System.out.println("Toa do cua chim: " +x + "- " +y);
	}
}
