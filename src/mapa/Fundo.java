/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package mapa;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import pacman.Jogo;

public class Fundo {
	
	private int width;
	private int height;
	
	private Bloco[][] blocos;
	private ArrayList<Fruta> frutas;
	private static int numFrutas;
	private ArrayList<Fantasma> fantasmas;
	
	public Fundo(String str) {
		frutas = new ArrayList<Fruta>();
		fantasmas = new ArrayList<Fantasma>();
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(str));
			this.width = map.getWidth();
			this.height = map.getHeight();
			int[] pixels = new int[width*height];
			blocos = new Bloco[width][height];
			pixels = map.getRGB(0, 0, width, height, pixels, 0, width);
			
			int i = 0;
			for (int xx = 0; xx < width; xx++) {
				for (int yy = 0; yy < height; yy++) {
					Color c = new Color(pixels[xx + (yy*width)]);

					if((c.getRed() == 0) && (c.getGreen() == 0) && (c.getBlue() == 128)) {
						// Insere Bloco
						blocos[xx][yy] = new Bloco(xx*20, yy*20);
					}
					else if((c.getRed() == 255) && (c.getGreen() == 255) && (c.getBlue() == 0)) {
						// Insere Player
						Jogo.player.x = xx*20;
						Jogo.player.y = yy*20;
					}
					else if((c.getRed() == 255) && (c.getGreen() == 0) && (c.getBlue() == 0)) {
						//Fantasmas
						fantasmas.add(new Fantasma(xx*20, yy*20,i));
						i++;
					}
					else if((c.getRed() == 255) && (c.getGreen() == 255) && (c.getBlue() == 255)) {
						//Espaco a ser ignorado
						
					}
					else if((c.getRed() == 0) && (c.getGreen() == 255) && (c.getBlue() == 0)) {
						//Fruta Especial
						frutas.add(new Fruta(xx*20, yy*20, 1));
					}
					else {
						// Frutas
						frutas.add(new Fruta(xx*20, yy*20, 0));
						
					}
				}
				Fundo.setNumFrutas(frutas.size());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public synchronized void tick() {
		//	Render Fantasmas
		for (int i = 0; i < fantasmas.size(); i++) {			
			fantasmas.get(i).tick();
		}
	}
	
	public void reader(Graphics g) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(blocos[x][y] != null) 
					blocos[x][y].render(g);
			}
		}
		
		//	Render Frutas
		for (int i = 0; i < frutas.size(); i++) {
			frutas.get(i).render(g);
		}
		
		//	Render Fantasma
		for (int i = 0; i < fantasmas.size(); i++) {
			Fantasma.setG(g);
			fantasmas.get(i).render();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Bloco[][] getBlocos() {
		return blocos;
	}

	public Bloco[] getBlocos(int i) {
		return blocos[i];
	}
	
	public Bloco getBloco(int i, int j) {
		return blocos[i][j];
	}
	
	public void setBlocos(Bloco[][] blocos) {
		this.blocos = blocos;
	}

	public ArrayList<Fruta> getFrutas() {
		return frutas;
	}

	public void setFrutas(ArrayList<Fruta> frutas) {
		this.frutas = frutas;
	}

	public ArrayList<Fantasma> getFantasmas() {
		return fantasmas;
	}

	public void setFantasmas(ArrayList<Fantasma> fantasmas) {
		this.fantasmas = fantasmas;
	}

	public static int getNumFrutas() {
		return numFrutas;
	}

	public static void setNumFrutas(int numFrutas) {
		Fundo.numFrutas = numFrutas;
	}
	
	
}
