package opticsimulator;


import javafx.scene.shape.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cstuser
 */
public class Object {
    private Rectangle rectangle;
    private double centerX;
    private double centerY;
    private double width;
    private double height;
    
    public Object(double x, double y, double w, double h){
        this.centerX = x;
        this.centerY = y;
        this.width = w;
        this.height = h;
        
        rectangle = new Rectangle(x, y, width, height);    
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
