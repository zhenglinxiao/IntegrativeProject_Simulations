
package mainmenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Lin Xiao Zheng, Yutong Yang & Gary Zhang
 */
public class MainMenuFXMLController implements Initializable {
    
    @FXML
    private Button opticsButton;
    
    @FXML
    private Button floatingButton;
    
    @FXML
    private Button popButton;
    
    @FXML
    private void startOptics(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/opticsimulator/FXMLDocument.fxml"));
        
        Scene simScene = new Scene(root);
        Stage simWindow = new Stage();
        
        Stage menuWindow = (Stage)((Node)(event.getSource())).getScene().getWindow();
        menuWindow.close();
        
        simWindow.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e){
                menuWindow.show();
            }
        });
        
        simWindow.setTitle("");
        // simWindow.getIcons().add(new Image("file extension"));
        simWindow.setScene(simScene);
        simWindow.show();
    }
    
    @FXML
    private void startFloat(ActionEvent event){
        // GRAY
    }
    
    @FXML
    private void startPop(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/populationdynamics/PDFXMLDocument.fxml"));
        
        Scene simScene = new Scene(root);
        Stage simWindow = new Stage();
        
        Stage menuWindow = (Stage)((Node)(event.getSource())).getScene().getWindow();
        menuWindow.close();
        
        simWindow.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e){
                menuWindow.show();
            }
        });
        
        simWindow.setTitle("Population Dynamics Simulator");
        // simWindow.getIcons().add(new Image("file extension"));
        simWindow.setScene(simScene);
        simWindow.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
