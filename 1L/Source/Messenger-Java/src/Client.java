import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.*;

import javax.swing.*;

public class Client extends JFrame implements Runnable, Serializable {
	/**
	 * Version no: 1L Created By: Trishant Pahwa http://www.github.com
	 */
	private static final long serialVersionUID = 1L;
	Socket socket;
	JTextArea ta;
	JButton send, logout;
	JTextField tf;
	Thread thread;
	DataInputStream din;
	DataOutputStream dout;
	String LoginName;

	Client(String login, String serverIP) throws UnknownHostException, IOException {
		super(login);
		LoginName = login;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					dout.writeUTF(LoginName + " " + "LOGOUT");
					System.exit(1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ta = new JTextArea(18, 50);
		ta.setEditable(false);
		tf = new JTextField(50);
		tf.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if (tf.getText().length() > 0) {
							dout.writeUTF(LoginName + " " + "DATA " + tf.getText().toString());
							tf.setText("");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		send = new JButton("Send");
		logout = new JButton("Logout");
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tf.getText().length() > 0) {
						dout.writeUTF(LoginName + " " + "DATA " + tf.getText().toString());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dout.writeUTF(LoginName + " " + "LOGOUT");
					System.exit(1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		socket = new Socket(serverIP, 5217); /* Address to server */
		din = new DataInputStream(socket.getInputStream());
		dout = new DataOutputStream(socket.getOutputStream());
		dout.writeUTF(LoginName);
		dout.writeUTF(LoginName + " " + "LOGIN");
		thread = new Thread(this);
		thread.start();
		setup();
	}

	public void setup() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.add(new JScrollPane(ta));
		panel.add(tf);
		panel.add(send);
		panel.add(logout);
		add(panel);
		setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				ta.append("\n" + din.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}