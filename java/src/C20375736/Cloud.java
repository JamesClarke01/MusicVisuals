package C20375736;

import processing.core.PApplet;

//chance of meatballs?

public class Cloud
{
	PApplet pa;

	float hPos, leftMax, rightMax, cent;
	int gap = 50;//length of gaps between vertexes
	int offset = 50;
	int gaps; 

	public Cloud(PApplet pa)
	{
		this.pa = pa;


		hPos = pa.height/5;
		leftMax = -100;
		rightMax = pa.width + 100;
		cent = pa.width/2;
		gaps = (int)rightMax/gap;
	}
	

	public void render()
	{
		
		pa.pushMatrix();
		//shapes and stuff go here

		pa.translate(0,-pa.height,0);//Resets the postition of the cloud to 0,0,0
		pa.translate(leftMax,hPos,0);
		pa.fill(100);
		for(int j = 1; j < 4; j++)
		{
			pa.beginShape(pa.TRIANGLE_STRIP);
			for(int i = 0; i <gaps+2 ; i++)
			{
				pa.vertex(gap * i, offset*j, 0);
				pa.vertex((gap * i) + gap/2, offset * (j+1), 0);
			}
			pa.endShape();
		}

		pa.popMatrix();
	}

}
