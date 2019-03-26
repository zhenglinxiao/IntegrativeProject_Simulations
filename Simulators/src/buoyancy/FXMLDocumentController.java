/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buoyancy;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javax.swing.JButton;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    AnchorPane pane;
    
    @FXML
   Button cube;
    
    @FXML 
   Button rectangularPrism;
    
    @FXML
   Button sphere;
    
    @FXML
    Label percentage;
    
    @FXML
    Label densityDiff;
    
    @FXML
    Label floating;
    
    @FXML
    Label archi;
    
    @FXML
    Slider ratio;
    
    @FXML
    TextField objectMass;
    
    @FXML
    TextField objectVolume;
    
    @FXML
    TextField liquidMass;
    
    @FXML
    Rectangle rec;
    
    @FXML
    Rectangle square;
    
    @FXML
    Circle circle;
    
     public void addToPane(Node node) {
        pane.getChildren().add(node);
        
    }

    public void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }
       
//    double vol = Double.parseDouble(objectVolume.getText());
    
     
     
     
    // if (Cube.isSelected){
     
      //  Cube c = new Cube(vol);
        
       // }
    
    //when you click the rectangular prism, set the slider disable to false and visible to true
    
   
    
    @FXML
    public void rectangularPrismButtonAction(ActionEvent event) {
        rec = new Rectangle(100, 50);
        rec.setLayoutX(75);
        rec.setLayoutY(273);
        RectangularPrism Gary = new RectangularPrism(rec);
        removeFromPane(circle);
        removeFromPane(square);
        addToPane(Gary.getRectangularPrism());
       
        
    }
    
    @FXML
    public void cubeButtonAction(ActionEvent event){
        square = new Rectangle();
        square.setLayoutX(100);
        square.setLayoutY(100);
        Rectangle Rima = new Rectangle(250, 250);
        removeFromPane(circle);
        removeFromPane(rec);
        addToPane(Rima);
        
    }
    
    @FXML
    public void sphereButtonAction(ActionEvent event){
        circle = new Circle(10);
        circle.setLayoutX(75);
        circle.setLayoutY(273);
        Sphere Ted = new Sphere(circle, 100);
        removeFromPane(rec);
        removeFromPane(square);
        addToPane(Ted.getCircle());
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        
        
   
       
       
        
        
       
    }    
    
}
