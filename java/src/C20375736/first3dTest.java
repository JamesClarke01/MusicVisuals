package C20375736;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

//from audio1
import ddf.minim.AudioBuffer;

public class first3dTest extends PApplet
{
    Minim minim;
    AudioPlayer ambiAp;
    AudioPlayer drumAp;

    //from audio1
    //AudioBuffer drumAb;
    //float[] lerpedBuffer;

    public void settings()
    {
        size(1024, 500, P3D);
    }

    public void setup()
    {
        minim = new Minim(this);
  
        ambiAp = minim.loadFile("ambiTestTrack.mp3", 1024);
        drumAp = minim.loadFile("drumTestTrack.mp3", 1024);
        
        ambiAp.play();
        drumAp.play();

        //drumAb = drumAp.mix;


    }

    public void draw()
    {
        fill(255, 243, 77);
        
        translate(width/2, height/4); 
        
        box(50, 100, 50);  //w, h, d

        
    }

}
