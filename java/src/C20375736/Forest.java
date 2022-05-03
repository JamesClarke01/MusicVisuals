package C20375736;

import processing.core.PApplet;

import ddf.minim.AudioBuffer;

public class Forest
{
    
    PApplet pa;
    Tree treeArray[];
    final int TREECOLS = 7; 
	final int TREEROWS = 3;
	final int TREEAMOUNT = TREECOLS * TREEROWS;

    public Forest(PApplet pa)
    {
        this.pa = pa;

        initTreeArray(TREECOLS,TREEROWS);
    }

    public void render(AudioBuffer ambiBuffer, float bassModifier)
    {
        drawTrees(ambiBuffer, bassModifier);
    }

    public void initTreeArray(int treeCols, int treeRows)  //call this in constructor
    {  //calculates tree positions and populates the treeArray with tree objects

		int treeAmount = treeRows * treeCols;

        treeArray = new Tree[treeAmount]; //initialise array

        float incrementX = (float)(pa.width/(treeCols-1)); //treeAmount must be decremented to account for tree at first pos and end pos
		float incrementZ = 100;
		
        float treePosX = 0;
		float treePosZ = 0;
        Tree newTree;
        float treeSize;

		float branchMin= 3;float branchMax = 6;

		float branches;

        for(int i = 0; i < treeAmount; i++)
        {
            treeSize = pa.random(100, 150);
			branches = pa.random(branchMin,branchMax);
            newTree = new Tree(pa, treeSize, treePosX, 0, treePosZ,branches);  //tree x and y left blank for now
            treeArray[i] = newTree; 
            treePosX += incrementX;
			if(i % treeCols == treeCols-1) 
			{
				treePosZ -= incrementZ;
				treePosX = incrementX/2;
			}
        }	
    }


    public void drawTrees(AudioBuffer ambiBuffer, float bassModifier)
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

            //pa.stroke(0);
            if(Math.abs(offset - treeArray[i].getOffset()) < 10) //eliminate shaking
            {
                treeArray[i].setOffset(pa.lerp(treeArray[i].getOffset(), offset,  0.1f));
            }
            
        }
        for(int i = 0; i < treeArray.length; i++)
        {
            treeArray[i].newRender((int)treeArray[i].branches, bassModifier);
        }

        pa.popMatrix();
    }
}

