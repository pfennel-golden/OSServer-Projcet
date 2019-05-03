 /**
 * Java implementation of Server:
 * This is a threaded socket server. 
 * Two classes Server and ClientHandler
 * class Server:
 * 1. Generates ClientHandler Threads
 * 2. Generates Producer Threads
 * 3. Initilizes a BondedBuffer.
 * 
 * BoundedBuffer(ArrayQue)
 * Ref: https://www.geeksforgeeks.org/introducing-threads-socket-programming-java/
 * Pete Fennell
 * @version (040152019)
 * 
 */
// 
// It contains two classes :  
// Save file as Server.java 

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 


// Server class 
public class Server <S>
{ 
    static boolean monitor = true;
    //CfigArray<S> cfa;
    //public Server() {
       // cfa= new CfigArray<S>(5); }
    public static void main(String[] args) throws IOException 
    { 
     int buffSize=0;
     String config[] = new String[5];
    
        // server is listening on port 3055 
        ServerSocket ss = new ServerSocket(3055);
        
        
        while (monitor) 
        { 
            Socket s = null; 

            try
            { 
                // socket object to receive incoming client requests 
                s = ss.accept(); 

                System.out.println(" client passing configuration: "); 

                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

                System.out.println("Assigning new thread for this client"); 

                // create a new thread object 
                Thread GCH = new GetConfigHandler(s, dis, dos); 
                // Invoking the start() method 
                GCH.start(); 
                
               
                
            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            }
          
        } // onitor Configuration 
        
        //BoundedBuffer1 buffer = new BoundedBuffer1(Integer.valueOf(config[2]));
        BoundedBuffer1 buffer = new BoundedBuffer1(50);        
        //for (int n=0; n < (Integer.valueOf(config[1])); n++){  //allow mutiple producers 
            for (int n=0; n < (3); n++){  //allow mutiple producers 
           Thread prod1 = new Producer1(buffer );
            //int n = 1000;
            prod1.start();
        }
        // client request 
        while (true) 
        { 
            Socket s = null; 

            try
            { 
                // socket object to receive incoming client requests 
                s = ss.accept(); 

                System.out.println("A new client is connected : " + s); 

                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

                System.out.println("Assigning new thread for this client"); 

                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos, buffer); 
                // Invoking the start() method 
                t.start(); 

            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
        } 
    } 
} 

// ClientHandler class 
class ClientHandler extends Thread 
{ 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s;
    public static  BoundedBuffer1 buffer;
    
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos,BoundedBuffer1 buffer ) 
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        this.buffer = buffer;
    } 

    @Override //subclass provide a specific implementation of a method that
    //is already provided by one of its super-classes or parent 
    //classes.
    public void run() 
    { 
        String received; 
        String toreturn; 
        String config[] = new String[5];
        while (true) 
        { 
            try { 
                //sleep(500);
                // Ask user what he wants 
                dos.writeUTF("The client will return values "+ 
                    "Type Exit to terminate connection."); 

                // receive the answer from client 
                received = dis.readUTF();

                if(received.equals("Exit")) 
                { 
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 

                // write on output stream based on the 
                // answer from the client 
                switch (received) { 

                    case "GET" : // Read buffer
                    System.out.println(received);
                    received=String.valueOf(buffer.consume());
                    dos.writeUTF(received); 
                    break; 
                    //Client upload to server
                    case "produce" : 
                    System.out.println(received);
                    buffer.produce(s +received);
                    break; 
                    //for client for client that more funtionality
                    default: 
                    dos.writeUTF("Invalid input"); 
                    break; 
                } 

            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 

        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 

        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 
//Get configuration
class GetConfigHandler extends Thread 
{ 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s;
    public static  BoundedBuffer1 buffer;
    
    // Constructor 
    public GetConfigHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        this.buffer = buffer;
        //this.cf = cf;
        
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

    @Override //subclass provide a specific implementation of a method that
    //is already provided by one of its super-classes or parent 
    //classes.
    public void run() 
    { 
        String received; 
        String toreturn; 
        while (true) 
        { 
            try { 

                // Ask user what he wants 
                dos.writeUTF("The client will return values "+ 
                    "Type Exit to terminate connection."); 

                // receive the answer from client 
                received = dis.readUTF();
                System.out.println(received);
                

                if(received.equals("Exit")) 
                { 
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 

                // write on output stream based on the 
                // answer from the client 


            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 

        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 

        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
        Server.monitor = false;
    } 
} 