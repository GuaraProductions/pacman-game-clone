package world;

import pacman.Game;

public class Camera {

	public static int x = (int)Game.player.getX() ,y = (int)Game.player.getY();
	
	public static int clamp(int xAtual,int xMin,int xMax) {
		if (xAtual < xMin) {
			xAtual = xMin;
		}
		if (xAtual > xMax) {
			xAtual = xMax;
		}
		
		return xAtual;
	}
	public static int getX() {
		
		int xstart = x >> 4,xfinal = xstart + (Game.getWIDTH() >> 4);
		return xfinal - xstart;
	}
	
	public static int getY() {
		
		int ystart = y >> 4,yfinal = ystart + (Game.getWIDTH() >> 4);
		return yfinal - ystart;
	}
}
