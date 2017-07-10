/*
 * 
 * Rodrigo Sousa 	- 0011264
 * Rafaela Martins	- 0002852
 * 
 */

package pacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import mapa.Fundo;


public class Jogo extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	public static final int WIDTH = 560;	//28 x 20
	public static final int HEIGHT = 620;	//31 x 20
	public static final String TITLE = "Pac-Man";
	
	private Thread thread;
	public static Player player;
	public static Fundo fundo;
	
	public Jogo() {
		Dimension dimensao = new Dimension(Jogo.WIDTH, Jogo.HEIGHT);
		setPreferredSize(dimensao);
		setMinimumSize(dimensao);
		setMaximumSize(dimensao);

		addKeyListener(this);
		Jogo.player = new Player(Jogo.WIDTH/2, Jogo.HEIGHT/2);
		Jogo.fundo = new Fundo("/map/mapaPacman2.png");
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setRight(true);
			player.setLeft(false);
			player.setUp(false);
			player.setDown(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 	{
			player.setRight(false);
			player.setLeft(true);
			player.setUp(false);
			player.setDown(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)  {
			player.setRight(false);
			player.setLeft(false);
			player.setUp(true);
			player.setDown(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setRight(false);
			player.setLeft(false);
			player.setUp(false);
			player.setDown(true);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
//		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.setRight(false);
//		if(e.getKeyCode() == KeyEvent.VK_LEFT) 	player.setLeft(false);
//		if(e.getKeyCode() == KeyEvent.VK_UP) 	player.setUp(false);
//		if(e.getKeyCode() == KeyEvent.VK_DOWN) 	player.setDown(false);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public synchronized void start() {
		if(isRunning()) return;
		setRunning(true);
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if(!isRunning()) return;
		setRunning(false);
		
		try {
			thread.join();
		} catch ( InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		requestFocus();
		
		int fps = 0;
		double time = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetTick = 60.0;
		double delta = 0;
		double ns = 1000000000/targetTick;
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis() - time >= 1000) {
				System.out.println("FPS:"+fps);
				fps = 0;
				time += 1000;
			}
		}
		
		stop();
		
	}

	private void render() {
		BufferStrategy bfs = getBufferStrategy();
		if(bfs == null) {
			createBufferStrategy(5);
			return;
		}
		
		Graphics g = bfs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Jogo.WIDTH, Jogo.HEIGHT);
			
		player.render(g);
		fundo.reader(g);
		g.dispose();
		bfs.show();
	}


	private synchronized void tick() {
		player.tick();
		fundo.tick();
	}

	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
