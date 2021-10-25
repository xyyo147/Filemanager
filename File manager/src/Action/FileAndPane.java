package Action;

import javafx.scene.control.TreeItem;
import sample.FileStructure;

import java.util.HashMap;

public class FileAndPane {
    public static HashMap<TreeItem, FileStructure>TreeItemtoFile=new HashMap<>();
    public static HashMap<FileStructure,TreeItem>FiletoTreeItem=new HashMap<>();
}
