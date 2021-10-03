package sample;

import java.util.ArrayList;
import java.util.List;

//创建磁盘块
public  class DiskBlock {
    static List<Object> diskBlock = new ArrayList();
    static void initialization(){
        int[] a=new int[64];
        for(int i=2;i<128;i++)
            diskBlock.add(i,0);
        a[0]=a[1]=-1;
        diskBlock.add(0,a);
        a[0]=a[1]=0;
        diskBlock.add(1,a);
        //初始化fat
    }//初始化磁盘块
    public static void RefreshFat(int i,int j,int num){
        int[] a;
        a= (int[]) diskBlock.get(i);
        a[j]=num;
        diskBlock.add(i,a);
    }
    public  static List<Object> getDiskBlock() {
        return diskBlock;
    }

    public static void setDiskBlock(List<Object> diskBlock) {
        DiskBlock.diskBlock = diskBlock;
    }

    public static int searchEmptyDiskBlock(Boolean finish,int lastnum){
        FAT.getFAT();
        Boolean isntEmpty=true;
        if(!finish&&lastnum==254)//写入未完成时并是第一次开始检索时，默认lastum为254
        {
            for (int i=0;i<2;i++)
            {
                for(int j=0;j<64;j++)
                {
                    if(FAT.fat[i][j]==0)
                        lastnum=i*64+j;
                    isntEmpty=false;
                }
            }

        }
        else
        for (int i=0;i<2;i++)
        {
            for(int j=0;j<64;j++)
            {
                if(!finish)//写入未完成时,且有上任磁盘号
                {
                    if(FAT.fat[i][j]==0)
                    {
                        RefreshFat(lastnum/64,lastnum%64,i*64+j);
                        lastnum=i*64+j;
                        RefreshFat(i,j,255);
                        isntEmpty=false;
                    }
                }
                else
                {
                    if(FAT.fat[i][j]==0) {
                        RefreshFat(lastnum / 64, lastnum % 64, i * 64 + j);
                        lastnum=i*64+j;
                        RefreshFat(i, j, -1);
                        isntEmpty=false;
                    }
                }
            }
        }
        if(isntEmpty)
            System.out.println("磁盘已满");
        FAT.getFAT();
        return lastnum;
    }


}
