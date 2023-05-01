package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

import C21352183.*;
import C21436494.*;
import C21755919.*;

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

	public void scene1()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new Scene1());		
	}

	public void bridgeScene() {
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new BridgeScene());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.MainVisual();			
	}
}