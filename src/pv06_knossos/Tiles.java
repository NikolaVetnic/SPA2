package pv06_knossos;

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
		tileColors.put("Ardn", Color.hsb(255, 0.45, 0.75));
		tileColors.put("Lair", Color.hsb(  0, 0.45, 0.95));
		tileColors.put("Mntr", Color.hsb(  0, 0.75, 0.75));
		tileColors.put("Wpns", Color.hsb(225, 0.25, 0.80));
		tileColors.put("Path", Color.hsb( 35, 0.16, 0.88));
		tileColors.put("Swmp", Color.hsb(100, 0.45, 0.40));
		tileColors.put("Wall", Color.hsb( 25, 0.20, 0.35));
	}
	
	
	private static boolean inRange(int n, int min, int max) {
		
		if (min == max)
			if (n == min) 				return true ;
			else 						return false;
		else
			if (min <= n && n <= max) 	return true ;
			else						return false;
	}
	
	
	private static boolean inRange(int n, int min) {
		return inRange(n, min, min);
	}
	
	
	public static void byCode(View view, Vector p, int code, boolean labels) {
		
		if (inRange(code,  30    ))	single(view, p, code, "Ardn", labels);
		if (inRange(code,  25    ))	single(view, p, code, "Lair", labels);
		if (inRange(code,  20    ))	single(view, p, code, "Mntr", labels);
		if (inRange(code,   1, 10))	single(view, p, code, "Wpns", labels);
		if (inRange(code,   0    ))	single(view, p, code, "Path", labels);
		if (inRange(code, -10, -1))	single(view, p, code, "Swmp", labels);
		if (inRange(code, -11    ))	single(view, p, code, "Wall", labels);
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
		
		view.setStroke(Color.hsb(baseHue, baseSat * 0.75, baseBri * 0.35));
		view.setLineWidth(2);
		view.strokeBox(p, SIZE);
		
		String l;
			   l = label.equalsIgnoreCase("Ardn") ? "Ariadne" 	:
				   label.equalsIgnoreCase("Mntr") ? "Minotaur" 	:
				   label.equalsIgnoreCase("Wpns") ? "Weapons" 	:
				   label.equalsIgnoreCase("Swmp") ? "Swamp" 	: label;
		
		view.setFill(Color.hsb(baseHue, baseSat * 0.75, baseBri * 0.50));
		if (labels) view.fillTextNativeOrientation(l, p.add(Vector.xy(2)));
	}
}
