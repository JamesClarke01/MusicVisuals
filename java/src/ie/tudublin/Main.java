package ie.tudublin;

import C20375736.FirstVisualTest;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	// public void startUI()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new MyVisual());		
	// }

	public void startFirstVisualTest()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new FirstVisualTest());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startFirstVisualTest();			
	}
}