
/**
 * Class uses a Java arrayList using class methods:
 * size
 * isEmpty
 * isFull
 * put
 * peek
 * take
 * From 
 * Pete Fennell
 * @version 04192019
 */
/** An instance implements a queue of bounded size in an array */ 
public class ArrayQueue<B> {
    private B[] b; private int n; private int h;
    // The n elements of the queue
    /** Constructor: a empty queue of maximum "size". */ 
    public ArrayQueue(int size) {
        b= (B[])new Object[size]; }

    /** Return the size of the queue. */ 
    public int size() { return n; }

    /** isEmpty() method of ArrayList in java is used to check if a list is empty or not */
    public boolean isEmpty() { return n == 0; }

    /** isFull method of ArrayList in java is used to check if a list is full or not */
    public boolean isFull() { return n == b.length; }

    /** Throw RuntimeException if queue is full. Otherwise, add e to the queue. */ 
    public void put(B e) {
        if (n == b.length) throw new RuntimeException("queue full");
        b[(h+n) % b.length]= e; n= n+1; }

    /** Throw a RuntimeException if queue empty. Otherwise, return first element */ 
    public B peek() {
        if (n == 0) throw new RuntimeException("queue empty");
        return b[h]; }

    /** Throw a RuntimeException if the queue is empty.
     * Otherwise, take head of queue off queue and return it. */
    public B take() {
        if (n == 0) throw new RuntimeException("queue empty"); 
        B e= b[h]; 
        h= (h+1) % b.length; 
        n= n-1; 
        return e;
    }
}