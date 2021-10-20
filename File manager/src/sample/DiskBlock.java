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
        RefreshFat(0,2,-1);
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
    private static void RefreshFat(int i,int j,int num){
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
    private static void write(int num,Object theFile) //文件写入磁盘
    {
        if (num!=0&&num!=1&&num<128) {
            diskBlock.set(num, theFile);
        }
        else if(num>=128)
            System.out.println("非法" +num);
    }
    public static String readFile(int number) //读出磁盘内的文件
    {
        StringBuilder str=new StringBuilder();
       char[] ch=new char[100000];
       int i=0;
        if(number>128||number<=0)
            return "错误";
        else
        {
            while (number>2&&number<128){


                    String st= (String) diskBlock.get(number);
                        str.append(st);
                number=FAT.getNextLnum(number);
            }
            return str.toString();
        }

    }
    private static Object read(int number) //读出磁盘内的文件
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
    /*public static void writeIntFile(int[] array,int lastnum){
        int len=array.length;
        int i=0;
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
    */
    public static int ifFull(){
        int lastnum=searchEmptyDiskBlock(true,254);
        return lastnum;
    }//判断磁盘是否有空磁盘块
    public static int writeStringFile(String array,int lastnum){
        int len=array.length();
        int i=0;
        int head=-1;
        char[] first=new char[64];
        if(len<=0)
        {
            return head;
        }
        else if(len>64){
            head=lastnum;
            array.getChars(i,i+64,first,0);
            i+=64;
            len-=64;
            String str;
            str=String.valueOf(first);
            DiskBlock.write(lastnum,str);
        }//写入第一个磁盘储存头磁盘号
        else if(len<=64){
            head=lastnum;
            char[] ob=new char[len];
            array.getChars(i,i+len,ob,0);
            String str;
            str=String.valueOf(ob);
            DiskBlock.write(lastnum,str);
            return head;
        }
        while (len>64){
            char[] ob=new char[64];
            lastnum= DiskBlock.searchEmptyDiskBlock(false,lastnum);
            array.getChars(i,i+64,ob,0);
            i+=64;
            len-=64;
            String str;
            str=String.valueOf(ob);
            DiskBlock.write(lastnum,str);
        }
        if(len>0){
            lastnum= DiskBlock.searchEmptyDiskBlock(true,lastnum);
            char[] ob=new char[len];
            array.getChars(i,i+len,ob,0);
            String str;
            str=String.valueOf(ob);
            DiskBlock.write(lastnum,str);
        }
        return head;
    }//文件写入磁盘块
    public static int  writeCatalog(FileStructure catalog){
        int lastnum=searchEmptyDiskBlock(true,254);
        DiskBlock.write(lastnum,catalog);
        return lastnum;
    }//写文件
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
        if(isntEmpty){
            System.out.println("磁盘已满");
            return -1;
        }

        FAT.getFAT();
        return lastnum;
    }
}
