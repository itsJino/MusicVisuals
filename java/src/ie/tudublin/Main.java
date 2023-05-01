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

	public void MainVisual()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new MainVisual());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.MainVisual();			
	}
}