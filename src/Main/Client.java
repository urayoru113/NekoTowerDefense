package Main;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
	}

	//init var
	private Socket client;
	final int port = 8888;
	private static final String addr = "127.0.0.1";

	//connect server
	public Client(){
		client = new Socket();
		try{

			client = new Socket(InetAddress.getLocalHost(), port);
			System.out.println("Connection to server IP: " +
					client.getInetAddress().getHostAddress());
		} catch(java.io.IOException e) {
			System.out.print(e.toString());
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
