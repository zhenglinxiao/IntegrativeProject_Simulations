
package populationdynamics;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Lin Xiao Zheng
 */
public class PDController implements Initializable {
    
    Polygon[] hexGrid;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Hexagon center = new Hexagon(50d);
        Hexagon shift = new Hexagon(50d, 100d, 100d);
        Polygon hexagon = new Polygon(center.getPoints());
        Polygon hex2 = new Polygon(shift.getPoints());
        
//        for(int i = 0; i < shift.getPoints().length; i++){
//            System.out.println(center.getPoints()[i]);
//            System.out.println(shift.getPoints()[i]);
//        }
        
        for(double a: center.getCenter()){
            System.out.println(a);
        }
        for(double a: shift.getCenter()){
            System.out.println(a);
        }        
        
        addToPane(hexagon);
        addToPane(hex2);
    }    
    
}
