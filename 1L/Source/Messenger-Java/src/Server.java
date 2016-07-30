import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.net.*;

public class Server implements Serializable {
	/**
	 * Version no: 1L Created By: Trishant Pahwa http://www.github.com
	 */
	private static final long serialVersionUID = 1L;
	static Vector ClientSockets, LoginNames;

	Server() throws IOException {
		ServerSocket server = new ServerSocket(5217);
		ClientSockets = new Vector();
		LoginNames = new Vector();
		while (true) {
			Socket client = server.accept();
			AcceptClient acceptClient = new AcceptClient(client);
		}
	}

	public static void main(String args[]) throws IOException {
		Server server = new Server();
	}

	class AcceptClient extends Thread {
		Socket ClientSocket;
		DataInputStream din;
		DataOutputStream dout;

		AcceptClient(Socket client) throws IOException {
			ClientSocket = client;
			din = new DataInputStream(ClientSocket.getInputStream());
			dout = new DataOutputStream(ClientSocket.getOutputStream());
			String LoginName = din.readUTF();
			LoginNames.add(LoginName);
			ClientSockets.add(ClientSocket);
			start();
		}

		public void run() {
			while (true) {
				try {
					String msgFromClient = din.readUTF();
					StringTokenizer st = new StringTokenizer(msgFromClient);
					String LoginName = st.nextToken();
					String MsgType = st.nextToken();
					String msg = "";
					int lo = -1;
					while (st.hasMoreTokens()) {
						msg = msg + " " + st.nextToken();
					}
					if (MsgType.equals("LOGIN")) {
						for (int i = 0; i < LoginNames.size(); i++) {
							Socket pSocket = (Socket) ClientSockets.elementAt(i);
							DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
							pOut.writeUTF(LoginName + " has logged in.");
						}
					} else if (MsgType.equals("LOGOUT")) {
						for (int i = 0; i < LoginNames.size(); i++) {
							if (LoginName.equals(LoginNames.elementAt(i))) {
								lo = i;
							}
							Socket pSocket = (Socket) ClientSockets.elementAt(i);
							DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
							pOut.writeUTF(LoginName + " has logged out.");
						}
						if (lo >= 0) {
							LoginNames.removeElementAt(lo);
							ClientSockets.removeElementAt(lo);
						}
					} else {
						for (int i = 0; i < LoginNames.size(); i++) {
							Socket pSocket = (Socket) ClientSockets.elementAt(i);
							DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
							pOut.writeUTF(LoginName + ": " + msg);
						}
					}
					if (MsgType.equals("LOGOUT")) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}