
package mainmenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Lin Xiao Zheng, Yutong Yang & Gary Zhang
 */
public class MainMenuFXMLController implements Initializable {
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button opticsButton;
    
    @FXML
    private Button floatingButton;
    
    @FXML
    private Button popButton;
    
    @FXML
    private Label title;
    
    @FXML 
    private Label info;
    
    @FXML
    private void startOptics(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/opticsimulator/FXMLDocument.fxml"));
        
        //Scene is the content inside the pane
        //Stage is the window (pane)
        Scene simScene = new Scene(root);
        Stage simWindow = new Stage();
        root.requestFocus();
        // get the menuWindow from the button pressed
        Stage menuWindow = (Stage)((Node)(event.getSource())).getScene().getWindow();
        menuWindow.close();
        
        // once the simulation window is closed, open the main window
        simWindow.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e){
                menuWindow.show();
            }
        });
        
        simWindow.setTitle("Geometrical Optics Simulator");
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
        mainmenu.AssetManager.preloadAllAssets();
        
        // set the background for the mainWindow
        pane.setBackground(AssetManager.getBackgroundImage());

    }    
    
}
