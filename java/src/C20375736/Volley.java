package C20375736;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
public class Volley
{	
	PApplet pa;
	Bolt boltArray[];

	final int BOLTAMOUNT = 6;
	
	public Volley(PApplet pa)
	{
		this.pa = pa;
		initBolts(BOLTAMOUNT);
	}

	public void initBolts(int BOLTAMOUNT)
	{
		boltArray = new Bolt[BOLTAMOUNT];

		float gap = (pa.width + 200)/BOLTAMOUNT;


		float boltX = 0;

		Bolt newBolt;
		for(int i = 0; i < BOLTAMOUNT; i++)
		{
			newBolt = new Bolt(pa,boltX + (gap * i) - pa.width/2,-400,0);
			boltArray[i] = newBolt;
			System.out.println("INITIALIZED:" + i);
		}
	}

	public void drawBolts(AudioBuffer drumBuffer)
	{
		for(int i = 0; i < BOLTAMOUNT; i++)
		{
			boltArray[i].charge();
			boltArray[i].strike((int)pa.map(drumBuffer.level(),0,0.1f,0,15),50);
			boltArray[i].discharge();
		}
	}
}
