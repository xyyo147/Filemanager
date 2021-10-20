package sample;
import java.io.*;

public class Serialization implements Serializable {    //文件内容序列化与反序列化类
        final static long serialVersionUID=1L;      //UID版本号，序列化与反序列化的版本号一样，反序列化才能够执行
        public static void serialization(FileStructure Ffile)       //文件序列化
        {
            try {
            File file=new File("save.dat");
            FileOutputStream fos=new FileOutputStream(file);//FileOutputStream对象写入文本文件
            ObjectOutputStream oos=new ObjectOutputStream(fos);     //建立对象的序列化流
            oos.writeObject(Ffile);                             //将Ffile对象序列化流写入文件save
            oos.close();
            fos.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        public static FileStructure Not_serialization(File file)     //反序列化，注意这里是以文件作为参数
        {
            FileStructure Ffile = null;
            try {
                FileInputStream fis = new FileInputStream(file);    //FileInputStream对象读取文本文件
                ObjectInputStream ois = new ObjectInputStream(fis); //建立反序列化流对象
                Ffile = (FileStructure) ois.readObject();     //将读取到的对象序列赋给Ffile对象
                ois.close();
                fis.close();
                return Ffile;
            }catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }

            return Ffile;
        }
}
