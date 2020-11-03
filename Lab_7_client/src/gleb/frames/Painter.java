package gleb.frames;

import gleb.Connection;
import gleb.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class Painter extends JPanel {

	String login;
	String password;
	String[] vehicleTable;
	String[] element;
	ArrayList<Ellipse2D> ellipse2DS;
	ArrayList<Ellipse2D> temporaryEllipse;
	int x0 = 0;
	int y0 = 0;
	int r;
	JPanel paintFrame;
	Boolean b;
	Integer counter;

	public JPanel getPaintFrame(){
		return paintFrame;
	}

	public void setPaintFrame(JPanel paintFrame) {
		this.paintFrame = paintFrame;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Painter(String login, String password) {
		this.login = login;
		this.password = password;
		setSize(1000, 1000);
		setPreferredSize(new Dimension(1000, 1000));
		ellipse2DS = new ArrayList<>();
		temporaryEllipse = new ArrayList<>();
		counter=0;

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b = false;
				Point point = e.getPoint();
				for (Ellipse2D el: ellipse2DS){
					if (el.contains(point) && (e.getButton()==1)){
						b = true;


						/*temporaryEllipse.remove(el);
						el.setFrame(growEllipse(el,1.5).getFrame());
						temporaryEllipse.add(el);
						getPaintFrame().repaint();

						 */

						Timer timer = new Timer(200,null);

						timer.addActionListener(e1 -> {
							temporaryEllipse.remove(el);
							el.setFrame(growEllipse(el,1.5).getFrame());
							temporaryEllipse.add(el);
							getPaintFrame().repaint();
							int c = getCounter();
							c++;
							setCounter(c);
							if (c==2) {
								timer.stop();
								setCounter(0);
							}
						});

						timer.start();




					}
				}

				if (!b) {
					temporaryEllipse.clear();
					getPaintFrame().repaint();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {


			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		};

		this.addMouseListener(mouseListener);

		Timer timer = new Timer(2000, t -> {getPaintFrame().repaint();});
		timer.start();

	}

	public ArrayList getEllipseList(){
		ArrayList<Ellipse2D> defaultEllipseList = new ArrayList();
		try {
			vehicleTable = Connection.sendReadMessage(new Message(login, password, "SHOW", " ")).split("\n");
			for (String s : vehicleTable) {
				element = s.split(" ");
				int engine = Integer.parseInt(element[5]) * 5;
				int wheels = Integer.parseInt(element[6]) * 5;
				int r = (engine + wheels) / 2;
				int x = 500 + (int) Float.parseFloat(element[2]) - r/2;
				int y = 500 - (int) Float.parseFloat(element[3]) - r/2;
				Ellipse2D el = new Ellipse2D.Double(x, y, r, r);
				defaultEllipseList.add(el);
			}
		} catch (ArrayIndexOutOfBoundsException | IOException ignored) {
		}

		return defaultEllipseList;

	}


	public Ellipse2D growEllipse(Ellipse2D el, double scale){
		double r = el.getWidth()  * scale;
		return new Ellipse2D.Double(el.getX() + (r/scale-r)/2, el.getY() + (r/scale-r)/2, r, r);
	}




	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(500, 0, 500, 1000);
		g2d.drawLine(0, 500, 1000, 500);
		g2d.setColor(Color.RED);

		ellipse2DS = getEllipseList();
		ellipse2DS.addAll(temporaryEllipse);
		for (Ellipse2D el : ellipse2DS) {
			x0 = (int) el.getX();
			y0 = (int) el.getY();
			r = (int)el.getWidth();
			el.setFrame(x0, y0, r, r);
			g2d.fill(el);
		}






	}


}

