package sample;

import java.util.ArrayList;
import java.util.List;

//创建磁盘块
public  class DiskBlock {
    private static List<Object> diskBlock = new ArrayList();
    static void initialization(){
        for(int i=0;i<128;i++)
            diskBlock.add(0);
    }//初始化磁盘块

    public static List<Object> getDiskBlock() {
        return diskBlock;
    }

    public static void setDiskBlock(List<Object> diskBlock) {
        DiskBlock.diskBlock = diskBlock;
    }
/*
    public static void searchEmptyDiskBlock(){

        Boolean isntEmpty=true;

        for (int i=0;i<2;i++)
        {
            for(int j=0;j<64;j++)
            {
                if(FAT.fat[i][j]=='0')
                {
                    isntEmpty=false;
                    FAT.setFAT(i,j, ' ');
                    switch (i)
                    {
                        case 0:
                            cat[4]= String.valueOf(i).charAt(0);
                            break;
                        case 1:
                            int xiabiao=j+64;
                            cat[4]= String.valueOf(xiabiao).charAt(0);
                            break;
                    }
                }
            }
        }
        if(isntEmpty)
            System.out.println("磁盘已满");
    }

 */
}
