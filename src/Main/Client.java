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
	private static final String addr = "127.0.0.1";
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

	public void sendpkg() {
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
								output.writeUTF(sendMsg);
								output.flush();
								sendMsg = "";
							}
							Thread.sleep(10);
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
	public void send(String type, String mod, int n) {
		this.sendMsg += type + "&8o" + mod + "&8o" + n + "&9o";
	}

	/*
	 * 
	 */
	public void send(String type, int index, String ability, int value) {
		this.sendMsg += type + "&8o" + index + "&8o" + ability + "&8o" + value + "&9o";
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