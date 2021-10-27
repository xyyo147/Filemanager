package sample;
import java.io.File;
import java.util.*;

public class FileOperation {
    public static int begin;
    public static final int FILE=0100, CATALOG=1000;

    public static FileStructure createFile(FileStructure currentCatalog,String name){
        //currentCatalog为当前目录，name为文件名
            if(FileConstraint.createFile(currentCatalog,name,FILE)){
                begin = DiskBlock.ifFull();
                /*得到初始磁盘块号，-1说明内存满了，创建失败*/
            }
            else{
                throw new IllegalArgumentException("创建失败");
            }
            if(begin!=-1){
                FileStructure file = new FileStructure(currentCatalog,name,begin,FILE);
                currentCatalog.setFileCatalog(file);

                return file;
            }
            else{
                throw new IllegalArgumentException("创建失败");
            }
    }
    /*创建一个文件*/


    public static String readFile(FileStructure file){
        if(!FileConstraint.havedOpen(file)){
            file.getFatherCatalog().openSignal();
            FileConstraint.enterTable(file);

            String string = DiskBlock.readFile(file.getBegin());
            return string;
        }
        else{
            throw new IllegalArgumentException("文件已经打开了,不能重复打开哦亲");
        }
    }
    /*打开一个文件*/


    public static void writeFile(FileStructure file,String string){
        int need_number;
        if(string.length()%64==0){
            need_number = string.length();
        }
        else{
            need_number = string.length()+1;
        }
        if(DiskBlock.ifEnough(need_number)){
            DiskBlock.writeStringFile(string,file.getBegin());
        }
        else{
            throw new IllegalArgumentException("磁盘已满");
        }
    }
    /*写文件*/


    public static void closeFile(FileStructure file){
        if(FileConstraint.havedOpen(file)){
            file.getFatherCatalog().closeSignal();
            FileConstraint.outTable(file);
        }
        else{
            throw new IllegalArgumentException("文件已经关闭，不能重复关闭哦亲");
        }
    }
    /*关闭一个文件*/

    public static void clearFile(FileStructure file){
        if(!FileConstraint.havedOpen(file)){
            file.getFatherCatalog().deleteFileCatalog(file);
            DiskBlock.deleteFile(file.getBegin());
        }
        else{
            throw new IllegalArgumentException("请先将该文件关闭再删除");
        }
    }
    /*删除一个文件*/


    public static void changeAttribute(FileStructure file,int temp){
        file.setAttribute(temp);
    }
    /*改变一个文件属性*/


    //-----------------------------------------------------------------------------

    public static FileStructure createCatalog(FileStructure currentCatalog,String catalogName){
        //currentCatalog为当前目录，catalogName为目录名
        if(FileConstraint.createFile(currentCatalog,catalogName,CATALOG)){
            begin = DiskBlock.ifFull();
            /*得到初始磁盘块号，-1说明内存满了，创建失败*/
        }
        else{
            throw new IllegalArgumentException("创建失败");
        }

        if(begin!=-1){
            FileStructure catalog = new FileStructure(currentCatalog,catalogName,begin,CATALOG);
            currentCatalog.setFileCatalog(catalog);
            return catalog;
        }
        else{
            throw new IllegalArgumentException("创建失败");
        }
    }
    /*创建一个目录*/

    public static List openCatalog(FileStructure currentCatalog){
        if(!FileConstraint.havedOpen(currentCatalog)){
            currentCatalog.openSignal();
            return currentCatalog.getFlieCatalog();
        }
        else{
            throw new IllegalArgumentException("目录已打开");
        }
    }
    //打开一个目录

    public static void closeCatalog(FileStructure currentCatalog){
        if(FileConstraint.havedOpen(currentCatalog)){
            currentCatalog.closeSignal();
        }
        else{
            throw new IllegalArgumentException("目录已关闭");
        }
    }
    //关闭一个目录

    public static boolean clearCatalog(FileStructure currentCatalog){
        FileStructure temp;
        if(currentCatalog.getSignal()==0){
            currentCatalog.getFatherCatalog().deleteFileCatalog(currentCatalog);

            for(int i=currentCatalog.getFlieCatalog().size()-1;i>=0;i--){
                temp = (FileStructure) currentCatalog.getFlieCatalog().get(i);
                if(temp.getAttribute()==CATALOG){
                    clearCatalog(temp);
                }
                else
                {
                    FileOperation.clearFile(temp);
                }
            }
            FileOperation.clearFile(currentCatalog);
            return true;
        }
        else{
            throw new IllegalArgumentException("文件正在使用,删除失败");
        }

    }
    //删除一个目录

}

