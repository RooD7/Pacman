/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package pacman;

import javax.swing.*;

public class JOption {
	
	public static void showFimJogoLose(int pontos) {
		JOptionPane.showMessageDialog(null, "Você perdeu! Pontuação: "+pontos, "FIM DE JOGO", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void showFimJogoWin() {
		JOptionPane.showMessageDialog(null, "Parabéns! Você Venceu!", "FIM DE JOGO", JOptionPane.PLAIN_MESSAGE);
		
	}
}
