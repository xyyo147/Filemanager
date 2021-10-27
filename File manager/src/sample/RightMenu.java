package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class RightMenu extends ContextMenu{
    private static RightMenu INSTANCE = null;

    /** * 私有构造函数 */
    public RightMenu() {
        MenuItem openMenuItem = new MenuItem("打开文件");
        MenuItem deleteMenuItem = new MenuItem("删除文件");
        MenuItem attributeMenuItem = new MenuItem("文件属性");
        getItems().add(openMenuItem);
        getItems().add(deleteMenuItem);
        getItems().add(attributeMenuItem);
        Clickopen(openMenuItem);
        Clickdelete(deleteMenuItem);
        Clickattribute(attributeMenuItem);
    }

    /** * 获取实例 * @return GlobalMenu */
    public static RightMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RightMenu();
        }
        return INSTANCE;
    }


    public static void Clickopen(MenuItem o){
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
    public static void Clickdelete(MenuItem o){
        o.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //进入文件删除操作
                if(MenuAction.vvBox.catalog.getAttribute()==1000){
                    FileOperation.clearCatalog(MenuAction.vvBox.catalog);
                }
                else{
                    FileOperation.clearFile(MenuAction.vvBox.catalog);
                }
                Client.flow_pane.getChildren().remove(MenuAction.vvBox);
            }
        });
    }
    //-------------------------------------------------------------
    public static void Clickattribute(MenuItem o){
        o.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //进入文件属性界面
                Pane pane=new Pane();
                Stage stage=new Stage();
                stage.setScene(new Scene(pane,200,300));
                stage.show();
            }
        });
    }
}
