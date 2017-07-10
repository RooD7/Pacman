/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package automaton;

import java.awt.Rectangle;
import java.util.Random;

import mapa.Fundo;
import pacman.Jogo;


public class Arbitro {

	private static AFD auto;
	
	public Arbitro() {
		auto = new AFD();
	}
	
	public static int random() {
		Random rand = new Random();
		int n = (rand.nextInt(4))+1;
		if(auto.verificaMovimento(n))
			return n;
		return 0;
	}
	
	// Cheque de Colisao
	public synchronized static boolean podeMover(int nextX, int nextY, int width, int height) {
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

	public AFD getAuto() {
		return auto;
	}

	public void setAuto(AFD auto) {
		Arbitro.auto = auto;
	}	
}
