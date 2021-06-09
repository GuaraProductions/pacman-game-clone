package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import pacman.Game;

public class UI {
	
	/**
	 * Renderiza a pontuação do player
	 * @param g é o objeto que irá renderizar os objetos na tela
	 */
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.setFont(new Font("arial", Font.BOLD, 9));
		g.drawString("Pontos: " + Game.player.getPontos(), 5, 8);
	}
	
	/**
	 * Renderiza a tela de pausa 
	 * @param g é o objeto que irá renderizar os objetos na tela
	 * @param showMessage é a condicional se o programa pode mostrar a mensagem
	 * na tela
	 */
	public void renderPause(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, Game.getWIDTH()*Game.SCALE, Game.getHEIGHT()*Game.SCALE);
		
		g.setColor(Color.green);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString("Pausado", 80, 80);
	}
	

	/**
	 * Renderiza a tela de game over
	 * @param g é o objeto que irá renderizar os objetos na tela
	 * @param showMessage é a condicional se o programa pode mostrar a mensagem
	 * na tela
	 */
	public void renderWin(Graphics g, boolean showMessage) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, Game.getWIDTH()*Game.SCALE, Game.getHEIGHT()*Game.SCALE);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString("Você Ganhou!", 52, 85);
		
		if (showMessage) {
			g.setColor(Color.green);
			g.setFont(new Font("arial", Font.BOLD, 9));
			g.drawString("Pressione \'Enter\' para continuar...", 22, 110);
			
		}
			
	}
	
	/**
	 * Renderiza a tela de game over
	 * @param g é o objeto que irá renderizar os objetos na tela
	 * @param showMessage é a condicional se o programa pode mostrar a mensagem
	 * na tela
	 */
	public void renderGameOver(Graphics g, boolean showMessage) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, Game.getWIDTH()*Game.SCALE, Game.getHEIGHT()*Game.SCALE);
		
		g.setColor(Color.red);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString("Você Perdeu!", 52, 85);
		
		if (showMessage) {
			g.setColor(Color.green);
			g.setFont(new Font("arial", Font.BOLD, 9));
			g.drawString("Pressione \'Enter\' para continuar...", 40, 100);
			
		}
	}
}
