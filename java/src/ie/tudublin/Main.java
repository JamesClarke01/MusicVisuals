package ie.tudublin;

import C20375736.FirstVisualTest;
import C20375736.audio3d;
import C20375736.audioExample;
import C20375736.first3dTest;
import C20375736.landscape;
import C20375736.Audio1;


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

	public void startFirst3dTest()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new first3dTest());		
	}

	public void startAudioExample()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new audioExample());		
	}

	public void startAudio3d()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new audio3d());		
	}
	
	public void startLandscape()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new landscape());		
	}
	

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startLandscape();			
	}
}