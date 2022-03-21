package C20375736;

import processing.core.PApplet;

//audio stuff imports
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Visualisation extends PApplet{
    
    Landscape landscape;

    //audio stuff variables
    Minim minim;
    AudioPlayer ambiPlayer;
    AudioBuffer ambiBuffer;

    public void settings()
    {
        size(1024, 500, P3D);
    }

    public void setup()
    {
        //audio stuff
        minim = new Minim(this);

        ambiPlayer = minim.loadFile("ambiTestTrack.mp3", 1024);
        //ambiPlayer = minim.loadFile("drumTestTrack.mp3", 1024);
        ambiPlayer.play();
        ambiBuffer = ambiPlayer.mix;  //mix means mix right and left stereo


        //create landscape
        landscape = new Landscape(this);

    }


    public void draw()
    {
        landscape.render(ambiBuffer);
    }


}
