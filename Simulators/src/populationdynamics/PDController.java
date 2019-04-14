
package populationdynamics;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author Lin Xiao Zheng
 */
public class PDController implements Initializable {
    
    private ArrayList<Polygon> hexGrid = new ArrayList();
    private boolean isPreset = true;
    private DecimalFormat integer = new DecimalFormat("#");
    private DecimalFormat twoDecimals = new DecimalFormat("#.##");
    long initialTime = System.nanoTime();
    double lastFrameTime = 0.0f;
    double timeSinceLastUpdate = 0;
    Series pop = new Series();
    int timeStep = 0;
    int hexIndex = 0;
    double popRemainder = 0;
    double lastPopulation = 0;
    String name;
    ArrayList<AnimalPreset> presetList = new ArrayList();
    
    private void resetTimer(){
        pop.getData().clear();        
        lastFrameTime = 0.0f;
        timeSinceLastUpdate = 0;
        timeStep = 0;
        hexIndex = 0;
        popRemainder = 0;
        lastPopulation = 0;
    }
    
    private AnimationTimer timer = new AnimationTimer(){
        @Override
        public void handle(long now){
            // Time calculations
            double currentTime = (now - initialTime) / 1000000000.0;
            double frameDeltaTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime; 
            
            timeSinceLastUpdate += frameDeltaTime;
            if(timeSinceLastUpdate > 1.0){
                // Graph update
                double population = PDUtils.equation((int)capacity.getValue(), (int)initialPop.getValue(), timeStep);
                pop.getData().add(new Data(timeStep, population));
     
                
                // HexGrid update
                double popPerHex = capacity.getValue() / hexGrid.size();
                double popIncrease = population - lastPopulation;
                int hexUsed = (int)((popIncrease + popRemainder) / popPerHex);
                popRemainder = (popIncrease + popRemainder) - hexUsed * popPerHex;
                
                hexTimeStep.setText("Year " + timeStep);
                hexEquals.setText("1 hex ~ " + integer.format(popPerHex) + " " +  name + "s");
                
                for(int i = 0; i < hexUsed; i++){
                    hexGrid.get(hexIndex).setFill(Color.AQUA);
                    hexIndex++;
                }
                
                if(Math.ceil(population) >= capacity.getValue()){
                    hexGrid.get(hexGrid.size() - 1).setFill(Color.AQUA);
                    timer.stop();
                }    
                
                timeStep++;
                timeSinceLastUpdate = 0.0f;
                lastPopulation = population;
            }
        }
    };
    
    @FXML
    private LineChart survivorship;
    
    @FXML
    private LineChart popVsTime;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private AnchorPane optionsBox;
    
    @FXML
    private VBox dataBox;
    
    @FXML
    private ToggleGroup preset;
    
    @FXML
    private TextField speciesName;
    
    @FXML
    private ComboBox selectPreset;
    
    @FXML
    private ComboBox survivorshipType;
    
    @FXML
    private Slider capacity;
    
    @FXML
    private Slider lifespan;
    
    @FXML
    private Slider offspring;
    
    @FXML
    private Slider initialPop;
    
    @FXML
    private Label hexTimeStep;
    
    @FXML 
    private Label intrinsicRate;
    
    @FXML
    private Label netRepRate;
    
    @FXML
    private Label meanGenTime;
    
    @FXML
    private Label capacityLabel;
    
    @FXML
    private Label lifespanLabel;
    
    @FXML
    private Label offspringLabel;
    
    @FXML 
    private Label initialPopLabel;
    
    @FXML
    private Label hexEquals;
    
    @FXML
    private Button start;
    
    @FXML
    private Button restart;
    
    @FXML
    private Button helpButton;
    
    @FXML
    private void switchToHelp(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("UDFXML.fxml"));
        
