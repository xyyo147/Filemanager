package file;

public class OpenTable {                    //已打开文件表的结构
    private String filepath;              //文件绝对路径
    private int attribute;              //属性为0100为读写性质，为0001是只读性质
    private int number;                 //文件的起始盘块号
    private int length;                 //文件长度，文件占用的字节数
    private int flag;                   //需要做的文件操作类型
    private String filename;               //文件名称
    public int pread;
    public int pwrite;
    public void setAttribute(String path,char att,int sta,int leng,int way){            //构造函数
        filepath=path;
        attribute=att;
        number=sta;
        length=leng;
        flag=way;
    }
    public String getFilename(){                //得到该已打开文件的文件名
        return this.filename;
    }
    public void setRead(int read){
        pread=read;
    }           //读取指针的起始位置
    public void setWrite(int write){
        pwrite=write;
    }           //写入指针的起始位置
}
