/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package pacman;

import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		//Mapa mapa = new Mapa();
		//Mapa mapa2 = new Mapa();
		
		//mapa.geradorDeMapa("mapa.txt");
		//mapa.imprimeMapa();

		Jogo jogo = new Jogo();
		JFrame frame = new JFrame();
		frame.setTitle(Jogo.TITLE);
		frame.add(jogo);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		jogo.start();
	}
	 
}
