package file;

public class typeFile {
    public typeFile(String filename){
        file tpfile=new file();
        if(tpfile.getExist()==0)
        {
            System.out.println("失败");
        }
        else
        {
            if(tpfile.scanfile()==1)
            {
                System.out.println("打开失败");
            }
            else
            {
//                从目录中取出文件的起始盘块号，一块一块显示文件内容

            }
        }
    }
}
