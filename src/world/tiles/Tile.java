package world.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pacman.Game;
import world.Camera;

public class Tile {

		public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
		public static BufferedImage TILE_FLOOR2 = Game.spritesheet.getSprite(16, 0, 16, 16);
		public static BufferedImage TILE_FLOOR3 = Game.spritesheet.getSprite(32, 0, 16, 16);
		public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(48, 0, 16, 16);
		
		private BufferedImage sprite;
		protected int x,y;
		
		public Tile(int newX, int newY, BufferedImage newSprite) {
			
			this.x = newX;
			this.y = newY;
			this.sprite = newSprite;
		}
		
		public void render(Graphics g) {
			
			g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
		}
}
