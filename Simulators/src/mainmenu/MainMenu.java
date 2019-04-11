
package mainmenu;

import com.sun.scenario.effect.impl.Renderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lin Xiao Zheng
 */
public class MainMenu extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("MainMenuFXML.fxml"));
        
        Scene scene = new Scene(mainMenu);
        mainMenu.requestFocus();
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
