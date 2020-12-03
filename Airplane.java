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
public class Airplane {
    String queueName;
    int runwayNbr;
    int remainingtime;
    int IDnumber;
    int totalwaitingtime;
    static int arrivingIDnumber;
    static int departingIDnumber;
    
    static {
        arrivingIDnumber = 1;
        departingIDnumber = 2;
    }
    
    Airplane(int initialRemainingTime, int runwayNumber, String queuename){
        remainingtime = initialRemainingTime;
        IDnumber = 0;
        totalwaitingtime = 0;
        runwayNbr = runwayNumber;
        queueName = queuename;
    }
    
    boolean isArriving(){
        int remainder = IDnumber%2;
        return remainder > 0;
    }
    
    boolean isDeparting(){
        return !isArriving();
    }
    
    void setarrivingIDnum(){
        IDnumber = arrivingIDnumber;
        arrivingIDnumber +=2;
        
    }
    
    void setdepartingIDnum(){
        IDnumber = departingIDnumber;
        departingIDnumber +=2;
    }
    
    void incrementtotalwaitingtime(){
        totalwaitingtime ++;
    }
    
    void decrementwaitingtime(){
        remainingtime --;
    }
    
    String tostring(){
        String s = "Airplane ID# " + IDnumber;
        
        if(isArriving())
            s+= " Remaining time: " + remainingtime;
                    
        s+= " Total waiting time: " + totalwaitingtime +
            " Runway# " + runwayNbr + 
            " Queue: " + queueName + "\r\n";
    return s;
    }
}
