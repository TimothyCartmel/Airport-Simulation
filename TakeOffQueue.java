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
public class TakeOffQueue extends Queue {

    TakeOffQueue(){
        
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
