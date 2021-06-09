package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import world.Camera;

public class Pellets extends Entity{

	public Pellets(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		this.maskX = this.maskY = 7;
		// TODO Auto-generated constructor stub
	}
	
	public void render(Graphics g) {
		
		renderMask(g);
		g.setColor(Color.orange);
		g.fillRect((int)(x - Camera.x) + maskX, (int)(y - Camera.y) + maskY, width, height);
	}

}
