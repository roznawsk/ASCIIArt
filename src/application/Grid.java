package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Grid extends GridPane {
    private final Button optionsButton = new Button ("Apply");
    private final Button convertButton = new Button("Convert to ASCII");

    private final Slider contrastSlider = new Slider();
    private final Slider exposureSlider = new Slider();

    private final ComboBox<GrayScaleMethod> grayscaleTypesComboBox = new ComboBox<>();
    private final ComboBox<Integer> cellSizeComboBox = new ComboBox<Integer>();
    private final ComboBox<Integer> fontSizeComboBox = new ComboBox<Integer>();
    private final ComboBox<File> picturesComboBox = new ComboBox<>();

    Grid(){
        for(GrayScaleMethod method : GrayScaleMethod.values()){
            grayscaleTypesComboBox.getItems().add(method);
        }
        for(int i = 1 ; i < 21 ; i++){
            cellSizeComboBox.getItems().add(i);
        }
        for(int i = 1 ; i < 7.1 ; i += 1){
            fontSizeComboBox.getItems().add(i);
        }
        final File folder = new File("resources/pictures");
        for (final File fileEntry : folder.listFiles()) {
            picturesComboBox.getItems().add(fileEntry);
        }
        grayscaleTypesComboBox.getSelectionModel().select(GrayScaleMethod.None);
        cellSizeComboBox.getSelectionModel().select(2);
        fontSizeComboBox.getSelectionModel().select(3);
        picturesComboBox.getSelectionModel().select(0);

        contrastSlider.adjustValue(0);
        contrastSlider.setMax(100);
        contrastSlider.setMin(-100);
        contrastSlider.setBlockIncrement(1);
        contrastSlider.setMinorTickCount(8);
        contrastSlider.setMajorTickUnit(25);
        contrastSlider.setShowTickLabels(true);
        contrastSlider.setShowTickMarks(true);

        exposureSlider.adjustValue(0);
        exposureSlider.setMax(100);
        exposureSlider.setMin(-100);
        exposureSlider.setBlockIncrement(1);
        exposureSlider.setMinorTickCount(8);
        exposureSlider.setMinorTickCount(25);
        exposureSlider.setShowTickLabels(true);
        exposureSlider.setShowTickMarks(true);

        setVgap(4);
        setHgap(10);
        setPadding(new Insets(5, 5, 5, 5));
        add(new Label("Picture: "), 0, 0);
        add(picturesComboBox, 1,0);
        add(new Label("Grayscale method: "), 0, 1);
        add(grayscaleTypesComboBox, 1, 1);
        add(new Label("Exposure: "), 0, 2);
        add(exposureSlider, 1,2);
        add(new Label("Contrast: "), 0,3);
        add(contrastSlider, 1,3);
        add(optionsButton, 1,4);
        add(new Label("Cell size: "), 0, 5);
        add(cellSizeComboBox, 1, 5);
        add(new Label("Font size: "),0, 6);
        add(fontSizeComboBox, 1, 6);
        add(convertButton, 1, 7);
    }

    public Button getOptionsButton() {
        return optionsButton;
    }

    public Button getConvertButton() {
        return convertButton;
    }

    public int getCellSize() {
        return cellSizeComboBox.getValue();
    }

    public GrayScaleMethod getGrayscaleMethod() {
        return grayscaleTypesComboBox.getValue();
    }

    int getFontSize() {
        return fontSizeComboBox.getValue();
    }

    int getContrast() {
        return (int) contrastSlider.getValue();
    }

    public int getExposure() {
        return (int) exposureSlider.getValue();
    }

    public Image getImage() throws FileNotFoundException {
        String imageUrl = picturesComboBox.getValue().toString();
        return new Image(new FileInputStream(imageUrl));
    }
}
