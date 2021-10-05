package sample;

import java.util.ArrayList;
import java.util.List;

public class FAT {
    static int[][]  fat = new int[2][64];
    public static int search(int num){
        int target;
        target=fat[num/64][num%64];
        return target;
    }

    public static   int[][] getFAT(){
        fat[0]= (int[]) DiskBlock.getDiskBlock().get(0);
        fat[1]= (int[]) DiskBlock.getDiskBlock().get(1);
        return fat;
    }//获取fat或者刷新fat


}
