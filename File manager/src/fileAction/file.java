package file;

import javafx.scene.Node;

public class file extends Node {
    private String filename;    //文件名称
    private String filepath;    //绝对路径
    private int way;            //打开方式
    private int exist;
    file(){}
    file(int a,String b,String c){  //打开方式、绝对路径、文件名称
        way=a;
        filepath=b;
        filename=c;
    }
    public int getWay(){
        return way;
    }
    public int getExist(){
        return exist;
    }
    public void create_content(){       //建立文件目录操作

    }
    public void openAction(){       //打开文件

    }
    public int scanfile(){          //已打开文件表的检查

    }
    public void alopfile(){         //填写已打开文件表

    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
