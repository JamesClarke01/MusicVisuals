package C20375736;

import com.fazecast.jSerialComm.*;

import processing.core.PApplet;



//audio stuff imports
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import C20375736.MindFlexReader;

public class Visualisation extends PApplet{

    //eeg setup
    SerialPort comPort;

    Landscape landscape;

    //float drumGain;
   

    //audio stuff variables
    Minim minim;
    AudioPlayer ambiPlayer;
    AudioBuffer ambiBuffer;
	AudioPlayer drumPlayer;
	AudioBuffer drumBuffer;
    AudioPlayer bassPlayer;
	AudioBuffer bassBuffer;
    
    float musicModifier;  //value from 0-100

    //for mode switch statemnet
    int mode = 0;

    final int GAIN_MIN  = -20;
    final int GAIN_MAX = 5;

    public void keyPressed() {
        if (key == '1')  //mouse mode
        {
            System.out.println("Switching to mouse mode");
            mode = 1;
        }
        else if (key == '2')  //brain mode
        {
            System.out.println("Switching to eeg mode");
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
        drumPlayer = minim.loadFile("drumsTestTrack.mp3", 1024);
        bassPlayer = minim.loadFile("bassTestTrack.mp3", 1024);
        ambiPlayer.play();
		drumPlayer.play();
        bassPlayer.play();
        ambiBuffer = ambiPlayer.mix;  //mix means mix right and left stereo
        drumBuffer = drumPlayer.mix;  //mix means mix right and left stereo
		bassBuffer = bassPlayer.mix;  //mix means mix right and left stereo

        //create landscape
        landscape = new Landscape(this);

        //eeg setup
        try
        {
            openComport();
            System.out.println("Comport opened");
        }
        catch (Exception e)
        {
            System.out.println("Failed to open comport");
        }
    }

    public void openComport()
    {
        comPort = SerialPort.getCommPorts()[2];  
        
        comPort.openPort();
    }

    public void setStemsGain(float x)
    {
        float drumGain, bassGain;

        final int DRUMSTART = 50;

        if(x <= DRUMSTART)  //bass will dynamically increase and drums are muted
        {
            bassGain = map(x, 0, DRUMSTART, GAIN_MIN, GAIN_MAX);
            drumGain = GAIN_MIN; 
        }
        else  //bass is at max volume and drums will dynamically increase
        {
            bassGain = GAIN_MAX;
            drumGain = map(x, DRUMSTART + 1, 100, GAIN_MIN, GAIN_MAX);
        }

        drumPlayer.shiftGain(drumPlayer.getGain(),drumGain,200); 
        bassPlayer.shiftGain(bassPlayer.getGain(),bassGain,200); 
        
    }
	
    public void draw()
    {   
        switch (mode)
        {
            case 1:  //mouse mode
            {
                musicModifier = map(mouseY, height, 0, 0, 100);
                setStemsGain(musicModifier);
                break;  //break must be here
            }
            case 2:
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
                            
                            setStemsGain(MindFlexReader.attention);
            
                        }
                    }
                    
                }
                catch (Exception e)
                {
                    System.out.println("No Signal!");
                    openComport();
                }

                break;
            }

            default:
            {
                drumPlayer.setGain(-30);
                bassPlayer.setGain(-30);
            }
        }

        landscape.render(ambiBuffer);
    }


}
