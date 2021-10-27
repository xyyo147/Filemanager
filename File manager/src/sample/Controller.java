package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ScrollPane fatScroll;

    @FXML
    private AnchorPane left;
    @FXML
    private ScrollPane diskscroll;
    @FXML
    private Pane mid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Client();
        RefreshFat();
        RefreshDisk();
        left.getChildren().add(Client.init());
        mid.getChildren().add(Client.getFp());
    }
    public void RefreshDisk(){
        diskscroll.setContent(null);
        GridPane fatpane=new GridPane();
        fatpane.setVgap(15);
        fatpane.setHgap(10);
        for(int i=0;i<2;i++){
            for(int j=0;j<64;j++){
                VBox vBox=new VBox();
                int x=i*64+j;
                Button b =new Button(String.valueOf(x));
                if(FAT.fat[i][j]!=0)
                    b.setStyle("-fx-border-color:red");
                vBox.getChildren().addAll(b);
                fatpane.add(vBox,i,j);
            }
        }
        diskscroll.setContent(fatpane);
    }
    public void RefreshFat(){
        fatScroll.setContent(null);
        GridPane fatpane=new GridPane();
        fatpane.setVgap(15);
        fatpane.setHgap(10);
        for(int i=0;i<2;i++){
            for(int j=0;j<64;j++){
                VBox vBox=new VBox();
                int x=i*64+j;
                Label label = new Label(String.valueOf(x));
                Button b =new Button(String.valueOf(FAT.fat[i][j]));
                vBox.getChildren().addAll(label,b);
                fatpane.add(vBox,i,j);
            }
        }
        fatScroll.setContent(fatpane);
    }
}
