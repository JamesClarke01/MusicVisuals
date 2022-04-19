package C20375736;

import processing.core.PApplet;

import ddf.minim.AudioBuffer;


public class Landscape
{

    float smoothedAmplitude = 0;

    PApplet pa;
    
    final int TREEAMOUNT = 20; //will be made a dynamic value later
	Cloud cloud;	    
    Tree treeArray[];
    public Landscape(PApplet pa)
    {
        this.pa = pa;

        initTreeArray(TREEAMOUNT);
		initCloud();

    }
    


    public void render(AudioBuffer ambiBuffer, float bassModifier)
    {
        pa.pushMatrix();
        drawBackground();

        pa.translate(-(pa.width/2),0);  //set the current drawing pos to the far left of the screen

        float singleTreeSum;
        float singleTreeAverage;
        
        int increment = (int)(ambiBuffer.size() / TREEAMOUNT);  //potential rounding error?
        
        int k=0;

        for(int i = 0; i < TREEAMOUNT; i++)
        {
            singleTreeSum = 0;
            singleTreeAverage = 0;

            for(int j = 0; j< increment; j++)
            {
                singleTreeSum += ambiBuffer.get(k);

                k++;
            }

            singleTreeAverage = singleTreeSum/TREEAMOUNT;

            //this line needs some tweaking
            treeArray[i].setSphereRadius(pa.lerp(treeArray[i].getSphereRadius(), pa.map(singleTreeAverage, -1, 1, 0, 50) ,  0.1f));
        }
        

        for(int i = 0; i < treeArray.length; i++)
        {
            treeArray[i].render();
            //treeArray[i].render(lerpedBuffer[i]);
            //System.out.println(lerpedBuffer[i]);
        }
		cloud.render(bassModifier);
        
        pa.popMatrix();  //restore matrix to default
    }



    public void drawBackground()
    {
        pa.background(28, 221, 255);
        
        //pa.translate(pa.width/2, (float)(pa.height * 0.75));  
        pa.translate(pa.width/2, pa.height); //translate to ground level
        pa.fill(56, 232, 53);  //ground colour
        
        pa.box(pa.width*2, 1, 750); //draws ground (hard coded values need to be removed)
    }

    public void initTreeArray(int treeAmount)  //call this in constructor
    {  //calculates tree positions and populates the treeArray with tree objects

        treeArray = new Tree[treeAmount]; //initialise array

        float increment = (float)(pa.width/(treeAmount-1)); //treeAmount must be decremented to account for tree at first pos and end pos

        float treePosX = 0;
        Tree newTree;
        float treeSize;

        for(int i = 0; i < treeAmount; i++)
        {
            treeSize = pa.random(100, 150);
            newTree = new Tree(pa, treeSize, treePosX, 0, 0);  //tree x and y left blank for now
            treeArray[i] = newTree; 
            treePosX += increment;
        }	
    }
	
	public void initCloud()
	{
		cloud = new Cloud(pa) ;	
	}

}

// // Calculate sum and average of the samples
//         // Also lerp each element of buffer;
//         for(int i = 0 ; i < ambiBuffer.size() ; i ++)
//         {   
//             singleTreeSum = 0;
//             singleTreeAverage = 0;
            
//             for(int j = 0; j< TREEAMOUNT;j++)
//             {
//                 ambiBuffer[i] 
//             }
            
            
//             //sum += pa.abs(ambiBuffer.get(i));


//             //lerpedBuffer[i] = pa.lerp(lerpedBuffer[i], ambiBuffer.get(i), 0.05f);
//         }
//         // average= sum / (float) ambiBuffer.size();

//         // smoothedAmplitude = pa.lerp(smoothedAmplitude, average, 0.1f);
