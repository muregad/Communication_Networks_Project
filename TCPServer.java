package MileStone1;

import java.io.*;
import java.net.*; 
  
public class TCPServer { 
    
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
    private DataInputStream inConsole       =  null; 
    private DataOutputStream out     = null; 
  
     
    public TCPServer(int port) 
    { 
        
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
            inConsole = new DataInputStream(new BufferedInputStream(System.in));
            out = new DataOutputStream(socket.getOutputStream()); 
            
            String line1 = "" , line2 = ""; 
  
            
            while (!(line1.equals("Over") || line2.equals("over"))) 
            { 
                try
                { 
                    line1 = in.readUTF(); 
                    System.out.println(line1); 
  
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
                
                try
                { 
                    line2 = inConsole.readLine(); 
                    out.writeUTF(line2);
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
  
            
            socket.close(); 
            in.close(); 
            out.close(); 
            inConsole.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        TCPServer server = new TCPServer(5000); 
    } 
} 