package ServerProject_G;


/**
 * Write a description of class CfigArray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CfigArray<S>
{
    private String CfigArray[], cfa;
        private S[] b; private int n; private int h;
    // The n elements of the queue
    /** Constructor: a empty queue of maximum "size". */ 
    public CfigArray(int size) {
        b = (S[])new Object[size]; }
 

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
   
    /** Put object*/ 
    public  void produce() {
              }

    /** Remove object */ 
    public String consume(int n) {
        String cf;
        cfa = b [n]; 
        return cf;
    } 
}
