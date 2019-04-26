/* Client class 
 * 
 *Ref:
 * https://www.javatpoint.com/sleep()-method
 * Pete Fennell Rev 
 */
import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

public class Client 
{ 
    public static void main(String[] args) throws IOException 
    { 
        try
        { 
            Scanner scn = new Scanner(System.in); 
            // getting localhost ip 
            InetAddress ip = InetAddress.getByName("localhost"); 
            // establish the connection with server port 3055 
            Socket s = new Socket(ip, 3055); 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

            // the following loop performs the exchange of 
            // information between client and client handler 
            boolean run = true;
            while (run) 
            { // The thread sleep added control read t
                 try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);}  
                //System.out.println(dis.readUTF()); 
                //String tosend = scn.nextLine();
                String tosend ="GET";
                dos.writeUTF(tosend); 

                // Turned off for testing system "infnit loop"
                //If client sends exit,close this connection 
                // and then break from the while loop 
                if(tosend.equals("Exit")) 
                { 
                    System.out.println("Closing this connection : " + s); 
                    s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 

                // printing date or time as requested by client 
                String received = dis.readUTF(); 
                System.out.println(received); 
            } 

            // closing resources 
            scn.close(); 
            dis.close(); 
            dos.close(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 
} 