package C20375736;

import processing.core.PApplet;

public class Tree{
    PApplet pa;

    float sphereRadius, offset, trunkLength, trunkWidth, xPos, yPos, zPos;

	float branches;

	float branchLength;

    public Tree(PApplet pa, float height, float xPos, float yPos, float zPos,float branches)
    {
        this.pa = pa;

        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
		this.branches = branches;
        sphereRadius = height/4;
        //offset = 0;
		


        trunkLength = height/2;

        trunkWidth = height/8;

    }

    // public float getSphereRadius()
    // {
    //     return sphereRadius;
    // }

    // public void setSphereRadius(float sphereRadius)
    // {
    //     this.sphereRadius = sphereRadius;
    // }

    public float getOffset()
    {
        return offset;
    }

    public void setOffset(float offset)
    {
        this.offset = offset;
    }

    
	public void newRender(int n) //n is number of branches!
	{	
        pa.pushMatrix();

        pa.translate(xPos,yPos,zPos);  //go to tree position
    
		// pa.noStroke();  //comment for outlines
        
        //for drawing head
        pa.translate(0, -trunkLength);

		pa.pushMatrix();
        pa.translate(offset, 0);
        pa.sphereDetail(5);
		pa.stroke(0);
		float len = 100;
		pa.translate(0,-len/2,0);
		for(int i = 0; i < n; i++)
		{
			pa.pushMatrix();
			
			pa.rotateZ(2*pa.PI*i/n);
			if(i != 0) pa.rotateZ(offset/15);	
			pa.translate(0,trunkLength/2,0);
			
			pa.fill(51, 5, 5);  //trunk colour
			pa.box(trunkWidth,trunkLength,trunkWidth);
			pa.translate(0,trunkLength/2,0);
			pa.fill(0,255,0);
			if(i != 0) pa.sphere(sphereRadius); 
			pa.popMatrix();
		}
		pa.popMatrix();


        pa.popMatrix();
	}	

}
