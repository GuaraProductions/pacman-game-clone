package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import pacman.Game;
import world.Camera;
import world.pathfinding.*;

public class Entity {
	
	public static BufferedImage CHERRY = Game.spritesheet.getSprite(0, 112, 16, 16);
	public static BufferedImage ENEMY1 = Game.spritesheet.getSprite(0, 96, 16, 16);
	public static BufferedImage ENEMY2 = Game.spritesheet.getSprite(16, 96, 16, 16);
	public static BufferedImage ENEMY3 = Game.spritesheet.getSprite(32, 96, 16, 16);
	public static BufferedImage ENEMY4 = Game.spritesheet.getSprite(48, 96, 16, 16);
	
	protected double x,y;
	protected int    maskX,maskY;
	protected int    width,height;
	protected double speed;

	public Random gerador;					
	
	public byte depth;
	
	protected ArrayList<Node> path;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		this.x      = x;
		this.y      = y;
		this.maskX  = maskY = 0;
		this.width  = width;
		this.height = height;
		this.speed  = speed;
		this.sprite = sprite;
		
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public BufferedImage getSprite() {
		return this.sprite;
	}
	
	public static Comparator<Entity> entitySorter = new Comparator<Entity>() {
		
		@Override
		public int compare(Entity t0, Entity t1) {

			if (t1.depth < t0.depth) {
				return + 1;
			}
			if (t1.depth > t0.depth) {
				return - 1;
			}
			return 0;
		}
	};
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public int getType() {
		
		return 0;
	}
	
	public void tick() {

	}
	
	public double calculateDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
	}
	
	public void followPath(ArrayList<Node> path) {
		
		if ( path != null) {
			
			if ( path.size() > 0) {
				
				Vector2i target = path.get(path.size() - 1).tile;
				if ( x < target.x * 16 ) {
					
					x++;
				}
				else if ( x > target.x * 16) {
					
					x--;
				}
				if ( y < target.y * 16 ) {
					
					y++;
				}
				else if ( y > target.y * 16) {
					
					y--;
				}
				if ( x == target.x * 16 && y == target.y * 16) {
					
					path.remove(path.size()-1);
				}
			}
		}
	}
	
	//Conferir colisao entre duas entidades
	public static boolean isColidding(Entity e1,Entity e2) {
		
		Rectangle e1Mask = new Rectangle((int)(e1.x + e1.maskX),(int)(e1.y + e1.maskY),e1.width,e1.height);
		Rectangle e2Mask = new Rectangle((int)(e2.x + e2.maskX),(int)(e2.y + e2.maskY),e2.width,e2.height);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite,(int)x - Camera.x, (int)y - Camera.y, null);
	}
	
	protected void renderMask(Graphics g) {
		
		//Funcao para renderizar mascara das entidades(Funcao de debug)
		g.setColor(Color.blue);
		g.fillRect((int)(x - Camera.x) + maskX, (int)(y - Camera.y) + maskY, width, height);
	}
}
