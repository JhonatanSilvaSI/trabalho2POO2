import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Retangulo extends Rectangle {
	
	public Image patoImage;
	public Clip patoAudio;
	public Color cor;
	public int velocidade = 0;
	public int rotation = 0;
	private int x_atual;
	private int y_atual;
	
	public Retangulo(int x, int y, int width, int height) {
		
		super (x,y,width, height);// construtor de super classe Rectangle
		//gerar uma velocidade aleatoria
		velocidade = new Random().nextInt(4)+3;
		try {
		    patoAudio = AudioSystem.getClip();
		    patoAudio.open(AudioSystem.getAudioInputStream(new File("C:/Users/jhonatan.silva/Documents/PROGRAMACAOORIENTADAOBJETOS/TrabalhoAvaliacao2/arquivos/audio/qua-qua.wav"))); // Substitua pelo caminho correto do arquivo de áudio do pato
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void update() {
		//incrementar o x de acordo com a velocidade
		x += velocidade;
		//incrementar o angulo de rotação
		rotation++;
		if(rotation >= 360 ) // se atingiu o angulo maximo, zerar novamente
			rotation = 0;
	}

	public Image getPatoImage() throws IOException {
		
	    patoImage = ImageIO.read(new File("C:/Users/jhonatan.silva/Documents/PROGRAMACAOORIENTADAOBJETOS/TrabalhoAvaliacao2/arquivos/imagem/pato.png")); // Substitua pelo caminho correto da imagem do pato

		return patoImage;
	}
	public void render(Graphics g) throws IOException {
	    // Desenhar a imagem do pato na tela
	    g.drawImage(getPatoImage(), (int) x, (int) y, null);
	}
}
