
/**
 *  BoundedBuffer1
 *  
 *
 * @author (Pete Fennell)
 * @version (04192019)
 */

public class BoundedBuffer1<B> {
    ArrayQueue<B> aq; // bounded buffer is implemented in aq
    /** Constructor: boundedbuffer of max size n */ 
    public BoundedBuffer1(int n) {
        aq= new ArrayQueue<B>(n); }

    /** Put object into the bounded buffer */ 
    public synchronized void produce(B v) {
        while (aq.isFull())
            try { wait(); }
            catch (InterruptedException e) {}
        aq.put(v);
        notifyAll(); }

    /** Remove first object from bounded buffer and return it. */ 
    public synchronized B consume() {
        while (aq.isEmpty())
            try { wait(); }
            catch (InterruptedException e) {}
        B item= aq.take(); notifyAll();
        return item;
    } }
