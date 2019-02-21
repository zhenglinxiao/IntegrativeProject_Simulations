/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populationdynamics;

import static java.lang.Math.sqrt;
import javafx.scene.shape.Polygon;

/**
 *
 * @author lxz19
 */
public class Hexagon {
    double[] points = new double[12];
    double[] center = new double[2];
    double halfHeight;
    
    
    public Hexagon(double side){
      halfHeight = getH(side);
      
      //     X                          Y
      points[0] = 0.0;           points[1] = 0.0;
      points[2] = side;          points[3] = 0.0;
      points[4] = side+(side/2); points[5] = halfHeight;
      points[6] = side;          points[7] = halfHeight * 2;
      points[8] = 0.0;           points[9] = halfHeight * 2;
      points[10] = -side/2;      points[11] = halfHeight;

    }

    private double getH(double side) {
      return ((sqrt(3)/2)*side);
    }
    public double [] getPoints(){
      return points;
    }    
}
