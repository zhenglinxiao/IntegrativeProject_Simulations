/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buoyancy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cstuser
 */
public class Sphere {
    
    private Circle circle;
    private double volume;
    private double radius;
    

    
    public Sphere(Circle c, double volume){
        circle = c;
        this.volume = volume;
        radius = Math.cbrt((3*volume)/4*Math.PI);
        //c.getCenterX();
        //c.getCenterY();
        
        
    } public Circle getCircle(){
         return circle;
     }

    
    public void setLayoutX(double x){
        circle.setLayoutX(x);
    }
    
    public void setLayoutY(double y){
        circle.setLayoutY(y);
    }
    
    public double getSphereX(){
         return circle.getCenterX();
     }
    
     public double getSphereY(){
         return circle.getCenterY();
     }
  
    public void setRadius(double radius){
        this.radius = radius;
    }
}