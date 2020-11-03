package gleb.frames;

import javax.swing.*;

public class PaintFrame extends JFrame {
	static String login;
	static String password;

	public PaintFrame(String login, String password) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		PaintFrame.login = login;
		PaintFrame.password = password;
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel panel = new JPanel();
		Painter painter = new Painter(login, password);
		painter.setPaintFrame(panel);
		panel.add(painter);
		add(panel);
		/*Timer timer = new Timer(100, evt -> {
			painter.repaint();
		});
		timer.start();

		 */
	}
}
