package pkg4_2;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class mandelbrotSet extends JFrame{
    //Sets up global variables
    static final int width = 1280;
    static final int height = 720;
    static final double increment = 0.004;
    static double startX;
    static double startY;
    static double endX;
    static double endY;
    static int itterations;
    static int arrayPositionsX;
    static int arrayPositionsY;
    static complexNumber complexSet[][];
    static final Color inBoundsColor = Color.white;
    static final Color outOfBoundsColor = Color.black;
    
    //Inputs data from a file
    //The first line of input is the minimum coordinate (bottom left of image)
    //The second line of input is the maximum coordinate (top right of image)
    // The last line is the number of checks that should be made when deciding if a value is in the Mandelbrot Set
    public void inputData() throws IOException{
        FileReader f = new FileReader("Mandelbrot Set Input.txt");
        Scanner s = new Scanner(f);
        startX = s.nextDouble();
        startY = s.nextDouble();
        endX = s.nextDouble();
        endY = s.nextDouble();
        itterations = s.nextInt();
        s.close();
    }
    
    public void populate(){
        double xVal = startX;
        double yVal = endY;
        
        //Determines the amount of positions to allocate for the array based on the x and y-range
        arrayPositionsX = (int)((endX-startX)/increment + 1);
        arrayPositionsY = (int)((endY-startY)/increment + 1);
        //Assigns this number of positions to the global array complexSet
        complexSet = new complexNumber[arrayPositionsY][arrayPositionsX];
        
        for(int i = 0; i < arrayPositionsY; i++){
            for(int j = 0; j < arrayPositionsX; j++){
                //Stores the array with a complexNumber starting from the top left moving to the bottom right
                //depending on the x and y-Range and increment value
                complexNumber c = new complexNumber(xVal, yVal);
                complexSet[i][j] = c;
                xVal += increment;
            }
            xVal = startX;
            yVal -= increment;
        }
    }
    
    public void paint (Graphics g){
        int x = 0;
        int y = 0;
        
        for(int i = 0; i < arrayPositionsY; i++){
            for(int j = 0; j < arrayPositionsX; j++){
                int tries = complexSet[i][j].isInMandelbrotSet();
                if(tries == itterations){
                    //If the returned value is equal to the number of itterations, the complexNumber is in the Mandelbrot Set
                    g.setColor(inBoundsColor);
                }
                
                else{
                    //Otherwise, create a colour depending on how many tries were taken before the complexNumber was determined
                    //to be outside of the Mandelbrot Set
                    int shadeVal = (int)(tries/0.2);
                    
                    if (shadeVal > 255){
                        shadeVal = 255;
                    }
                    //Color shade = new Color(shadeVal, 0, 0); //RED
                    Color shade = new Color(0, shadeVal, 0); //GREEN
                    //Color shade = new Color(0, 0, shadeVal); //BLUE
                    //Color shade = new Color(shadeVal, shadeVal, 0); //YELLOW
                    //Color shade = new Color(shadeVal, 0, shadeVal); //HOT PINK
                    //Color shade = new Color(0, shadeVal, shadeVal); //AQUA
                    //Color shade = new Color(shadeVal, shadeVal, shadeVal); //WHITE
                    
                    g.setColor(shade);
                }
                //Draws a line 1 pixel long (creates a point)
                
                g.drawLine(x, y, x + 1, y + 1);
                x += 1;
            }
            x = 0;
            y += 1;
        }
    }
    
    public void initializeWindow(){
        setTitle("Mandelbrot Set");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(outOfBoundsColor);
        setVisible(true);
    }
}