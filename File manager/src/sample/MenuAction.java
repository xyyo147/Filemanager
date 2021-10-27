package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MenuAction {
    public static VVBox vvBox;
    public static void addRightMenu(VVBox v){
        vvBox = v;
        v.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton()== MouseButton.SECONDARY) {
                    MenuAction.vvBox = v;
                    Node node = event.getPickResult().getIntersectedNode();
                    RightMenu.getInstance().show(node, event.getScreenX(), event.getScreenY());

                }

            }
        });
    }
}
