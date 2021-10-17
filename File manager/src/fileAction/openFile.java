package file;

public class openFile {
    openFile(){}
    openFile(String filename,int way)//文件名，操作类型
    {
            file opfile=new file();
            if(opfile.getExist()==0)                       //如果文件不存在应该怎么表示
            {
                System.out.println("创建失败");    //如果文件不存在打开失败
            }
            else
            {
                if(opfile.getWay()==0)            //文件形式为只读
                {
                    if(way==1)                    //只读文件，操作类型为写，打开失败
                    {
                        System.out.println("创建失败");
                    }
                    else
                    {
                        if(opfile.scanfile()==0)            //查看已打开文件表，如果文件表内没有该文件
                            opfile.alopfile();          //填写已打开文件表
                        opfile.openAction();       //只读文件，操作类型为读，打开文件
                    }
                }
                else
                {
                    if (opfile.scanfile() == 0)            //查看已打开文件表，如果文件表内没有该文件
                        opfile.alopfile();          //填写已打开文件表
                    opfile.openAction();            //读写文件，打开文件
                }
            }

    }

}
