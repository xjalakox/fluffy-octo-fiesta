package rpg.menu;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rpg.Loadingscreen;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel mainpanel;
	private JLabel mainlabel;
	private JLabel start;
	private JLabel close;

	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {

		try {
		new CustomCursor("default");
		setCursor(CustomCursor.cursor);

		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
		addKeyListener(new MenuInput());

		mainpanel = new JPanel();
		mainpanel.setSize(getWidth(), getHeight());
		mainpanel.setVisible(true);
		mainpanel.setLayout(null);
		super.add(mainpanel);
		
		final Image image1 = ImageIO.read(Menu.class.getResourceAsStream("/Menu/homescreen.jpg"));
		final Image	image2 = ImageIO.read(Menu.class.getResourceAsStream("/Menu/start_default.png"));
		final Image	image3 = ImageIO.read(Menu.class.getResourceAsStream("/Menu/close_hover.png"));
		final Image	image4 = ImageIO.read(Menu.class.getResourceAsStream("/Menu/start_pressed.png"));
		final Image	image5 = ImageIO.read(Menu.class.getResourceAsStream("/Menu/start_hover.png"));
		final Image	image6 = ImageIO.read(Menu.class.getResourceAsStream("/Menu/close_pressed.png"));
		final Image	image7 = ImageIO.read(Menu.class.getResourceAsStream("/Menu/close_default.png"));
		

		mainlabel = new JLabel(new ImageIcon(image1));
		mainlabel.doLayout();
		mainlabel.setBounds(0, 0, getWidth(), getHeight());
		mainpanel.add(mainlabel);

		start = new JLabel();
		start.setIcon(new ImageIcon(image2));
		start.setBounds(735, 500, 450, 172);
		start.addMouseListener(new MouseAdapter() {

			boolean sgHover = false;

			public void mouseEntered(MouseEvent evt) {
				start.setIcon(new ImageIcon(image5));
				sgHover = true;
			}

			public void mouseExited(MouseEvent evt) {
				start.setIcon(new ImageIcon(image2));
				sgHover = false;
			}

			public void mousePressed(MouseEvent evt) {
				start.setIcon(new ImageIcon(image4));
				Close();
				Loadingscreen.start();
			}

			public void mouseReleased(MouseEvent evt) {
				if (sgHover == true) {
					start.setIcon(new ImageIcon(image5));
				}
			}
		});
		mainlabel.add(start);

		close = new JLabel();
		close.setIcon(new ImageIcon(image7));
		close.setBounds(1830, 15, 69, 69);
		close.addMouseListener(new MouseAdapter() {

			boolean sgHover = false;

			public void mouseEntered(MouseEvent evt) {
				close.setIcon(new ImageIcon(image3));
				sgHover = true;
			}

			public void mouseExited(MouseEvent evt) {
				close.setIcon(new ImageIcon(image7));
				sgHover = false;
			}

			public void mousePressed(MouseEvent evt) {
				close.setIcon(new ImageIcon(image6));
				Close();
			}

			public void mouseReleased(MouseEvent evt) {
				if (sgHover == true) {
					close.setIcon(new ImageIcon(image3));
				}
			}
		});
		mainlabel.add(close);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Close() {
		super.dispose();
	}
}
