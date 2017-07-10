/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package mapa;

import java.io.FileNotFoundException;

public class Mapa {
	public static final int LINHA = 30; // x20 = 600
	public static final int COLUNA = 28; // x20 = 560
	private static int campo[][];

	public Mapa() {
		campo = new int[Mapa.LINHA][Mapa.COLUNA];
	}

	public int getValueMapa(int x, int y) {
		if ((x <= Mapa.LINHA) && (y <= Mapa.COLUNA))
			return Mapa.campo[x][y];

		return -2;
	}

	public void setValueMapa(int x, int y, int z) {
		if ((x <= Mapa.LINHA) && (y <= Mapa.COLUNA))
			Mapa.campo[x][y] = z;
	}

	public void geradorDeMapa(String nameFile) throws FileNotFoundException {

		
	}

	public void setLinhaMapa(int lin, String[] linha) {

		for (int i = 0; i < linha.length; i++) {
			setValueMapa(lin, i, Integer.parseInt(linha[i]));
		}
	}

	public static int consultaX(int x, int y) {
		int aux = 0;
		if(Mapa.getCampo()[x][y] == 0) {
			aux = x-1;
		}
		return aux;
	}
	
	public void imprimeMapa() {
		for (int i = 0; i < Mapa.LINHA; i++) {
			for (int j = 0; j < Mapa.COLUNA; j++) {
				System.out.print(getValueMapa(i, j) + " ");
			}
			System.out.println();
		}
	}

	public static int[][] getCampo() {
		return campo;
	}

	public void setCampo(int[][] campo) {
		Mapa.campo = campo;
	}

	public static int getLinha() {
		return LINHA;
	}

	public static int getColuna() {
		return COLUNA;
	}
	
	

}
