package buoyancy;

import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button cube;

    @FXML
    private Button rectangularPrism;

    @FXML
    private Button sphere;

    @FXML
    private Label waterLevel;

    @FXML
    private Label percentage;

    @FXML
    private Label densityRatioLabel;

    @FXML
    private Label objDensityLabel;

    @FXML
    private Label floatingLabel;

    @FXML
    private Label archi;

    @FXML
    private Label objVolPrompt;

    @FXML
    private Label objMassPrompt;

    @FXML
    private Label liqMassPrompt;

    @FXML
    private TextField objectMass;

    @FXML
    private TextField objectVolume;

    @FXML
    private TextField liquidMass;

    @FXML
    private Rectangle rec;

    @FXML
    private Rectangle square;

    @FXML
    private Circle circle;

    @FXML
    private Rectangle water;

    @FXML
    private Label liquidDensityLabel;

    private double massVal = 500;
    private double volVal = 1;
    private double massLiq = 997;
    private final double volLiq = 1;
    private double objectDensity;
    private double liquidDensity;
    private double floatingPercentage;
    private double densityRatio;
    private double Archimedes;
    private boolean floating;
    private RectangularPrism prismObject;
    private Cube cubeObject;
    private Sphere sphereObject;
    private boolean waterMove = false;
    private boolean moveShape = false;
    private double lastFrameTime = 0;
    private double timeSinceLastUpdate = 0;
    private long iniTime = System.nanoTime();
    private DecimalFormat formatter = new DecimalFormat("#.##");

    private void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    private void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }

    @FXML
    private void rectangularPrismButtonAction(ActionEvent event) {
        cube.setDisable(false);
        sphere.setDisable(false);
        rectangularPrism.setDisable(true);

        removeFromPane(square);
        removeFromPane(circle);
        if (rec.getParent() == null) {
            addToPane(rec);
        }

        rec.setVisible(true);
        square.setVisible(false);
        circle.setVisible(false);
    }

    @FXML
    private void cubeButtonAction(ActionEvent event) {
        cube.setDisable(true);
        rectangularPrism.setDisable(false);
        sphere.setDisable(false);

        removeFromPane(rec);
        removeFromPane(circle);
        if (square.getParent() == null) {
            addToPane(square);
        }

        square.setVisible(true);
        rec.setVisible(false);
        circle.setVisible(false);
    }

    @FXML
    private void sphereButtonAction(ActionEvent event) {
        cube.setDisable(false);
        rectangularPrism.setDisable(false);
        sphere.setDisable(true);

        removeFromPane(square);
        removeFromPane(rec);

        if (circle.getParent() == null) {
            addToPane(circle);

        }
        circle.setVisible(true);
        rec.setVisible(false);
        square.setVisible(false);
    }

    private void waterMovement() {
        waterMove = !waterMove;
    }

    private void borders() {
        if (cubeObject.getCube().getLayoutX() < 245 && cubeObject.getCube().getLayoutY() + cubeObject.getCube().getHeight() > 323) {        //makes the cube stay on the platform
            cubeObject.getCube().setLayoutY(323 - cubeObject.getCube().getHeight());
        } else if (cubeObject.getCube().getLayoutX() >= 255 && cubeObject.getCube().getLayoutY() + cubeObject.getCube().getHeight() >= 665) {     //makes the cube not sink at the bottom of the pool
            cubeObject.getCube().setLayoutY(665 - cubeObject.getCube().getHeight());
        } else if (cubeObject.getCube().getLayoutX() + cubeObject.getCube().getWidth() >= 778) {                                            //makes the cube not pass into the settings
            cubeObject.getCube().setLayoutX(778 - cubeObject.getCube().getWidth());
        } else if (cubeObject.getCube().getLayoutX() >= 245 && cubeObject.getCube().getLayoutX() <= 255) {                                            //makes the cube not pass into the settings
            cubeObject.getCube().setLayoutX(256);
        } else if (cubeObject.getCube().getLayoutX() <= 0) {                                                                            //makes the cube stay in the pane on the x-axis
            cubeObject.getCube().setLayoutX(0);
        } else if (cubeObject.getCube().getLayoutY() <= 0) {                                                                            //makes the cube stay in the pane on the Y-axis
            cubeObject.getCube().setLayoutY(0);
        }

        if (prismObject.getRectangularPrism().getLayoutX() < 255 && prismObject.getRectangularPrism().getLayoutY() + prismObject.getRectangularPrism().getHeight() > 323) {
            prismObject.getRectangularPrism().setLayoutY(323 - prismObject.getRectangularPrism().getHeight());
        } else if (prismObject.getRectangularPrism().getLayoutX() >= 255 && prismObject.getRectangularPrism().getLayoutY() + prismObject.getRectangularPrism().getHeight() >= 665) {
            prismObject.getRectangularPrism().setLayoutY(665 - prismObject.getRectangularPrism().getHeight());
        } else if (prismObject.getRectangularPrism().getLayoutX() + prismObject.getRectangularPrism().getWidth() >= 778) {
            prismObject.getRectangularPrism().setLayoutX(778 - prismObject.getRectangularPrism().getWidth());
        } else if (prismObject.getRectangularPrism().getLayoutX() <= 0) {
            prismObject.getRectangularPrism().setLayoutX(0);
        } else if (prismObject.getRectangularPrism().getLayoutY() <= 0) {
            prismObject.getRectangularPrism().setLayoutY(0);
        }

        if (sphereObject.getCircle().getLayoutX() - sphereObject.getCircle().getRadius() < 255 && sphereObject.getCircle().getLayoutY() + sphereObject.getCircle().getRadius() > 323) {
            sphereObject.getCircle().setLayoutY(323 - sphereObject.getCircle().getRadius());
        } else if (sphereObject.getCircle().getLayoutX() >= 255 && sphereObject.getCircle().getLayoutY() + sphereObject.getCircle().getRadius() >= 665) {
            sphereObject.getCircle().setLayoutY(665 - sphereObject.getCircle().getRadius());
        } else if (sphereObject.getCircle().getLayoutX() + sphereObject.getCircle().getRadius() >= 778) {
            sphereObject.getCircle().setLayoutX(778 - sphereObject.getCircle().getRadius());
        } else if (sphereObject.getCircle().getLayoutX() - sphereObject.getCircle().getRadius() <= 0) {
            sphereObject.getCircle().setLayoutX(0 + sphereObject.getCircle().getRadius());
        } else if (sphereObject.getCircle().getLayoutY() - sphereObject.getCircle().getRadius() <= 0) {
            sphereObject.getCircle().setLayoutY(0 + sphereObject.getCircle().getRadius());
        }
    }

    @FXML
    private void mouseMoved(MouseEvent event) {
        if (moveShape) {
            square.setLayoutX(event.getX() - square.getWidth() / 2);
            square.setLayoutY(event.getY() - square.getWidth() / 2);

            rec.setLayoutX(event.getX() - rec.getWidth() / 2);
            rec.setLayoutY(event.getY() - rec.getHeight() / 2);

            circle.setLayoutX(event.getX());
            circle.setLayoutY(event.getY());
        }
        borders();
    }

    @FXML
    private void shapeClicked() {
        moveShape = !moveShape;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cubeObject = new Cube(square);
        prismObject = new RectangularPrism(rec);
        sphereObject = new Sphere(circle, 100);
        double waterIniHeight = water.getHeight();
        double waterBottomY = water.getLayoutY() + water.getHeight();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Timer for gravity
                double currentTime = (now - iniTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                timeSinceLastUpdate += frameDeltaTime;
                if (timeSinceLastUpdate > 0.001) {
                    borders();
                    timeSinceLastUpdate = 0;

                    // Gravity
                    if (!moveShape) {
                        int gravCube = 2;
                        int gravPrism = 2;
                        int gravSphere = 2;

                        if (waterMove && densityRatio != 0) {
                            if (!floating) {
                                gravCube = 1;
                                gravPrism = 1;
                                gravSphere = 1;

                            } else if (floating) {
                                // Check cube
                                if (cubeObject.getCube().getLayoutY() == water.getLayoutY() - ((1 - densityRatio) * cubeObject.getCube().getHeight())) {
                                    gravCube = 0;
                                } else if (cubeObject.getCube().getLayoutY() > water.getLayoutY() - ((1 - densityRatio) * cubeObject.getCube().getHeight())) {
                                    gravCube = -1;
                                } else if (cubeObject.getCube().getLayoutY() < water.getLayoutY() - ((1 - densityRatio) * cubeObject.getCube().getHeight())) {
                                    gravCube = 1;
                                }

                                // Check prism
                                if (prismObject.getRectangularPrism().getLayoutY() == water.getLayoutY() - ((1 - densityRatio) * prismObject.getRectangularPrism().getHeight())) {
                                    gravPrism = 0;
                                } else if (prismObject.getRectangularPrism().getLayoutY() > water.getLayoutY() - ((1 - densityRatio) * prismObject.getRectangularPrism().getHeight())) {
                                    gravPrism = -1;
                                } else if (prismObject.getRectangularPrism().getLayoutY() < water.getLayoutY() - ((1 - densityRatio) * prismObject.getRectangularPrism().getHeight())) {
                                    gravPrism = 1;
                                }

                                // Check sphere
                                if (sphereObject.getCircle().getLayoutY() - sphereObject.getCircle().getRadius() == water.getLayoutY() - ((1 - densityRatio) * sphereObject.getCircle().getRadius() * 2)) {
                                    gravSphere = 0;
                                } else if (sphereObject.getCircle().getLayoutY() - sphereObject.getCircle().getRadius() > water.getLayoutY() - ((1 - densityRatio) * sphereObject.getCircle().getRadius() * 2)) {
                                    gravSphere = -1;
                                } else if (sphereObject.getCircle().getLayoutY() - sphereObject.getCircle().getRadius() < water.getLayoutY() - ((1 - densityRatio) * sphereObject.getCircle().getRadius() * 2)) {
                                    gravSphere = 1;
                                }
                            }
                        }
                        cubeObject.getCube().setLayoutY(cubeObject.getCube().getLayoutY() + gravCube);
                        prismObject.getRectangularPrism().setLayoutY(prismObject.getRectangularPrism().getLayoutY() + gravPrism);
                        sphereObject.getCircle().setLayoutY(sphereObject.getCircle().getLayoutY() + gravSphere);
                        borders();
                    }

                    // Water movement
                    double waterHeightLimit = 0;
                    if (waterMove) {
                        waterHeightLimit = 175 + volVal * densityRatio * 80;
                        if (waterHeightLimit > 343) {
                            waterHeightLimit = 343;
                        }
                    } else if (!waterMove) {
                        waterHeightLimit = waterIniHeight;
                    }

                    if (water.getHeight() < waterHeightLimit) {
                        water.setHeight(water.getHeight() + 1);
                    } else if (water.getHeight() >= waterHeightLimit) {
                        water.setHeight(water.getHeight() - 1);
                    }
                    water.setLayoutY(waterBottomY - water.getHeight());
                }

                // Check if shape is touching water
                if (cube.isDisable() && cubeObject.getCube().getLayoutY() + cubeObject.getCube().getHeight() >= water.getLayoutY() && waterMove == false) {
                    waterMovement();
                } else if (rectangularPrism.isDisable() && prismObject.getRectangularPrism().getLayoutY() + prismObject.getRectangularPrism().getHeight() >= water.getLayoutY() && waterMove == false) {
                    waterMovement();
                } else if (sphere.isDisable() && sphereObject.getCircle().getLayoutY() + sphereObject.getCircle().getRadius() >= water.getLayoutY() && waterMove == false) {
                    waterMovement();
                } else if (cube.isDisable() && cubeObject.getCube().getLayoutY() + cubeObject.getCube().getHeight() < water.getLayoutY() && waterMove == true) {
                    waterMovement();
                } else if (rectangularPrism.isDisable() && prismObject.getRectangularPrism().getLayoutY() + prismObject.getRectangularPrism().getHeight() < water.getLayoutY() && waterMove == true) {
                    waterMovement();
                } else if (sphere.isDisable() && sphereObject.getCircle().getLayoutY() + sphereObject.getCircle().getRadius() < water.getLayoutY() && waterMove == true) {
                    waterMovement();
                }
                // End check

                // Get input
                objectVolume.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        objVolPrompt.setTextFill(Color.BLACK);
                        try {
                            cubeObject.getCube().setHeight(Math.cbrt(Double.parseDouble(newValue)) * 20);             //sets the height of the cube
                            cubeObject.getCube().setWidth(Math.cbrt(Double.parseDouble(newValue)) * 20);              //sets the width of the cube

                            cubeObject.getCube().setLayoutY(323 - cubeObject.getCube().getHeight());                   //makes sure that the cube always stays on the platform

                            prismObject.getRectangularPrism().setHeight(Math.cbrt(Double.parseDouble(newValue)) * 20 / 2);      //sets the height of the rectangular prism
                            prismObject.getRectangularPrism().setWidth(Math.cbrt(Double.parseDouble(newValue)) * 20);       //sets the width of the rectangular prism
                            prismObject.getRectangularPrism().setLayoutY(323 - prismObject.getRectangularPrism().getHeight()); //makes sure that the rectangular prism always stays on the platform

                            sphereObject.getCircle().setRadius(Math.cbrt(Double.parseDouble(newValue)) * 20 / 2);  //sets the size of the circle
                            sphereObject.getCircle().setLayoutY(323 - sphereObject.getCircle().getRadius());                           //makes sure that the circle always stays on the platform

                            volVal = Double.parseDouble(newValue);
                        } catch (Exception e) {
                            objVolPrompt.setTextFill(Color.RED);
                        }
                    }
                });
                objectMass.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        objMassPrompt.setTextFill(Color.BLACK);
                        try {
                            massVal = Double.parseDouble(newValue);
                        } catch (Exception e) {
                            objMassPrompt.setTextFill(Color.RED);
                        };
                    }
                });
                liquidMass.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        liqMassPrompt.setTextFill(Color.BLACK);
                        try {
                            massLiq = Double.parseDouble(newValue);
                        } catch (Exception e) {
                            liqMassPrompt.setTextFill(Color.RED);
                        };
                    }
                });
                if (volVal == 0) {
                    objVolPrompt.setTextFill(Color.RED);
                } else if (massVal == 0) {
                    objMassPrompt.setTextFill(Color.RED);
                } else if (massLiq == 0) {
                    liqMassPrompt.setTextFill(Color.RED);
                }
                // End input

                // Do calculations
                objectDensity = massVal / volVal;
                liquidDensity = massLiq / volLiq;
                densityRatio = objectDensity / liquidDensity;

                // Do Archimedes
                if (objectDensity <= liquidDensity) {
                    floating = true;
                    floatingPercentage = densityRatio * 100;
                    Archimedes = massLiq * volVal * 9.8 * densityRatio;
                } else {
                    floatingPercentage = 100;
                    floating = false;
                    Archimedes = massLiq * volVal * 9.8;
                }

                // Output: if(in water), then water changes
                if (waterMove == true) {
                    // Display
                    percentage.setText(formatter.format(floatingPercentage));
                    floatingLabel.setText("" + floating);
                    archi.setText(formatter.format(Archimedes));
                } else {
                    percentage.setText("...");
                    floatingLabel.setText("...");
                    archi.setText("...");
                }
                densityRatioLabel.setText(formatter.format(densityRatio));
                liquidDensityLabel.setText(formatter.format(liquidDensity));
                objDensityLabel.setText(formatter.format(objectDensity));
            }
        }.start();
    }
}
