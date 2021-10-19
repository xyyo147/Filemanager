package file;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class FileConstraint {
    public static FileStructure file;     //这里应该怎么构造
    public static OpCheck opcheck;
    public static boolean createFile(String filename,int attribute){      //文件名，文件属性
        if(attribute==0001){             //如果文件属性是只读，建立失败
            return false;
        }
        else{
            //根据文件绝对路径名查找
            if(file.getFatherCatalog()==null)//父目录不存在，创建失败
            {
                return false;
            }
            else
            {                                                         //有重名文件，创建文件失败
                List<FileStructure> fileComp=(file.getFatherCatalog()).getFlieCatalog();
                for(FileStructure fileStructure:fileComp){              //当文件目录属于文件目录队列（表示种类是文件时）
                    if(Objects.equals(fileStructure.getName(), filename))       //如果已有的文件有当前的名字，即重名
                        return false;
                    else
                        return true;
                }
            }

        }
        return true;
    }


    public static boolean openFile(String filename,int way)//文件名，操作类型
    {
        if(Objects.equals(file.getName(), filename))                       //如果文件不存在应该怎么表示
        {
           return false;    //如果文件不存在打开失败
        }
        else
        {
            if(file.getAttribute()==0001)            //文件形式为只读
            {
                if(way==1)                    //只读文件，操作类型为写，打开失败
                {
                    return false;
                }
                else                            //只读文件，操作类型为读
                {
                    List<OpenTable> openTableList=opcheck.getOpenTables();
                    for(OpenTable op:openTableList){
                        if(Objects.equals(file.getName(), op.getFilename())){           //该文件在已打开文件表内
                            return false;                               //则打开失败？
                        }
                    }
                    return true;                        //填写已打开文件表
//                    if(file.getName()==)            //查看已打开文件表，如果文件表内没有该文件
//                        file.alopfile();          //填写已打开文件表
//                    file.openAction();
//                         return true;
//                    else
//                        return false;
                }
            }
            else
            {
                List<OpenTable> openTableList=opcheck.getOpenTables();      // 得到已打开文件表的list
                for(OpenTable op:openTableList){                        //已打开文件表list中遍历
                    if(Objects.equals(file.getName(), op.getFilename())){           //该文件在已打开文件表内
                        return false;                               //则打开失败？
                    }
                }
                return true;                        //填写已打开文件表
//                if (file.scanfile() == 0)            //查看已打开文件表，如果文件表内没有该文件,还没打开文件
//                    return true;
////                    file.alopfile();          //填写已打开文件表
////                file.openAction();            //读写文件，打开文件
//                else
//                    return false;
            }
        }

    }

    public static boolean readFile(String filename,int rd_length,int way)           //文件名、文件读取长度、操作类型
    {

        if(file.scanfile()==0)          //如果已打开文件表不存在
        {
//            file.alopfile();          //则填写已打开文件表
        }
        else
        {
            if(way==1)              //打开方式为写入
            {
                return false;
            }
            else
            {
                return true;

//                从已打开文件表读出读指针，从这个位置上读出所需要的长度，若所需长度没有读完已经遇到文件结束符，终止操作
//                用#来表示文件结束

            }
        }
            return true;
    }
    public static boolean writeFile(String filename,int buffer[],int wr_length,int way){
        if(file.scanfile()==0)          //如果已打开文件表没有该文件
        {
//            file.alopfile();                //
        }
        else
        {
            if(way==0)                      //以读方式打开文件
            {
               return false;
            }
            else
            {

//                从已打开文件表读出写指针，从这个位置上写入缓冲中的数据
            }
        }
        return true;
    }
}
