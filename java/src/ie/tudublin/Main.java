package ie.tudublin;

import C21522836.*;
import example.CubeVisual;
import example.CubeVisual1;
import example.MyVisual;

public class Main {

	public void startUI() {
		String[] a = { "MAIN" };
		processing.core.PApplet.runSketch(a, new Myco());
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.startUI();
	}
}