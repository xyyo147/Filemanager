package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DiskBlock.initialization();
        int a[][]= FAT.getFAT();//初始化，必须要有
        /*以下是测试代码
        int lastnum;
        lastnum= DiskBlock.searchEmptyDiskBlock(true,254);
        DiskBlock.write(lastnum,"hello");
        DiskBlock.deleteFile(lastnum);
        for(int i=0;i<2;i++)
        {
            for (int j=0;j<64;j++)
                System.out.println(a[i][j]);
        }

        System.out.println(lastnum);
        System.out.println(DiskBlock.read(lastnum));
         */

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
