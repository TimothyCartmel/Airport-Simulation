/*
Timothy Cartmel
This program simulates airplanes taking off and 
landings involving queues
 */
package timcsimaircomponentb;

/**
 *
 * @author timca
 */
import java.util.Scanner;

public class TimCSimAirComponentBDriver {
    
    public static void main(String[] args){
        
        Airport airport = new Airport();
        Scanner input = new Scanner(System.in);
    
        int nbrOfTimeUnits = input.nextInt();
        for(int t = 1; t <= nbrOfTimeUnits; t++){
            int nbrOfTakeOffPlanes = input.nextInt();
            
            for(int p = 0; p < nbrOfTakeOffPlanes; p++){
                Airplane airplanet = airport.assigntakeoffplanetoqueue();
                System.out.println("Arriving plane: ID# " + airplanet.IDnumber + "    Assign to takeOff queue, runway#: " + airplanet.runwayNbr);
            }
        
            int nbrOfLandingPlanes = input.nextInt();
            for(int l = 0; l < nbrOfLandingPlanes; l++){
                int remainingTimeNbr = input.nextInt();
                Airplane airplanel = airport.assignlandingplanetoqueue(remainingTimeNbr);
                System.out.println("Landing plane: ID# " + airplanel.IDnumber + "   Remaining time: " + airplanel.remainingtime 
                    + "   Assigned to landing runway#/queue " + airplanel.runwayNbr + " " + airplanel.queueName); 
            }
            
            if( !airport.assignELandings()){
                System.out.println("There are too many emergency landings for the airport");
                System.exit(1);
            }
            
            airport.assignTakeOffAndLandings();
            System.out.println("Planes taking off at time unit " + t + ": ");
            System.out.println(airport.tostringtakeoff()); // List of planes taking off, the plane id, total_waiting_time and the assigned queue
            System.out.println("Planes landing at time unit " + t + ": ");
            System.out.println(airport.tostringlanding()); // List of planes landing, the plane id, remaining_flying_time, total_waiting_time and queue
            
            //final output
            if(t % 5 == 0){
                System.out.println(airport.tostring()); // the contents of the four landing and three takeoff queues 
                System.out.println("Accum avg takeoff waiting time: " + airport.avgwaitingtimetakeoffs()); // accumulated average takeoff waiting time (for planes that have taken off)
                System.out.println("Accum avg landing waiting time: " + airport.avgwaitingtimelandings()); // the accumulated average landing waiting time (for planes that have landed)
                System.out.println("Total E landings: " + airport.eplanecounter); // the number of emergency landing
            }
            
            airport.incrwaitingtimeallrunways();
            airport.clearRunways();
        }    
    }
    //read in time units
    //create for loop with it running as many times as the read time units
    //read in first num on 2nd row
    //in airport class, call assigntakeoff for the number of times that you read in the first num on first row
    //read in 2nd num on 2nd row
    //create for loop that reads in ints using nextInt, in airport class, call assignlanding plane with it running as many time as the 2nd num on 2nd row
    //check for elandings using the method find0fuelplane
    //land or takeoff a plane for each runway(need to create the method still)
    //increment waiting time
}
