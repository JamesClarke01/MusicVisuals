package C20375736;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class audio3d extends PApplet
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

        ap = minim.loadFile("drumTestTrack.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(RGB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }



    public void draw()
    {

        //float halfH = height / 2;
        float average = 0;
        float sum = 0;
        

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        

        System.out.println(smoothedAmplitude);
        
        background(0);

        
        
        translate(width/2, height/4); 
        
        fill(255, 243, 77);

        box(50, map(smoothedAmplitude, 0, 0.1f, 0, 100), 50);  //w, h, d
        
        

    }        
}
