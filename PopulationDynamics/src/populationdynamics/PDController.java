/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author lxz19
 */
public class PDController implements Initializable {
    
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
        Polygon hexagon = new Polygon(new Hexagon(100d).getPoints());
        addToPane(hexagon);
    }    
    
}
