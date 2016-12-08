package Main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
public class Client extends Thread{

	//init var
	private Socket client;
	final int port = 8888;
	private static final String addr = "127.0.0.1";
	//connect server
	public Client(){

		try{
			client = new Socket(InetAddress.getByName(addr), port);
			System.out.println("Connection to server IP: " + client.getInetAddress());
			System.out.println("Connection to server host: " + client.getInetAddress().getHostName());
		} catch(java.io.IOException e) {
			System.out.print(e.toString());
		}
		if(client != null){
			System.out.print("connect successd");
		}
		else {
			System.out.print("connect fail");
		}
	}

	public void close(){
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
