package C20375736;


import processing.core.PApplet;


public class Landscape
{
    PApplet pa;
    
    float[] treePosArray;

    int TREEAMOUNT = 10;
    

    public Landscape(PApplet pa)
    {
        this.pa = pa;

        
    }
    
    // public void createTreeArray()  //initaise this in constructor
    // {
    //     fo

    // }

    Tree tree;

    public void render()
    {
        pa.background(28, 221, 255);
        
        //pa.translate(pa.width/2, (float)(pa.height * 0.75));  //translate to ground level
        pa.translate(pa.width/2, pa.height);
        pa.fill(56, 232, 53);  //ground colour
        pa.box(pa.width*2, 1, 750); //ground



        pa.translate(-(pa.width/2),0);
            
        int treeAmount = 15;

        float increment = (float)(pa.width/(treeAmount-1)); //treeAmount must be decremented to account for tree at first pos and end pos

        float treePosX = 0;

        while(treePosX < pa.width)
        {
            tree = new Tree(pa, 150);
            tree.draw();
            pa.translate(increment, 0);
            treePosX+=increment;
        }
    }

}


