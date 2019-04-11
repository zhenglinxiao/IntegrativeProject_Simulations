
package populationdynamics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lxz19
 */
public class UDFXMLController implements Initializable {
    
    private ArrayList<Hexagon> hexPoints = new ArrayList();
    private ArrayList<Polygon> hexGrid = new ArrayList(); 
    private ArrayList<Label> labelList = new ArrayList();
    private Hexagon center = new Hexagon(30d, 450, 140);
    long initialTime = System.nanoTime();
    double lastFrameTime = 0.0f;
    double timeSinceLastUpdate = 0;
    int hexIndex = 0;
    boolean isTimerOn = false;


    private void reset() {
        lastFrameTime = 0.0f;
        timeSinceLastUpdate = 0;
        hexIndex = 0;

        for(Polygon h: hexGrid){
            removeFromPane(h);
        }
        for(Label l: labelList){
            removeFromPane(l);
        }        
        hexPoints.clear();
        hexGrid.clear();
        labelList.clear();
    }    
    
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            isTimerOn = true;
            
            // Time calculations
            double currentTime = (now - initialTime) / 1000000000.0;
            double frameDeltaTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            timeSinceLastUpdate += frameDeltaTime;
            if (timeSinceLastUpdate > 0.3) {
                
                Polygon currentPoly = hexGrid.get(hexIndex);
                Hexagon currentHex = hexPoints.get(hexIndex);
                addToPane(currentPoly);
                
                Label currentNumber = labelList.get(hexIndex);
                addToPane(currentNumber);
                
                timeSinceLastUpdate = 0.0f;
                hexIndex++;
            }
            
            if(hexIndex >= hexGrid.size()){
                isTimerOn = false;
                startButton.setDisable(false);
                timer.stop();
            }
        }
    };    
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button startButton;
    
    @FXML
    private Slider hexSize;
    
    @FXML
    private void startAnimation(ActionEvent event){
        reset();
        // Create HexGrid
        HexGrid gridObject = new HexGrid(center, (int) hexSize.getValue());
        hexPoints = gridObject.getHexGrid();

        int counter = 1;
        for (Hexagon h : hexPoints) {
            Polygon temp = new Polygon(h.getPoints());
            temp.setFill(Color.TRANSPARENT);
            temp.setStroke(Color.BLACK);
            hexGrid.add(temp);
            
            Label tempL = new Label(counter + "");
            tempL.setScaleY(2);
            tempL.setScaleX(2); 
            tempL.setLayoutX(h.getPoints()[0] + h.getSide()/2 - 4);
            tempL.setLayoutY(h.getPoints()[1] + h.getHalfHeight() - 7);
            labelList.add(tempL);
            counter++;
        }
        
        if(!isTimerOn){
            timer.start();
            startButton.setDisable(true);
        }
    }
    
    @FXML
    private void backAction(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("PDFXMLDocument.fxml"));

            Scene sim = new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setTitle("Population Dynamics Simulator");
            window.setScene(sim);
            window.show();       
    }

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }  
    
    public void removeFromPane(Node node){
        pane.getChildren().remove(node);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
