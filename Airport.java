/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timcsimaircomponentb;

/**
 *
 * @author timca
 */
public class Airport {
    
    Runway runways[];
    int numofRunways; 
    int eplanecounter;
    
    Airport(){
        numofRunways = 3;
        runways = new Runway[numofRunways];
        String[] direction = new String[2];
        direction[0] = "North";
        direction[1] = "South";
        runways[0] = new Runway(1,2,direction);
        direction[0] = "East";
        direction[1] = "West";
        runways[1] = new Runway(2,2,direction);
        runways[2] = new Runway(3,0,direction);
        eplanecounter = 0;
    }
    
    Airplane assignlandingplanetoqueue(int remainingtime){
        int minQueueLength = 999;
        int runwayNumb = -1;
        int landingQueueNumb = -1;
        
        for(int i = 0; i < numofRunways; i++){
            for(int j = 0; j < runways[i].numoflandingqueues; j++){
                int landingQueueLength = runways[i].landingqueues[j].Queuelength();
                if(landingQueueLength < minQueueLength){
                    minQueueLength = landingQueueLength;
                    runwayNumb = i;
                    landingQueueNumb = j;
                }
            }
        }
        
        Airplane airplane = new Airplane(remainingtime, runwayNumb + 1, 
            runways[runwayNumb].landingqueues[landingQueueNumb].direction);
        airplane.setarrivingIDnum();
        runways[runwayNumb].landingqueues[landingQueueNumb].offer(airplane);
        return airplane; 
    }
    
    Airplane assigntakeoffplanetoqueue(){
        int minQueueLength = 999;
        int runwayNumb = -1;
        
        for(int i = numofRunways - 1; i >= 0; i--){
            int takeOffQueueLength = runways[i].takeOffQueue.Queuelength();
            if(takeOffQueueLength < minQueueLength){
                minQueueLength = takeOffQueueLength;
                runwayNumb = i;
            }
        }
        
        Airplane airplane = new Airplane(-100, runwayNumb + 1, "takeoff");
        airplane.setdepartingIDnum();
        runways[runwayNumb].takeOffQueue.offer(airplane);
        return airplane;
    }
    
    boolean assignELandings(){
        for(int r = 0; r < numofRunways; r++){
            for(int q = 0; q < runways[r].numoflandingqueues; q++){
                Airplane eLandingPlane = runways[r].landingqueues[q].find0fuelplane();
                    
                while(eLandingPlane != null){
                    if( !assignELandingToRunWay(eLandingPlane) )
                        return false;
                    eLandingPlane = runways[r].landingqueues[q].find0fuelplane();
                    eplanecounter++;
                }           
            }   
        }
        return true;
    }
    
    boolean assignELandingToRunWay(Airplane ePlane){
        for(int r = numofRunways - 1; r >= 0; r--){
            if( !runways[r].isBusy() ){
                runways[r].setBusy(ePlane);
                return true;
            }          
        }
        return false; 
    }
    
    void assignTakeOffAndLandings(){
        for(int r = 0; r < numofRunways; r++){
            if( !runways[r].isBusy()){
                runways[r].assignTakeOffOrLanding();
            }
        }
    }
    
    void clearRunways(){
        for(int r = 0; r < numofRunways; r++){
            runways[r].setBusy(null);
        }
    }
    
    String tostring(){
        String s = "";
        
        for(int i = 0; i < numofRunways; i++){
            s += runways[i].takeOffQueue.tostring();
        }
        
        for(int i = 0; i < numofRunways; i++){
            for(int j = 0; j < runways[i].numoflandingqueues; j++){ 
                s += runways[i].landingqueues[j].tostring();
            }
        }
 
        return s;   
    }
    
    String tostringtakeoff(){
        String s = "";
        
        for(int r = 0; r < numofRunways; r++){
            if(runways[r].isBusy())
                if(runways[r].currentPlaneInRunway.isDeparting())
                    s += runways[r].currentPlaneInRunway.tostring();
        }
        
        return s;
    }
    
    String tostringlanding(){
        String s = "";
        
        for(int r = 0; r < numofRunways; r++){
            if(runways[r].isBusy())
                if(runways[r].currentPlaneInRunway.isArriving())
                    s += runways[r].currentPlaneInRunway.tostring();
        }
    
        return s;
    }
    
    float avgwaitingtimetakeoffs(){
        float totalTimeTakeOff = 0;
        float totalPlanesTakenOff = 0;
     
        for(int r = 0; r < numofRunways; r++){
            totalTimeTakeOff += runways[r].totalWaitingTimeTakeoff;
            totalPlanesTakenOff += runways[r].nbrOfPlanesTakenOff;
        }
        float avg = 0;
        if(totalPlanesTakenOff>0)
            avg = totalTimeTakeOff / totalPlanesTakenOff;
        return avg;
    }
    
    float avgwaitingtimelandings(){
        float totalTimeLanded = 0;
        float totalPlanesLanded = 0;
     
        for(int r = 0; r < numofRunways; r++){
            totalTimeLanded += runways[r].totalWaitingTimeLanding;
            totalPlanesLanded += runways[r].nbrOfPlanesLanded;
        }
        float avg = 0;
        if(totalPlanesLanded>0)
            avg = totalTimeLanded / totalPlanesLanded;
        return avg;
    }
    
    void incrwaitingtimeallrunways(){
        for(int r = 0; r < numofRunways; r++){
            runways[r].incrementwaitingtimeofallplanesinallqueues();
        }
    }
}
