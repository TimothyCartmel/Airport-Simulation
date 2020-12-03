
package timcsimaircomponentb;

/**
 *
 * @author timca
 */
public class MyNode<E> {
    private E data;
    private MyNode<E> next;

    public MyNode(E data, MyNode<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public MyNode<E> getNext() {
        return next;
    }

    public void setNext(MyNode<E> next) {
        this.next = next;
    }
    
}
