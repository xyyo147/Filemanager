package file;

public class changeFile {
    public changeFile(String filename)
    {
        file cgfile=new file();
        if(cgfile.getExist()==0)
        {
            System.out.println("失败");
        }
        else
        {
            if(cgfile.scanfile()==1)
            {
                System.out.println("失败");

            }
            else
            {
//                未打开，根据要求改变目录项中的属性值
            }
        }
    }
}
