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
public class Queue extends TimCQueue<Airplane> {
    
    void incrementwaitingtimeallplanes(){
        
        if(head != null){
            MyNode<Airplane> tempNode = head; 
            
            do{
                Airplane airplane = tempNode.getData();
                airplane.incrementtotalwaitingtime();
                airplane.decrementwaitingtime();
                tempNode = tempNode.getNext();
            }
            while(tempNode != null);  
        }
    }
    
    int Queuelength(){
        int length = 0;
        if(head != null){
            MyNode tempNode = head; 
            length++;
            
            while(tempNode.getNext() != null){
                tempNode = tempNode.getNext();
                length++;
            }
        }
        return length;
    }
    
    boolean isEmpty(){
        return head == null;
    }
}
