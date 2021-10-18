package file;

public class opencheck {
    private String filepath;
    private int attribute;              //属性为1为读写性质，为0是只读性质
    private int number;                 //文件的起始盘块号
    private int length;                 //文件长度，文件占用的字节数
    private int flag;                   //需要做的文件操作类型
    public int pread;
    public int pwrite;
    public void setAttribute(String path,char att,int sta,int leng,int way){
        filepath=path;
        attribute=att;
        number=sta;
        length=leng;
        flag=way;
    }
    public void setRead(int read){
        pread=read;
    }
    public void setWrite(int write){
        pwrite=write;
    }
}
