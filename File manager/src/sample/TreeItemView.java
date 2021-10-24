package sample;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sun.reflect.generics.tree.Tree;

public class TreeItemView {
    public static TreeView<String> treeView;
    public static TreeItem<String>froot;
    //设置目录树的根目录
    public static void setTreeRoot(FileStructure rootfile)
    {
        String fileName= rootfile.getName();
        Image fileicon=new Image("sample/folder.png",30,30,false,false);
        TreeItem childItem=new TreeItem(fileName,new ImageView(fileicon));
        froot=new TreeItem("根目录");
        childItem.setExpanded(true);
        if(!fileName.equals("C"))
        froot.getChildren().add(childItem);
        FileAndPane.TreeItemtoFile.put(froot,rootfile);
        FileAndPane.FiletoTreeItem.put(rootfile,froot);
        treeView=new TreeView(froot);
        treeView.setRoot(froot);
        treeView.setPrefWidth(180);
        treeView.setPrefHeight(610);
        treeView.setStyle("-fx-border-color: gray; -fx-background-color: #F8F8FF");
    }
    public static void operationTree(){
        treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
                    FileStructure file=FileAndPane.TreeItemtoFile.get(item);
                    if(file.getAttribute()==0001) {
                        FileOperation.readFile(file);
                    }
                        else if(file.getAttribute()==0100){
                            FileOperation.writeFile(file,"hhh"); //这里的String参数不太知道填什么
                    }
                            else if(file.getAttribute()==1000){
                                FileOperation.openCatalog(file); //这里应该是创建新的一个窗口
                    }
                }
            }
        });

    }
    //增加目录节点
    public static void addTreeChild(FileStructure file)
    {
        Image x=new Image("sample/file.png",30,30,false,false);
        Image y=new Image("sample/folder.png",30,30,false,false);
        TreeItem<String>fatherTreeItem=(TreeItem)FileAndPane.FiletoTreeItem.get(file.getFatherCatalog());
        String fileName= file.getName();
        TreeItem<String> newTreeItem=null;
        switch (file.getAttribute()){
            case 0001:
            case 0010:
            case 0100: newTreeItem = new TreeItem(fileName,new ImageView(x)); break;
            case 1000:newTreeItem =new TreeItem(fileName,new ImageView(y)); break;
        }
        FileAndPane.TreeItemtoFile.put(newTreeItem,file);
        FileAndPane.FiletoTreeItem.put(file,newTreeItem);
        newTreeItem.setExpanded(true);
        fatherTreeItem.getChildren().add(newTreeItem);
    }
    public static void deleteTreeChild(FileStructure file) {
        TreeItem<String> treeItem = (TreeItem)FileAndPane.FiletoTreeItem.get(file);
        treeItem.getParent().getChildren().remove(treeItem);
        FileAndPane.TreeItemtoFile.remove(treeItem);
        FileAndPane.FiletoTreeItem.remove(file);
        TreeItemView.treeView.refresh();
    }
}
