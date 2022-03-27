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
	AudioPlayer drumPlayer;
	AudioBuffer drumBuffer;
    public void settings()
    {
        size(1024, 500, P3D);
    }

    public void setup()
    {
        //audio stuff
        minim = new Minim(this);

        ambiPlayer = minim.loadFile("ambiTestTrack.mp3", 1024);
        drumPlayer = minim.loadFile("drumTestTrack.mp3", 1024);
        ambiPlayer.play();
		drumPlayer.play();
        ambiBuffer = ambiPlayer.mix;  //mix means mix right and left stereo
        drumBuffer = drumPlayer.mix;  //mix means mix right and left stereo
		

        //create landscape
        landscape = new Landscape(this);

    }

	public float ambiGain;
	public float drumGain;
    public void draw()
    {
		
		//gain calc
		if(mouseX <= width/2)
		{
			ambiGain = map(mouseX,0,width/2,-10,20);
			ambiPlayer.shiftGain(ambiPlayer.getGain(),ambiGain,200);
			drumPlayer.setGain(-200);
		}
		if(mouseX > width/2)
		{
			drumGain = map(mouseX, width/2 + 1, width,-10, 20);
			drumPlayer.shiftGain(drumPlayer.getGain(),drumGain,200);
			ambiPlayer.setGain(20);
		}

        landscape.render(ambiBuffer);
    }


}
