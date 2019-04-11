package populationdynamics;

import static java.lang.Math.sqrt;
import java.util.Arrays;

/**
 *
 * @author Lin Xiao Zheng
 */
public class Hexagon {

    private double[] points = new double[12];
    private double[] center = new double[2]; // Initializes to [0, 0]
    private double halfHeight;
    private double side;
    private double width;
    private double height;
    private int[] coordinates = new int[2];

    public Hexagon(double side, double x, double y) { // change x, y to center and recalculate points?
        this.side = side;
        width = 2 * side;
        height = sqrt(3) * side;
        halfHeight = height / 2;
        center[0] = x;
        center[1] = y;        

        //     X                                   Y
        points[0] = x - side / 2;           points[1] = y - halfHeight;
        points[2] = x + side / 2;           points[3] = y - halfHeight;
        points[4] = x + side;               points[5] = y;
        points[6] = x + side / 2;           points[7] = y + halfHeight;
        points[8] = x - side / 2;           points[9] = y + halfHeight;
        points[10] = x - side;              points[11] = y;

//        //     X                                   Y
//        points[0] = 0.0;                    points[1] = 0.0;
//        points[2] = side;                   points[3] = 0.0;
//        points[4] = side + (side / 2);      points[5] = halfHeight;
//        points[6] = side;                   points[7] = halfHeight * 2;
//        points[8] = 0.0;                    points[9] = halfHeight * 2;
//        points[10] = -side / 2;             points[11] = halfHeight;

        shift(x, y);
    }

    public Hexagon(double side) {
        this(side, 0, 0);
    }
    
    public void setCoordinates(int q, int r){
        coordinates[0] = q;
        coordinates[1] = r;
    }

    public double getWidth(){
        return width;
    }
    
    public double getHeight() {
        return height;
    }

    public double getHalfHeight() {
        return halfHeight;
    }
    
    public double getSide(){
        return side;
    }
    
    public double[] getCenter(){
        return center;
    }
    
    public int[] getCoordinates(){
        return coordinates;
    }

    public double[] getPoints() {
        return points;
    }

    public void shift(double x, double y) {
        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0) {       // Even index: x coordinate
                points[i] += x;
            } else {                // Odd index: y coordinate
                points[i] += y;
            }
        }
        
//        center[0] += x;
//        center[1] += y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Arrays.hashCode(this.points);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hexagon other = (Hexagon) obj;
        if (!Arrays.equals(this.points, other.points)) {
            return false;
        }
        return true;
    }
    
    
}
