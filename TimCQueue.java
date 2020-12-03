
package timcsimaircomponentb;

/**
 *
 * @author timca
 */
public class TimCQueue<E> implements IQueue<E>{
    MyNode<E>head;
    MyNode<E>tail;
    
    public TimCQueue(){
        head = null;
        tail = null;
    }
    
    @Override
    public boolean offer(E data) {
        MyNode<E> node = new MyNode<>(data, null);
        //if empty queue
        if(tail==null){
            tail = node;
            head = node;
            return true;
        }
        //head info passed to the previous node
        tail.setNext(node);
        //sets new node = info passed in
        tail = node;
        return true;
    }

    @Override
    public E poll() {
        if(head==null){
            return null;
        }
        MyNode<E> tempNode = head;
        head = head.getNext();        
        if(head==null)
            tail = null;
        tempNode.setNext(null);
        return tempNode.getData();
    }

    @Override
    public E peek() {
        if(head == null){
            return null;
        }
        return head.getData();
        //check to see if queue is empty. return null if so
        //otherwise return head.getData()
    }

    @Override
    public E element() throws timNoSuchElementException {
        if(head == null){
            throw new timNoSuchElementException("Queue is empty");
        }
        return peek();
        //check to see if queue is empty. if so, throw timnosucheleexcept
        //return peek()
    }

    @Override
    public E remove() throws timNoSuchElementException {
        if(head == null){
            throw new timNoSuchElementException("Queue is empty");
        }
        return poll();
        
        //check to see if empty. if so, throe timNosucheleexce
        //then return poll()
    }
    
}
