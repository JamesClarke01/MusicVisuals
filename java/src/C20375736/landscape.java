package C20375736;


import processing.core.PApplet;


public class Landscape
{
    PApplet pa;
    
    final int TREEAMOUNT = 10; //will be made a dynamic value later
    
    float treePosArray[];

    public Landscape(PApplet pa)
    {
        this.pa = pa;

        initTreeArray(TREEAMOUNT);
    }
    
    public void initTreeArray(int treeAmount)  //call this in constructor
    {  //populates the treePosArray with the x positions of each tree

        treePosArray = new float[treeAmount]; //initialise array

        float increment = (float)(pa.width/(treeAmount-1)); //treeAmount must be decremented to account for tree at first pos and end pos

        float treePosX = 0;
        

        for(int i = 0; i < treeAmount; i++)
        {
            treePosArray[i] = treePosX;  //a tree must be added in 0 position
            treePosX += increment;
        }
    }

    
    

    public void render()
    {
        pa.background(28, 221, 255);
        
        //pa.translate(pa.width/2, (float)(pa.height * 0.75));  //translate to ground level
        pa.translate(pa.width/2, pa.height);
        pa.fill(56, 232, 53);  //ground colour
        
        pa.box(pa.width*2, 1, 750); //draws ground (hard coded values need to be removed)

        pa.translate(-(pa.width/2),0);
            
        for(int i = 0; i < treePosArray.length; i++)
        {
            pa.pushMatrix(); //push 0,0 coords onto matrix stack
            pa.translate(treePosArray[i], 0);  //translate to coords of current tree
            
            Tree tree = new Tree(pa, 150);
            tree.draw();
            pa.popMatrix(); //return to 0,0 coords
        }

        
    }

}


