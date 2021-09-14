package sample;

public class FAT {
    public  int[][] getFAT(){
        int[][]FAT = new int[2][64] ;
        for(int i=0;i<2;i++)
        {
            for( int j=0;j<64;j++)
            {
                FAT[i][j]=DiskBlock.diskBlock[i][j];
            }
        }
        return FAT;
    }//获取FAT
}
