package Action;

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
    public Client(){
        FileStructure rootfile=new FileStructure(null,"BEN",1,1000);
        TreeItemView.setTreeRoot(rootfile);
        //------文件类型-------//
        FlowPane flow_pane=new FlowPane();
        Image filekind=new Image("Action/file.png",100,100,false,false);
        ImageView imageViewfile=new ImageView(filekind);
        Button fileButton=new Button("",imageViewfile);
        Label filelable=new Label("File");
        fileButton.setPrefHeight(130);
        fileButton.setPrefWidth(130);
        fileButton.setBackground(Background.EMPTY);
        VBox v=new VBox();
        v.getChildren().add(fileButton);
        v.getChildren().add(filelable);
        v.setAlignment(Pos.BOTTOM_CENTER);
        addRightMenu(v);                //文件右键菜单
        OpenAction(v);                  //双击文件
        flow_pane.getChildren().add(v);
        //---以下是文件夹类型---//
        Image folderkind=new Image("Action/folder.png",100,100,false,false);
        ImageView imageViewfolder=new ImageView(folderkind);
        Button catalogButton=new Button("",imageViewfolder);
        Label folderlable=new Label("Folder");
        catalogButton.setPrefHeight(130);
        catalogButton.setPrefWidth(130);
        catalogButton.setBackground(Background.EMPTY);
        VBox v2=new VBox();
        v2.getChildren().add(catalogButton);
        v2.getChildren().add(folderlable);
        v2.setAlignment(Pos.BOTTOM_CENTER);
        addRightMenu(v2);                //文件右键菜单
        OpenAction(v2);                  //双击文件
        flow_pane.getChildren().add(v2);

        //-------主面板的设置----------//
        addEmptyMenu(flow_pane);          //空白右键菜单
        flow_pane.setHgap(5);
        flow_pane.setVgap(5);
        flow_pane.setPrefWidth(180);
        flow_pane.setPrefHeight(610);
        flow_pane.setStyle("-fx-border-color: gray; -fx-background-color: #F8F8FF");

        //目录树块
//        AnchorPane anchor_pane=new AnchorPane();
//        anchor_pane.getChildren().add(TreeItemView.treeView);
//        TreeItemView.operationTree();
//        TreeItemView.addTreeChild(new FileStructure(rootfile,"ABC",2,1000));
//
//        Pane all_pane=new Pane();
//        all_pane.getChildren().add(anchor_pane);
//        all_pane.getChildren().add(flow_pane);
        Scene scene=new Scene(flow_pane,860,610);
        Stage primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void addRightMenu(VBox v){
        v.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(event.getButton()==MouseButton.SECONDARY) {
                    Node node = event.getPickResult().getIntersectedNode();
                    RightMenu.getInstance().show(node, event.getScreenX(), event.getScreenY());

                }

            }
        });
    }
    public static void addEmptyMenu(FlowPane flowPane)
    {
        flowPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                Node node = event.getPickResult().getIntersectedNode();
                if(event.getButton()==MouseButton.SECONDARY) {
                    Node node = event.getPickResult().getIntersectedNode();
                    EmptyMenu.getInstance().show(node, event.getScreenX(), event.getScreenY());
                }
                }
        });
    }
    public static void OpenAction(VBox v){
        v.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    Pane pane = new Pane();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(pane, 200, 300));
                    stage.show();
                }
            }
        });
    }
}
