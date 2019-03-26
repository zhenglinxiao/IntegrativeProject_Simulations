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
    static private Background backgroundImage = null;
    static private ImagePattern candleImage = null;
    static private ImagePattern lensImage = null;
    static private ImagePattern invertedCandle = null;
    static private ImagePattern uprightCandle = null;
    
    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }
    
    static public void preloadAllAssets(){
        // Preload all images
        Image background = new Image(fileURL("./assets/images/whitebackground.png"));
        backgroundImage = new Background(
                            new BackgroundImage(background, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
        
        candleImage = new ImagePattern(new Image(fileURL("./assets/images/candleTransparent.png")));
        lensImage = new ImagePattern(new Image(fileURL("./assets/images/convergingLens.png")));
        invertedCandle = new ImagePattern(new Image(fileURL("./assets/images/invertedCandle.png")));
        uprightCandle = new ImagePattern(new Image(fileURL("./assets/images/uprightCandle.png")));
    }
    
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
}
