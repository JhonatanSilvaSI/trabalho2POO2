import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;


public class Particula extends Rectangle{
	public Retangulo pato;
	public Color cor;
	public int velocidade = 0;
	public int rotacao = 0;
	public double dx,dy;
	public double x_atual,y_atual;
	public int Contador_TempoParticula = 0;
	
	public Particula (int x, int y, int width, int heigth, Color color) {
		super(x,y,width,heigth); //chamar o construtor da superclasse
		this.cor = color;
		x_atual = x;
		y_atual = y;
		//deslocamento em angulos de cada particula
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
		velocidade = 8;
	}
	public void update() {
		//atualizar a posição
		x_atual += dx * velocidade;
		y_atual += dy * velocidade;
		//atualizar o tempo de vida da particula
		Contador_TempoParticula++;
	}
	
}
