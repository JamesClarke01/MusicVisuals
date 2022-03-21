package C20375736;


import processing.core.PApplet;


//audio stuff imports
import ddf.minim.AudioBuffer;
//import ddf.minim.AudioPlayer;
//import ddf.minim.Minim;

public class Landscape
{
    //audio variables
    float[] lerpedBuffer;
    float smoothedAmplitude = 0;

    //end audio variables

    PApplet pa;
    
    final int TREEAMOUNT = 10; //will be made a dynamic value later
    
    Tree treeArray[];

    public Landscape(PApplet pa)
    {
        this.pa = pa;

        initTreeArray(TREEAMOUNT);

        lerpedBuffer = new float[TREEAMOUNT];  //initialise lerped buffer to amount of trees
    }
    


    public void render(AudioBuffer ambiBuffer)
    {
        drawBackground();

        pa.translate(-(pa.width/2),0);  //set the current drawing pos to the far left of the screen
            
        //float average = 0;
        //float sum = 0;

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


            lerpedBuffer[i] = pa.lerp(lerpedBuffer[i], singleTreeAverage, 0.1f);
        }
        

        for(int i = 0; i < treeArray.length; i++)
        {
            treeArray[i].render(150 +(lerpedBuffer[i] * 300));

            //System.out.println(lerpedBuffer[i]);
        }

    }



    public void drawBackground()
    {
        pa.background(28, 221, 255);
        
        //pa.translate(pa.width/2, (float)(pa.height * 0.75));  //translate to ground level
        pa.translate(pa.width/2, pa.height);
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
            newTree = new Tree(pa, treeSize, treePosX, 0, 0);  //tree y and z left blank for now
            treeArray[i] = newTree; 
            treePosX += increment;
        }
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
