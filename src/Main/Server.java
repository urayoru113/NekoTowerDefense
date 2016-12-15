package Main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	/* initialize var */
	private ServerSocket server;
	private Socket socket;
	private final int port = 8888;
	private String[] getMsg;
	private String Msg = "";
	private int Soldier = 0, Hp = -1, Position = -1, Index = -1, TowerHp = -1;
	private int Action = -1;

	/* open server */
	public Server() {
		try {
			server = new ServerSocket(port);
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
		if (server != null) {
			System.out.println("start server");
		}

	}

	public void sendpkg() {
		new Thread(new Runnable() {
			DataInputStream input;
			DataOutputStream output;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						input = new DataInputStream(socket.getInputStream());
					} catch (Exception e) {
						continue;
					}
					try {

						while (input != null) {
							synchronized (this) {
								decode(input.readUTF());
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
			}
		}).start();
	}

	/* decode packet */
	public void decode(String packet) {
		String[] s = packet.split("&9o");
		for (String g : s)
			System.out.println(g);
		for (String getMsg : s) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getMsg = getMsg.split("&8o");
			/*
			 * for (String j : this.getMsg) System.out.println(j);
			 */
			switch (this.getMsg[0]) {
			case "Message":
				Msg = this.getMsg[1];
				break;
			case "Neko":
				switch (this.getMsg[1]) {
				case "Add":
					Soldier = addSoldier(this.getMsg[2]);
					break;
				case "Remove":
					Action = 0;
					Index = Integer.parseInt(this.getMsg[2]);
					break;
				default:
					Index = Integer.parseInt(this.getMsg[1]);
					switch (this.getMsg[2]) {
					// HP
					case "Hp":
						Hp = Integer.parseInt(this.getMsg[3]);
						break;
					// position
					case "Position":
						Position = Integer.parseInt(this.getMsg[3]);
						break;
					case "Attack":
						Action = 2;
						break;

					}
					break;
				}
				break;
			case "Tower":
				switch (this.getMsg[1]) {
				case "Hp":
					TowerHp = Integer.parseInt(this.getMsg[2]);
					break;
				case "Level":
					break;
				}
			}
		}
	}

	public int getIndex() {
		return Index;
	}

	public int getHp() {
		return Hp;
	}

	public int getPosition() {
		return Position;
	}

	public int getAction() {
		return Action;
	}

	public void resetSoldierStatus() {
		Index = -1;
		Hp = -1;
		Position = -1;
		Action = -1;
	}

	public String message() {
		return Msg;
	}

	public int addSoldier(String type) {
		return Integer.parseInt(type);
	}

	public int getSoldier() {
		return Soldier;
	}

	public void resetSoldier() {
		Soldier = 0;
	}

	public void resetMsg() {
		Msg = "";
	}

	/* wait client connect */
	public void waitClient() {
		try {
			System.out.println("wait connect");
			socket = server.accept();
			System.out.println("Server connect successed");
			System.out.println("gethost : InetAddress = " + socket.getInetAddress());
			server.close();
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/* get way */

	/* close server socket */
	public void closeSocket() {
		try {
			socket.close();
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/* close server */
	public void closeServer() {
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}