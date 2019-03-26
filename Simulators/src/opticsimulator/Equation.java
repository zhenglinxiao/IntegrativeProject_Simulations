/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticsimulator;

/**
 *
 * @author cstuser
 */
public class Equation {
    
    // take curvature radius and refraction index as parameters and return focal length
    // n represents refraction index
    // r represents curvature radius
    // This method is based on the Lens Maker's equation
    public double focalLength(double n, double r){
        double focalReciprocal = (n - 1) * (2 / r);
        double focal = 1 / focalReciprocal;
        return focal;
    }
    
    // take focal length and object position as parameters and return image position
    // f represents focal length
    // p represents object position
    // q represents image position
    // This method is based on the thin lens equation
    public double imagePosition(double f, double p){
        double q = 1 / (1 / f - 1 / p);
        return q;
    }
    
    // take object and image positions as parameters and return linear magnification
    // p represents object position
    // q represents image position
    // m represents magnification
    // This method is based on the magnification equation
    public double magnification(double p, double q){
        double m = -q / p;
        return m;
    }
    
    // take magnificatiion and object height as parameters and return the image height
    // m represents magnification
    // oh represents object height
    // ih represents image height
    public double imageHeight(double m, double oh){
        // when magnification is negative, take its absolute value
        if (m < 0){
            m = -m;
        }
        double ih = m * oh;
        return ih;
    }
}
