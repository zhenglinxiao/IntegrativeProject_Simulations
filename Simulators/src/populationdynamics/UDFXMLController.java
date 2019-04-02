/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populationdynamics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lxz19
 */
public class UDFXMLController implements Initializable {
    
    @FXML
    private void backAction(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("PDFXMLDocument.fxml"));

            Scene sim = new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setTitle("Population Dynamics Simulator");
            window.setScene(sim);
            window.show();       
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
