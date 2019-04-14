
package buoyancy;

import javafx.scene.shape.Rectangle;

public class RectangularPrism {
    
    private Rectangle RectangularPrism;
    private double volume;
    private double mass;
    private double height;
    private double width;
    
    
    public RectangularPrism(Rectangle r, double volume,double width, double height, double X, double Y){
        RectangularPrism = r;
        this.volume = volume;
        RectangularPrism.setLayoutX(X);
        RectangularPrism.setLayoutY(Y);
        RectangularPrism.setWidth(width);
        RectangularPrism.setHeight(height);
    }
    
    public RectangularPrism(Rectangle r){
       RectangularPrism = r;
       RectangularPrism.getHeight();
       RectangularPrism.getWidth();
     
    }
    
    public Rectangle getRectangularPrism(){
         return RectangularPrism;
     }

    public void setLayoutX(double x){
        RectangularPrism.setLayoutX(x);
    }
    
    public void setWidth(double width){
        this.width = width;
    }
    
    public void setHeight(double height){
        this.height = height;
    }
    
    public void setVolume(double volume){
        this.volume = volume;
    }
    
    public void setMass(double mass){
        this.mass = mass;
    }
    
    public void setLayoutY(double y){
        RectangularPrism.setLayoutY(y);
    }

    public double getLayoutX() {
        return RectangularPrism.getLayoutX();
    }

    public double getLayoutY() {
        return RectangularPrism.getLayoutY();
    }
    
    public double getVolume(){
        return volume;
    }
    
    public double getMass(){
        return mass;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
}
