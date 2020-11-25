package pv05_lavirint;

import java.io.IOException;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Main implements Drawing {
	
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
			"lavirint_1",
			"lavirint_2",
			"lavirint_neresiv",
			"lavirint_prazan",
			"lavirint_prepreke1",
			"lavirint_prepreke2",
			"lavirint_prepreke3",
			"lavirint_rupe",
		};
	
	int[][] path;
	
	
	{
		try {
			setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	void setup() throws IOException {

		l = Labyrinth.load(maps[0]);
		
		path = l.findPath(startX > l.size().xInt() ? l.size().xInt() : startX, 
						  startY > l.size().yInt() ? l.size().yInt() : startY);
	}
	
	
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
		
		if (path != null) 
			Tiles.drawPiece(view, Vector.xy(
					path[move >= path.length ? path.length - 1 : move][0], 
					path[move >= path.length ? path.length - 1 : move][1]).sub(adj).mul(Tiles.SIZE));
	}
	
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		try {
			l = Labyrinth.load(maps[mapIdx]);
			
			path = l.findPath(startX > l.size().xInt() ? l.size().xInt() : startX, 
					  		  startY > l.size().yInt() ? l.size().yInt() : startY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		drawLabyrinth(view, l, showLabels);
		
		DrawingUtils.drawInfoText(view, maps[mapIdx]);
	}
	
	
	public static void main(String[] args) throws IOException {
		
		DrawingApplication.launch(1280, 720);
	}
}