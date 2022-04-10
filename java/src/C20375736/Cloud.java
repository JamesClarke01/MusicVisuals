package C20375736;

import processing.core.PApplet;

//chance of meatballs?

public class Cloud
{
	PApplet pa;

	float hPos, leftMax, rightMax, cent, ySize;
	
	float xGap, yGap;
	int xCount,yCount;
	
	float[][] heights;
	

	public Cloud(PApplet pa)
	{
		this.pa = pa;


		hPos = pa.height/5;
		leftMax = -250;
		rightMax = pa.width + 100;
		cent = pa.width/2;
		ySize = 300;
		xGap = 50;
		yGap = 30;
		xCount = (int)(rightMax -leftMax / xGap);
		yCount = (int)(ySize/yGap);


		heights = new float[xCount][yCount];


		for(int i = 0; i < yCount; i++)
		{
			for(int j = 0; j < xCount; j++)
			{
				heights[j][i] = pa.random(-50,50);
			}
		}
	}
	

	public void render()
	{
		
		pa.pushMatrix();
		//shapes and stuff go here

		pa.translate(leftMax,-pa.height - 110,0);//Sets the postition of the cloud
		pa.rotateX(pa.PI * 1.7f);
		pa.fill(100);
		for(int i = 0; i < yCount-1; i++)
		{
			pa.beginShape(pa.TRIANGLE_STRIP);
			for(int j = 0; j < xCount; j++)
			{
				pa.vertex(xGap * j,hPos + (yGap * i),heights[j][i]);
				pa.vertex(xGap * j,hPos + (yGap * (i+1)),heights[j][i+1]);
			}
			pa.endShape();
		}

		pa.popMatrix();
	}

}
