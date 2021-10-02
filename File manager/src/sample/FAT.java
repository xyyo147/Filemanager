package sample;

public class FAT {
    static char[][]fat = new char[2][64] ;
    public static void setFAT(int i,int j,char num) {
        DiskBlock.diskBlock[i][j]=num;
        getFAT();
    }
    public static   void getFAT(){

        for(int i=0;i<2;i++)
        {
            for( int j=0;j<64;j++)
            {
                fat[i][j]=DiskBlock.diskBlock[i][j];
                System.out.println(fat[i][j]);
            }
        }
    }//获取fat或者刷新fat

}
