package populationdynamics;

/**
 * 
 * @author Lin Xiao Zheng
 */
public class PDUtils {
    static double[] survivorshipCurve;
    static double[] offspringPerAge;
    static double rate;
    static double netRepRate;
    static double meanGenTime;
    static double sexMaturity = 0.18;
    static double offspringConstant = -1;
    
    private static void setCurves(int lifespan, int type, int numOffspring){
        survivorshipCurve = new double[lifespan + 1];
        offspringPerAge = new double[lifespan + 1];
        
        switch(type){
            case 1: 
                for(int i = 0; i < survivorshipCurve.length; i++){
                    survivorshipCurve[i] = -Math.exp((Math.log(101f) / lifespan) * i) + 101f;  
//                    System.out.println(survivorshipCurve[i]);
                }
                offspringConstant = 0.1;
                break;
            case 2:
                for(int i = 0; i < survivorshipCurve.length; i++){
                    survivorshipCurve[i] = -(100f / lifespan) * i + 100f;
                }
                offspringConstant = 0.4;
                break;          
            case 3:
                for(int i = 0; i < survivorshipCurve.length; i++){
                    survivorshipCurve[i] = 13 * (- Math.log(i) + Math.log(lifespan));
                }
                survivorshipCurve[0] = 100; 
                offspringConstant = 0.7;
                break;
            default: System.out.println("survivorship switch"); break;
        }
        
        for(int k = (int) sexMaturity * lifespan; k < offspringPerAge.length; k++){
            offspringPerAge[k] = -1 / (9 * lifespan) * (k - 2 * lifespan / 3) + offspringConstant * numOffspring;
        }
        
    }

    public static void setData(int lifespan, int numOffspring, int type){
        // Survivorship Curve
        setCurves(lifespan, type, numOffspring);
        
        // Net reproductive rate
        netRepRate = 0;
        double num = 0;
        for(int i = 0; i <= lifespan; i++){
            netRepRate += survivorshipCurve[i] / 100 * offspringPerAge[i];
            num += i * survivorshipCurve[i] / 100 * offspringPerAge[i];
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
    
    public static double equation(int capacity, int initialPop, int timeStep){
        return capacity / ( 1 + ( capacity / initialPop - 1 ) * Math.exp(-rate * timeStep));
    }
    
    
}
