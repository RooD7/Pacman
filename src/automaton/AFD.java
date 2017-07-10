/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package automaton;

public class AFD {
	
	 boolean direita;
	 boolean esquerda;
	 boolean parede;
	 boolean acima;
	 boolean baixo;
	 String estadoAtual;
 
	 public AFD() {
		 direita=true;
		 esquerda=true;
		 parede=false;
		 acima=true;;
		 baixo=true;
		 estadoAtual="";
	 }
	 
	 public boolean verificaMovimento(int valorRandom){
		 if (valorRandom==0){
			 setEstadoAtual(valorRandom);
			 return parede;
		 }
		 if (valorRandom==1){
			 setEstadoAtual(valorRandom);
			 return direita;
		 }
		 if (valorRandom==2){
			 setEstadoAtual(valorRandom);
			 return esquerda;
		 }
		 if (valorRandom==3){
			 setEstadoAtual(valorRandom);
			 return acima;
		 }
		 if (valorRandom==4){
			 setEstadoAtual(valorRandom);
			 return baixo;
		 }
		 return false;
		
	}
	 public void setEstadoAtual(int valorRandom){
		 if (valorRandom==1){
			
		 estadoAtual="direita";
		 }
		 if (valorRandom==0){
			 estadoAtual="parede";
		 }
		 if (valorRandom==2){
			 estadoAtual="esquerda";
		 }
		 if (valorRandom==3){
			 estadoAtual="acima";
		 }
		 if (valorRandom==4){
			 estadoAtual="baixo";
		 }
	 }
	 public String getEstadoAtual(){
		 return estadoAtual;
	 }
 
}
