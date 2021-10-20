package sample;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileConstraint {
    private static final int MAX_OPFILE_NUMBER=5;
    private static int op_length=0;          //已打开文件登记表中登记的文件数量
    public static List<FileStructure> OpTable_check = new ArrayList<FileStructure>(5);  //已打开文件表的list
    public static FileStructure rootCatalog;
    //根目录

    public static void setRootCatalog(FileStructure file){
        rootCatalog = file;
    }
    //设立根目录

    public static boolean createFile(FileStructure file,String name,int P) {
        if(charTure(name)&&lengthTure(name)&&notReprtTrue(file,name,P)&&notFull(file)){
            return true;
        }
        else{
            return false;
        }
    }
    /*检测文件名字是否合法，重复，超长或没名字，或者当前目录是否已满,P=1为查询文件,P=2为查询目录*/

    private static boolean charTure(String name){
        if((name.indexOf("$") + name.indexOf(".") + name.indexOf("/")) == -3){
            return true;
        }
        else{
            return false;
        }
    }/*文件名字不含特殊符号*/

    private static boolean lengthTure(String name){
        if(0 < name.length() && name.length() <= 3){
            return true;
        }
        else{
            return false;
        }
    }/*长度争取*/

    private static boolean notReprtTrue(FileStructure file,String name,int P){
        if(DiskBlock.ifExist(file,name,P)){//在磁盘中查询是否有这个文件名的文件，应该在diskblock中实现，肖鹏没写
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean notFull(FileStructure file){
        if(file.getfileCatalog_number()<8){
            return true;
        } else {
            return false;
        }
    }
    /*文件不为空*/

    public static boolean havedOpen(FileStructure file){
        if(OpTable_check.indexOf(file)!=-1){
            return true;
        }
        else{
            return false;
        }
    }
    /*判断是否已经打开*/

    public static void enterTable(FileStructure file){
        OpTable_check.add(file);
    }
    /*将打开的文件加入打开表*/

    public static void outTable(FileStructure file){
        OpTable_check.remove(file);
    }
    /*将关闭的文件从打开表中删除*/

    public static boolean isEmpty(FileStructure file){
        if(file.getLength()<=64){
            return true;
        }
        else{
            return false;
        }
    }
    /*判断这个文件是不是只存在一个磁盘内，如果返回true，说明长度《=64，一个盘块，不然就是多个盘块*/

    public static boolean canWrite(FileStructure file){
        if(file.getAttribute()==0001||file.getAttribute()==0010){
            return false;
        }
        else{
            return true;
        }
    }
    /*判断该文件可不可以写*/

    public static int catalogNumber(String text){
        if(text!=null){
            int x = text.length()/64;
            return x+1;
        }
        else{
            return 0;
        }
    }
    public static boolean IsfilestructureExist(FileStructure file)     //该文件的文件目录项是否存在
    {
        if(file.getName()==null)                                       //通过传入的file文件能否得到名字得知目录项是否存在
            return false;
        return true;
    }

    public static boolean readFile(FileStructure file,String filename,int rd_length,int way)           //文件名、文件读取长度、操作类型
    {
        if(havedOpen(file)&&(way==0))
            return true;
        return false;
    }


    public static boolean writeFile(String filename,int buffer[],int wr_length,int way,FileStructure file){
        if(havedOpen(file)&&canWrite(file)&&way==1)          //如果已打开文件表有该文件
            return true;
        return false;
    }


    public static boolean closeFile(FileStructure file,String filename,int way)      //way=0为读，way=1为写
    {
        if((havedOpen(file))&&(way==1))       //已打开文件表存在该文件，打开方式是写
            return true;
        return false;
    }
    public static boolean deleteFile(FileStructure file,String filename){
        if((!notReprtTrue(file,filename,1)&&(havedOpen(file)))  ) //这里的P是1，查询文件
        return true;
        return false;
    }
    public static boolean typeFile(FileStructure file,String filename){
        if((IsfilestructureExist(file))&&(havedOpen(file))){ return true;}
        return false;

    }
    public static boolean change(FileStructure file,String filename){
        if((IsfilestructureExist(file))&&havedOpen(file)){ return true;}
        return false;
    }
}
