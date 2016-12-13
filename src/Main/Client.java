package Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	/* initailze var */
	private Socket socket;
	final int port = 8888;
	private static final String addr = "169.254.18.107";
	private String sendMsg = "";

	// connect server
	public Client() {

		try {
			socket = new Socket(InetAddress.getByName(addr), port);
		} catch (java.io.IOException e) {
			System.out.print(e.toString());
		}
		if (socket != null) {
			System.out.print("connect successd");
		}
	}

	public void sendpkg(){
		new Thread(new Runnable() {
			DataOutputStream output;
			DataInputStream input;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						output = new DataOutputStream(socket.getOutputStream());
					} catch (Exception e) {
						continue;
					}

					try {
						while (sendMsg != "") {
							synchronized (this) {

								System.out.println("\nClient" + sendMsg);
								output.writeUTF(sendMsg);
								output.flush();
								sendMsg = "";
								Thread.sleep(80);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	/*
	 * chat method
	 */
	public void send(String type, String msg) {
		this.sendMsg += type + "&8o" + msg + "&9o";
	}
	/*
	 * 
	 */
	public void send(String type, String method, int n) {
		this.sendMsg += type + "&8o"  + method + "&8o" + n + "&9o";
	}
	/*
	 * 
	 */
	public void send(String type, int n) {
		this.sendMsg += type + "&8o" + n + "&9o";
	}

	/* close socket socket */
	public void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
