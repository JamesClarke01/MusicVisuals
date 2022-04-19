package C20375736;

import processing.core.PApplet;

public class UI {
    
    PApplet pa;

    public UI(PApplet pa)
    {
        this.pa = pa;
    }
    
    public void render(float modifier)
    {
        //draw background rectange
        pa.rectMode(pa.CORNER);
        pa.fill(242, 154, 148);
        pa.rect(pa.width - 20, pa.height/2 - 50, 20, 50);

        //draw foreground rectange
        pa.fill(235, 52, 64);
        pa.rect(pa.width - 20, pa.height/2 - pa.map(modifier, 0, 100, 0, 50), 20, pa.map(modifier, 0, 100, 0, 50));  //pos x, pos y, width, height
    }

}
