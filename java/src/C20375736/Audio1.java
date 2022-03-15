package C20375736;


import java.util.concurrent.TimeUnit;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet
{
    Minim minim;
    AudioPlayer ambiAp;
    AudioPlayer drumAp;


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

        //drumAp.setGain(0);
        
        //float startVolume = 1;
        //float endVolume = 0f;

        //drumAp.shiftVolume(startVolume, endVolume, 5);

        

        // while(true)
        // {
        //     gain--;
        //     
        //     System.out.println(gain);
        // }
        //drumAp.setGain(-50f);

        // while(true)
        // {
        //     gain--;
        //     drumAp.setGain(gain);
        //     try {
        //         TimeUnit.SECONDS.sleep(1);
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        //     System.out.println(gain);
        // }
        //drumAp.mute();
        float gain;

        // for(int i=0; i<100; i++)
        // {

        //     try
        //     {
                
        //         TimeUnit.MILLISECONDS.sleep(500);
        //     }
        //     catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }

        //     gain = map(i, 0, 100, -25, 0);
        //     drumAp.setGain(gain);
            
        //     System.out.println("i:"+i + " Gain:"+gain);

        // }


        while(true)
        {
            drumAp.setGain(-10);
        }

        //drumAp.setGain(-25);
        
    }

}
