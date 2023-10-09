# Soul-Mixer

## Demonstration Video: https://youtu.be/g2lAJUHPwt0

## Name: David Lynch & James Clarke

### Student Number: C19500876 & C20375736

### Song choice : Mr.Kill Myself - Sewerslvt

##### If you'd like to use the visualiser without the EEG, just press '1' on your keyboard to activate mouse mode

We chose this song since it would work in conjunction with the peripheral device we were planning on using __The Mindflex EEG__

The Mindflex is a childrens toy which can read your brain waves in order to operate a fan.
We repurposed the device so it can use our brain waves to affect the nature of the visualiser.
It works by measuring how much the wearer is focusing on a given task and it returns a number based on that.

The reason we chose the song was because of how there are varying levels of intensity throughout it.
This allows us to change the playback of the song so that users can get a clear reflection of their concentration levels and get instant feedback through the way the music changes and how that effects the visuals.

#### Communication with the EEG
The EEG connects to the user's computer over Bluetooth Comport. The comport is then read using the library 'jSerialComm'. 

We implemented the code from the following repo pertaining to the low level reading of the comport, converting the Brain.cpp file to java.

The code works by listening for certain bytes to determine a packets start and end, and then parsing said packet for information on the multiple "powerbands" that the EEG sends out. The only values we were concerned with was the one that represents users attention and signal quality, but there are many other values such as meditation, etc.




#### The Visualisation consists of 4 Dynamic elements.

* The Ground
* Trees
* Clouds
* Lightning

Each element of the song affects part of the visual through a modifier value generated by the participant's brain waves.

| ELEMENT | INSTRUMENT | EFFECT |
|---------|------------|--------|
| Ground  | No instrument | Colour is affected by the modifier value.|
||||
| Trees   | Ambience | Trees sway depending on the ambient track.|
|| | |
| Cloud   | Bass | The cloud will become more bumpy and chaotic depending on the bass.|
|         |      |                                                                    |
|Lightning| Drums | The drums will determine the depth the lightning strikes.|

Each element has been given it's own class with unique constructors and rendering methods.

Elements which needed duplicates such as Bolt and Tree had classes called Volley and Forest respectively which would handle the allocation and rendering of their respective classes.

This project posed many challenges but also allowed us to come up with creative and interesting solutions.

## Forest

Rendering a forest posed some challenges. The main challenge being determining how the forest should look and behave in relation to the music and modifier.

We achieved this by making the trees sway to the ambient track. 
```java
for(int i = 0; i < n; i++)
{
	pa.pushMatrix();
	pa.rotateZ(2*pa.PI*i/n);
	if(i != 0) pa.rotateZ(offset/15);	
	pa.translate(0,trunkLength/2,0);
	pa.fill(255, pa.map(bassModifier, 0, 100, 255, 125), pa.map(bassModifier, 0, 100, 70, 50));  //trunk colour, rgb: 51, 5, 5
	pa.box(trunkWidth,trunkLength,trunkWidth);
	pa.translate(0,trunkLength/2,0);
	pa.fill(90, LEAF_MODIFIER, LEAF_MODIFIER); //leaf colour, rgb: 0,255,0
	if(i != 0) pa.sphere(sphereRadius); 
	pa.popMatrix();
}
```

## Cloud

To simulate the cloud we utilized perlin noise. This was to generate the clouds in a pseudo random way while also retaining a natural feeling to them. 

Since perlin noise provides natural randomness we decided to store the values generated by the noise in a 2D array. Through the use of shape generation via triangle strips we were able to create a billowing cloud.

```java
for(int i = 0; i < yCount; i++)
{
	float xoff = 0;
	for(int j = 0; j < xCount; j++)
	{
		heights[j][i] = pa.map(pa.noise(xoff,yoff),0,1,-50,50);
		xoff += 0.4;
	}
	yoff += 0.4;
}
```
This code generates perlin noise for each vertex of the cloud and stores it in a 2D array to be used later when generating the clouds.

```java
public void render(AudioBuffer bassBuffer,float modifier)
{
	rolling += 0.01;
	float yoff = rolling;
	//clouds will now slide in to view based on the modifier
	hPos = pa.map(modifier,0,100,(float)-2.45 * pa.height/5,pa.height/6);
	for(int i = 0; i < yCount; i++)
	{
		float xoff = 0;
		for(int j = 0; j < xCount; j++)
		{
			heights[j][i] = pa.map(pa.noise(xoff,yoff),0,1,-50,50) * pa.map(bassBuffer.level(),0,0.2f,1,2.5f);
			xoff += 0.4;
		}
		yoff += 0.4;
	}
	pa.pushMatrix();
	//shapes and stuff go here
	pa.fill(76, 77, 75, pa.map(modifier, 0, 100, 0, 255));  //set cloud colour/opacity
	pa.stroke(0,0,0, pa.map(modifier, 0, 100, 0, 255));  //set outline colour
	for(int i = 0; i < yCount-1; i++)
	{
		pa.beginShape(pa.TRIANGLE_STRIP);
		for(int j = 0; j < xCount; j++)
		{
			pa.vertex(xGap * j, hPos + (yGap * i), heights[j][i]);
			pa.vertex(xGap * j, hPos + (yGap * (i+1)), heights[j][i+1]);
		}
		pa.endShape();
	}
	pa.noStroke();
	pa.popMatrix();
}
```

This code does three things.

* Updates the perlin noise array by moving forward through noise space.
* Draws the clouds by generating multiple triangle strips and then using the perlin noise array to determine the height of each vertex.
* Utilises the bass modifier to determine their position and also uses the bass player's audiobuffer to affect the height of each vertex.

This means that the clouds look like their moving through the air while also rumbling to the bass of the song.

## Lightning

Visualising a bolt of lightning was difficult since they are so spioradic in nature.
```java
public void strike(int depth, float len, float drumModifier)
{
	pa.noStroke();
	pa.fill(0,255,255, pa.map(drumModifier, 0, 100, 0, 255));
		//229,254,69
	pa.rotateY(pa.PI/2);
	for(int i = 0; i < depth; i++)
	{
		pa.pushMatrix();
		pa.rotateY(pa.TAU * i/depth);			
		if(i % 2 == 0)	pa.rotateZ(pa.PI/12);
		else pa.rotateZ(-pa.PI/12);
		pa.translate(0,len,0);
		pa.box(10,len,10);
		//recursive call (where the magic happens)
		if(i == 1) strike(depth-1,len - 2, drumModifier);
		pa.popMatrix();
	}
}
```
To get the lightning rendered we utilised recursion. This was to provide sense that the lightning was moving in a chaotic fashion while also providing us precise control over how deep the bolt is rendered along with the length of each segment. 

Because of this recursive method it means that the lightning reacts to the drums with great precision and gives the impression that the sound of the drums is coming from the lightning.

The drum modifier controls the transparency of the lightning and gives it an almost ethereal effect like it's actually made of electricity.

### All of these elements work in tandem to give the visualiser a feeling of coalescence. 

Each element plays a part in the visualisation and shows a storm emerging as the concentration ramps up.

The sky darkens, trees sway more frantically, the clouds roll in and rumble, and finally the lightning strikes.
All of this illustrates how the user's mind behaves as they focusing on a task. 
As they need to recruit more of their mental bandwidth the music and visuals follow along giving a window into someone's mind as they wrap their brain around a task.

## The music.

To achieve the effect of different track emerging with the user's focus we used the different stems of the song.

* The Ambience 
* The Bass
* The Drums

This way the song would start out with just the calm ambience track, then the bass would come in with the clouds, and finally the track would complete with the drums and the lightning.
