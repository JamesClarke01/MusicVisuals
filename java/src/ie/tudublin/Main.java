package ie.tudublin;

import C20375736.Audio1;
import C20375736.FirstVisualTest;


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

	public void startAudio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startAudio1();			
	}
}
