
package populationdynamics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private VBox dataBox;
    
    @FXML
    private ToggleGroup preset;
    
    @FXML
    private TextField speciesName;
    
    @FXML
    private ComboBox selectPreset;
    
    @FXML
    private ComboBox survivorshipType;
    
    @FXML
    private Slider capacity;
    
    @FXML
    private Slider lifespan;
    
    @FXML
    private Slider offspring;
    
    @FXML
    private Slider initialPop;
    
    @FXML 
    private Label intrinsicRate;
    
    @FXML
    private Label netRepRate;
    
    @FXML
    private Label meanGenTime;
    
    @FXML
    private Label capacityLabel;
    
    @FXML
    private Label lifespanLabel;
    
    @FXML
    private Label offspringLabel;
    
    @FXML 
    private Label initialPopLabel;
    
    @FXML
    private Button start;
    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//    }
    
    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Hexagon center = new Hexagon(30d, 480, 120);
        
        HexGrid test = new HexGrid(center, 4);
        ArrayList<Hexagon> grid = test.getHexGrid();
        
        for(Hexagon h: grid){
            Polygon temp = new Polygon(h.getPoints());
            temp.setFill(Color.TRANSPARENT);
            temp.setStroke(Color.BLACK);
            addToPane(temp);
        }
        
        // initialize combobox values
    }    
    
}
