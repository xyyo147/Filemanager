package sample;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import sample.FileStructure;
//import javax.swing.text.html.ImageView;


public class Client {
    static AnchorPane ac;
    static FlowPane fp;
    public static FlowPane flow_pane=new FlowPane();
    public static FileStructure currentCatalog = null;
    public Client(){
        FileStructure rootFile=new FileStructure(null,"BEN",1,1000);
        currentCatalog = rootFile;
        TreeItemView.setTreeRoot(rootFile);


        //-------主面板的设置----------//
        addEmptyMenu(flow_pane);          //空白右键菜单
        flow_pane.setHgap(5);
        flow_pane.setVgap(5);
        flow_pane.setPrefWidth(449);
        flow_pane.setPrefHeight(512);
        flow_pane.setStyle("-fx-border-color: gray; -fx-background-color: #F8F8FF");
        fp=flow_pane;
        // 目录树块
        AnchorPane anchor_pane=new AnchorPane();
        anchor_pane.getChildren().add(TreeItemView.treeView);
        TreeItemView.operationTree();
        TreeItemView.addTreeChild(new FileStructure(rootFile,"ABC",2,1000));
        ac=anchor_pane;
        Pane all_pane=new Pane();
        all_pane.getChildren().add(anchor_pane);
        all_pane.getChildren().add(flow_pane);
//        Scene scene=new Scene(flow_pane,860,610);
//        Stage primaryStage=new Stage();
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void addEmptyMenu(FlowPane flowPane)
    {
        flowPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                Node node = event.getPickResult().getIntersectedNode();
                if(event.getButton()==MouseButton.SECONDARY) {
                    Node node = event.getPickResult().getIntersectedNode();
                    EmptyMenu.getInstance().setPrefSize(200,100);
                    EmptyMenu.getInstance().show(node, event.getScreenX(), event.getScreenY());
                }
                else
                    EmptyMenu.getInstance().hide();
            }
        });
    }




    public static FlowPane getFp(){
        return fp;
    }
    public static AnchorPane init(){
        return ac;
    }
}
