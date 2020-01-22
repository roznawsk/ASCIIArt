package util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PixelCounter{
    public static void main(String[] args) throws FileNotFoundException {
        String imageUrl = "resources/ASCIIChars/vertical_bar.jpg";
        Image image = new Image(new FileInputStream(imageUrl));
        PixelReader pixelReader = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int blackQ = 0;
        int whiteQ = 0;
        for(int i = 0 ; i < width ; i++){
            for(int j = 0 ; j < height ; j++){
                Color color = pixelReader.getColor(i, j);
                double gray = (color.getBlue() + color.getRed() + color.getGreen())/3;
                if(gray > 0.5)
                    whiteQ ++;
                else
                    blackQ ++;
            }
        }
        System.out.println("W: " + whiteQ + "  B: " + blackQ + " Ratio B/W: " + (double) blackQ/whiteQ);
    }
}
