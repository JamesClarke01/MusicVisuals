package C20375736;

import processing.core.PApplet;

public class Tree{
    PApplet pa;

    float sphereRadius, trunkLength, trunkWidth;

    public Tree(float height, PApplet pa)
    {
        this.pa = pa;
       
        sphereRadius = height/4;

        trunkLength = height/2;

        trunkWidth = height/8;


    }

    public void draw()
    {
        pa.pushMatrix();
    
        pa.noStroke();  //comment for outlines
        
        pa.translate(0, -trunkLength);
        pa.fill(21, 161, 26);  //tree colour
        pa.sphere(sphereRadius);

        pa.translate(0, trunkLength/2); 
        pa.fill(51, 5, 5);  //trunk colour
        pa.box(trunkWidth, trunkLength, trunkWidth);

        pa.popMatrix();
    }


}
