package Action;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class EmptyMenu extends ContextMenu{
    private static EmptyMenu INSTANCE = null;

    /** * 私有构造函数 */
    public EmptyMenu() {
        MenuItem createFileMenuItem = new MenuItem("新建文件");
        MenuItem catalogMenuItem = new MenuItem("新建文件夹");
        getItems().add(createFileMenuItem);
        getItems().add(catalogMenuItem);
//        getItems().add(attributeMenuItem);
        Clickcreate(createFileMenuItem);
        Clickcatalog(catalogMenuItem);
//        Clickattribute(attributeMenuItem);
    }

    /** * 获取实例 * @return GlobalMenu */
    public static EmptyMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmptyMenu();
        }
        return INSTANCE;
    }
    public static void Clickcreate(MenuItem o){
        o.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                     //进入打开文件方法
                Pane pane=new Pane();
                Stage stage=new Stage();
                stage.setScene(new Scene(pane,200,300));
                stage.show();
            }
        });
    }
    public static void Clickcatalog(MenuItem o){
        o.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                                //进入新建文件夹操作
                Pane pane=new Pane();
                Stage stage=new Stage();
                stage.setScene(new Scene(pane,200,300));
                stage.show();
            }
        });
    }

}
