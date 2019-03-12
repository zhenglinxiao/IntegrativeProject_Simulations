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
    
    private static void setSurvivorshipCurve(int lifespan, int type){
        survivorshipCurve = new double[lifespan];
        
        switch(type){
            case 1: 
                for(int i = 0; i < survivorshipCurve.length; i++){
                    survivorshipCurve[i] = - Math.exp(Math.log(101) / lifespan) * i + 101;
                };break;
            case 2:
                for(int i = 0; i < survivorshipCurve.length; i++){
                    survivorshipCurve[i] = (-100 / lifespan) * i + 100;
                };break;          
            case 3:
                for(int i = 0; i < survivorshipCurve.length; i++){
                    survivorshipCurve[i] = 13 * (- Math.log(i) + Math.log(lifespan));
                };
                survivorshipCurve[0] = 100; break;
            default: System.out.println("survivorship switch"); break;
        }
    }

    public static void setData(int lifespan, int numOffspring, int type){
        // Survivorship Curve
        setSurvivorshipCurve(lifespan, type);
        
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
