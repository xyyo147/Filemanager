package fileAction;

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
        if(true){//在磁盘中查询是否有这个文件名的文件，应该在diskblock中实现，肖鹏没写
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









}

   /* public static FileStructure file;     //当前文件
    private static final int MAX_OPFILE_NUMBER=5;
    private static int op_length=0;          //已打开文件登记表中登记的文件数量
    public static FileStructure OpTable;        //已打开文件表
    public static List<FileStructure> OpTable_check = new ArrayList<FileStructure>(5);  //已打开文件表的list

    public static boolean setOpenTable(FileStructure a){           //添加已打开文件表结点
        if(op_length<MAX_OPFILE_NUMBER){                //不超过最大文件个数
            OpTable_check.add(a);
            op_length++;
            return true;
        }
        return false;
    }
    public static boolean TableDetect(FileStructure file)                 //检测该文件是否在已打开文件表内
    {
        for(FileStructure op:OpTable_check){
            if(Objects.equals(op.getName(), file.getName())){             //如果已打开文件表内有该文件
                return true;
            }
        }
        return false;                                                   //已打开文件表内没有该文件
    }

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
                    return !Objects.equals(fileStructure.getName(), filename); //如果已有的文件有当前的名字，即重名
                }
            }

        }
        return true;
    }

    public static boolean openFile(String filename,int way)//文件名，操作类型
    {
        if(Objects.equals(file.getName(), filename))                       //如果文件不存在
        {
            return false;    //如果文件不存在打开失败
        }
        else                     //如果文件存在
        {
            if(file.getAttribute()==0001)            //文件形式为只读
            {
                if(way==1)                    //只读文件，操作类型为写，打开失败
                {
                    return false;
                }
                else                            //只读文件，操作类型为读
                {
                    //如果文件已打开,则打开失败
                    return !TableDetect(file);
                }
            }
            else               //文件为普通文件
            {
                if(!TableDetect(file)){                  //文件未打开，需要填表
                    return false;
                }
                return true;
            }
        }

    }

    public static boolean readFile(String filename,int rd_length,int way)           //文件名、文件读取长度、操作类型
    {

        if(!TableDetect(file))          //如果已打开文件表不存在该文件
        {
            return false;
        }
        else
        {//打开方式为写入
            return way != 1;
        }
    }
    public static boolean writeFile(String filename,int buffer[],int wr_length,int way){
        if(!TableDetect(file))          //如果已打开文件表有该文件
        {
            return false;
        }
        else
        {
            return way != 0;    //如果打开方式为读way=0，则false，如果打开方式为写way=1，则true
        }
    }
    public static boolean closeFile(String filename,int way)      //way=0为读，way=1为写
    {
        if(!TableDetect(file))       //已打开文件表不存在
            return false;
        else
        {
            if(way==0)
            {
                return false;
            }
            else return true;

        }
    }
    public static boolean deleteFile(String filename){
        List<FileStructure> fileComp=file.getFlieCatalog();
        for(FileStructure fileStructure:fileComp){              //当文件目录属于文件目录队列（表示种类是文件时）
            if(Objects.equals(fileStructure.getName(), filename)){          //文件存在
                if(TableDetect(file)) return false;                 //如果文件打开，不可删除
            }
        }
        return true;
    }

    /*
    public static boolean typeFile(String filename){
        List<FileStructure> fileComp=(file.getFatherCatalog()).getFlieCatalog();
        for(FileStructure fileStructure:fileComp) {              //当文件目录属于文件目录队列（表示种类是文件时）
            if(Objects.equals(fileStructure.getName(), filename)) {          //文件存在

            }
        }
        if(tpfile.scanfile()==1)
        {
            System.out.println("打开失败");
        }
        else
        {
//                从目录中取出文件的起始盘块号，一块一块显示文件内容
        }

    }

    public static boolean closeFile(String filename,int way)      //way=0为读，way=1为写
    {
        if(!TableDetect(file)||(way==0))       //已打开文件表不存在
            return false;
        return true;
    }
    public static boolean deleteFile(FileStructure file,String filename){
      if((!notReprtTrue(file,filename,1))||(!TableDetect(file)))   //这里的P是什么意思
            return false;
    }
    public static boolean IsfilestructureExist(FileStructure file)     //该文件的文件目录项是否存在
    {
        if(file.getName()==null)                                       //通过传入的file文件能否得到名字得知目录项是否存在
            return false;
        return true;
    }
    public static boolean typeFile(FileStructure file,String filename){
        if((!IsfilestructureExist(file))||TableDetect(file)){ return false;}
        return true;

    }
    public static boolean change(String filename){
        if((!IsfilestructureExist(file))||TableDetect(file)){ return false;}
        return true;
    }
}

    */
