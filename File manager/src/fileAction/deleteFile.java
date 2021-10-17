package file;

public class deleteFile {
    public deleteFile(String filename){
        file dlfile=new file();
        if(dlfile.getExist()==0)
        {
            System.out.println("删除失败");
        }
        else
        {
            if(dlfile.scanfile()==1)
            {
                System.out.println("删除失败");
            }
            else
            {
//                删除文件目录项并归还文件所占用的磁盘空间

            }

        }
    }
}
