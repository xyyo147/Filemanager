package sample;

import fileAction.FileStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//创建磁盘块
public  class DiskBlock {
    static List<Object> diskBlock = new ArrayList();
    static void initialization(){
        int[] a=new int[64];
        for(int i=0;i<128;i++)
            diskBlock.add(i,null);
        RefreshFat(0,0,-1);
        RefreshFat(0,1,-1);
        RefreshFat(1,0,0);
        FileStructure fileStructure=new FileStructure(null,"根目录",2,1000);
        write(2,fileStructure);
        //初始化fat
    }//初始化磁盘块
    public static Boolean ifExist(FileStructure fileStructure, String name){
                  List<FileStructure> catalog=  fileStructure.getFlieCatalog();
        for(int i=0;i<8;i++){
            FileStructure fs;
            fs=catalog.get(i);
            if(fs.getName()==name)
                return true;
        }
        return false;
    }

    public static void RefreshFat(int i,int j,int num){
        int[] a=new int[64];
        if(diskBlock.get(i)!=null)
        a= (int[]) diskBlock.get(i);//取出fat的前半段或后半段
        a[j]=num;//更新
        diskBlock.set(i,a);//放回
        FAT.getFAT();
    }//更新fat
    public  static List<Object> getDiskBlock() {
        return diskBlock;
    }

    public static void write(int num,Object theFile) //文件写入磁盘
    {
        if (num!=0&&num!=1&&num<128) {
            diskBlock.set(num, theFile);
        }
        else if(num>=128)
            System.out.println("非法" +num);
    }
    public static String readFile(int number) //读出磁盘内的文件
    {
        StringBuilder str=null;
        if(number==-1)
            return "错误";
        else
        {
            while (number!=-1){
                str.append(read(number)) ;
                number=FAT.getNextLnum(number);
            }
        }
        return str.toString();
    }
    public static Object read(int number) //读出磁盘内的文件
    {
        if(number!=-1&&number>1&&number<128)
        return diskBlock.get(number);
        else return "错误";
    }
    public static void deleteFile(int number) //删除磁盘文件
    {
        int lastnum=number;
        if (number!=0&&number!=1){
            if(FAT.search(lastnum)==-1){
                diskBlock.set(number, null);
                RefreshFat(number/64,number%64,0);
            }
            else {
                deleteFile(FAT.search(lastnum));
                diskBlock.set(number,null);
                RefreshFat(number/64,number%64,0);
            }
        }
        //保护fat
    }
    public static void writeIntFile(int[] array){
        int len=array.length;
        int i=0;
        int lastnum=254;
        while (len>64){
            int[] ob=new int[64];
            lastnum= DiskBlock.searchEmptyDiskBlock(false,lastnum);
            System.arraycopy(array, i, ob, 0, 64);
            i+=64;
            len-=64;
            DiskBlock.write(lastnum,ob);
        }
        lastnum= DiskBlock.searchEmptyDiskBlock(true,lastnum);
        int[] ob=new int[len];
        if (len >= 0) System.arraycopy(array, i, ob, 0, len);
        DiskBlock.write(lastnum,ob);
    }
    public static void writeStringFile(String array){
        int len=array.length();
        int i=0;
        int lastnum=254;
        while (len>64){
            char[] ob=new char[64];
            lastnum= DiskBlock.searchEmptyDiskBlock(false,lastnum);
            array.getChars(i,i+64,ob,0);
            i+=64;
            len-=64;
            DiskBlock.write(lastnum,ob);
        }
        lastnum= DiskBlock.searchEmptyDiskBlock(true,lastnum);
        char[] ob=new char[len];
        array.getChars(i,i+len,ob,0);
        DiskBlock.write(lastnum,ob);
    }

    public static int searchEmptyDiskBlock(Boolean finish,int lastnum){
        FAT.getFAT();
        Boolean isntEmpty=true;
        if(!finish&&lastnum==254)//写入未完成时并是第一次开始检索时，默认lastum为254
        {
            try
            {
            for (int i=0;i<2;i++)
                 {
                    for(int j=0;j<64;j++)
                    {
                    if(FAT.fat[i][j]==0)
                    {
                        lastnum=i*64+j;
                        RefreshFat(i,j,255);//255表示尚未完成储存
                        isntEmpty=false;//找到空磁盘并返回空磁盘序号
                        throw new Exception("跳出循环");
                    }
                    }
                }
            }
            catch(Exception e){}
        }
        else if(finish&&lastnum==254){//写入完成时并是第一次开始检索时，默认lastum为254
            try {
                for (int i=0;i<2;i++)
                {
                    for(int j=0;j<64;j++)
                    {
                        if(FAT.fat[i][j]==0){

                            lastnum=i*64+j;
                            RefreshFat(i,j,-1);//-1表示已未完成储存
                            isntEmpty=false;//找到空磁盘并返回空磁盘序号
                            throw new Exception("跳出循环");
                        }
                    }
                }
            }
            catch(Exception e){}
        }
        else
            try {
                for (int i=0;i<2;i++)
                {
                    for(int j=0;j<64;j++)
                    {
                        if(!finish)//写入未完成时,且有上任磁盘号
                        {
                            if(FAT.fat[i][j]==0)
                            {
                                RefreshFat(lastnum/64,lastnum%64,i*64+j);//将上任磁盘块的对应fat位置改为当前磁盘块的序号
                                lastnum=i*64+j;
                                RefreshFat(i,j,255);
                                isntEmpty=false;
                                throw new  Exception("跳出循环");
                            }
                        }
                        else//写入已完成时,且有上任磁盘号
                        {
                            if(FAT.fat[i][j]==0) {
                                RefreshFat(lastnum / 64, lastnum % 64, i * 64 + j);//同上
                                lastnum=i*64+j;
                                RefreshFat(i, j, -1);
                                isntEmpty=false;
                                throw new  Exception("跳出循环");
                            }
                        }
                    }
            }
        }catch (Exception e){}
        if(isntEmpty)
            System.out.println("磁盘已满");
        FAT.getFAT();
        return lastnum;
    }


}
