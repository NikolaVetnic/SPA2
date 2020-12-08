package pv06_knossos;

import java.io.IOException;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Main implements Drawing {
	
	
	private static final double EPS = 1e-9;
	private static final int TIME_STEP = 1;
	
	
	@GadgetAnimation(speed = 5.0)
	double time = 0;
	
	@GadgetInteger(min = 0, max = 100)
	int move = 0;

	@GadgetInteger(min = 0, max = 100)
	int startX = 0;
	
	@GadgetInteger(min = 0, max = 100)
	int startY = 0;
	
	@GadgetInteger(min = 0, max = 7)
	int mapIdx = 0;
	
	@GadgetBoolean
	boolean showLabels = true;
	
	
	Labyrinth l;
	
	String[] maps = new String[] {
			"pv06_knossos_1",
			"pv06_knossos_2",
			"pv06_knossos_3",
			"pv06_knossos_4",
			"pv06_knossos_5",
			"pv06_knossos_empty",
			"pv06_knossos_no-solution",
			"pv06_knossos_lav",
		};
	
	Path path;
	
	
	public void drawLabyrinth(View view, Labyrinth l, boolean showLabels) {
				
		Vector adj = l.size().div(2);
		
		for (int i = 0; i < l.size().yInt(); i++)
			for (int j = 0; j < l.size().xInt(); j++)
				Tiles.byCode(
						view, 
						Vector.xy(j, i).sub(adj).mul(Tiles.SIZE),
						l.tile(Vector.xy(j, i)),
						showLabels
						);
		
		if (path != null) {
			
			if (time <= EPS) { 
				// kretanje pomocu moves gadgeta
				Tiles.drawPiece(view, Vector.xy( 
						path.x(move >= path.length() ? path.length() - 1 : move),
						path.y(move >= path.length() ? path.length() - 1 : move)).sub(adj).mul(Tiles.SIZE));
			} else {
				// automatsko kretanje
				Tiles.drawPiece(view, Vector.xy(
						path.x((int) (time / TIME_STEP) >= path.length() ? path.length() - 1 : (int) (time / TIME_STEP)), 
						path.y((int) (time / TIME_STEP) >= path.length() ? path.length() - 1 : (int) (time / TIME_STEP))).sub(adj).mul(Tiles.SIZE));
			}
		}
	}
	
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		int value = 0;
		
		try {
			l = Labyrinth.load(maps[mapIdx], "ardn");
			path = l.getOptimalPath(startX, startY, new CompValue(), new CompLength());
			if (path != null) value = path.pathValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		drawLabyrinth(view, l, showLabels);
		
		DrawingUtils.drawInfoText(view, maps[mapIdx], "weapons collected : " + value);
	}
	
	
	public static void main(String[] args) throws IOException {
		
		DrawingApplication.launch(1280, 720);
	}
}