package C20375736;

import processing.core.PApplet;

import ddf.minim.AudioBuffer;

public class Forest
{
    
    PApplet pa;
    Tree treeArray[];
    final int TREEAMOUNT = 7; 

    public Forest(PApplet pa)
    {
        this.pa = pa;

        initTreeArray(TREEAMOUNT);
    }

    public void render(AudioBuffer ambiBuffer)
    {
        drawTrees(ambiBuffer);
    }

    public void initTreeArray(int treeAmount)  //call this in constructor
    {  //calculates tree positions and populates the treeArray with tree objects

        treeArray = new Tree[treeAmount]; //initialise array

        float increment = (float)(pa.width/(treeAmount-1)); //treeAmount must be decremented to account for tree at first pos and end pos

        float treePosX = 0;
        Tree newTree;
        float treeSize;

		float branchMin= 3;float branchMax = 6;

		float branches;

        for(int i = 0; i < treeAmount; i++)
        {
            treeSize = pa.random(100, 150);
			branches = pa.random(branchMin,branchMax);
            newTree = new Tree(pa, treeSize, treePosX, 0, 0,branches);  //tree x and y left blank for now
            treeArray[i] = newTree; 
            treePosX += increment;
        }	
    }


    public void drawTrees(AudioBuffer ambiBuffer)
    {
        float singleTreeSum;
        float singleTreeAverage;
        
        pa.pushMatrix();

        pa.translate(-(pa.width/2),0);  //set the current drawing pos to the far left of the screen

        int increment = (int)(ambiBuffer.size() / TREEAMOUNT);  //potential rounding error?
        
        int k=0;

        for(int i = 0; i < TREEAMOUNT; i++)
        {
            singleTreeSum = 0;
            singleTreeAverage = 0;

            //getting average of audio buffer
            for(int j = 0; j< increment; j++)  
            {
                singleTreeSum += ambiBuffer.get(k);

                k++;
            }
            singleTreeAverage = singleTreeSum/TREEAMOUNT;


            float offset = pa.map(singleTreeAverage, -0.1f, 0.1f, -20, 20);

            
            if(Math.abs(offset - treeArray[i].getOffset()) < 10) //eliminate shaking
            {
                treeArray[i].setOffset(pa.lerp(treeArray[i].getOffset(), offset,  0.1f));
            }
            
        }
        for(int i = 0; i < treeArray.length; i++)
        {
            treeArray[i].newRender((int)treeArray[i].branches);
        }

        pa.popMatrix();
    }
}

