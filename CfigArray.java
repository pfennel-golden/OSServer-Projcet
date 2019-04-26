
/**
 * Write a description of class CfigArray here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CfigArray
{
    // instance variables - replace the example below with your own
    private String CfigArray[] cfa; String cf;
    /**
     * Constructor for objects of class CfigArray
     */
    public CfigArray()
    {
       cfa = new CfigArray[5];
    }
    

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
   
    /** Put object into the bounded buffer */ 
    public  void produce() {
              }

    /** Remove first object from bounded buffer and return it. */ 
    public String consume(int n) {

        cf = cfa [n]; 
        return cf;
    } 
}
