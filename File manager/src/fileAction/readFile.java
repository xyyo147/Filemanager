package file;

public class readFile {
    public void readFile(String filename,int rd_length)
    {
        file rdfile=new file();
        if(rdfile.scanfile()==0)
        {
            rdfile.alopfile();
        }
        else
        {
            if(rdfile.getWay()==1)
            {
                System.out.println("打开失败");
            }
            else
            {
//                从已打开文件表读出读指针，从这个位置上读出所需要的长度，若所需长度没有读完已经遇到文件结束符，终止操作
//                用#来表示文件结束

            }
        }

    }
}
