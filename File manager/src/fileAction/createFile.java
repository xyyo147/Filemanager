package file;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.*;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//createFile建立当前指定的文件夹的文件表
//way 是读写方式，0为只读，1为读写
public class createFile{

    public createFile(int way,String filepath,String filename) {
        Node cre_file=new file(way,filepath,filename);
        if(way==0){             //如果文件属性是只读，建立失败
            System.out.println("创建失败");
        }
        else{
                                //根据文件绝对路径名查找
            if(cre_file.getParent()==null)//父目录不存在，创建失败
            {
                System.out.println("创建失败");
            }
            else
            {                                                       //有重名文件，创建文件失败
                if(cre_file.getParent().findSamename()！=null)        //这里需要写与它同一目录的文件，是否有重名
                {//如果有重名
                    System.out.println("创建失败");
                }
                else
                {
                    cre_file.create_content();//将信息排除过后，建立文件目录
                }
            }

        }

    }
}
