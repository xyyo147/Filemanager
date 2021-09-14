package sample;
//创建磁盘块
public  class DiskBlock {
    static int[][]diskBlock=  new int[128][64];

    static void initialization(){
        int i,j;
        for(i=0;i<2;i++)
        {
            for(j=0;j<64;j++)
            {
                diskBlock[i][j]=-1;
            }
        }
    }//初始化磁盘块


}
