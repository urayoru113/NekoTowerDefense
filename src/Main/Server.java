package Main;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	//init var
	private ServerSocket server;
	Socket socket;
			final int port = 8888;

	//wait clinet
	public Server(){
		try{
			server = new ServerSocket(port);
		} catch (java.io.IOException e){
			System.out.println(e.getMessage());
		}
		if (server != null){
			System.out.println("�ߊJ��server");
		}
	}

	@Override
	public void run(){
		try{
			System.out.println("���ҘA��");
			socket = server.accept();
            System.out.println("�擾�A�� : InetAddress = " + socket.getInetAddress());
		} catch(java.io.IOException e) {
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
