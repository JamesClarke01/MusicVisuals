package C20375736;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class landscape extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;



    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    
    public void settings()
    {
        size(1024, 500, P3D);
        //fullScreen(P3D, SPAN);
        
    }

    public void setup()
    {
        minim = new Minim(this);

        ap = minim.loadFile("ambiTestTrack.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(RGB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

	float rot = 0;

    Tree tree;
    public void draw()
    {

        // //float halfH = height / 2;
        // float average = 0;
        // float sum = 0;
        

        // // Calculate sum and average of the samples
        // // Also lerp each element of buffer;
        // for(int i = 0 ; i < ab.size() ; i ++)
        // {
        //     sum += abs(ab.get(i));
        //     lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        // }
        // average= sum / (float) ab.size();

        // smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        

        // System.out.println(smoothedAmplitude);
        
	

        background(28, 221, 255);
        
        translate(width/2, (float)(height * 0.75));
        fill(56, 232, 53);  //ground colour
        box(width*2, 1, 750); //ground



        translate(-(width),0);
            
        float treePosX = 0;

        while(treePosX <= width*2)
        {
            tree = new Tree(150, this);
            translate(treePosX, 0);
            tree.draw();
            treePosX+=5;
        }
        
        

        

    }        
}
