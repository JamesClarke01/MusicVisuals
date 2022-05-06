//Low level code for converting the brain reader to comport data to readable packets was ported to java from the following repo:
//https://github.com/kitschpatrol/Brain 

package C20375736;

public class MindFlexReader {
    
    
    private static final int MAX_PACKET_LENGTH = 32;
    private static final int EEG_POWER_BANDS = 8;

	private static boolean inPacket = false;
    private static int packetIndex = 0;
    private static int packetLength = 0;

    private static int checksumAccumulator = 0;
    private static int[] packetData = new int[MAX_PACKET_LENGTH];
    private static int[] eegPower = new int[EEG_POWER_BANDS];
    private static int lastByte = -1;
	
	public static int attention = -1;
	public static int signalQuality = 0;


    private static void clearEegPower()
	{
		for (int i = 0; i < EEG_POWER_BANDS; i++)
		{
			eegPower[i] = 0;
		}
	}

    private static boolean parsePacket(int[] packetData)
	{
		boolean parseSuccess = true;

		clearEegPower(); //will reset the "power band" values  

		int i = 0;
		while( i< packetLength)
		{
			int bCmd = packetData[i];
			if (bCmd==0x2)
            {
                signalQuality = packetData[++i];
            }
			else if (bCmd==0x4)
            {
                attention = packetData[++i];
            }
			else if (bCmd == 0x5)
            {
                //meditation value unused so skip and increment i
                i++;
            }
			else if (bCmd == 0x83)
			{
				i++;

				// Extract the values
				for (int j = 0; j < EEG_POWER_BANDS; j++)
				{
					eegPower[j] = ((int)packetData[++i] << 16) | ((int)packetData[++i] << 8) | (int)packetData[++i];
				}
					
			}
			else if (bCmd == 0x80)
			{
				//dont use value so skip and increment i
				i++;
			}
			else
			{
				//Broken packet
				parseSuccess = false;
			}
			i++;
		}
		return parseSuccess;
	}


    public static boolean AddByte(int latestByte)
	{//process the new received byte and return true if whole data line received

		boolean bNewData = false;

		//Begin building a packet
		if (inPacket)
		{
			//First byte tells us the length of the upcoming packet
			if (packetIndex == 0)
			{
				packetLength = latestByte;

				//Throw error if packet is too long
				if (packetLength > MAX_PACKET_LENGTH)
				{
					System.out.println("ERROR: Packet too long " + packetLength);
					inPacket = false;
				}
			}
			else if (packetIndex <= packetLength)
			{
				//Store the byte in an array
				packetData[packetIndex - 1] = latestByte;
				
				//Increment the checksum
				checksumAccumulator += latestByte;
			}
			else //if (packetIndex > packetLength)
			{
				//When we reach the end of the packet
                
				int checksum = latestByte;
				
				//note: typcasting from int to byte in java gives a range of -128 to 127
				checksumAccumulator = (byte)(255 - checksumAccumulator);  
				checksum = (byte)(checksum);
			
				//Compare checksum value
				if (checksum == checksumAccumulator)
				{
					bNewData = parsePacket(packetData);
				}
				else
				{
					System.out.println("ERROR: Checksum");
				}

				//Reset and prepare for the next packet
				inPacket = false;
			}
			packetIndex++;
		}

		
		if ((latestByte == 170) && (lastByte == 170) && !inPacket)
		{
			
			inPacket = true;
			packetIndex = 0;
			checksumAccumulator = 0;
		}

		// Keep track of the last byte so we can find the sync byte pairs.
		lastByte = latestByte;
		return bNewData;
	}

   
}
