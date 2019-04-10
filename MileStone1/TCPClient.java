package MileStone1;

import java.net.*;
import java.io.*;

public class TCPClient {
	DataOutputStream dos;
	BufferedReader inServer;
	Socket socket;
	BufferedReader inConsole;
	
public TCPClient(String ip,int port) {
	try {
		inConsole = new BufferedReader(new InputStreamReader(System.in));
		
	try {
		socket= new Socket(ip, port);
		dos = new DataOutputStream(socket.getOutputStream());
		inServer =new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String sentence ="";
		String serverSentence="";
		while (!(sentence.equals("over"))) {
			if (inConsole.ready()) {
				sentence=inConsole.readLine();
				dos.writeBytes(sentence +"\n");
			}
			if (inServer.ready()) {
				serverSentence=inServer.readLine();
				System.out.println("FROM SERVER : " + serverSentence);
			}
				
		}
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
	finally {
		socket.close();
	}
	}
	catch (IOException x) {
		System.out.println(x.getMessage());
	}

	
}

public static void main (String[]args) {
	TCPClient client=new TCPClient("localhost", 5000);
}
}
