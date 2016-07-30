import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Login implements Serializable {
	/**
	 * Version no: 1L Created By: Trishant Pahwa http://www.github.com
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField ServerIP;

	public static void main(String args[]) {
		final JFrame login = new JFrame("Login");
		login.setSize(300, 160);
		login.setResizable(false);
		login.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JLabel LoginName = new JLabel("Login Name:");
		final JTextField loginName = new JTextField(25);

		LoginName.setLabelFor(loginName);
		panel.add(LoginName);
		panel.add(loginName);
		login.getContentPane().add(panel);

		JLabel lblServerIp = new JLabel("Server IP:");
		panel.add(lblServerIp);

		ServerIP = new JTextField(25);
		ServerIP.setText("localhost");
		panel.add(ServerIP);
		JButton enter = new JButton("Login");
		panel.add(enter);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!(loginName.getText().isEmpty()) && !(ServerIP.getText().isEmpty())) {
						Client client = new Client(loginName.getText(), ServerIP.getText());
						login.setVisible(false);
						login.dispose();
					}
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		login.setVisible(true);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginName.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if (!(loginName.getText().isEmpty()) && !(ServerIP.getText().isEmpty())) {
							Client client = new Client(loginName.getText(), ServerIP.getText());
							login.setVisible(false);
							login.dispose();
						}
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		ServerIP.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						if (!(loginName.getText().isEmpty()) && !(ServerIP.getText().isEmpty())) {
							Client client = new Client(loginName.getText(), ServerIP.getText());
							login.setVisible(false);
							login.dispose();
						}
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
}