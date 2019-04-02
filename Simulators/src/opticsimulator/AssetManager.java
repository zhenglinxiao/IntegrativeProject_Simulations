package opticsimulator;


import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.ImagePattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cstuser
 */
public class AssetManager {
    // create imagePatterns and background variables
    static private Background backgroundImage = null;
    static private ImagePattern candleImage = null;
    static private ImagePattern lensImage = null;
    static private ImagePattern invertedCandle = null;
    static private ImagePattern uprightCandle = null;
    static private ImagePattern canImage = null;
    static private ImagePattern invertedCan = null;
    static private ImagePattern uprightCan = null;
    static private ImagePattern pencilImage = null;
    static private ImagePattern invertedPencil = null;
    static private ImagePattern uprightPencil = null;
    
    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }
    
    // when calling this method, the imagePatterns and background will be loaded
    static public void preloadAllAssets(){
        // Preload all images
        Image background = new Image(fileURL("./assets/images/whitebackground.png"));
        backgroundImage = new Background(
                            new BackgroundImage(background, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
        
        // link the variables to the picture that are saved in the target file
        candleImage = new ImagePattern(new Image(fileURL("./assets/images/candleTransparent.png")));
        lensImage = new ImagePattern(new Image(fileURL("./assets/images/convergingLens.png")));
        invertedCandle = new ImagePattern(new Image(fileURL("./assets/images/invertedCandle.png")));
        uprightCandle = new ImagePattern(new Image(fileURL("./assets/images/uprightCandle.png")));
        canImage = new ImagePattern(new Image(fileURL("./assets/images/can.png")));
        invertedCan = new ImagePattern(new Image(fileURL("./assets/images/invertedCan.png")));
        uprightCan = new ImagePattern(new Image(fileURL("./assets/images/uprightCan.png")));
        pencilImage = new ImagePattern(new Image(fileURL("./assets/images/pencil.png")));
        invertedPencil = new ImagePattern(new Image(fileURL("./assets/images/invertedPencil.png")));
        uprightPencil = new ImagePattern(new Image(fileURL("./assets/images/uprightPencil.png")));
    }
    
    // get methods are used to get specific image patterns or background
    static public Background getBackgroundImage(){
        return backgroundImage;
    }
    
    static public ImagePattern getCandleImage(){
        return candleImage;
    }
    
     static public ImagePattern getLensImage(){
         return lensImage;
     }
     
     static public ImagePattern getInvertedCandleImage(){
         return invertedCandle;
     }
     
     static public ImagePattern getUprightCandleImage(){
         return uprightCandle;
     }
     
     static public ImagePattern getCanImage(){
         return canImage;
     }
     
     static public ImagePattern getInvertedCanImage(){
         return invertedCan;
     }
     
     static public ImagePattern getUprightCanImage(){
         return uprightCan;
     }
     
     static public ImagePattern getPencilImage(){
         return pencilImage;
     }
     
     
     static public ImagePattern getInvertedPencilImage(){
         return invertedPencil;
     }
     
     static public ImagePattern getUprightPencilImage(){
         return uprightPencil;
     }

}
