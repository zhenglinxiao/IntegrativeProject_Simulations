/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticsimulator;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    // create the elements in the scene builder
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
    private Label helpFocal;

    @FXML
    private Label helpLens;

    @FXML
    private Label helpImage;

    @FXML
    private Label helpPrinciple;

    @FXML
    private Label helpObject;

    @FXML
    private Label info;

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
    private Button restartButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button cancelButton;

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

    @FXML
    private Line firstLineFirstPart;

    @FXML
    private Line firstLineSecondPart;

    @FXML
    private Line secondLine;

    @FXML
    private Line thirdLineFirstPart;

    @FXML
    private Line thirdLineSecondPart;

    @FXML
    private Line dottedLine1;

    @FXML
    private Line dottedLine2;

    @FXML
    private Line dottedLine3;

    @FXML
    private Line boundary;

    @FXML
    private CheckBox principleBox;

    @FXML
    private ComboBox pickPicture;

    // create necessary class variables
    private boolean start = false;
    private boolean restart = false;
    private boolean help = false;
    private double lensX = 570;
    private final double lensY = 250;
    private double lensWidth = 1000 / 80;
    private final double lensHeight = 400;
    private final double lensCenterX = lensX + lensWidth / 2;
    private final double groundLineY = 440;
    private final double indexDefault = 135;
    private final double radiusDefault = 80;
    private final double positionDefault = 280;
    private final double heightDefault = 100;
    private double objectCenterX = 280;
    private double objectHeight = 100;
    private final double objectWidth = 40;
    private final double objectX = objectCenterX - objectWidth;
    private final double objectY = 140 + objectHeight;
    private double imageCenterX = 0;
    private double focalLength = 0;
    private double imagePosition = 0;
    private double magnification = 0;
    private double imageHeight = 0;
    private double lastFrameTime = 0.0;
    private double boundIndex = 0;

    // Once the start button is clicked, make the sliders, comboBox, and checkBox enabled.
    // Also start the real time simulation by setting the boolean variable start equal to true
    @FXML
    private void handleButtonAction(ActionEvent event) {
        restart = false;
        
        //Change start button to quit button
        restartButton.setVisible(true);
        restartButton.setDisable(false);
        startButton.setVisible(false);
        startButton.setDisable(true);

        // enable all the sliders and the help button
        enableSliderBoxes();
        helpButton.setDisable(false);

        start = true;
    }

    // Once the restart button is clicked, make the start button appears.
    // Also set the boolean variable restart to true to make sliders, comboBox, checkbox to their default values
    @FXML
    private void restartButtonAction(ActionEvent event) {

        //Change restart button to start button
        restartButton.setVisible(false);
        restartButton.setDisable(true);
        startButton.setVisible(true);
        startButton.setDisable(false);

        // if the simulation is initially in help event, make it go back
        cancelButton.fire();
        helpButton.setDisable(true);
        
        restart = true;
    }

    // button action once the help button is clicked
    // display the range of the sliders and a brief explaination of the application
    @FXML
    private void helpButtonAction(ActionEvent event) {
        //Change help button to cancel button
        cancelButton.setVisible(true);
        cancelButton.setDisable(false);
        helpButton.setVisible(false);
        helpButton.setDisable(true);

        help = true;

        //disable sliders and checkbox
        disableSliderBoxes();

        // display information about the input range
        indexLabel.setText("Range: 1.2 - 1.5");
        radiusLabel.setText("Range: 0.3 - 1.3 decimeters");
        objectPositionLabel.setText("Range: 0.1 - 5.5 meters");
        objectHeightLabel.setText("Range: 0.5 - 1.5 decimeters");
        focalLabel.setText(null);
        imagePositionLabel.setText(null);
        imageHeightLabel.setText(null);
        identityLabel.setText(null);

        // if the sliders are at their initial positions, give the user a hint to drag the sliders to start the application
        if (indexSlider.getValue() == indexDefault && radiusSlider.getValue() == radiusDefault
                && objectPositionSlider.getValue() == positionDefault && objectHeightSlider.getValue() == heightDefault) {
            helpLens.setText("You can drag the sliders \n"
                    + "on the right side of the window \n"
                    + "to discover the magic of \n"
                    + "geometrical optics");
        } else {
            // display explanation of the simulation
            helpFocal.setText("The two points are the focal \n"
                    + "points of the converging lens.\n"
                    + "As the focal length grows,\n"
                    + " they will move further away\n"
                    + " from the lens, vice versa");
            helpLens.setText("Convergent lens centers the \n"
                    + "line rays that are going \n"
                    + "through. It becomes thinner \n"
                    + "as the curvature radius increases, \n"
                    + "vice versa");
            helpObject.setText("As the object position increases, \n"
                    + "the object goes further from the \n"
                    + "converging lens, vice versa. \n"
                    + "As the object height increases, \n"
                    + "the object becomes bigger, \n"
                    + "vice versa");
            if (imagePosition > 0) {
                // display corresponding message if a real, inverted image is formed
                helpImage.setText("In convergent lens optics, when the \n"
                        + "object position is further than the focal \n"
                        + "length, a real and inverted image is \n"
                        + "formed. In which case the image position is \n"
                        + "positive (the image is on the right side of \n"
                        + "the lens), the magnification is negative \n"
                        + "(the image is inverted)");
            } else {
                // display corresponding message if a virtual, upright image is formed
                helpImage.setText("In convergent lens optics, when the \n"
                        + "object position is less than the focal \n"
                        + "length, a virtual and upright image is \n"
                        + "formed. In which case the image position is \n"
                        + "negative (the image is on the left side of \n"
                        + "the lens), the magnification is positive \n"
                        + "(the image is upright)");
            }
            // give the explanation about the principle rays if the user displays the principle rays
            if (principleBox.isSelected()) {
                helpPrinciple.setText("In convergent lens optics, there are \n"
                        + "three principle rays formed, the point where \n"
                        + "they intersect is the tip of the image formed. \n"
                        + "In the simulation, the solid black lines are \n"
                        + "real light rays, the purple dotted lines are \n"
                        + "virtual light rays");
            } else {
                // lead the user to click on display principle rays check box to show the principle rays if the user haven't done it already
                helpPrinciple.setText("Click the display principle rays \n"
                        + "check box to discover more about \n"
                        + "the refraction of light rays");
            }
            info.setText("The numbers in green color are user inputs, \n"
                    + "in purple color are outputs from the calculation");
        }
    }

    // button action once the user clicked the cancel button
    @FXML
    private void cancelButtonAction(ActionEvent event) {
        //Change cancel button to help button
        cancelButton.setVisible(false);
        cancelButton.setDisable(true);
        helpButton.setVisible(true);
        helpButton.setDisable(false);
        
        help = false;

        // enable the sliders and checkbox
        enableSliderBoxes();
        // remove help informations
        clearInfo();
    }

    // This method creates a rectangle on the anchorpane and fill up it with the lens image
    public void setLens(double x, double y, double width, double height) {
        // remove the original lens image from the pane and create a new rectangle
        removeFromPane(lens);
        lens = new Rectangle(x, y, width, height);

        // fill up the rectangle with the lens image and add it to the pane
        lens.setFill(AssetManager.getLensImage());
        addToPane(lens);

        // update the lens width and the X coordinates of the lens rectangle
        lensWidth = lens.getWidth();
        lensX = lensCenterX - lensWidth / 2;
    }

    // This method takes the object height as a parameter and creates 
    // an object on the pane at the appriopriate prosition with the proper height 
    public void setObject(double height) {
        // remove the object from the pane if it's already existed
        removeFromPane(object);
        
        // create object rectangle
        // x coordinate = Xcenter - half of the rectangle's width
        // y coordinate = Y coordinate of the ground line - rectangle's height
        object = new Rectangle(objectWidth, height);
        object.setLayoutX(objectCenterX - objectWidth / 2);
        object.setLayoutY(groundLineY - height);

        // Check the comboBox to set the corresponding object
        // fill the appropriate image to the object rectangle
        if (pickPicture.getValue().equals("Candle")) {
            object.setFill(AssetManager.getCandleImage());
        } else if (pickPicture.getValue().equals("Can")) {
            object.setFill(AssetManager.getCanImage());
        } else {
            object.setFill(AssetManager.getPencilImage());
        }

        // add the object rectangle to the pane
        addToPane(object);

        // update the object height value
        objectHeight = object.getHeight();
    }

    // this method only applies when the lens creates an inverted image
    // it takes the image position and image height as parameters and sets the image 
    // at the proper position with appropriate height
    public void setInvertedImage(double position, double height) {
        // set the X coordinate of the image center
        // X imageCenter = X of lensCenter + the distance between the lens and the image
        imageCenterX = lensCenterX + position;

        // remove the image from the pane if it's already existed
        removeFromPane(image);
        
        // create new image rectangle
        // x coordinate = Xcenter - half of the rectangle's width (since the width of the object is equal to the image)
        // y coordinate = Y coordinate of the ground line
        image = new Rectangle(objectWidth, height);
        image.setLayoutX(imageCenterX - objectWidth / 2);
        image.setLayoutY(groundLineY);

        // Check the comboBox to set the corresponding inverted image
        if (pickPicture.getValue().equals("Candle")) {
            image.setFill(AssetManager.getInvertedCandleImage());
        } else if (pickPicture.getValue().equals("Can")) {
            image.setFill(AssetManager.getInvertedCanImage());
        }
        else
           image.setFill(AssetManager.getInvertedPencilImage());
        
        // add the image rectangle to the pane
        addToPane(image);
    }

    // this method only applies when the lens creates an upright image 
    public void setUprightImage(double position, double height) {
        // set the X coordinate of the image center
        // X imageCenter = X of lensCenter + the distance between the lens and the image
        imageCenterX = lensCenterX + position;

        // remove the image from the pane if it's already existed
        removeFromPane(image);
        
        // create new image rectangle
        // x coordinate = Xcenter - half of the rectangle's width (since the width of the object is equal to the image)
        // y coordinate = Y coordinate of the ground line - the height of the image rectangle
        image = new Rectangle(objectWidth, height);
        image.setLayoutX(imageCenterX - objectWidth / 2);
        image.setLayoutY(groundLineY - height);

        // Check the comboBox to set the corresponding upright image
        if (pickPicture.getValue().equals("Candle")) {
            image.setFill(AssetManager.getUprightCandleImage());
        } else if (pickPicture.getValue().equals("Can")) {
            image.setFill(AssetManager.getUprightCanImage());
        }
        else
            image.setFill(AssetManager.getUprightPencilImage());
        
        // add the image rectangle to the pane
        addToPane(image);
    }

    // this method calculated the focal length according to the refractive index and the curvature radius
    // and set the focal points on the pane
    public void setFocalPoints() {
        Equation e = new Equation();
        // read the refractive index and the curvature radius from the sliders and divide their slider values by 100
        double index = indexSlider.getValue() / 100;
        double radius = radiusSlider.getValue() / 100;
        // calls the focalLength method from the equation class and bring the index and radius as the parameters
        focalLength = e.focalLength(index, radius);

        // set the X coordinates of focal point one and focal point two
        // focal point one is on the left side of the lens
        // X = X of lensCenter - focal Length
        // focal point two is on the right side of the lens
        // X = X of lensCenter + focal Length
        focalOne.setLayoutX(lensCenterX - (focalLength * 100));
        focalTwo.setLayoutX(lensCenterX + (focalLength * 100));
    }

    // if the image is real (inverted), set and diaplay the three principle rays on the pane
    public void setPrincipleReal(double objectTipX, double objectTipY, double imageTipX, double imageTipY) {
        // remove the principles rays if they already exist on the pane
        removePrincipleLines();

        firstLineFirstPart = new Line(objectTipX, objectTipY, lensCenterX, objectTipY);
        thirdLineFirstPart = new Line(objectTipX, objectTipY, lensCenterX, imageTipY);

        // if the image within the bound, display every principle rays from the tip of the object to the tip of the image
        if (imagePosition * 100 < boundIndex) {
            firstLineSecondPart = new Line(lensCenterX, objectTipY, imageTipX, imageTipY);
            secondLine = new Line(objectTipX, objectTipY, imageTipX, imageTipY);
            thirdLineSecondPart = new Line(lensCenterX, imageTipY, imageTipX, imageTipY);
        } else {
            // if the image is out of the bound, calculate the slope of firstLineSecondPart and secondLine
            // then set their endLayout Y 
            // endLayout Y = delta X * slope + object Tip Y
            // calculates the slope of each line by using the getSlope method and bringing the startX, startY, endX, and endY as parameters
            double slope1 = getSlope(lensCenterX, objectTipY, imageTipX, imageTipY);
            double slope2 = getSlope(objectTipX, objectTipY, imageTipX, imageTipY);
            firstLineSecondPart = new Line(lensCenterX, objectTipY, boundary.getLayoutX(), objectTipY + (boundIndex) * slope1);
            secondLine = new Line(objectTipX, objectTipY, boundary.getLayoutX(), objectTipY + (boundary.getLayoutX() - objectTipX) * slope2);
            thirdLineSecondPart = new Line(lensCenterX, imageTipY, boundary.getLayoutX(), imageTipY);
        }

        // add all the lines to the pane
        addToPane(firstLineFirstPart);
        addToPane(firstLineSecondPart);
        addToPane(secondLine);
        addToPane(thirdLineFirstPart);
        addToPane(thirdLineSecondPart);

    }

    // if the image is virtual (upright), set and siaplay the three principle rays on the pane
    public void setPrincipleVirtual(double objectTipX, double objectTipY, double imageTipX, double imageTipY) {
        // calculates the slope of each line by using the getSlope method and bringing the startX, startY, endX, and endY as parameters
        double slope = getSlope(objectTipX, objectTipY, imageTipX, imageTipY);

        // remove the principles rays if they already exist on the pane
        removePrincipleLines();
        
        // set the lines with their startX, startY, endX, and endY
        firstLineFirstPart = new Line(objectTipX, objectTipY, lensCenterX, objectTipY);
        firstLineSecondPart = new Line(lensCenterX, objectTipY, focalTwo.getLayoutX(), focalTwo.getLayoutY());
        // imageTip = delta X * slope + groundLineY
        secondLine = new Line(objectTipX, objectTipY, focalTwo.getLayoutX(), groundLineY + (focalTwo.getLayoutX() - lensCenterX) * slope);
        thirdLineFirstPart = new Line(objectTipX, objectTipY, lensCenterX, imageTipY);
        thirdLineSecondPart = new Line(lensCenterX, imageTipY, focalTwo.getLayoutX(), imageTipY);

        // create the dotted lines and set its color to purple
        dottedLine1 = new Line(imageTipX, imageTipY, lensCenterX, objectTipY);
        dottedLine1.getStrokeDashArray().addAll(25d, 10d);
        dottedLine1.setStroke(Color.PURPLE);
        dottedLine2 = new Line(imageTipX, imageTipY, objectTipX, objectTipY);
        dottedLine2.getStrokeDashArray().addAll(25d, 10d);
        dottedLine2.setStroke(Color.PURPLE);
        dottedLine3 = new Line(imageTipX, imageTipY, lensCenterX, imageTipY);
        dottedLine3.getStrokeDashArray().addAll(25d, 10d);
        dottedLine3.setStroke(Color.PURPLE);

        // add all the lines to the anchorPane
        addToPane(firstLineFirstPart);
        addToPane(firstLineSecondPart);
        addToPane(secondLine);
        addToPane(thirdLineFirstPart);
        addToPane(thirdLineSecondPart);
        addToPane(dottedLine1);
        addToPane(dottedLine2);
        addToPane(dottedLine3);
    }

    // remove all the principle lines from the pane if this method is being called
    public void removePrincipleLines() {
        removeFromPane(firstLineFirstPart);
        removeFromPane(firstLineSecondPart);
        removeFromPane(secondLine);
        removeFromPane(thirdLineFirstPart);
        removeFromPane(thirdLineSecondPart);
        removeFromPane(dottedLine1);
        removeFromPane(dottedLine2);
        removeFromPane(dottedLine3);
    }

    // this method remove all the help explanations
    public void clearInfo() {
        helpFocal.setText(null);
        helpLens.setText(null);
        helpObject.setText(null);
        helpImage.setText(null);
        helpPrinciple.setText(null);
        info.setText(null);
    }

    // this method make the sliders, checkbox, and comboBox in the pane disabled
    public void disableSliderBoxes() {
        indexSlider.setDisable(true);
        radiusSlider.setDisable(true);
        objectPositionSlider.setDisable(true);
        objectHeightSlider.setDisable(true);
        principleBox.setDisable(true);
        pickPicture.setDisable(true);
    }

    // this method inables all the sliders, checkbox, and comboBox in the pane
    public void enableSliderBoxes() {
        indexSlider.setDisable(false);
        radiusSlider.setDisable(false);
        objectPositionSlider.setDisable(false);
        objectHeightSlider.setDisable(false);
        principleBox.setDisable(false);
        pickPicture.setDisable(false);
    }

    // this method calculates the image position with fcoal length and object position inputs
    public double getImagePosition(double position) {
        Equation e = new Equation();
        // calls the imagePosition method from the equation class and uses focalLength and object position as parameters
        double iPosition = e.imagePosition(focalLength, position);
        return iPosition;
    }

    // this method calculates the image height and uses the object position, image position, and object height as parameters
    public double getImageHeight(double p, double q, double oh) {
        Equation e = new Equation();
        // calls the manification method from the equation class and uses the object and image position as parameters
        magnification = e.magnification(p, q);
        // call s the imageHeight method from the equation class and uses the magnification value and object height as parameters
        imageHeight = e.imageHeight(magnification, oh);
        return imageHeight;
    }

    // this method calculates the slope by using x1, y1, x2, and y2 as parameters
    public double getSlope(double x1, double y1, double x2, double y2) {
        // slope = (|y2 - y1|) / (|x2 - x1|)
        double slope = Math.abs(y2 - y1) / Math.abs(x2 - x1);
        return slope;
    }

    // this method put the noade onto the pane
    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    // this method remove the node from the pane
    public void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add string elements to the comboBox
        ArrayList<String> pictureList = new ArrayList();
        pictureList.add("Candle");
        pictureList.add("Can");
        pictureList.add("Pencil");
        pickPicture.getItems().addAll(pictureList);
        //set the original value of the comboBox as "Candle"
        pickPicture.setValue("Candle");

        // disable the sliders, checkbox. and comboBox
        disableSliderBoxes();
        // initially set the help button disabled
        helpButton.setDisable(true);

        //disable the restart button and set it invisible
        restartButton.setVisible(false);
        restartButton.setDisable(true);
        //disable the cancel button and set it invisible
        cancelButton.setVisible(false);
        cancelButton.setDisable(true);

        long initialTime = System.nanoTime();
        lastFrameTime = 0.0f;

        // preload the pictures
        AssetManager.preloadAllAssets();

        // set the lens according to the default lensX, lensY, lensWidth. and lensHeight
        setLens(lensX, lensY, lensWidth, lensHeight);
        // set the object according to the default object height
        setObject(objectHeight);
        // set the focal points
        setFocalPoints();

        // set the background image
        pane.setBackground(AssetManager.getBackgroundImage());

        // create an animationTimer
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                // once the start button is clicked
                if (start) {
                    // update refraction index
                    //        curvature radius
                    //        object position: which is from the object to the lens (x of lens - x of object)
                    //        object height
                    double refractionIndex = indexSlider.getValue() / 100;
                    double curvatureRadius = radiusSlider.getValue() / 100;
                    double objectPosition = (lensCenterX - objectPositionSlider.getValue()) / 100;
                    double objectHeight = objectHeightSlider.getValue() / 100;

                    // calculate the image position by calling getImagePosition method and use object position as a parameter
                    imagePosition = getImagePosition(objectPosition);
                    
                    imageHeight = getImageHeight(objectPosition, imagePosition, objectHeight);

                    // reset the radius of the lens if the value of the curvature radius sider changes
                    if (radiusSlider.getValue() != lensWidth) {
                        setLens(lensX, lensY, 1000 / radiusSlider.getValue(), lensHeight);
                    }

                    // reset the height or position of the object if the values of the object height slider or object
                    // position slider change
                    if (objectHeightSlider.getValue() != objectHeight || objectCenterX != objectPositionSlider.getValue()) {
                        // pass the new object height to setObject method to set the new object
                        setObject(objectHeightSlider.getValue());
                        // update the value of X coordinate of the object center 
                        objectCenterX = objectPositionSlider.getValue();

                        // check if an inverted image is formed. If object position is bigger than focal length, an inverted / real image is formed
                        if (objectPosition > focalLength) {
                            // create an inverted image on the pane
                            setInvertedImage(imagePosition * 100, imageHeight * 100);

                            // update the value of boundIndex
                            // check if whether the image is out of bound or not, make it invisible if it is out of bound
                            boundIndex = boundary.getLayoutX() - lensCenterX;
                            if (imagePosition * 100 > boundIndex) {
                                // remove the image from the pane if its position is bigger than the boundIndex
                                removeFromPane(image);
                            }

                            if (principleBox.isSelected()) // create the principle rays on the pane if the inverted(real) image is formed
                            {
                                setPrincipleReal(objectCenterX, object.getLayoutY(), imageCenterX, image.getLayoutY() + image.getHeight());
                            }
                        } //check if an upright image is formed. If object position is smaller than focal length, an upright / virtual image is formed
                        else if (objectPosition < focalLength) {
                            // create an upright image on the pane
                            setUprightImage(imagePosition * 100, imageHeight * 100);

                            if (principleBox.isSelected()) // create the principle rays on the pane if the upright(virtual) image is formed
                            {
                                setPrincipleVirtual(objectCenterX, object.getLayoutY(), imageCenterX, image.getLayoutY());
                            }
                        }

                    }
                    setFocalPoints();

                    // if the user unclick the principle lines check box, remove the principle rays from the pane
                    if (!principleBox.isSelected()) {
                        removePrincipleLines();
                    }

                    // if the help button is not being clicked
                    if (!help) {
                        //display input slider values
                        indexLabel.setText(String.format("%.2f", refractionIndex));
                        radiusLabel.setText(String.format("%.2f", curvatureRadius) + " dm");
                        objectPositionLabel.setText(String.format("%.2f", objectPosition) + " m");
                        objectHeightLabel.setText(String.format("%.2f", objectHeight) + " dm");
                        //display the focal length in the focal length label
                        //display the image position in the proper label
                        //display the image height in the proper label
                        focalLabel.setText(String.format("%.2f", focalLength) + " m");
                        imagePositionLabel.setText(String.format("%.2f", imagePosition) + " m");
                        imageHeightLabel.setText(String.format("%.2f", imageHeight) + " dm");

                        String info = "This image is ";
                        // the image is upright if magnification is positive, vice versa
                        if (magnification > 0) {
                            info += "upright, ";
                        } else {
                            info += "inverted, ";
                        }

                        // the image is enlarged if image height is bigger than the object height
                        // identical if they have the same height
                        // reduced if image height is smaller
                        if (imageHeight > objectHeight) {
                            info += "enlarged, ";
                        } else if (imageHeight == objectHeight) {
                            info += "identical, ";
                        } else {
                            info += "reduced, ";
                        }

                        // the image is virtual if the image position is negative, vice versa
                        if (imagePosition < 0) {
                            info += "and virtual.";
                        } else {
                            info += "and real.";
                        }

                        // display the info in the proper label
                        identityLabel.setText(info);
                    }

                }

                // if the restart simulation button is clicked, set everything to default
                if (restart) {
                    // set everything to initial state(sliders, checkbox, comboBox, remove the image from the pane)
                    indexSlider.setValue(indexDefault);
                    radiusSlider.setValue(radiusDefault);
                    objectPositionSlider.setValue(positionDefault);
                    objectHeightSlider.setValue(heightDefault);
                    principleBox.setSelected(false);
                    pickPicture.setValue("Candle");
                    removeFromPane(image);

                    // disable sliders, checkbox, and comboBox
                    disableSliderBoxes();

                    //empty the labels
                    indexLabel.setText(null);
                    radiusLabel.setText(null);
                    objectPositionLabel.setText(null);
                    objectHeightLabel.setText(null);
                    focalLabel.setText(null);
                    imagePositionLabel.setText(null);
                    imageHeightLabel.setText(null);
                    identityLabel.setText(null);
                }

                // make the ground line and two focal points to the front of the pane
                groundLine.toFront();
                focalOne.toFront();
                focalTwo.toFront();
            }

        }.start();
    }

}
