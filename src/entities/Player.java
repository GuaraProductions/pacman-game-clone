package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pacman.Game;
import world.Camera;
import world.World;

public class Player extends Entity{

	//Variaveis de movimento
	public boolean right,up,left,down;
	private byte   dir;
	public double  speed;
	
	//Variaveis de estatísticas
	private int     pontos;
	public int      numPellets;
	
	//Variaveis de animacao do movimento
	private byte frames    = 0;
	private byte maxFrames = 4;
	private byte index     = 0;
	private byte maxIndex  = 1;
	private boolean moved  = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	public int getPontos() {
		return this.pontos;
	}

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		
		//Chamando construtor da classe Entity
		super(x, y, width, height, speed, sprite);
		depth   = 1;
		pontos  = 0;
		
		this.speed = 2.0;
		
		byte numOfSprites = (byte)(maxIndex +1);
		
		//Iniciando todos arrays de sprites 
		rightPlayer = new BufferedImage[numOfSprites];
		leftPlayer  = new BufferedImage[numOfSprites];
		upPlayer    = new BufferedImage[numOfSprites];
		downPlayer  = new BufferedImage[numOfSprites];
		
		//Inserindo todos os sprites em todos vetores
		for (int i = 0; i < numOfSprites; i++ ) 
			rightPlayer[i] = Game.spritesheet.getSprite((i)*16, 32, 16, 16);
	
		leftPlayer[0] = Game.spritesheet.getSprite(0, 48, 16, 16);
		upPlayer[0]   = Game.spritesheet.getSprite(0, 64, 16, 16);
		downPlayer[0] = Game.spritesheet.getSprite(0, 80, 16, 16);
		leftPlayer[1] = upPlayer[1] = downPlayer[1] = rightPlayer[1];
		
	}

	public void tick() {
		
		System.out.println(getX());
		
		movement();	
		checkCollision();
		updateCamera();
	}
	
	public void checkCollision() {
		
		for (Entity current : Game.entities) {
			if(current instanceof Cherry && isColidding(this,current)) {
				Game.entities.remove(current);
				pontos += 50;
				return;
				
			} else if (current instanceof Pellets && isColidding(this,current)) {
				numPellets++;
				Game.entities.remove(current);
				pontos += 10;
				return;
				
			}
		}
	}
	
	public void movement() {
		
		if(right && World.isFree((int)(x+speed),(int)y)) {
			
			moved = true;
			dir = 0;
			x+=speed;
		}
		else if(left && World.isFree((int)(x-speed),(int)y)) {
			
			moved = true;
			dir = 1;
			x-=speed;
			
		} else {
			index = 0;
			
		}
		if (up && World.isFree((int)x,(int)(y-speed))) {
			
			moved = true;
			dir = 2;
			y-=speed;
		}
		else if (down && World.isFree((int)x,(int)(y+speed))) {
			
			moved = true;
			dir = 3;
			y+=speed;
			
		} else {
			index = 0;
			
		}
		
		if (moved) {
			
			moved = false;
			frames++;
			if (frames == maxFrames) {
				
				frames = 0;
				index++;
				if ( index > maxIndex) {
					
					index = 0;
				}
			}
		}
	}
	
	public void updateCamera() {
		
		//Configurando a camera para seguir o jogador
		Camera.x = Camera.clamp((int)x - (Game.getWIDTH()/2),0,(World.WIDTH*16) - Game.getWIDTH());
		Camera.y = Camera.clamp((int)y - (Game.getHEIGHT()/2),0,(World.HEIGHT*16) - Game.getHEIGHT());	
	}
	
	//Renderizar o jogador na tela
	public void render(Graphics g) {

		switch(dir) {
		
			case 0: // direita
				
				g.drawImage(rightPlayer[index],(int)x - Camera.x, (int)y - Camera.y , null);
				break;
			case 1: // esquerda
				
				g.drawImage(leftPlayer[index],(int)x - Camera.x , (int)y - Camera.y , null);
				break;
			case 2: // cima
				
				g.drawImage(upPlayer[index],(int)x - Camera.x , (int)y - Camera.y , null);
				break;
			case 3: // baixo
				
				g.drawImage(downPlayer[index],(int)x - Camera.x , (int)y - Camera.y , null);
				break;
			default:
				
				g.drawImage(rightPlayer[index],(int)x - Camera.x, (int)y - Camera.y , null);
				break;
		}
		
	}
	
}
