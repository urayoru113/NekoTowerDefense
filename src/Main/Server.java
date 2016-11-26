package Main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import Main.Client.transpacket;

public class Server {
	/*initialize var*/
	private ServerSocket server;
	Socket socket;
			final int port = 8888;

	/*open server*/
	public Server(){
		try{
			server = new ServerSocket(port);
		} catch (java.io.IOException e){
			System.out.println(e.getMessage());
		}
		if (server != null){
			System.out.println("start server");
		}
	}

	/*wait client connect*/
	public void waitClient(){
		try{
			System.out.println("wait connect");
			socket = server.accept();
            System.out.println("gethost : InetAddress = " + socket.getInetAddress());
            
		} catch(java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*get way*/
	public class getpacket implements Runnable{
		public void run() {
			try {
	            DataInputStream input = new DataInputStream(socket.getInputStream());
	            System.out.println(input.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*get packet*/
	public void get(){
		getpacket gp = new getpacket();
		Thread t = new Thread(gp);
		t.start();
	}
	
	/*close server socket*/
	public void closeSocket(){
		try{
			socket.close();
		} catch (java.io.IOException e){
			System.out.println(e.getMessage());
		}
	}

	/*close server*/
	public void closeServer(){
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
