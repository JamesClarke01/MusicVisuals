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
       
        sphereRadius = height/4;

        trunkLength = height/2;

        trunkWidth = height/8;

    }

    public float getSphereRadius()
    {
        return sphereRadius;
    }

    public void setSphereRadius(float sphereRadius)
    {
        this.sphereRadius = sphereRadius;
    }

    public void render()
    {
        

        pa.pushMatrix();
        pa.translate(xPos,yPos,zPos);  //go to tree position

    
       // pa.noStroke();  //comment for outlines
        
        //for drawing head
        pa.translate(0, -trunkLength);
		pa.pushMatrix();
        pa.fill(21, 161, 26);  //tree colour
		pa.sphereDetail(5);
        pa.sphere(sphereRadius);
		pa.rotateY(pa.map(sphereRadius,0,1,0,pa.PI));
		pa.sphere(sphereRadius);
		pa.popMatrix();
        //for drawing trunk
        pa.translate(0, trunkLength/2); 
        pa.fill(51, 5, 5);  //trunk colour
        pa.box(trunkWidth, trunkLength, trunkWidth);

        pa.popMatrix();
    }


}
