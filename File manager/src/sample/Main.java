package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DiskBlock.initialization();
        int a[][]= FAT.getFAT();//初始化，必须要有


/*
        FAT.fat[0][3]=2;
        DiskBlock.deleteFile(3);

 */
        /*
        for(int k=0;k<2;k++)
        {
            for (int j=0;j<64;j++)
                System.out.println(a[k][j]);
        }


         */
        String array="撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦撒旦          撒旦撒旦撒旦撒旦";
        int lastnum=DiskBlock.writeStringFile(array,3);
        System.out.println("输出盘块"+lastnum);
       String str= DiskBlock.readFile(3);
        System.out.println(str);
        DiskBlock.deleteFile(3);
        str= DiskBlock.readFile(3);System.out.println(str);
        /*           序列化测试部分           */
//        FileStructure rootfile=new FileStructure(null,"BEN",1,1000);
//        rootfile.setFileCatalog(new FileStructure(rootfile, "ben1", 2, 0001));
//        rootfile.setFileCatalog(new FileStructure(rootfile, "ben2", 3, 0100));
//        Serializationmode.Serialization(rootfile);
//        System.out.println("序列化成功！");
//        File savefile=new File("D:\\学习文件\\大三上学期\\操作系统实验\\课设\\BEN\\save.dat");
//        Serializationmode.Deserialization(savefile);
//        System.out.println("反序列化成功！");
        /*           序列化测试部分           */
        /*char[] x= (char[]) DiskBlock.getDiskBlock().get(3);
        if (x!=null)
        System.out.println(String.valueOf(x));


         */
        FileStructure rootfile=new FileStructure(null,"BEN",1,1000);
            TreeItemView.setTreeRoot(rootfile);
        AnchorPane anchor_pane=new AnchorPane();
        anchor_pane.getChildren().add(TreeItemView.treeView);
        TreeItemView.operationTree();
        TreeItemView.addTreeChild(new FileStructure(rootfile,"ABC",2,1000));
                Scene scene=new Scene(anchor_pane,860,610);
                primaryStage.setScene(scene);
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("OS课设");
//        primaryStage.setScene(new Scene(root, 860, 610));
                primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
