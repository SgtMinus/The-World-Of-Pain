package gleb.frames;

import javax.swing.*;
import java.awt.*;

public class InfoFrame extends JFrame {
	JTextArea info;
	public InfoFrame(String info, String title) {
		setTitle(title);
		JPanel panel = new JPanel();
		this.info = new JTextArea(info);
		this.info.setEditable(false);
		panel.add(this.info);
		add(panel);
		setMinimumSize(new Dimension(200, 200));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
