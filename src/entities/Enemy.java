package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import pacman.Game;
import world.Gamestate;
import world.pathfinding.*;

public class Enemy extends Entity{
	
	//Configurando variaveis de animacao
	private int frames = 0,maxFrames = 10,index = 0,maxIndex = 1,deathFrames = 10;
	
	//Variaveis de estatísticas
	private int life = 1;
	private boolean isDamaged = false;
	
	public Enemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		
		super(x, y, width, height, speed, sprite);
		
		depth = 0;
		
	}
	
	public void tick() {
		
		Astarmovement();
		//movement();
			
		frames++;
		if (frames == maxFrames) {
			
			frames = 0;
			index++;
			if ( index > maxIndex) {
				
				index = 0;
			}
		}
		
		if(isDamaged) {
			
			deathFrames++;
			if( deathFrames >= 10) {
				
				isDamaged = false;
			}
		}
		
	}
	
	public void Astarmovement() {

		if(!isAttackingPlayer()) {
			
			if(path == null || path.size() == 0) {
				
				Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
				Vector2i end = new Vector2i((int)(Game.player.getX()/16),(int)(Game.player.getY()/16));
				path = Pathfinding.findPath(Game.world,start,end);
			}
		}
		else {
			Game.gamestate = Gamestate.gameover;
			
		}
			
		followPath(path);
	}
	
	public void destroySelf() {
		
		Game.entities.remove(this);
	}
	
	public boolean isAttackingPlayer() {
		
		Rectangle enemyColision = new Rectangle((int)(x) ,(int)(y) ,width, height);
		Rectangle playerColision = new Rectangle((int)(Game.player.getX()) ,(int)(Game.player.getY()) ,16, 16);
		
		return enemyColision.intersects(playerColision);
	}
	
}
