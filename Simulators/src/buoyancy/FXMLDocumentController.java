/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buoyancy;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
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
    
   
     RectangularPrism Gary;
     
    @FXML
    public void rectangularPrismButtonAction(ActionEvent event) {
        rec = new Rectangle(100, 50);
        rec.setLayoutX(75);
        rec.setLayoutY(273);
        Gary = new RectangularPrism(rec);
        removeFromPane(circle);
        removeFromPane(square);
        addToPane(Gary.getRectangularPrism());
        cube.setDisable(false);
        rectangularPrism.setDisable(true);
        sphere.setDisable(false);
       
       
        
    }
    Cubee Rima;
    @FXML
    public void cubeButtonAction(ActionEvent event){
        square = new Rectangle(80,80);
        square.setLayoutX(75);
        square.setLayoutY(273);
        Rima = new Cubee(square);
        removeFromPane(circle);
        removeFromPane(rec);
        addToPane(Rima.getCube());
        cube.setDisable(true);
        rectangularPrism.setDisable(false);
        sphere.setDisable(false);
        
        
    }
    Sphere Ted;
    @FXML
    public void sphereButtonAction(ActionEvent event){
        circle = new Circle(50);
        circle.setLayoutX(75);
        circle.setLayoutY(273);
        Ted = new Sphere(circle, 100);
        removeFromPane(rec);
        removeFromPane(square);
        addToPane(Ted.getCircle());
        cube.setDisable(false);
        rectangularPrism.setDisable(false);
        sphere.setDisable(true);  
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new AnimationTimer(){
            @Override
            public void handle(long now) {
                // Drag & drop
                
                // Get input
                double massVal = 0;
                double volVal = 0;
                double massLiq = 0;
                double objectDensity;
                double liquidDensity = 1;
                double floatingPercentage;
                double densityRatio;
                double Archimedes;
                boolean floating = false;
                
                try {
                    massVal = Double.parseDouble(objectMass.getText()); 
                    volVal = Double.parseDouble(objectMass.getText());
                    massLiq = Double.parseDouble(objectMass.getText());

                } catch (Exception e) {
                    //System.out.println("error in parsing");
                }
                
                if (rectangularPrism.isDisable()){
                   Gary.setVolume(volVal); 
                   Gary.setMass(massVal);
                   
                   //System.out.print("" + Gary.getMass() + " " +  Gary.getVolume());
                }
                
                else if (cube.isDisable()){
                    Rima.setVolume(volVal);
                    Rima.setMass(massVal);
                   // System.out.print("" + Rima.getMass() + Rima.getVolume());
                    
                }
                
                else if (sphere.isDisable()){
                    Ted.setVolume(volVal);
                    Ted.setVolume(massVal);
                   // System.out.print("" + Ted.getMass() + Ted.getVolume());
                }                                             
                // Do calculations
                objectDensity = massVal/volVal;
                liquidDensity = massLiq;
                densityRatio= objectDensity/liquidDensity;
                
                //do Archimedes
                
                
                
                // Output: if(in water), then water changes
                if (objectDensity < liquidDensity){
                    floatingPercentage = 0;
                    densityRatio = 0;
                    Archimedes = 0;
                    
    
                    
                }
                
                
            }
        }.start();


       
    }  
                
    
}
