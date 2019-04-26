/**
 * Write a description of class ClinetMonitor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

public class ClinetMonitor
{

    /**
     * Constructor for objects of class ClinetMonitor
     */
    public ClinetMonitor()
    {
        // initialise instance variables

    }

    /**
     * On the client (Consumers) side there is some a ConsumerMonitor, which:
     * number of the clients, producers, the size of the buffer and some modes of the system;
     * creates a socket connection with the ProducerServer to exchange some needed information;
     * generates consumer threads;
     * provides some results to the user.  
     */
    public static String setSleep()
    { Scanner scn = new Scanner(System.in); 
        String tosend = " ";
        System.out.println(" Enter thread sleep value:  ");
        tosend = scn.nextLine();
        return tosend;
    }

    public static String setCNum()//client (Consumers)
    { Scanner scn = new Scanner(System.in); 
        String tosend = " ";
        System.out.println(" Enter number of clients:  ");
        tosend =  scn.nextLine();
        return tosend;
    }

    public static String setPNum()//producers,
    { Scanner scn = new Scanner(System.in); 
        String tosend = " ";

        System.out.println(" Enter number of producers:  ");
        tosend =  scn.nextLine();

        return tosend;
    }
    //size of the buffer
    public static String setBSize()//size of the buffer
    { Scanner scn = new Scanner(System.in); 
        String tosend = " ";
        System.out.println(" Enter boundedBuffer size:  ");
        tosend =  scn.nextLine();
        return tosend;
    }

    public static void main(String[] args) throws IOException 
    { 
        Scanner scn = new Scanner(System.in); 
        boolean done = true;
        String config[] = new String[5];
        String tosend = "";
        do {
            config[0]= setPNum();
            config[1]= setCNum();
            config[2]= setBSize();
            config[3]= setSleep();

            System.out.println("Number of producers   : " + config[0]);
            System.out.println("Number of Clients     : " + config[1]);
            System.out.println("BoundedBuffer size    : " + config[2]);
            System.out.println("Sleep time of threads : " + config[3]);
            System.out.println("  ");
            System.out.println("Settings correct y or n ?"); 
            tosend =  scn.nextLine();
            if (tosend.equals("y"))
            { done = false;}
        }
        while (done);

        try
        { 
            //Scanner scn = new Scanner(System.in); 
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
                System.out.println("Buffer size: integer value "); 
                //String tosend = scn.nextLine();
                for (int i =0; i<4; i++){
                dos.writeUTF(config[i]); 
            }
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

