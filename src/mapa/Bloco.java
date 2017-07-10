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

public class Bloco extends Rectangle {
	
	private static final long serialVersionUID = 1L;

	public Bloco(int x, int y) {
		setBounds(x, y, 20, 20);
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 128));	//Azul Escuro
		g.fillRect(x, y, width, height);
		setBounds(x, y, 20, 20);
	}
}
