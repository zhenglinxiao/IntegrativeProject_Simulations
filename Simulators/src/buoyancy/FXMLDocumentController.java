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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    Label ff;

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
    Cubee Rima;
    Sphere Ted;

    @FXML
    public void rectangularPrismButtonAction(ActionEvent event) {

        cube.setDisable(false);
        rectangularPrism.setDisable(true);
        sphere.setDisable(false);
        removeFromPane(circle);
        removeFromPane(square);
        addToPane(Gary.getRectangularPrism());

    }

    @FXML
    public void cubeButtonAction(ActionEvent event) {

        cube.setDisable(true);
        rectangularPrism.setDisable(false);
        sphere.setDisable(false);
        removeFromPane(circle);
        removeFromPane(rec);
        addToPane(Rima.getCube());

    }

    @FXML
    public void sphereButtonAction(ActionEvent event) {

        cube.setDisable(false);
        rectangularPrism.setDisable(false);
        sphere.setDisable(true);
        removeFromPane(rec);
        removeFromPane(square);
        addToPane(Ted.getCircle());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        square = new Rectangle(60, 60);
        square.setLayoutX(75);
        square.setLayoutY(263);
        Rima = new Cubee(square);

        rec = new Rectangle(100, 50);
        rec.setLayoutX(75);
        rec.setLayoutY(273);
        Gary = new RectangularPrism(rec);

        circle = new Circle(50);
        circle.setLayoutX(75);
        circle.setLayoutY(273);
        Ted = new Sphere(circle, 100);

        new AnimationTimer() {
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

                    volVal = Double.parseDouble(objectVolume.getText());
                    massLiq = Double.parseDouble(liquidMass.getText());

                    if (rectangularPrism.isDisable()) {
                        Gary.setWidth(Math.cbrt(volVal));
                        Gary.setHeight(Math.cbrt(volVal));
                        Gary.setVolume(volVal);
                        Gary.setMass(massVal);
                        System.out.println(volVal);

                        //System.out.print("" + Gary.getMass() + " " +  Gary.getVolume());
                    }
                    
                    if (cube.isDisable()){
                        
                    }

                } catch (Exception e) {
                    //System.out.println("error in parsing");
                }
                objectVolume.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue) {

                        try {
                            
                            

                            Rima.getCube().setHeight(Math.cbrt(Double.parseDouble(newValue)));             //sets the height of the cube
                            Rima.getCube().setWidth(Math.cbrt(Double.parseDouble(newValue)));              //sets the width of the cube

                            Rima.getCube().setLayoutY(323 - Rima.getCube().getHeight());                   //makes sure that the cube always stays on the platform

                            Gary.getRectangularPrism().setHeight((Double.parseDouble(newValue)) / 8);      //sets the height of the rectangular prism
                            Gary.getRectangularPrism().setWidth((Double.parseDouble(newValue) / 4));       //sets the width of the rectangular prism
                            Gary.getRectangularPrism().setLayoutY(323 - Gary.getRectangularPrism().getHeight()); //makes sure that the rectangular prism always stays on the platform

                            Ted.getCircle().setRadius(Math.cbrt(3 * Double.parseDouble(newValue) / (4 * Math.PI)));  //sets the size of the circle
                            Ted.getCircle().setLayoutY(323 - Ted.getCircle().getRadius());                           //makes sure that the circle always stays on the platform
                        } 
                        
                        catch (Exception e) {
                            //change text to red
                        };

                    }
                });

                /*    else if (cube.isDisable()){
                    Rima.setVolume(volVal);
                    Rima.setMass(massVal);
                   // System.out.print("" + Rima.getMass() + Rima.getVolume());
                    
                }
                
                else if (sphere.isDisable()){
                    Ted.setVolume(volVal);
                    Ted.setVolume(massVal);
                   // System.out.print("" + Ted.getMass() + Ted.getVolume());
                }                */
                // Do calculations
                objectDensity = massVal / volVal;
                liquidDensity = massLiq;
                densityRatio = objectDensity / liquidDensity;

                //do Archimedes
                // Output: if(in water), then water changes
                if (objectDensity > liquidDensity) {
                    floating = true;
                    floatingPercentage = densityRatio * 100;

                    //need to do Archimedes
                    percentage.setText("" + floatingPercentage);
                    densityDiff.setText("" + densityRatio);
                    ff.setText("" + floating);
                    //archi.setText(""+Archimedes);

                }

                if (objectDensity < liquidDensity) {
                    floatingPercentage = 0;
                    densityRatio = 0;
                    Archimedes = 0;

                    percentage.setText("" + floatingPercentage);
                    densityDiff.setText("" + densityRatio);
                    ff.setText("" + floating);
                    archi.setText("" + Archimedes);

                }

            }
        }.start();

    }

}
