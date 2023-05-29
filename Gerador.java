import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Gerador {
	public int Tempo_GeradorRetangulos = 0;
	public ArrayList<Retangulo> vetor_retangulos = new ArrayList<Retangulo>();
	public ArrayList<Particula> vetor_particulas = new ArrayList<Particula>();
	
	public void update() {
		Tempo_GeradorRetangulos++;
		
		if(Tempo_GeradorRetangulos % 60 == 0) //gerar retangulos a cada 60
		{
			//criar um objeto retangulo com posição y aleatoria
			Retangulo objRetangulo = new Retangulo(0, new Random().nextInt(480-40),40,40);
			vetor_retangulos.add(objRetangulo);
		}
		for (int i = 0; i < vetor_retangulos.size(); i++) {
			Retangulo current = vetor_retangulos.get(i);
			vetor_retangulos.get(i).update();
			
			//se o resultado atingiu o final da tela, deletar o objeto!
			if(current.x > Jogo.Largura) {
				vetor_retangulos.remove(current);
				//a cada retanguloque atinge o final da tela, o tempo é decrementado
				Jogo.Contador_Tempo--;
			}
			if(Jogo.MouseClicado) {
				
				//verificar se os pontos (mouse x, mouse y) estão dentro do retangulo
				if(Jogo.mouse_x >= current.x && Jogo.mouse_x < current.x + current.width)
					{
					if(Jogo.mouse_y >= current.y && Jogo.mouse_y < current.y + current.height)
					{
						//Atingiu o retangulo, então ele será removido!
						vetor_retangulos.remove (current);
						//o jogador pontuou!
						Jogo. pontuacao++;
						Jogo.MouseClicado = false;
					}
				}
				
			}
				}
	}
	public void render (Graphics g) throws IOException
	{
		//desenhar na tela cada um dos retanqulos
		for(int i = 0; i < vetor_retangulos.size(); i++)
			
		{
			Retangulo objRetangulo = vetor_retangulos.get(i);
			//adicionar rotação em cada um dos retanqulos
			Graphics2D g2 = (Graphics2D) g;
			objRetangulo.render(g);
			//g2.rotate (Math. toRadians (objRetangulo.rotation),objRetangulo.x + objRetangulo.width/2, objRetangulo.y+ objRetangulo.height/2);
			//g2 .setColor (objRetangulo.cor);
			//g2.fillRect (objRetangulo.x, objRetangulo.y, objRetangulo.width, objRetangulo.height);
			//g2 .rotate (-Math. toRadians (objRetangulo.rotation), objRetangulo.x + objRetangulo.width/2, objRetangulo.y+ objRetangulo.height/2);
			
		}
		
	}
}
