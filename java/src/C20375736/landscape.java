package C20375736;


import processing.core.PApplet;


public class Landscape
{
    PApplet pa;
    
    public Landscape(PApplet pa)
    {
        this.pa = pa;
    }
    

    Tree tree;

    public void render()
    {
        pa.background(28, 221, 255);
        
        pa.translate(pa.width/2, (float)(pa.height * 0.75));  //translate to ground level
        
        pa.fill(56, 232, 53);  //ground colour
        pa.box(pa.width*2, 1, 750); //ground



        pa.translate(-(pa.width),0);
            
        float treePosX = 0;

        while(treePosX <= pa.width*2)
        {
            tree = new Tree(150, pa);
            pa.translate(treePosX, 0);
            tree.draw();
            treePosX+=5;
        }
    }

}


// float[] treePosArray;

    // public void createTreeArray()  //may have to make this less globally later
    // {
    //     for 

    // }