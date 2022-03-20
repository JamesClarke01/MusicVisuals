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

    public void render(float height)
    {
        sphereRadius = height/4;

        trunkLength = height/2;

        trunkWidth = height/8;

        pa.pushMatrix();
        pa.translate(xPos,yPos,zPos);  //go to tree position

    
        pa.noStroke();  //comment for outlines
        
        //for drawing head
        pa.translate(0, -trunkLength);
        pa.fill(21, 161, 26);  //tree colour
        pa.sphere(sphereRadius);

        //for drawing trunk
        pa.translate(0, trunkLength/2); 
        pa.fill(51, 5, 5);  //trunk colour
        pa.box(trunkWidth, trunkLength, trunkWidth);

        pa.popMatrix();
    }


}
