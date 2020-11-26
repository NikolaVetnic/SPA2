package pv05_lavirint;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;

public class Tiles implements Drawing {
	
	
	public static final Vector SIZE = Vector.xy(70);
	
	
	private static final Map<String, Color> tileColors;
	
	
	static {
		tileColors = new HashMap<>();
		tileColors.put("Path", Color.hsb( 60, 0.45, 0.95));
		tileColors.put("Hole", Color.hsb(180, 0.25, 0.35));
		tileColors.put("Wall", Color.hsb(120, 0.65, 0.45));
		tileColors.put("Goal", Color.hsb(240, 0.65, 0.95));
		tileColors.put("Obst", Color.hsb(  0, 0.65, 0.25));
	}
	
	
	public static void byCode(View view, Vector p, int code, boolean labels) {
		
		switch (code) {
			
			case   0:	single(view, p, code, "Path", labels);	break;
			case  -1:	single(view, p, code, "Hole", labels);	break;
			case -11:	single(view, p, code, "Wall", labels);	break;
			case -99:	single(view, p, code, "Goal", labels);	break;
			default:	single(view, p, code, "Obst", labels);	break;
		}
	}
	
	
	public static void drawPiece(View view, Vector p) {
		
		view.setFill(Color.FIREBRICK);
		view.fillCircleCentered(p.add(SIZE.div(2)), SIZE.xInt() * 0.25);
	}
	
	
	private static void single(View view, Vector p, int code, String label, boolean labels) {
		
		int 	baseHue = (int) tileColors.get(label).getHue();
		double 	baseSat = tileColors.get(label).getSaturation();
		double 	baseBri = tileColors.get(label).getBrightness();
		
		baseBri = code <= 0 ? baseBri : baseBri + (1.0 - baseBri) * (100 - code) / 100.0;
		
		view.setFill(Color.hsb(baseHue, baseSat, baseBri));
		view.fillBox(p, SIZE);
		
		if (label.equals("Goal")) {
			view.setFill(Color.hsb(baseHue - 30, baseSat * 0.45, baseBri));
			view.fillCircleCentered(p.add(SIZE.div(2)), SIZE.x() * 0.35);
		}
		
		view.setStroke(Color.hsb(baseHue, baseSat * 0.75, baseBri * 0.35));
		view.setLineWidth(2);
		view.strokeBox(p, SIZE);
		
		view.setFill(Color.hsb(baseHue, baseSat * 0.75, baseBri * 0.50));
		if (labels) 
			view.fillTextNativeOrientation(
					code <= 0 ? label : label.substring(0, 2) + " " + code, p.add(Vector.xy(2)));
	}
}
