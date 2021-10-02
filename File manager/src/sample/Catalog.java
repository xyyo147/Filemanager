package sample;
//目录
public class Catalog {
    char[] cat=new char[8];
    Catalog(){
        cat[0]='新';
        cat[3]='1';
        cat[7]='1';
        DiskBlock.searchEmptyDiskBlock();
        occupyEmptyDiskBlock(2);
    }//无参构造方法
    Catalog(char name){
        if(name=='$'||name=='.'||name=='/')
            System.out.println("目录非法构建");
        else {
            cat[0]=name;
            cat[3]='1';
            cat[7]='1';
            DiskBlock.searchEmptyDiskBlock();
            occupyEmptyDiskBlock(2);
        }
    }//有参构造方法（提供名字


    void occupyEmptyDiskBlock(int number){
        for(int i=7;i<64;i=i+8){
            if(DiskBlock.diskBlock[number][i]!=1)
            {
                for(int j=0;j<8;j++)
                {
                    DiskBlock.diskBlock[number][j]=cat[j];
                }
            }
        }
    }//将目录登记项写入磁盘，参数为写入的盘块号



}
