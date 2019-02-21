/*
 * TO DO: survivorship curve
 * 
 * 
 */
package populationdynamics;

/**
 * 
 * @author Lin Xiao Zheng
 */
public class PDUtils {
    static double[] survivorshipCurve;
    static double rate;
    static double netRepRate;
    static double meanGenTime;
    
    private static void setSurvivorshipCurve(int type){
        switch(type){
            case 1:
            case 2:
            case 3:
            default: // EXCEPTION 
        }
    }

    public static void setData(int lifespan, int numOffspring, int type){
        // Survivorship Curve
        setSurvivorshipCurve(type);
        
        // Net reproductive rate
        netRepRate = 0;
        double num = 0;
        for(int i = 0; i <= lifespan; i++){
            netRepRate += survivorshipCurve[i] * numOffspring;
            num += i * survivorshipCurve[i] * numOffspring;
        }

        // Mean generation time
        meanGenTime = num / netRepRate;
        
        // Intrinsic rate of natural increase
        rate = Math.log(netRepRate) / meanGenTime;
    }
    
    public static double getRate(){
        return rate;
    }
    
    public static double getNetRepRate(){
        return netRepRate;
    }
    
    public static double getMeanGenTime(){
        return meanGenTime;
    }
    
    public static double[] getSurvivorshipCurve(){
        return survivorshipCurve;
    }
    
    public static double equation(double rate, int capacity, int initialPop, int timeStep){
        return capacity / ( 1 + ( capacity / initialPop - 1 ) * Math.exp(-rate * timeStep));
    }
    
    
}
