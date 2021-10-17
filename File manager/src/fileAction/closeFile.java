package file;

public class closeFile {
    public closeFile(String filename)
    {
        file clfile=new file();
        if(clfile.scanfile()==0)
        {
            System.out.println("关闭失败");
        }
        else
        {
            if(clfile.getWay()==1)
            {
//                追加文件结束符，修改目录项，已打开文件表中删除
            }
        }
    }
}
