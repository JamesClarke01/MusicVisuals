package C20375736;

import com.fazecast.jSerialComm.*;

import processing.core.PApplet;



//audio stuff imports
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
//import C20375736.MindFlexReader;

public class Visualisation extends PApplet{

    //eeg setup
    SerialPort comPort;

    //visual classes
    Landscape landscape;
    UI ui;
    
   

    //audio stuff variables
    Minim minim;
    AudioPlayer ambiPlayer;
    AudioBuffer ambiBuffer;
	AudioPlayer drumPlayer;
	AudioBuffer drumBuffer;
    AudioPlayer bassPlayer;
	AudioBuffer bassBuffer;
    

    float musicModifier;  //value from 0-100
    float attention; 
    int signal;

    //for mode switch statemnet
    int mode = 0;

    final int MUSICSPLIT = 50;  //0-100, used for determining when bass caps and drums start
    final int CALMZONE = 20;

    Queue queue;

    public void keyPressed() 
	{
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

        signal = 100;
        
    }
    
    public void settings()
    {
        size(1024, 500, P3D);
        
    }

    public void setup()
    {
        colorMode(RGB);

        //audio stuff
        minim = new Minim(this);

        ambiPlayer = minim.loadFile("ambiTestTrack.mp3", 1024);
        drumPlayer = minim.loadFile("drumsTestTrack.mp3", 1024);
        bassPlayer = minim.loadFile("bassTestTrack.mp3", 1024);
        
        // ambiPlayer = minim.loadFile("mkmsAmbiAud.mp3", 1024);
        // drumPlayer = minim.loadFile("mkmsDrumsAud.mp3", 1024);
        // bassPlayer = minim.loadFile("mkmsBassAud.mp3", 1024);

        ambiPlayer.loop();
		drumPlayer.loop();
        bassPlayer.loop();
        ambiBuffer = ambiPlayer.mix;  //mix means mix right and left stereo
        drumBuffer = drumPlayer.mix;  //mix means mix right and left stereo
		bassBuffer = bassPlayer.mix;  //mix means mix right and left stereo

        //initialise visual classes
        landscape = new Landscape(this);
        ui = new UI(this);

        //eeg setup
        signal = 100;
        openComport();
        
        queue = new Queue();
    }

    public void openComport()
    {
        try
        {
            comPort = SerialPort.getCommPorts()[2];  
            
            comPort.openPort();

            System.out.println("Comport opened");
        }
        catch (Exception e)
        {
            System.out.println("Failed to open comport!");
        }
    }

    public float drumModifier(float modifier)
    {
        float drumModifier;

        if(modifier < MUSICSPLIT)  //drums are muted
        {
            drumModifier = 0;
        }
        else  //drums will dynamically increase
        {
            drumModifier = map(modifier, MUSICSPLIT, 100, 0, 100);
        }

        return drumModifier;
    }

    public float bassModifier(float modifier)
    {
        float bassModifier;

        if(modifier < MUSICSPLIT && modifier > CALMZONE)  //bass will dynamically increase
        {
            bassModifier = map(modifier, CALMZONE, MUSICSPLIT, 0, 100);;
        }
        else if(modifier > MUSICSPLIT)
        { //bass is at max volume
            bassModifier = 100;
        }
        else
        {
            bassModifier = 0;
        }

        return bassModifier;
    }

    public void setStemsGain(float x)
    {   
        final int GAIN_MIN  = -20;
        final int GAIN_MAX = 5;

        float drumGain, bassGain;

        drumGain = map(drumModifier(x), 0, 100, GAIN_MIN, GAIN_MAX);
        bassGain = map(bassModifier(x), 0, 100, GAIN_MIN, GAIN_MAX);

        drumPlayer.shiftGain(drumPlayer.getGain(),drumGain,200); 
        bassPlayer.shiftGain(bassPlayer.getGain(),bassGain,200); 
        
    }
	
    public void draw()
    {   
        switch (mode)
        {
            case 1:  //mouse mode
            {
                signal = 100;
                musicModifier = map(mouseY, height, 0, 0, 100);
                attention = musicModifier;
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
                            //System.out.println("Sig: "+ MindFlexReader.signalQuality + " Att: " + MindFlexReader.attention); 
                            
                            //musicModifier = lerp(musicModifier, MindFlexReader.attention, 0.f);
                            //musicModifier = MindFlexReader.attention;

                            if(queue.isFull())
                            {
                                queue.dequeue();
                            }

                            attention = MindFlexReader.attention;
                            signal = MindFlexReader.signalQuality;

                            queue.enqueue(attention);

                            musicModifier = queue.average();

                            //System.out.println(musicModifier);

                            setStemsGain(musicModifier);
            
                        }
                    }
                    
                }
                catch (Exception e)
                {
                    System.out.println("No Signal!");
                    openComport();
                    try {
                        Thread.sleep(1500);
                    } catch(InterruptedException interupException) {
                        System.out.println("Got interrupted!");
                    }
                }

                break;
            }

            default:
            {
                drumPlayer.setGain(-30);
                bassPlayer.setGain(-30);
            }
        }

        
        
        landscape.render(ambiBuffer,bassBuffer, drumBuffer, musicModifier, bassModifier(musicModifier), drumModifier(musicModifier), signal);
        ui.render(attention, signal);
       
        
    }


}
