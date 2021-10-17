package file;

public class writeFile {
    public writeFile(String filename,int buffer[],int wr_length){
        file wrfile=new file();
        if(wrfile.scanfile()==0)
        {
            wrfile.alopfile();
        }
        else
        {
            if(wrfile.getWay()==0)
            {
                System.out.println("创建失败");
            }
            else
            {
//                从已打开文件表读出写指针，从这个位置上写入缓冲中的数据
            }
        }

    }
}
