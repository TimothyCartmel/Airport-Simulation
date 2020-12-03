/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timcsimaircomponentb;

/**
 *
 * @author timca
 * @param <E>
 */
public interface IQueue<E> {
    boolean offer(E data);
    E poll();
    E peek();
    E element()throws timNoSuchElementException;
    E remove()throws timNoSuchElementException; 
}
