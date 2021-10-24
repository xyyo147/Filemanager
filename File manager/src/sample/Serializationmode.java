package sample;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializationmode implements Serializable {    //文件内容序列化与反序列化类
        public static long serialVersionUID=1L;      //UID版本号，序列化与反序列化的版本号一样，反序列化才能够执行
    public static List<FileStructure> all_files = new ArrayList<FileStructure>();  //储存全部节点的list
    public static List<FileStructure> new_files = new ArrayList<FileStructure>();  //加载全部节点的list
    public static void getAllfiles(FileStructure catalog){
        all_files.add(catalog);
        List<FileStructure> current_files=new ArrayList<>();
        current_files=catalog.getFlieCatalog();
        for(FileStructure file:current_files){
           if((file.getAttribute() == 0001)||(file.getAttribute() == 0100)||(file.getAttribute()==0010))
            all_files.add(file);    //如果属于文件
            if(file.getAttribute()==1000) {
//                all_files.add(file);
                getAllfiles(file);     //如果属于目录，继续进入

            }
        }
    }//这样写是可以遍历全部的文件，但其中的父子之间的关系弄混乱，应该怎么解决

    public static void clearFilecontent(File s)
    {
        try{
            FileWriter fileWriter=new FileWriter(s);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            System.out.println("原文件内容已清空");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void Serialization(FileStructure rootfile)       //从根目录开始文件序列化
        {
            try {
            File savefile=new File("save.dat");
            clearFilecontent(savefile);
            FileOutputStream fos=new FileOutputStream(savefile,true);//FileOutputStream对象写入文本文件
            ObjectOutputStream oos=new ObjectOutputStream(fos);     //建立对象的序列化流
//                 oos.writeObject(Ffile);                             //将Ffile对象序列化流写入文件save
                getAllfiles(rootfile);
//                List<FileStructure> save_files=all_files;
                for(FileStructure files:all_files) {
                    oos.writeObject(files);
                    System.out.println(files.getName());
                }
//                oos.writeObject(all_files);
//                oos.writeObject(null);
                oos.close();
                fos.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        public static void Deserialization(File savefile)     //反序列化，注意这里是以文件作为参数
        {
            FileStructure Ffile = null;
            try {
                FileInputStream fis = new FileInputStream(savefile);    //FileInputStream对象读取文本文件
                ObjectInputStream ois = new ObjectInputStream(fis); //建立反序列化流对象
//                System.out.println(fis.available());
                while(fis.available()>0)
                {
                    Ffile = (FileStructure) ois.readObject();     //将读取到的对象序列赋给Ffile对象
                    if(Ffile!=null) {
                        new_files.add(Ffile);
                        System.out.println(Ffile.getName());
                    }
                }
//                System.out.println(new_files);
//                System.out.println("反序列化成功！");


                ois.close();
                fis.close();
            }catch (ClassNotFoundException | IOException ex){
                ex.printStackTrace();
            }

        }
}
