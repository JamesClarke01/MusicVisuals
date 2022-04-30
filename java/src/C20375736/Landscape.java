package C20375736;

import processing.core.PApplet;

import ddf.minim.AudioBuffer;


public class Landscape
{

    float smoothedAmplitude = 0;

    PApplet pa;
    
	Cloud cloud;
    Forest forest;	    

    public Landscape(PApplet pa)
    {
        this.pa = pa;
        
        cloud = new Cloud(pa);
        forest = new Forest(pa);
    }
    
    public void render(AudioBuffer ambiBuffer, AudioBuffer bassBuffer,float bassModifier)
    {
        pa.pushMatrix();

        pa.translate(pa.width/2, pa.height); //translate to bottom

        drawBackground();  //draw sky and ground

        forest.render(ambiBuffer);

		cloud.render(bassBuffer,bassModifier);
        

        pa.popMatrix();  //restore matrix to default
    }

    
    public void drawBackground()
    {
        pa.background(28, 221, 255);
        
        pa.fill(56, 232, 53);  //ground colour
        
        pa.box(pa.width*2, 1, 750); //draws ground (hard coded values need to be removed)
    }

}

