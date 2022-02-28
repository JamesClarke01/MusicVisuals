package C20375736;

import ie.tudublin.*;

import java.io.File; // Import the File class
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.IOException;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

//import processing.core.PApplet;

public class FirstVisualTest extends Visual{

    

    File myObj;
    Scanner myReader;

    public void settings()
    {
        size(1024, 500);
        
        
        try
        {
            myObj = new File("java/src/C20375736/mfReadings.txt");
            myReader = new Scanner(myObj);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public static int getMindValue(String s)
    {  //extract the mind reading value as an int from the input string

        int beginIndex = s.indexOf(",") + 1;

        String newString = s.substring(beginIndex);

        int endIndex = newString.indexOf(",");

        newString = newString.substring(0, endIndex);
        
        int mindValue = Integer.parseInt(newString);

        return mindValue;
    }


    public void draw()
    {
        colorMode(RGB);
            
        if (myReader.hasNextLine())
        {
            String data = myReader.nextLine();
            //System.out.println(data);

            if(!data.equals("Start"))
            {
                //System.out.println(getMindValue(data));
                int MindValue = getMindValue(data);
                int colourValue = (int)map(MindValue, 0, 100, 255, 0);

                System.out.println(colourValue);

                background(255, colourValue, 41);
            }
            
        }
       
    }

    // public static void readFile(String filepath) {
    
    //     try
    //     {
    //         File myObj = new File(filepath);
    //         Scanner myReader = new Scanner(myObj);
            
    //         while (myReader.hasNextLine())
    //         {
    //             String data = myReader.nextLine();
    //             //System.out.println(data);

    //             if(!data.equals("Start"))
    //             {
    //                 System.out.println(getMindValue(data));
    //             }
                
    //         }

    //         myReader.close();
    //     }
    //     catch (FileNotFoundException e)
    //     {
    //         System.out.println("An error occurred.");
    //         e.printStackTrace();
    //     }
    // }

}
