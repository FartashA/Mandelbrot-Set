package pkg4_2;
import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException{
        mandelbrotSet ms = new mandelbrotSet();
        ms.inputData();
        ms.populate();
        ms.initializeWindow();
    }
}
