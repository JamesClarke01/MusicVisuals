package C20375736;

import processing.core.PApplet;

public class Clouds
{
	PApplet pa;

	float leftMax, rightMax, hPos, intensity;


	public Clouds(PApplet pa, float leftMax, float rightMax, float hPos, float intensity)
	{	
		this.pa = pa;
		
		this.leftMax = leftMax;
		this.rightMax = rightMax;
		this.hPos = hPos;
		this.intensity = intensity;

		rightMax = pa.width + 100;
		leftMax = -100;
		hPos = pa.height/5;	
	}	
	
	public void render()
	{
		pa.pushMatrix();
		pa.translate(pa.width/2,hPos,0);
		pa.fill(150);
		pa.box(200);
		pa.popMatrix();
	}
}
