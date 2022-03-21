package C20375736;

import processing.core.PApplet;

public class Tree{
    PApplet pa;

    float sphereRadius, trunkLength, trunkWidth, xPos, yPos, zPos;

    public Tree(PApplet pa, float height, float xPos, float yPos, float zPos)
    {
        this.pa = pa;

        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
       
        // sphereRadius = height/4;

        // trunkLength = height/2;

        // trunkWidth = height/8;


    }

	int count = 0;
	public void drawBranch(float depth)
	{
		if(depth <= 3 || depth >= 1) //max depth of tree will be 5, I don't want the heckin' jvm to slurp all my RAM
		{
				drawBranch(pa.random(1,depth));//garuntees that the depth of the next branch/subtree will always be less
		}
	}

    public void render(float height)
    {
        sphereRadius = height/4;

        trunkLength = height/2;

        trunkWidth = height/8;

        pa.pushMatrix();
        pa.translate(xPos,yPos,zPos);  //go to tree position

    
        //pa.noStroke();  //comment for outlines
        
        //for drawing head
        pa.translate(0, -trunkLength);
        pa.fill(21, 161, 26);  //tree colour
		pa.sphereDetail(5); //make the heads look a bit chunky
        pa.sphere(sphereRadius);
		pa.rotateY(pa.map(height,0,1,0,pa.PI));
		pa.sphere(sphereRadius);

        //for drawing trunk
        pa.translate(0, trunkLength/2); 
        pa.fill(51, 5, 5);  //trunk colour
        pa.box(trunkWidth, trunkLength, trunkWidth);

        pa.popMatrix();
    }


}
