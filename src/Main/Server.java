package Main;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}

	//init var
	private ServerSocket server;
			final int port = 8888;

	//wait clinet
	public void Server(){
		try{
			server = new ServerSocket(port);

		} catch (java.io.IOException e){
			System.out.println(e.getMessage());
		}
	}

	public void close(){
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
