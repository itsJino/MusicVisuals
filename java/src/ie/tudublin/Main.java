package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public void scene3()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new Scene3());		
	}

	public void joshuaTerrain() {
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new JoshuaTerrain());
	}

	public void mainVisuals() {
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new MainVisuals());
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.joshuaTerrain();			
	}
}