        Scene help = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setTitle("Population Dynamics Simulator - Help");
//        window.getIcons().add(new Image("file:./assets/images/Basic_Alien.png"));
        window.setScene(help);
        window.show();
    }
    
    @FXML
    private void presetRadioButton(ActionEvent event){
        selectPreset.setDisable(false);
        dataBox.setDisable(true);
        isPreset = true;
    }
    
    @FXML
    private void createRadioButton(ActionEvent event){
        dataBox.setDisable(false);
        selectPreset.setDisable(true);
        isPreset = false;
    }
    
    @FXML
    private void bounds(ActionEvent event){
        survivorshipType.getValue();
        int type = determineType((String)survivorshipType.getValue());
        switch(type){
            case 1: lifespan.setMin(10); lifespan.setMax(200); 
                    offspring.setMax(5); break;
            case 2: lifespan.setMin(5); lifespan.setMax(100);
                    offspring.setMax(15); break;
            case 3: lifespan.setMin(1);lifespan.setMax(20);
                    offspring.setMin(50); offspring.setMax(500); break;
            default: System.out.println("PDController 220");break;
        }
    }
    
    @FXML
    private void startSimulation(ActionEvent event){
        // Switch buttons
        restart.setVisible(true);
        restart.setDisable(false);
        start.setVisible(false);
        start.setDisable(true);
        
        // Disable options
        optionsBox.setDisable(true);
        
        // Output data
        name = "";
        if(!isPreset){
            if(survivorshipType.getValue() == null){
                survivorshipType.setValue("Type 1");
            }
            PDUtils.setData((int)lifespan.getValue(), (int)offspring.getValue(), determineType((String)survivorshipType.getValue()));
            if(speciesName.getText().isEmpty()){
                name = "Species #" + (int)(Math.random() * 50);
            }
            else{
                name = speciesName.getText();
            }
        }
        else{
            if(selectPreset.getValue() == null){
                selectPreset.setValue("Dog");
            }
            name = (String)selectPreset.getValue();
            AnimalPreset species = presetList.get(presetList.indexOf(new AnimalPreset(name, 0, 0, 0, 0, 0)));
            PDUtils.setData(species.getLifespan(), species.getOffspring(), species.getType());
            
            lifespanLabel.setText(species.getLifespan() + "");
            lifespan.setValue(species.getLifespan());
            offspringLabel.setText(species.getOffspring() + "");
            offspring.setValue(species.getOffspring());
            survivorshipType.setValue("Type " + species.getType());
            speciesName.setText(species.getName());
            initialPopLabel.setText(species.getIniPop() + "");
            initialPop.setValue(species.getIniPop());
            capacityLabel.setText(species.getCapacity() + "");
            capacity.setValue(species.getCapacity());
        }
        intrinsicRate.setText(twoDecimals.format(PDUtils.getRate()));
        netRepRate.setText(twoDecimals.format(PDUtils.getNetRepRate()));
        meanGenTime.setText(twoDecimals.format(PDUtils.getMeanGenTime()));        
        
        survivorship.setTitle("Survivorship curve of " + name); 
        Series s = new Series();
        for(int i = 0; i < PDUtils.getSurvivorshipCurve().length; i++){
            s.getData().add(new Data(i, PDUtils.getSurvivorshipCurve()[i]));
        }
        survivorship.getData().add(s);

        popVsTime.setTitle("Population per year of " + name); 
        popVsTime.getData().add(pop);
        popVsTime.setLegendVisible(false);
        timer.start();       
    }
    
    @FXML
    private void restartSimulation(ActionEvent event){
        timer.stop();
        
        // Switch buttons
        restart.setVisible(false);
        restart.setDisable(true);
        start.setVisible(true);
        start.setDisable(false); 
        
        // Enable options
        optionsBox.setDisable(false);
        
        // Reset numerical outputs
        intrinsicRate.setText("");
        netRepRate.setText("");
        meanGenTime.setText("");
        
        // Reset graphs
        survivorship.getData().clear();
        popVsTime.getData().clear();
        resetTimer();
        
        // Reset hex grid
        for(Polygon p: hexGrid){
            p.setFill(Color.TRANSPARENT);
        }
    }
    
    private int determineType(String str){
        int returnValue = -1;
        switch(str){
            case "Type 1": returnValue = 1; break;
            case "Type 2": returnValue = 2; break;
            case "Type 3": returnValue = 3; break;
            default: System.out.println("PDController 308"); break;
        }
        return returnValue;
    }
    
    private void addToPane(Node node) {
        pane.getChildren().add(node);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ComboBox elements
        presetList.add(new AnimalPreset("Human", 1, 79, 1, 30, 400));
        presetList.add(new AnimalPreset("Dog", 2, 12, 5, 10, 1000));
        presetList.add(new AnimalPreset("African Elephant", 1, 65, 1, 50, 500));
        presetList.add(new AnimalPreset("Galapagos Tortoise", 1, 100, 10, 5, 300));
        presetList.add(new AnimalPreset("Rabbit", 2, 2, 6, 10, 800));
        presetList.add(new AnimalPreset("Lion", 1, 12, 3, 5, 300));
        presetList.add(new AnimalPreset("Goldfish", 3, 8, 500, 2, 1000));
        presetList.add(new AnimalPreset("Blue Jay", 2, 7, 5, 2, 200));
        
        ArrayList<String> animalList = new ArrayList();
        for(AnimalPreset p: presetList){
            animalList.add(p.getName());
        }
        selectPreset.getItems().addAll(animalList);
        
        String[] types = {"Type 1", "Type 2", "Type 3"};
        survivorshipType.getItems().addAll(Arrays.asList(types));
        
        // Update slider value labels
        capacity.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                capacityLabel.setText(integer.format(capacity.getValue()));
            }
        });
        
        lifespan.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                lifespanLabel.setText(integer.format(lifespan.getValue()));
            }
        });        

        offspring.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                offspringLabel.setText(integer.format(offspring.getValue()));
            }
        });    
        
        initialPop.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                initialPopLabel.setText(integer.format(initialPop.getValue()));
            }
        }); 

        // Graphs
        survivorship.getXAxis().setLabel("time (yrs)");
        survivorship.getYAxis().setLabel("% surviving population");
        
        popVsTime.getXAxis().setLabel("time (yrs)");
        popVsTime.getYAxis().setLabel("population");
        
        // Create HexGrid
        Hexagon center = new Hexagon(30d, 480, 120);
        
        HexGrid test = new HexGrid(center, 4);
        ArrayList<Hexagon> hexPoints = test.getHexGrid();
        
        for(Hexagon h: hexPoints){
            Polygon temp = new Polygon(h.getPoints());
            temp.setFill(Color.TRANSPARENT);
            temp.setStroke(Color.BLACK);
            hexGrid.add(temp);
            addToPane(temp);
        }
        
    }
    
}
