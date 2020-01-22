package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class Window {
    private ImageProcessor processor;
    private Grid grid = new Grid();
    private HBox root = new HBox(10);
    private int photoFitWidth = 600;
    private int photoFitHeight = 600;

    public Window(Stage stage) throws FileNotFoundException {

        Image image = grid.getImage();
        this.processor = new ImageProcessor(image);
        double aspectRatio = image.getWidth() / image.getHeight();
        double realWidth = Math.min(photoFitWidth, photoFitHeight * aspectRatio);
        double realHeight = Math.min(photoFitHeight, photoFitWidth / aspectRatio);

        root.setPrefSize(realWidth + 300, realHeight);


        grid.getOptionsButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    processor = new ImageProcessor(grid.getImage());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                processor.setContrastInt(grid.getContrast());
                processor.setExposureInt(grid.getExposure());
                processor.setMethod(grid.getGrayscaleMethod());
                ImageView imageView = new ImageView(processor.getProcessedImage());
                imageView.setFitHeight(realHeight);
                imageView.setFitWidth(realWidth);
                imageView.setPreserveRatio(true);
                root.getChildren().clear();
                root.getChildren().addAll(imageView, grid);
            }
        });

        grid.getConvertButton().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                if(grid.getGrayscaleMethod() != GrayScaleMethod.None){
                    root.getChildren().clear();

                    int cellSize = grid.getCellSize();
                    double fontSize = grid.getFontSize();

                    Label label = new Label(processor.getASCIIText(cellSize));
                    label.setPadding(new Insets(20));
                    label.setStyle("-fx-line-spacing: -0.66em;-fx-font-size:" + fontSize + " ;-fx-font-family: 'monospaced';");
                    root.setPrefSize(root.getPrefWidth()*1.5, root.getPrefHeight()*1.5);
                    root.getChildren().addAll(label, grid);
                }
            }
        });

        ImageView imageView = new ImageView(processor.getProcessedImage());
        imageView.setFitWidth(realWidth);
        imageView.setFitHeight(realHeight);
        imageView.setPreserveRatio(true);
        root.getChildren().addAll(imageView, grid);


        // Set the padding of the HBox
        root.setStyle("-fx-padding: 10;");
        // Set the border-style of the HBox
        root.setStyle("-fx-border-style: solid inside;");
        // Set the border-width of the HBox
        root.setStyle("-fx-border-width: 2;");
        // Set the border-insets of the HBox
        root.setStyle("-fx-border-insets: 5;");
        // Set the border-radius of the HBox
        root.setStyle("-fx-border-radius: 5;");
        // Set the border-color of the HBox
        root.setStyle("-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
