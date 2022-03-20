package C20375736;


import processing.core.PApplet;


public class Landscape
{
    PApplet pa;
    
    final int TREEAMOUNT = 10; //will be made a dynamic value later
    
    Tree treeArray[];

    public Landscape(PApplet pa)
    {
        this.pa = pa;

        initTreeArray(TREEAMOUNT);
    }
    
    public void initTreeArray(int treeAmount)  //call this in constructor
    {  //populates the treeArray with the x positions of each tree

        treeArray = new Tree[treeAmount]; //initialise array

        float increment = (float)(pa.width/(treeAmount-1)); //treeAmount must be decremented to account for tree at first pos and end pos

        float treePosX = 0;
        Tree newTree;

        for(int i = 0; i < treeAmount; i++)
        {
            newTree = new Tree(pa, 150, treePosX, 0, 0);  //tree x and y left blank for now
            treeArray[i] = newTree; 
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

        pa.translate(-(pa.width/2),0);  //set the current drawing pos to the far left of the screen
            
        
        for(int i = 0; i < treeArray.length; i++)
        {
            treeArray[i].draw();
        }

        
    }

}


