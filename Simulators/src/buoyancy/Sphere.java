
package buoyancy;

import javafx.scene.shape.Circle;


public class Sphere {
    
    private Circle circle;
    private double volume;
    private double mass;
    private double radius;
    

    
    public Sphere(Circle c, double volume){
        circle = c;
        this.volume = volume;
        radius = Math.cbrt((3*volume)/4*Math.PI);
        
        
        
    } public Circle getCircle(){
        
         return circle;
         
     }

    
    public void setLayoutX(double x){
        circle.setLayoutX(x);
    }
    
    public void setLayoutY(double y){
        circle.setLayoutY(y);
    }
    
    public void setVolume(double volume){
        this.volume = volume;
    }
    
    public void setMass(double mass){
        this.mass = mass;
    }
    
    public double getRadius(){
        return getRadius();
    }
    
    public double getSphereX(){
         return circle.getCenterX();
     }
    
     public double getSphereY(){
         return circle.getCenterY();
     }
     public double getVolume(){
         return volume;
     }
     
     public double getMass(){
         return mass;
     }
    public void setRadius(double radius){
        this.radius = radius;
    }
    
 
}