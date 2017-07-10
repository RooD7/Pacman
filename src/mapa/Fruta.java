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

public class Fruta extends Rectangle {

	private static final long serialVersionUID = 1L;

	public Fruta(int x, int y, int z) {
		if(z == 0)
			setBounds(x+7, y+7, 8, 8);
		else
			setBounds(x+3, y+3, 15, 15);
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255));	//Branco
		g.fillRect(x, y, width, height);
		setBounds(x, y, width, height);
	}
}
