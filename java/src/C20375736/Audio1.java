package C20375736;


import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioPlayer ap1;


    public void settings()
    {
        size(1024, 500, P3D);
    }

    public void setup()
    {
        minim = new Minim(this);
  
        ap = minim.loadFile("ambiTestTrack.mp3", 1024);
        ap1 = minim.loadFile("drumTestTrack.mp3", 1024);
        ap.play();
        ap1.play();
      
    }

}
