package file;
import java.util.*;

public class FileStructure {
    private String name;
    /*文件或目录名*/
    private int attribute;
    /*0001为只读文件，0010为系统文件，0100为普通文件，1000为目录登记项*/
    private int begin,length;
    /*文件的起始盘块号和文件长度*/
    private FileStructure fatherCatalog;
    /*记录文件的父目录*/
    private final int COMFILE=0100,COMCATALOG=1000;
    /*用于判断是文件还是目录*/
    private final int MAX_FILE_NUMBER = 8;
    /*每个磁盘块最多可放8哥文件目录或子目录项*/
    private List<FileStructure> fileCatalog = new ArrayList<FileStructure>();
    /*模拟该文件目录所在磁盘空间，该磁盘块最多可存8哥目录项*/
    private static int fileCatalog_number = 0;
    /*数组的长度（该文件目录下有多少哥子文件或子目录）*/



    public FileStructure(FileStructure fatherCatalog,String filename,int begin,int attribute){
        name=filename;
        this.fatherCatalog = fatherCatalog;
        this.begin=begin;
        length=64;
        if(attribute==COMFILE){
            this.attribute = 0100;
        }
        else if(attribute==1000){
            this.attribute = 1000;
        }
        else {
            this.attribute = 0001;
        }
    }
    /*创建一个新的文件或目录*/


    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    /*获取和修改文件/目录名*/


    public void setAttribute(int attribute){
        this.attribute = attribute;
    }
    public int getAttribute(){
        return this.attribute;
    }
    /*获取或修改文件/目录属性*/


    public void setBegin(int begin){
        this.begin = begin;
    }
    public int getBegin(){
        return this.begin;
    }
    /*获取或修改文件/目录起始盘块号*/


    public void setLength(int length){
        this.length = length;
    }
    public int getLength(){
        return this.length;
    }
    /*获取或修改文件/目录长度*/

    public FileStructure getFatherCatalog(){
        return this.fatherCatalog;
    }
    /*获取父目录*/

    public List getFlieCatalog(){
        return this.fileCatalog;
    }
    /*获取子文件和子目录数组*/

    public boolean setFileCatalog(FileStructure a){
        if(fileCatalog_number<MAX_FILE_NUMBER) {
            fileCatalog.add(a);
            fileCatalog_number++;
            return true;
        }
        return false;
    }
    /*将子文件或子目录加到该数组*/


    public void deleteFileCatalog(FileStructure a){
        fileCatalog.remove(a);
    }
    /*将子文件或子目录从该数组中删除*/
    public static int getfileCatalog_number(){
        return fileCatalog_number;
    }

}
