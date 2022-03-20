package C20375736;

import processing.core.PApplet;

public class Visualisation extends PApplet{
    
    Landscape landscape;

    public void settings()
    {
        size(1024, 500, P3D);
    }

    public void setup()
    {
        landscape = new Landscape(this);
    }


    public void draw()
    {
        landscape.render();
    }
}
