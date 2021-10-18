package file;

import javafx.scene.Node;

public class file{
    private String filename;    //文件名称
    private String filepath;    //绝对路径
    private int way;            //打开方式
    private int exist;          //是否存在
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
    public void create_content(){       //建立文件目录

    }
    public void delete_content(){       //删除文件目录

    }
    public content getParent(){         //寻找父目录

        return null;
    }
    public int findSamename(){          //寻找同名文件，返回1代表有同名，返回0代表没有同名

    }
    public void openAction(){       //打开文件

    }
    public int scanfile(){          //已打开文件表的检查,0为文件表有此文件，1为文件表无此文件

    }
    public void alopfile(){         //填写已打开文件表

    }
//    @Override
//    public Node getStyleableNode() {
//        return super.getStyleableNode();
//    }
}
