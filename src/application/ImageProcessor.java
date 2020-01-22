package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageProcessor {

    private Image image;
    private WritableImage processedImage;
    private PixelReader imageReader;
    private PixelReader processedImageReader;
    private PixelWriter pixelWriter;
    private int width;
    private int height;
    private GrayScaleMethod method = GrayScaleMethod.None;
    private int contrastInt = 0;
    private int exposureInt = 0;

    public ImageProcessor(Image image){
        this.image = image;
        this.imageReader = image.getPixelReader();
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        this.processedImage = new WritableImage(width, height);
        this.pixelWriter = processedImage.getPixelWriter();
        this.processedImageReader = processedImage.getPixelReader();
    }

    public Image getProcessedImage(){
        applyGrayscale();
        applyExposure();
        applyContrast();
        return processedImage;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setContrastInt(int contrastInt) {
        this.contrastInt = contrastInt;
    }

    public void setExposureInt(int exposureInt) {
        this.exposureInt = exposureInt;
    }

    public void setMethod(GrayScaleMethod method) {
        this.method = method;
    }

    private void applyGrayscale()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(method == GrayScaleMethod.None){
                    pixelWriter.setColor(x, y, imageReader.getColor(x, y));
                }
                else{
                    Color color = imageReader.getColor(x, y);
                    double gray = method.getGray(color);
                    pixelWriter.setColor(x, y, Color.color(gray, gray, gray));
                }
            }
        }
    }

    private void applyExposure(){
        if(exposureInt != 0){
            double exposure =  ((double) exposureInt )/ 100;

            for(int y = 0; y < height; y++)
            {
                for(int x = 0; x < width; x++)
                {
                    Color color = processedImageReader.getColor(x, y);
                    double r = Math.max(0, Math.min(1,color.getRed() * Math.pow(2, exposure)));
                    double g = Math.max(0, Math.min(1,color.getGreen() * Math.pow(2, exposure)));
                    double b = Math.max(0, Math.min(1,color.getBlue() * Math.pow(2, exposure)));
                    pixelWriter.setColor(x, y, Color.color(r, g, b));
                }
            }
        }
    }

    private void applyContrast()
    {
        if(contrastInt != 0){
            double contrast =  ((double) contrastInt )/ 100;

            for(int y = 0; y < height; y++)
            {
                for(int x = 0; x < width; x++)
                {
                    Color color = processedImageReader.getColor(x, y);
                    double r = Math.max(0, Math.min(1,(0.5 + (color.getRed() - 0.5) * Math.pow(2, contrast))));
                    double g = Math.max(0, Math.min(1,(0.5 + (color.getGreen() - 0.5) * Math.pow(2, contrast))));
                    double b = Math.max(0, Math.min(1,(0.5 + (color.getBlue() - 0.5) * Math.pow(2, contrast))));
                    pixelWriter.setColor(x, y, Color.color(r, g, b));
                }
            }
        }
    }

    public String getASCIIText (int cellSize){
        StringBuilder text = new StringBuilder("");

        for(int i = 0 ; i < height ; i += cellSize){
            for(int j = 0 ; j < width ; j+=cellSize){
                double sum = 0;
                double area = 0;
                for(int k = 0 ; k < cellSize && i + k < height ; k++){
                    for(int l = 0 ; l < cellSize && j + l < width; l++, area++){
                        sum += imageReader.getColor(j + l, i + k).getRed();
                    }
                }
                double wanted = sum/area;
                text.append(ASCIIChar.getChar(wanted));
            }
            text.append('\n');
        }
        return text.toString();
    }
}
