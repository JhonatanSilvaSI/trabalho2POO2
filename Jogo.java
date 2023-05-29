import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

public class Jogo extends Canvas implements Runnable, MouseListener {
		public static final int Largura = 640, Altura = 480;
		public static int Contador_Tempo = 100;
		public static int pontuacao = 0;
		public Gerador objetoGerador;
		public static int mouse_x, mouse_y;
		public static boolean MouseClicado = false;
		public boolean JogoAcabou = false;

		public Jogo(){
			//definir dimensão da tela
			Dimension dimension = new Dimension (Largura, Altura);
			this.setPreferredSize (dimension);
			//adicionar evento do mouse
			this.addMouseListener (this);
			//criar o objeto que gera os retangulos e as particulas
			objetoGerador = new Gerador();
		}
		
		public static void main (String[] args){
			//inicar o objeto que controla o jogo
			Jogo game = new Jogo();
			//criar a tela
			JFrame jframe = new JFrame("Jogo Explosão");
			jframe.add(game);
			jframe.setLocationRelativeTo (null);
			jframe.pack();
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jframe.setVisible (true);
			//iniciar a Thread do jogo
			new Thread(game) .start();
		}
		
		public void update(){
			if (JogoAcabou == false) {
				//atualziar a posição dos retangulos
				objetoGerador.update();
				
				//se o tempo acabou, encerrar o jogo
				if(Contador_Tempo <= 0) {
					JogoAcabou = true;
				}
			}
		}
		
		public void render() throws IOException {
			//criar o front buffer
			BufferStrategy bs = this.getBufferStrategy();
			if(bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			
			//desenhar o cenario do jogo no back buffer
			Graphics g = bs.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,Largura, Altura);
			
			if(JogoAcabou == false) {
				//mostrar a pontuação na tela
				g.setColor(Color.white);
				g.setFont(new Font ("Arial", Font.BOLD, 23));
				g.drawString("Pontos: " + pontuacao, 10, 40);
				//desenhar o retangulo que mostra o tempo restante
				g.setColor(Color.green);
				g.fillRect(Largura/2 - 100-70,  20, Contador_Tempo*3, 20);
				//desenhar uma borda branca ao redor do retangulo que representa o tempo
				g.setColor(Color.white);
				g.drawRect(Largura/2 - 100-70 , 20, 300, 20);
				//desenhar os objetos na tela
				objetoGerador.render(g);
			}
			
			else { //mostrar mensagem de GAME OVER
				g.setColor(Color.white);
				g.setFont( new Font ("Arial", Font.BOLD, 30));
				g.drawString("GAME OVER!",Largura/2-100, Altura/2);
			}
			//mostrar o front buffer
			bs.show();
			}
		
		@Override
		public void run()
		{
			while (true)
			{
				//atualizar as posições dos objetos
				update () ;
				//desenhar os objetos na tela
				try {
					render();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thread.sleep(16); //esperar 16 ms
				}catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		
		@Override
		public void mousePressed(MouseEvent e) {
			MouseClicado = true;
			mouse_x = e.getX();
			mouse_y = e.getY();
			if (JogoAcabou == false) {
			    for (Retangulo retangulo : objetoGerador.vetor_retangulos) {
			        if (Jogo.mouse_x >= retangulo.x && Jogo.mouse_x < retangulo.x + retangulo.width &&
			                Jogo.mouse_y >= retangulo.y && Jogo.mouse_y < retangulo.y + retangulo.height) {
			            objetoGerador.vetor_retangulos.remove(retangulo);
			            Jogo.pontuacao++;
			            Jogo.MouseClicado = false;
			            try {
			                retangulo.patoAudio.stop(); // Reinicia o áudio se já estiver tocando
			                retangulo.patoAudio.setFramePosition(0);
			                retangulo.patoAudio.start();
			            } catch (Exception e1) {
			                e1.printStackTrace();
			            }
			            break;
			        }
			    }
			}

		}
		
		@Override 
		public void mouseReleased (MouseEvent e) {
		// TODO Auto-generated method stub
		}
		@Override
		public void mouseClicked (MouseEvent e) {
		// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered (MouseEvent e) {
		// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited (MouseEvent e) {
		// TODO Auto-generated method stub
		}
	}
