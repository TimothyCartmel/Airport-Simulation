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
public class Runway {
    int totalWaitingTimeLanding;
    int totalWaitingTimeTakeoff;
    int nbrOfPlanesLanded;
    int nbrOfPlanesTakenOff;
    
    int nextQueueNum;
    int runwayNum; 
    TakeOffQueue takeOffQueue;
    int numoflandingqueues;
    LandingQueue landingqueues[];
    Airplane currentPlaneInRunway;
    
    Runway(int runwayNumber, int numberLandingQueues, String direction[]){
        totalWaitingTimeLanding = 0;
        totalWaitingTimeTakeoff = 0;
        nbrOfPlanesLanded = 0;
        nbrOfPlanesTakenOff = 0;
    
        runwayNum = runwayNumber;
        numoflandingqueues = numberLandingQueues;
        currentPlaneInRunway = null;
        nextQueueNum = 0;
        takeOffQueue = new TakeOffQueue(); 
        landingqueues = new LandingQueue[numoflandingqueues];
        
        for(int j = 0; j < numoflandingqueues; j++){
            landingqueues[j] = new LandingQueue(direction[j]);
        }
    }
    
    boolean isBusy(){
        return currentPlaneInRunway != null;
    }
    
    void setBusy(Airplane plane){
        currentPlaneInRunway = plane;
        
        if(plane != null){
            if(plane.isDeparting()){
                totalWaitingTimeTakeoff += plane.totalwaitingtime;
                nbrOfPlanesTakenOff++;
            }    
            else{
                totalWaitingTimeLanding += plane.totalwaitingtime;
                nbrOfPlanesLanded++;
            }
        }
    }
    
    void incrementwaitingtimeofallplanesinallqueues(){
        for(int i = 0; i < numoflandingqueues; i++){
            landingqueues[i].incrementwaitingtimeallplanes();
        }
        
        takeOffQueue.incrementwaitingtimeallplanes();
    }    
    
    void assignTakeOffOrLanding(){
        //round robin queue selection   
        for(int a = 0; a < numoflandingqueues + 1; a++){
            Queue queue;
            if(nextQueueNum < numoflandingqueues)
                queue = landingqueues[nextQueueNum];
            else
                queue = takeOffQueue;
            
            nextQueueNum ++;    
            if(nextQueueNum > numoflandingqueues)
                nextQueueNum = 0;
            
            if( !queue.isEmpty() ){
                Airplane planetakenoffqueue = queue.poll();
                setBusy(planetakenoffqueue);
                break;
            }
        }
    }
}
