package Main;
import java.net.ServerSocket;

public class Server {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}
	private ServerSocket server;
			final int serverPort = 8888;
			String cntmsg;

	public Server(){
		try{
			server = new ServerSocket(serverPort);//connect to client
			cntmsg = "connect success";
		} catch (java.io.IOException e){
			cntmsg = e.getMessage().toString();
		}

	}
	public String connect_msg(){
		return cntmsg;
	}
}
