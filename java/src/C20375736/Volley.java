package C20375736;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
public class Volley
{	
	PApplet pa;
	Bolt boltArray[];

	final int BOLTAMOUNT = 5;
	
	public Volley(PApplet pa)
	{
		this.pa = pa;
		initBolts(BOLTAMOUNT);
	}

	public void initBolts(int BOLTAMOUNT)
	{
		boltArray = new Bolt[BOLTAMOUNT];

		float gap = pa.width/BOLTAMOUNT-1;


		float boltX = 0;
		Bolt newBolt;

		for(int i = 0; i < BOLTAMOUNT; i++)
		{
			newBolt = new Bolt(pa,boltX,0,0);
			boltArray[i] = newBolt;
			boltX += gap;
			System.out.println("INITIALIZED:" + i);
		}
	}

	public void drawBolts(AudioBuffer drumBuffer)
	{
		for(int i = 0; i < BOLTAMOUNT; i++)
		{
			boltArray[i].strike((int)pa.map(drumBuffer.level(),0,10,0,(float)0.1),30);
		}
	}
}
