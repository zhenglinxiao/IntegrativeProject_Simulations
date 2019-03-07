/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticsimulator;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    AnchorPane pane;

    @FXML
    private Line groundLine;

    @FXML
    private Label focalLabel;

    @FXML
    private Label imagePositionLabel;

    @FXML
    private Label imageHeightLabel;

    @FXML
    private Label identityLabel;
    
    @FXML
    private Label indexLabel;
    
    @FXML
    private Label radiusLabel;
    
    @FXML
    private Label objectPositionLabel;
    
    @FXML
    private Label objectHeightLabel;

    @FXML
    private Slider indexSlider;

    @FXML
    private Slider radiusSlider;

    @FXML
    private Slider objectPositionSlider;

    @FXML
    private Slider objectHeightSlider;

    @FXML
    private Button startButton;

    @FXML
    private Circle focalOne;

    @FXML
    private Circle focalTwo;

    @FXML
    private Rectangle lens;
    
    @FXML 
    private Rectangle object;
    
    @FXML 
    private Rectangle image;

    private boolean isClicked = false;
    private double lensX = 570;
    private double lensY = 130;
    private double lensWidth = 1000 / 80;
    private double lensHeight = 320;
    private final double lensCenterX = lensX + lensWidth / 2;
    //private final double lensCenterX = 590;
    private double objectCenterX = 280;
    private double objectHeight = 100;
    private double objectWidth = 40;
    private double objectX = objectCenterX - objectWidth;
    private double objectY = 140 + objectHeight;
    private double focalLength = 0;
    private double imagePosition = 0;
    private double magnification = 0;
    private double imageHeight = 0;
    private double lastFrameTime = 0.0;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        setFocalPoints();
        
        isClicked = true;
    }

    /*
    // This method creates a rectangle on the anchorpane and fill up it with the lens image
    public void setLens(double width, double height) {
        // remove the original lens image from the pane and create a new rectangle
        removeFromPane(lens);
        lens = new Rectangle(0, 0, width, height);
        lens.setLayoutX(lensCenterX - width);
        lens.setLayoutY(290 - lensHeight / 2);

        // fill up the rectangle with the lens image and add it to the pane
        lens.setFill(AssetManager.getLensImage());
        addToPane(lens);
        
        // update the lens width and the X coordinates of the left side of the rectangle
        lensWidth = lens.getWidth();
    }
    */
    
    
    // This method creates a rectangle on the anchorpane and fill up it with the lens image
    public void setLens(double x, double y, double width, double height) {
        // remove the original lens image from the pane and create a new rectangle
        removeFromPane(lens);
        lens = new Rectangle(x, y, width, height);

        // fill up the rectangle with the lens image and add it to the pane
        lens.setFill(AssetManager.getLensImage());
        addToPane(lens);
        
        // update the lens width and the X coordinates of the left side of the rectangle
        lensWidth = lens.getWidth();
        lensX = lensCenterX - lensWidth / 2;
    }
    
    
    public void setObject(double height){
        removeFromPane(object);
        object = new Rectangle(0, 0, objectWidth, height);
        object.setLayoutX(objectCenterX - objectWidth / 2);
        object.setLayoutY(290 - objectHeight);
        
        object.setFill(AssetManager.getCandleImage());
        addToPane(object);
        
        objectHeight = object.getHeight();
    }
    
    public void setInvertedImage(double height){
        removeFromPane(image);
        image = new Rectangle(0, 0, objectWidth, height);
    }

    public void setFocalPoints() {
        Equation e = new Equation();
        double index = indexSlider.getValue() / 100;
        double radius = radiusSlider.getValue() / 100;
        focalLength = e.focalLength(index, radius);

        focalOne.setLayoutX(lensCenterX - (focalLength * 100));
        focalTwo.setLayoutX(lensCenterX + (focalLength * 100));
    }
    
    /*
    public double getImagePosition(){
        Equation e = new Equation();
        double objectPosition = (lensCenterX - objectPositionSlider.getValue()) / 100;
        double iPosition = e.imagePosition(focalLength, objectPosition);
        return iPosition;
    }
    */
 
    // return the image position with fcoal length and object position inputs
    public double getImagePosition(double position){
        Equation e = new Equation();
        double iPosition = e.imagePosition(focalLength, position);
        return iPosition;
    }
    
    public double getImageHeight(double p, double q, double oh){
        Equation e = new Equation();
        magnification = e.magnification(p, q);
        imageHeight = e.imageHeight(magnification, oh);
        return imageHeight;
    }

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        long initialTime = System.nanoTime();
        lastFrameTime = 0.0f;

        AssetManager.preloadAllAssets();

        setLens(lensX, lensY, lensWidth, lensHeight);
        //setLens(lensWidth, lensHeight);
        setObject(objectHeight);
        setFocalPoints();
        
        
        //set the background image
        Image backgroundImage = new Image(new File("./assets/images/whitebackground.png").toURI().toString());
        Background background = new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
        pane.setBackground(background);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                if (isClicked) {
                    // update refraction index
                    //        curvature radius
                    //        object position: which is from the object to the lens (x of lens - x of object)
                    //        object height
                    double refractionIndex = indexSlider.getValue() / 100;
                    double curvatureRadius = radiusSlider.getValue() / 100;
                    double objectPosition = (lensCenterX - objectPositionSlider.getValue()) / 100;
                    double objectHeight = objectHeightSlider.getValue() / 100;
                    
                    //display slider values
                    indexLabel.setText(String.format("%.2f", refractionIndex));
                    radiusLabel.setText(String.format("%.2f", curvatureRadius));
                    objectPositionLabel.setText(String.format("%.2f", objectPosition));
                    objectHeightLabel.setText(String.format("%.2f", objectHeight));
                    
                    // reset the radius of the lens if the value of the curvature radius sider changes
                    if (radiusSlider.getValue() != lensWidth) {
                        setLens(lensX, lensY, 1000 / radiusSlider.getValue(), lensHeight);
                        //setLens(3000 / radiusSlider.getValue(), lensHeight);
                        //System.out.println(lensCenterX);    
                    }
                    
                    // reset the height or position of the object if the values of the object height slider or object
                    // position slider change
                    if (objectHeightSlider.getValue() != objectHeight || objectCenterX != objectPositionSlider.getValue()){
                        setObject(objectHeightSlider.getValue());
                        objectCenterX = objectPositionSlider.getValue();
                        //System.out.println(objectCenterX);
                    }
                    setFocalPoints();
                    
                    imagePosition = getImagePosition(objectPosition);
                    imageHeight = getImageHeight(objectPosition, imagePosition, objectHeight);
                    
                    //display the focal length in the focal length label
                    //display the image position in the proper label
                    //display the image height in the proper label
                    focalLabel.setText(String.format("%.2f", focalLength) + " m");
                    imagePositionLabel.setText(String.format("%.2f", imagePosition) + " m");
                    imageHeightLabel.setText(String.format("%.2f", imageHeight) + " m");
                    
                    String info = "This image is ";
                    // the image is upright if magnification is positive, vice versa
                    if (magnification > 0)
                        info += "upright, ";
                    else info += "inverted, ";
                    
                    // the image is enlarged if image height is bigger than the object height
                    // identical if they have the same height
                    // reduced if image height is smaller
                    if (imageHeight > objectHeight)
                        info += "enlarged, ";
                    else if (imageHeight == objectHeight)
                        info += "identical, ";
                    else info += "reduced, ";
                    
                    // the image is virtual if the image position is negative, vice versa
                    if (imagePosition < 0)
                        info += "and virtual.";
                    else info += "and real.";
                    
                    // display the info in the proper label
                    identityLabel.setText(info);
                    
                }
                
                // make the ground line and two focal points to the front of the pane
                groundLine.toFront();
                focalOne.toFront();
                focalTwo.toFront();
            }

        }.start();


        object.toFront();
        groundLine.toFront();
        focalOne.toFront();
        focalTwo.toFront();
    }

}
