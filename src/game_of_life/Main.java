package game_of_life;

import java.io.IOException;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Main implements Drawing {
	
	
	@GadgetAnimation(speed = 10.0)
	double time = 0.0;
	
	@GadgetDouble(p = 2.0, q = 100.0)
	double zoom = 4.5;
	
	@GadgetInteger(min = 0, max = Integer.MAX_VALUE)
	int seed = 42;

	@GadgetInteger(min = 0, max = 1000)
	int size = 50;

	@GadgetDouble(p = 0.0, q = 1.0)
	double prob = 0.15;
	
	
	static Map[] record;
	int numEpochs = (int) 1e3;
	
	
	void setup() throws IOException {
		
		// load map from file
//		record = new Map("map_test").recordEpochs(50);
		
		// random generated map
		record = new Map(size, size, seed, prob).recordEpochs(numEpochs);
	}
	
	
	public void drawMap(View view, Map m) {
		
		int xx = m.map.length;
		int yy = m.map[0].length;
		
		for (int i = 0; i < xx; i++) {
			for (int j = 0; j < yy; j++) {
				view.setFill(m.map[i][j] == 0 ? Color.hsb(264, 0.45, 0.35) : Color.hsb(53, 0.44, 0.95));
				view.fillCircleCentered(Vector.xy((i + 0.5) - xx * 0.5, (j + 0.5) - yy * 0.5).mul(zoom * 2.0), zoom * 0.8);
			}
		}
	}
	

	@Override
	public void draw(View view) {
		
		try {
			setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		drawMap(view, record[(int) time < numEpochs ? (int) time : numEpochs - 1]);
	}

	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
