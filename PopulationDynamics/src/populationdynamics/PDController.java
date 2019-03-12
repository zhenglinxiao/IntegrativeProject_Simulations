
package populationdynamics;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Toggle;
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
    
    private ArrayList<Polygon> hexGrid = new ArrayList();
    private boolean isPreset;
    private DecimalFormat integer = new DecimalFormat("#");
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private AnchorPane optionsBox;
    
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
    
    @FXML
    private Button restart;
    
    @FXML
    private void presetRadioButton(ActionEvent event){
        selectPreset.setDisable(false);
        dataBox.setDisable(true);
        isPreset = true;
    }
    
    @FXML
    private void createRadioButton(ActionEvent event){
        dataBox.setDisable(false);
        selectPreset.setDisable(true);
        isPreset = false;
    }
    
    @FXML
    private void startSimulation(ActionEvent event){
        // Switch buttons
        restart.setVisible(true);
        restart.setDisable(false);
        start.setVisible(false);
        start.setDisable(true);
        
        // Disable options
        optionsBox.setDisable(true);
        
        // Output data
        PDUtils.setData((int)lifespan.getValue(), (int)offspring.getValue(), determineType((String)survivorshipType.getValue()));
        intrinsicRate.setText("" + PDUtils.getRate());
        netRepRate.setText("" + PDUtils.getNetRepRate());
        meanGenTime.setText("" + PDUtils.getMeanGenTime());
        
        // Animation timer for graphs and hexgrid
    }
    
    @FXML
    private void restartSimulation(ActionEvent event){
        // Switch buttons
        restart.setVisible(false);
        restart.setDisable(true);
        start.setVisible(true);
        start.setDisable(false); 
        
        // Enable options
        optionsBox.setDisable(false);
        
        // Reset numerical outputs
        intrinsicRate.setText("");
        netRepRate.setText("");
        meanGenTime.setText("");
        
        // Reset graphs
        
        // Reset hex grid
    }
    
    private int determineType(String str){
        int returnValue = -1;
        switch(str){
            case "Type 1": returnValue = 1; break;
            case "Type 2": returnValue = 2; break;
            case "Type 3": returnValue = 3; break;
            default: System.out.println("determine type switch");
        }
        return returnValue;
    }
    
    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ComboBox elements; ENUMS?
        String[] animalList = {"bear", "wolf"};
        selectPreset.getItems().addAll(Arrays.asList(animalList));
        
        String[] types = {"Type 1", "Type 2", "Type 3"};
        survivorshipType.getItems().addAll(Arrays.asList(types));
        
        // Update slider value labels
        capacity.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                capacityLabel.setText(integer.format(capacity.getValue()));
            }
        });
        
        lifespan.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                lifespanLabel.setText(integer.format(lifespan.getValue()));
            }
        });        

        offspring.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                offspringLabel.setText(integer.format(offspring.getValue()));
            }
        });    
        
        initialPop.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                initialPopLabel.setText(integer.format(initialPop.getValue()));
            }
        });         
        
        // Create HexGrid
        Hexagon center = new Hexagon(30d, 480, 120);
        
        HexGrid test = new HexGrid(center, 4);
        ArrayList<Hexagon> hexPoints = test.getHexGrid();
        
        for(Hexagon h: hexPoints){
            Polygon temp = new Polygon(h.getPoints());
            temp.setFill(Color.TRANSPARENT);
            temp.setStroke(Color.BLACK);
            hexGrid.add(temp);
            addToPane(temp);
        }
        
    }
    
}
