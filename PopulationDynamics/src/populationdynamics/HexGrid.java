/*
 * Change hex fill
 */
package populationdynamics;

/**
 *
 * @author Lin Xiao Zheng
 */
public class HexGrid {
    
    double hexSide; // set default / final
    int gridSize;   
    double[] center = new double[2];
    Hexagon[][] grid;
    
    /*
        SPACING
           1
         2   3
           4
         5   6
           7
    
    In relation to center (4)
            X           Y          Axial Coordinates
    1:      0          -h               (0, -1)
    2:   -w * 3/4      -hh              (-1, 0)
    3:   +w * 3/4      -hh              (+1, -1)
    4:      0           0               (0, 0)
    5:   -w * 3/4      +hh              (-1, +1)
    6:   +w * 3/4      +hh              (+1, 0)
    7:      0          +h               (0, +1)
    
        ITERATION
    for p: -1, 1
        for r: -1, 1
            switch(p)
                switch(r)
            if !Arrays.asList(hexGrid).contains(new Hexagon(side, x, y)
                create hexagon
                add to hexGrid array
                recursive iteration call
    */
    
    
    
}
