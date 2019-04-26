
/** 
 * A class producer: places objects in a bounded buffer.  
 * Calls: BoundedBuffer1
 * 
 * Taken from _Concepts in Programming Languages_ by John Mitchell
 * Comments by Scot Drysdale
 * @author John Mitchell
 * 
 * Modified by Pete Fennell rev 04252019
 */

public class Producer1 extends Thread{
	public static BoundedBuffer1 buff;

	/**
	 * Create a Producer
	 * @param b the buffer the producer puts into
	 */
	public Producer1(BoundedBuffer1 b) {
		buff = b;
		System.out.println(" producer generated");
	}

	/**
	 * Run when the thread is started 
	 */
	public void run() {
	    System.out.println("prod start");
		try {
			for (int index = 0; index < 10000; index ++) {
			    System.out.println("prod buffW");
				buff.produce("INPUT#"+index);
				sleep(500);
			}
		} catch (InterruptedException e) {	}
	}
}
