package graficos;

//Bibliotecas necessaria
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spritesheet {
	
	//Objeto com o spritesheet
	private BufferedImage spritesheet;
	
	//Capturar o spritesheet no diretorio em "path"
	public Spritesheet(String path) throws IOException {
		spritesheet = ImageIO.read(getClass().getResource(path));
	}
	
	//Pegar um sprite dentro do spritesheet
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
	
}
