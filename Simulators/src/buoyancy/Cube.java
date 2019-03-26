/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buoyancy;

import javafx.scene.shape.Rectangle;
import sun.net.www.content.audio.x_aiff;

/**
 *
 * @author cstuser
 */
public class Cube {
    
    private Rectangle cube;
    private double volume;
    private double height;
    private double width;
  
    
     public Cube(Rectangle cu, double volume) {
        cube = cu;
        this.volume = volume;  
        height = Math.cbrt(volume);
        width = Math.cbrt(volume);       
        
     }   
        
     public Rectangle getCube(){
         return cube;
     }

    public void setX(double x) {
        cube.setX(x);
    }

    public void setY(double y) {
       cube.setY(y);
    }

    public double getX() {
        return cube.getX();
    }

    public double getY() {
        return cube.getY();
    }
    
}
