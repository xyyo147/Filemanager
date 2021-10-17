package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Background extends Application{
    public void start(Stage primaryStage){
//        myJFrame backg = new myJFrame("background.jpg", 1.0f);
//        Group root=new Group();
//         StackPane stack=new StackPane();
//        BorderPane border=new BorderPane();
        Controller start=new Controller();
        start.Controller();
//        stack.getChildren().add(border);
//        primaryStage.setScene(new Scene(stack,1920,1080));
//        primaryStage.show();
    }

}
