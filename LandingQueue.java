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
public class LandingQueue extends Queue {
    String direction;
    
    LandingQueue(String dir){
        direction = dir;
    }
    
    Airplane find0fuelplane(){
        if(head == null)
            return null;
        
        MyNode<Airplane> tempNode = head; 
        MyNode prevNode = null;
        
        while(tempNode.getNext() != null){
            Airplane airplane = tempNode.getData();
            //if we find a value less than or equal to 0, there are 3 ways
            //to unlink an element in the singly linked list.
            if (airplane.remainingtime <=0){
                if(prevNode==null){
                    poll();
                }
                else if(tempNode.getNext()==null){
                    prevNode.setNext(null);
                    tail = prevNode;
                }
                else{
                    prevNode.setNext(tempNode.getNext());
                    tempNode.setNext(null);
                }
                return airplane;
            }
            prevNode = tempNode;   
            tempNode = tempNode.getNext();
        }
        return null;
    }
    
    String tostring(){
        String s = "";
        if(head != null){
            MyNode<Airplane> tempNode = head; 
        
            while(tempNode.getNext() != null){
                Airplane airplane = tempNode.getData();
                s += airplane.tostring();
                tempNode = tempNode.getNext();
            }
        }
        return s;   
    }
   
}
    