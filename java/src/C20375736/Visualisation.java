package C20375736;

import com.fazecast.jSerialComm.*;

import processing.core.PApplet;



//audio stuff imports
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Visualisation extends PApplet{

    //eeg setup
    SerialPort comPort;

    Landscape landscape;
   

    //audio stuff variables
    Minim minim;
    AudioPlayer ambiPlayer;
    AudioBuffer ambiBuffer;
	AudioPlayer drumPlayer;
	AudioBuffer drumBuffer;
    
    int mode = 0;

    final int DRUM_GAIN_MIN  = -20;
    final int DRUM_GAIN_MAX = 5;

    public void keyPressed() {
        if (key == '1')  //mouse mode
        {
            mode = 1;
        }
        else if (key == '2')  //brain mode
        {
            mode = 2;
        }
    }
    
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

        //eeg setup
        System.out.println("Opening Comport");

        comPort = SerialPort.getCommPorts()[2];  
        
        comPort.openPort();
    }


	public float drumGain;
    public void draw()
    {   

        try
        {
            byte[] byteBuffer = new byte[comPort.bytesAvailable()];  //create a buffer of length of bytes available
            
            comPort.readBytes(byteBuffer, byteBuffer.length);  //read bytes into buffer

            for(int i = 0; i < byteBuffer.length; i++)  //loop through byte buffer
            {
                byte b = byteBuffer[i];

                boolean bGoodData = MindFlexReader.AddByte(Byte.toUnsignedInt(b)); //will return true when a complete packet has been processed

                if (bGoodData)
                {
                    System.out.println("Sig: "+ MindFlexReader.signalQuality + " Att: " + MindFlexReader.attention); 
                    drumGain = map(MindFlexReader.attention, 0, 100, DRUM_GAIN_MIN, DRUM_GAIN_MAX);   //change the volume of drumtrack based on mouseY, -20 to 15 is a good range for drum volumes
                    drumPlayer.shiftGain(drumPlayer.getGain(),drumGain,200); 
    
                }
            }
            
        }
        catch (Exception e)
        {
            System.out.println("No Signal!");
            //Thread.sleep(5); //wait
        }

        landscape.render(ambiBuffer);
    }


}
