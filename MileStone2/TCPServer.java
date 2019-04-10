package MileStone2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	
	DataOutputStream doc;
	BufferedReader inClient;
	Socket socket;
	ServerSocket welcomeSocket;
	BufferedReader inConsole;
	public TCPServer(int port) throws IOException {
		try {
			welcomeSocket =new ServerSocket(port);
			System.out.println("Initilized....."+"\n"+"Waiting for a client");
			socket= welcomeSocket.accept();
			System.out.println("Connected");
			doc =new DataOutputStream(socket.getOutputStream());
			inClient=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			inConsole= new BufferedReader(new InputStreamReader(System.in));
			
			String sentence="";
			String clientSentence="";
			while (!(sentence.equals("over")||clientSentence.equals("over"))) {
				if (inConsole.ready()) {
					sentence=inConsole.readLine();
					doc.writeBytes(sentence +"\n");
				}
				if (inClient.ready()) {
					clientSentence=inClient.readLine();
					System.out.println(clientSentence);
				}
					
			}
		}
		catch(IOException e1) {
			System.out.println(e1.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			welcomeSocket.close();
			socket.close();
		}
		
	}

	public static void main(String[] args) throws IOException{
		
		TCPServer server=new TCPServer(5000);
		

	}

}
