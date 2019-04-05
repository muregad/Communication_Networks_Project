package MileStone1;

import java.net.*; 
import java.io.*; 
  
public class TCPClient { 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataInputStream  inputٍServer   = null;
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public TCPClient(String address, int port) 
    { 
        // establish a connection 
        try
        { 
        	
        	 // takes input from terminal 
            input  = new DataInputStream(System.in); 
            
            while(!input.readLine().equals("Connect"));
            
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
            inputٍServer = new DataInputStream(socket.getInputStream());
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line1 = "" , line2 = ""; 
  
        // keep reading until "Over" is input 
        while (!(line1.equals("Over") || line2.equals("Over"))) 
        { 
            try
            { 
                line1 = input.readLine(); 
                out.writeUTF(line1); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
            
            try
            { 
                line2 = inputٍServer.readUTF(); 
                System.out.println(line2); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
  
        // close the connection 
        try
        { 
            input.close(); 
            inputٍServer.close();
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        TCPClient client = new TCPClient("10.0.1.184", 5000); 
    } 
} 