package pv06_knossos;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class CharSheet {
	
	
	public static final Vector CHAR_SIZE 	= Vector.xy(8, 16);
	public static final Vector SHEET_SIZE 	= Vector.xy(32, 8);
	public static final String SHEET_PATH	= "img//charsheet_transparent.png";
	
	public static int[][] badChars = { 
			{  0, 0 }, {  2, 0 }, {  8, 0 }, { 10,  0 },
			{ 17, 5 }, { 18, 5 }, { 19, 5 },
			{ 26, 6 }, { 27, 6 }, { 28, 6 }, { 30,  6 }
	};
	
	
	WritableImage[][] characters;
	double[][] values;
	
	
	@GadgetInteger(min = 0, max = 255)
	int n = 0;
	
	
	public CharSheet() {

		// Load char sheet image.
		Image im = new Image(SHEET_PATH);
		PixelReader pr = im.getPixelReader();
		
		characters = new WritableImage[SHEET_SIZE.xInt()][SHEET_SIZE.yInt()];
		values = new double[SHEET_SIZE.xInt()][SHEET_SIZE.yInt()];
		
		for (int i = 0; i < SHEET_SIZE.xInt(); i++) {
			for (int j = 0; j < SHEET_SIZE.yInt(); j++) {
				
				characters[i][j] = new WritableImage(CHAR_SIZE.xInt(), CHAR_SIZE.yInt());
				PixelWriter pw = characters[i][j].getPixelWriter();
				
				double value = 0.0;
				
				for (int y = 0; y < CHAR_SIZE.yInt(); y++) {
					for (int x = 0; x < CHAR_SIZE.xInt(); x++) {
						
						Color c = pr.getColor((int) (i * CHAR_SIZE.x() + x), (int) (j * CHAR_SIZE.y() + y));
						
						pw.setColor(x, y, c.getBrightness() > 1e-8 ? c : Color.TRANSPARENT);
						value += c.getBrightness();
					}
				}
				
				values[i][j] = value / CHAR_SIZE.area();
			}
		}
	}
	
	
	public int charX()					{ return CHAR_SIZE.xInt();					}
	public int charY()					{ return CHAR_SIZE.yInt();					}
	public int charArea()				{ return (int) CHAR_SIZE.area();			}
	public int sheetX()					{ return SHEET_SIZE.xInt();					}
	public int sheetY()					{ return SHEET_SIZE.yInt();					}
	
	public int width() 					{ return characters.length; 				}
	public int height() 				{ return characters[0].length; 				}
	public Image character(Vector p) 	{ return characters[p.xInt()][p.yInt()]; 	}
	
	public boolean isBad(int i, int j)	{
		for (int[] p : badChars) if (i == p[0] && j == p[1]) return true;
		return false;
	}
}
