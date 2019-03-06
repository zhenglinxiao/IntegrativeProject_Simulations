
package populationdynamics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
//        Hexagon center = new Hexagon(30d, 150d, 150d);
//        
//        Polygon hex = new Polygon(new Hexagon(10d, 100d, 100d).getPoints());
//        addToPane(hex);
//        
//        HexGrid test = new HexGrid(center, 3);
//        ArrayList<Hexagon> grid = test.getHexGrid();
//        
//        for(Hexagon h: grid){
//            Polygon temp = new Polygon(h.getPoints());
//            temp.setFill(Color.TRANSPARENT);
//            temp.setStroke(Color.BLACK);
//            addToPane(temp);
//        }
        
    }    
    
}
