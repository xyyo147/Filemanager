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
        /*char[] x= (char[]) DiskBlock.getDiskBlock().get(3);
        if (x!=null)
        System.out.println(String.valueOf(x));



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

 */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
