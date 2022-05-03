package C20375736;

import processing.core.PApplet;

public class Bolt
{
	PApplet pa;

	float xPos, yPos, zPos;


	public Bolt(PApplet pa, float xPos, float yPos, float zPos)
	{ 
		this.pa = pa; 
		this.xPos = xPos; 
		this.yPos = yPos;
		this.zPos = zPos;
	}

	public void charge()
	{	pa.pushMatrix(); 
		pa.translate(xPos,yPos,zPos);
	}
	
	public void strike(int depth, float len)
	{
		pa.fill(229,254,69);
		

		for(int i = 0; i < depth; i++)
		{
			pa.pushMatrix();
			pa.rotateY(pa.TAU * i/depth);

			if(i % 2 == 0)	pa.rotateZ(pa.PI/12);
			else pa.rotateZ(-pa.PI/12);
			pa.translate(0,len,0);
			pa.box(10,len,10);
			//recursive call (where the magic happens)
			if(i == 1) strike(depth-1,len);

			pa.popMatrix();
		}
	}
	public void discharge()
	{
		pa.popMatrix();
	}
	public void sstrike(int depth, float length)
	{
		pa.fill(0);
		pa.pushMatrix();
		pa.translate(pa.mouseX,yPos,zPos);
		pa.box(10);
		pa.popMatrix();
	}
}
