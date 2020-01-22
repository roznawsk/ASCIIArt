import application.Window;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        Window window = new Window(stage);

        stage.setTitle("ASCII Art");
        stage.show();

    }

}