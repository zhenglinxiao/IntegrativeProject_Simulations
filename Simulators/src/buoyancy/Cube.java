
package buoyancy;

import javafx.scene.shape.Rectangle;

public class Cube {
    
    private Rectangle cube;
    private double mass;
    private double volume;
    private double height;
    private double width;
  
    
     public Cube(Rectangle cu, double volume) {
        cube = cu;
        this.volume = volume;  
        height = Math.cbrt(volume);
        width = Math.cbrt(volume);  
        cube.getLayoutX();
        cube.getLayoutY();
        
        
     }   
     
     public Cube(Rectangle cu){
         this(cu, 100);
         cube.getLayoutX();
         cube.getLayoutY();
     }
        
     public Rectangle getCube(){
         return cube;
     }

    public void setLayoutX(double x) {
        cube.setLayoutX(x);
    }
    
    public void setVolume(double volume){
        this.volume = volume;
    }
    
    public void setMass(double mass){
        this.mass = mass;
    }

    public void setLayoutY(double y) {
       cube.setLayoutY(y);
    }

    public double getLayoutX() {
        return cube.getLayoutX();
    }

    public double getLayoutY() {
        return cube.getLayoutY();
    }
    
    public double getVolume(){
        return volume;
    }
    
    public double getMass(){
        return mass;
    }
    
}
