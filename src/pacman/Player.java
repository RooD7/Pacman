/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import mapa.Fantasma;
import mapa.Fundo;

public class Player extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	private boolean right, left, up, down;
	private int speed;
	
	public Player(int x, int y) {
		
		setBounds(x, y, 16, 16);
		
		
		this.right = false;
		this.left = false;
		this.up = false;
		this.down = false;
		this.speed = 4;
	}

	public void tick() {
		if(isRight() && podeMover(x+speed, y)) 	x += speed;
		if(isLeft() && podeMover(x-speed, y))	x -= speed;
		if(isUp() && podeMover(x, y-speed))		y -= speed;
		if(isDown() && podeMover(x, y+speed))	y += speed;
		//System.out.println("("+x+", "+y+")");
		
		// Pegando as Frutas
		Fundo fundo = Jogo.fundo;
		for (int i = 0; i < fundo.getFrutas().size(); i++) {
			if(this.intersects(fundo.getFrutas().get(i))) {
				fundo.getFrutas().remove(i);
				break;
			}
		}
		// Pegou todas as Frutas -- FIM DE JOGO
		if(fundo.getFrutas().size() == 0) {
			JOption.showFimJogoWin();
			Jogo.player = new Player(0, 0);
			Jogo.fundo = new Fundo("/map/mapaPacman2.png");
			return;
		}
		// Fantasmas encontraram o jogador -- FIM DE JOGO
		for (int i = 0; i < Jogo.fundo.getFantasmas().size(); i++) {
			Fantasma fan = Jogo.fundo.getFantasmas().get(i);
			if(fan.intersects(this)) {
				JOption.showFimJogoLose((Fundo.getNumFrutas() - fundo.getFrutas().size())*10);
				Jogo.player = new Player(0, 0);
				Jogo.fundo = new Fundo("/map/mapaPacman2.png");
			}
		}
	}
	
	// Cheque de Colisao
	private boolean podeMover(int nextX, int nextY) {
		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Fundo fundo = Jogo.fundo;
		
		for (int xx = 0; xx < fundo.getBlocos().length; xx++) {
			for (int yy = 0; yy < fundo.getBlocos(0).length; yy++) {
				if(fundo.getBloco(xx, yy) != null) {
					if(bounds.intersects(fundo.getBloco(xx, yy))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 20);
	}
	
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
}
