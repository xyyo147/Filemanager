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
public class FlowPaneAction {
    public static int number_file = 1;
    public static int number_catalog = 1;
    public static void fileIcon(){
        String name_number = "F"+number_file+"";
        Image fileKind=new Image("sample/file.png",100,100,false,false);
        ImageView imageViewFile=new ImageView(fileKind);
        Button fileButton=new Button("",imageViewFile);
        Label fileLable=new Label(name_number);
        fileButton.setPrefHeight(130);
        fileButton.setPrefWidth(130);
        fileButton.setBackground(Background.EMPTY);
        VVBox v=new VVBox();
        v.getChildren().add(fileButton);
        v.getChildren().add(fileLable);
        v.setAlignment(Pos.BOTTOM_CENTER);
        MenuAction.addRightMenu(v);                //文件右键菜单
        OpenActionFile(v);                  //双击文件
        Client.flow_pane.getChildren().add(v);

        v.catalog = FileOperation.createFile(Client.currentCatalog,name_number);//创建一个新文件并绑定
        number_file++;
    }
    //创建一个文件图标

    public static void OpenActionFile(VVBox v){
        v.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {

                }
            }
        });
    }
    //双击文件的操作

    public static void file_enter(FileStructure file){
        Image folderKind=new Image("sample/file.png",100,100,false,false);
        ImageView imageViewFolder=new ImageView(folderKind);
        Button catalogButton=new Button("",imageViewFolder);
        Label folderLable=new Label(file.getName());
        catalogButton.setPrefHeight(130);
        catalogButton.setPrefWidth(130);
        catalogButton.setBackground(Background.EMPTY);
        VVBox v=new VVBox();
        v.getChildren().add(catalogButton);
        v.getChildren().add(folderLable);
        v.setAlignment(Pos.BOTTOM_CENTER);
        MenuAction.addRightMenu(v);                //文件右键菜单
        Client.flow_pane.getChildren().add(v);

        v.catalog = file;  //将当前VBOX绑定目录
    }
    //将当前目录的子文件导入面板

    //___________________________________________________________文件操作——————————————————————————————————————————————————————


    public static void catalogIcon(){
        String name_number = "C"+number_catalog+"";
        Image folderKind=new Image("sample/folder.png",100,100,false,false);
        ImageView imageViewFolder=new ImageView(folderKind);
        Button catalogButton=new Button("",imageViewFolder);
        Label folderLable=new Label(name_number);
        catalogButton.setPrefHeight(130);
        catalogButton.setPrefWidth(130);
        catalogButton.setBackground(Background.EMPTY);
        VVBox v2=new VVBox();
        v2.getChildren().add(catalogButton);
        v2.getChildren().add(folderLable);
        v2.setAlignment(Pos.BOTTOM_CENTER);
        MenuAction.addRightMenu(v2);                //文件右键菜单
        OpenActionCatalog(v2);                  //双击文件
        Client.flow_pane.getChildren().add(v2);

        v2.catalog =  FileOperation.createCatalog(Client.currentCatalog,name_number);//创建一个新目录并绑定
        number_catalog++;

    }
    //创建一个目录图标





    public static void OpenActionCatalog(VVBox v){
        v.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2) {
                    Client.flow_pane.getChildren().clear();
                    Client.currentCatalog = v.catalog;

                    //以下是子目录传入操作
                    for(FileStructure file:Client.currentCatalog.getFlieCatalog()){
                        if(file.getAttribute()==1000){
                            catalog_enter(file);
                        }
                        else{
                            file_enter(file);
                        }
                    }
                }
            }
        });
    }
    //双击目录的操作：将流动面板清空，子目录链表导入


    public static void catalog_enter(FileStructure catalog){
        Image folderKind=new Image("sample/folder.png",100,100,false,false);
        ImageView imageViewFolder=new ImageView(folderKind);
        Button catalogButton=new Button("",imageViewFolder);
        Label folderLable=new Label(catalog.getName());
        catalogButton.setPrefHeight(130);
        catalogButton.setPrefWidth(130);
        catalogButton.setBackground(Background.EMPTY);
        VVBox v=new VVBox();
        v.getChildren().add(catalogButton);
        v.getChildren().add(folderLable);
        v.setAlignment(Pos.BOTTOM_CENTER);
        MenuAction.addRightMenu(v);                //文件右键菜单
        Client.flow_pane.getChildren().add(v);

        v.catalog = catalog;  //将当前VBOX绑定目录
    }
    //将当前目录的子目录导入面板


    //--------------------------目录操作-----------------------------------------


}
