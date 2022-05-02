package C20375736;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
//chance of meatballs?

public class Cloud
{
	PApplet pa;

	float hPos, leftMax, rightMax, cent, ySize;
	
	float xGap, yGap;
	int xCount,yCount;
	float rolling; 	
	float[][] heights;
	

	public Cloud(PApplet pa)
	{
		this.pa = pa;

		hPos = (float)-2.45 * pa.height/5;
		leftMax = -400;
		rightMax = pa.width + 100;
		cent = pa.width/2;
		ySize = 400;
		xGap = 25;
		yGap = 15;
		xCount = (int)(rightMax -leftMax / xGap);
		yCount = (int)(ySize/yGap);

		rolling = 0;
		heights = new float[xCount][yCount];

		float yoff = 0;
		for(int i = 0; i < yCount; i++)
		{
			float xoff = 0;
			for(int j = 0; j < xCount; j++)
			{
		
				heights[j][i] = pa.map(pa.noise(xoff,yoff),0,1,-50,50);
				xoff += 0.4;
			}
			yoff += 0.4;
		}
	}
	

	public void render(AudioBuffer bassBuffer,float modifier)
	{
		rolling += 0.01;
		float yoff = rolling;
		//clouds will now slide in to view based on the modifier
		hPos = pa.map(modifier,0,100,(float)-2.45 * pa.height/5,pa.height/6);
		for(int i = 0; i < yCount; i++)
		{
			float xoff = 0;
			for(int j = 0; j < xCount; j++)
			{
		
				heights[j][i] = pa.map(pa.noise(xoff,yoff),0,1,-50,50) * pa.map(bassBuffer.level(),0,0.2f,1,2.5f);
				xoff += 0.4;
			}
			yoff += 0.4;
		}
		pa.pushMatrix();
		
		//shapes and stuff go here
		pa.translate(-(pa.width/2) + leftMax,-pa.height - 150,0);//Sets the postition of the cloud
		
		pa.rotateX(pa.PI * 1.7f);
		
		pa.fill(76, 77, 75, pa.map(modifier, 0, 100, 0, 255));  //set cloud colour/opacity
		pa.stroke(0,0,0, pa.map(modifier, 0, 100, 0, 255));  //set outline colour
			
		for(int i = 0; i < yCount-1; i++)
		{
			pa.beginShape(pa.TRIANGLE_STRIP);
			for(int j = 0; j < xCount; j++)
			{
				pa.vertex(xGap * j, hPos + (yGap * i), heights[j][i]);
				pa.vertex(xGap * j, hPos + (yGap * (i+1)), heights[j][i+1]);
			}
			pa.endShape();
		}
		pa.noStroke();

		pa.popMatrix();
	}

}
