/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package mapa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import automaton.Arbitro;
import pacman.Jogo;

public class Fantasma extends Rectangle implements Runnable {
	
	private static final long serialVersionUID = 1L;

	private static Graphics g;
	private final int ID;

	Arbitro arb = new Arbitro();
	private int random = 0, smart = 1;
	private int state = random;
	private int right = 1, left = 2, up = 3, down = 4;
	private int dir = -1;
	private boolean direcao = true;

	private boolean isRunning = false;
	Random randomGen;
	private int time = 0;
	private int targetTime = 60;
	private int spd = 2;
	
	public Fantasma(int x, int y, int id) {
		 randomGen = new Random();
		setBounds(x, y, 20, 20);
		ID = id;
		dir = randomGen.nextInt(4);
	}
	
	public synchronized void run() {
		tick();
		render();
		
	}
	
	public synchronized void render() {
		g.setColor(new Color(255, 0, 0));	//Vermelho
		g.fillRect(x, y, width, height);
		setBounds(x, y, 20, 20);
	}
	
	// Fantasmas percorrem randomicamente
	public synchronized void tick() {
		switch (getID()) {
		case 0: {
//			System.out.println("State: "+state+"\tRandom: "+random);
			if(direcao) {
				if(dir == right) {
					if(Arbitro.podeMover(x+spd, y, width, height)) {
						x+=spd*3;
					}
				} else if(dir == left) {
					if(Arbitro.podeMover(x-spd, y, width, height)) {
						x-=spd*3;
					}
				} else if(dir == up) {
					if(Arbitro.podeMover(x, y-spd, width, height)) {
						y-=spd*3;
					}
				} else if(dir == down) {
					if(Arbitro.podeMover(x, y+spd, width, height)) {
						y+=spd*3;
					}
				}
			}
			
			time++;
			if(time == targetTime/3) {
				direcao = true;
				time = 0;
				dir = randomGen.nextInt(4);
			}
			break;
		}
		case 1: {
			if(x < Jogo.player.x) {
				if(Arbitro.podeMover(x+spd, y, width, height)) {
					x+=spd;
				}
				else {
					dir = Arbitro.random();
				}
			}
			if(x > Jogo.player.x) {
				if(Arbitro.podeMover(x-spd, y, width, height)) {
					x-=spd;
				}
				else {
					dir = Arbitro.random();
				}
			}
			if(y < Jogo.player.y) {
				if(Arbitro.podeMover(x, y+spd, width, height)) {
					y+=spd;
				}
				else {
					dir = Arbitro.random();
				}
			}
			if(y > Jogo.player.y) {
				if(Arbitro.podeMover(x, y-spd, width, height)) {
					x-=spd;
				}else {
					dir = Arbitro.random();
				}
			}
			break;
		}
		case 2: {
			if(state == random) {
				if(dir == right) {
					if(Arbitro.podeMover(x+spd, y, width, height)) {
						x+=spd;
					} else {
						dir = randomGen.nextInt(4);
					}
				} else if(dir == left) {
					if(Arbitro.podeMover(x-spd, y, width, height)) {
						x-=spd;
					} else {
						dir = randomGen.nextInt(4);
					}
				}else if(dir == up) {
					if(Arbitro.podeMover(x, y-spd, width, height)) {
						y-=spd;
					} else {
						dir = randomGen.nextInt(4);
					}
				}else if(dir == down) {
					if(Arbitro.podeMover(x, y+spd, width, height)) {
						y+=spd;
					} else {
						dir = randomGen.nextInt(4);
					}
				}
				
				time++;
				if(time == targetTime) {
					state = smart;
					time = 0;
				}
				
			// Colocando um pouco de inteligencia no Fantasma
			// Fantasmas percorrem o Jogador
			} else if(state == smart) {
				// Fantasma move de encontro ao Jogador
				
				if(x < Jogo.player.x) {
					if(Arbitro.podeMover(x+spd, y, width, height)) {
						x+=spd;
					}
				}
				if(x > Jogo.player.x) {
					if(Arbitro.podeMover(x-spd, y, width, height)) {
						x-=spd;
					}
				}
				if(y < Jogo.player.y) {
					if(Arbitro.podeMover(x, y+spd, width, height)) {
						y+=spd;
					}
				}
				if(y > Jogo.player.y) {
					if(Arbitro.podeMover(x, y-spd, width, height)) {
						x-=spd;
					}
				}
				
				time++;
				if(time == targetTime/2) {
					state = random;
					time = 0;
				}
			}
			break;
		}
		case 3: 
			if(direcao) {
				if(dir == right) {
					if(Arbitro.podeMover(x+spd, y, width, height)) {	//right
						x+=spd;
						dir = right;
					}
				} else if(dir == up) { 
					if(Arbitro.podeMover(x, y-spd, width, height)) { //up
						y-=spd;
						dir = up;
					}
				}else if(dir == left) { 
					if(Arbitro.podeMover(x-spd, y, width, height)) {	//left
						x-=spd;
						dir = left;
					}
				}
				else if(dir == down) { 
					if(Arbitro.podeMover(x, y+spd, width, height)) { // down
						y+=spd;
						dir = down;
					}
				}
			}
			
			time++;
			if(time == targetTime/2) {
				direcao = true;
				time = 0;
				dir = randomGen.nextInt(4);
			}
			break;
		default:
			break;
		}
	//	if(state == random) {
			
		
//			

	}	

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public static Graphics getG() {
		return g;
	}

	public static void setG(Graphics g) {
		Fantasma.g = g;
	}

	public int getID() {
		return ID;
	}
}
