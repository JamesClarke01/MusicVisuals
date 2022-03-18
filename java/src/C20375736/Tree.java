package C20375736;

import processing.core.PApplet;

public class Tree{
    PApplet pa;

    float sphereRadius, trunkLength, trunkWidth;

    public Tree(float sphereRadius, PApplet pa)
    {
        this.pa = pa;
       
        this.sphereRadius = sphereRadius;

        trunkLength = sphereRadius * 2;

        trunkWidth = sphereRadius/2;


    }

    public void drawTree()
    {
        pa.pushMatrix();

        pa.fill(21, 161, 26);  //tree colour
        pa.noStroke();
        pa.sphere(sphereRadius);

        pa.translate(0, trunkLength/2); 
        pa.fill(51, 5, 5);  //trunk colour
        pa.box(trunkWidth, trunkLength, trunkWidth);

        pa.popMatrix();
    }


}
