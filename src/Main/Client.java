package Main;
import java.io.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
public class Client{
	/*initailze var*/
	private Socket socket;
	final int port = 8888;
	private static final String addr = "127.0.0.1";
	
	//connect server
	public Client(){

		try{
			socket = new Socket(InetAddress.getByName(addr), port);
			System.out.println("Connection to server IP: " + socket.getInetAddress());
			System.out.println("Connection to server host: " + socket.getInetAddress().getHostName());
		} catch(java.io.IOException e) {
			System.out.print(e.toString());
		}
		if(socket != null){
			System.out.print("connect successd");
		}
	}
	
	public class transmit{
		String[] catagory = {"message"};
		private String dataType;
		private String msg;
		
		Thread t = new Thread(){
			public void run(){
				try {
					DataOutputStream output = new DataOutputStream(socket.getOutputStream());
					output.writeUTF(dataType);
					output.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		void send(String s, String msg){
			dataType = s;
			this.msg = msg;
			t.start();
		}
		void send(String s, int num){
			dataType = s;
		}
	}
	
	/*transpacket way*/
	public class transpacket implements Runnable{
		public void run() {
			try {
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				output.writeUTF("hi !!!");
				output.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*transpacket*/
	public void tran(){
		transpacket tp = new transpacket();
		Thread t = new Thread(tp);
		t.start();
	}
	
	/*close socket socket*/
	public void closeSocket(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
