package populationdynamics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Lin Xiao Zheng
 */
public class HexGrid {
    
    private double hexSide;
    private int gridSize;   
    private double[] center = new double[2];
    private ArrayList<Hexagon> grid = new ArrayList();
    private double width;
    private double height;
    private double halfHeight;
    
    private final int[][] axials = { {0, -1}, {-1, 0}, {1, -1}, {-1, 1}, {1, 0}, {0, 1} };
    
    /*
        SPACING
           1
         2   3
           4
         5   6
           7
    */
    
    public HexGrid(Hexagon centerHex, int gridSize){
        width = centerHex.getWidth();
        height = centerHex.getHeight();
        halfHeight = centerHex.getHalfHeight();
        hexSide = centerHex.getSide(); 
        this.gridSize = gridSize;
        centerHex.setCoordinates(0, 0);
        grid.add(centerHex);
        createGrid(centerHex);
    }
    
    public HexGrid(double[] center, double hexSide, int gridSize){
        this(new Hexagon(hexSide, center[0], center[1]), gridSize);
    }
    
    private void createGrid(Hexagon centerHex){
        double[] centerPoint = centerHex.getCenter();
        int[] centerCoor = centerHex.getCoordinates();
        
        if(maxCoordinate(centerCoor[0], centerCoor[1], 0 - centerCoor[0] - centerCoor[1]) < gridSize){
            
            for(int[] c: axials){
                
                double x = centerPoint[0] + c[0] * width * 3 / 4 * 1 / 2;
                double y = 0;
                switch(c[0]){
                    case 0: y = centerPoint[1] + c[1] * height / 2; break;
                    case -1: switch(c[1]){
                        case 0: y = centerPoint[1] - halfHeight / 2; break;
                        case 1: y = centerPoint[1] + halfHeight/ 2; break;
                        default: System.out.println("axials switch error 1"); break;
                    }; break;
                    case 1: switch(c[1]){
                        case -1: y = centerPoint[1] - halfHeight / 2; break;
                        case 0: y = centerPoint[1] + halfHeight / 2; break;
                        default: System.out.println("axials switch error 2"); break;
                    }; break;
                    default: System.out.println("axials switch error 3"); break;
                }

                Hexagon lastHex = new Hexagon(hexSide, x, y);
                lastHex.setCoordinates(c[0] + centerCoor[0], c[1] + centerCoor[1]);
                                
                if(!grid.contains(lastHex)){
                    grid.add(lastHex);
                    createGrid(lastHex);
                }
            }
        }
    }

    public ArrayList<Hexagon> getHexGrid(){
        return grid;
    }
    
    private int maxCoordinate(int x, int y, int z){
        ArrayList<Integer> list = new ArrayList();
        list.add(Math.abs(x));
        list.add(Math.abs(y));
        list.add(Math.abs(z)); 
        
        return Collections.max(list);
    }
    
}
