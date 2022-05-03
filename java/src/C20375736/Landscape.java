package C20375736;

import processing.core.PApplet;

import ddf.minim.AudioBuffer;


public class Landscape
{
    float smoothedAmplitude = 0;

    PApplet pa;
    
	Cloud cloud;
    Forest forest;	    
	Volley volley;
    UI ui;

    public Landscape(PApplet pa)
    {
        this.pa = pa;
        
        cloud = new Cloud(pa);
        forest = new Forest(pa);
		volley  = new Volley(pa);
        ui = new UI(pa);
    }
    
    public void render(AudioBuffer ambiBuffer, AudioBuffer bassBuffer, AudioBuffer drumBuffer, float musicModifier, float bassModifier, float drumModifier, int signal)
    {
        pa.pushMatrix();

        pa.translate(pa.width/2, pa.height); //translate to bottom

        drawBackground(bassModifier);  //draw sky and ground
     
        forest.render(ambiBuffer, bassModifier);
		
        cloud.render(bassBuffer,bassModifier);

		volley.drawBolts(drumBuffer, drumModifier);

        ui.render(musicModifier, signal);


        
        pa.popMatrix();  //restore matrix to default
    }

    
    public void drawBackground(float bassModifier)
    {
        float BACK_MODIFIER = pa.map(bassModifier, 0, 100, 255, 80);
        float GROUND_MODIFIER = pa.map(bassModifier, 0, 100, 225, 130);


        pa.colorMode(pa.HSB);
        
        pa.background(135, BACK_MODIFIER, BACK_MODIFIER);  //rgb: 28, 221, 255
        
        pa.fill(80, GROUND_MODIFIER, GROUND_MODIFIER);  //ground colour, rgb:  56, 232, 53
        
        
        pa.box(pa.width*2, 1, 750); //draws ground (hard coded values need to be removed)

        pa.colorMode(pa.RGB);
    }
}

