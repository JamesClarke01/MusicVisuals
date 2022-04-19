package ie.tudublin;


import C20375736.Visualisation;



public class Main
{	
	
	public void startVisualisation()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Visualisation());		
	}
	

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startVisualisation();			
	}
}