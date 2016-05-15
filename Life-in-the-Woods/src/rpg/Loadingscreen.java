package rpg;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Loadingscreen extends JFrame {
	private static final long serialVersionUID = 1L;
	public static Loadingscreen frame = new Loadingscreen();
	public static Game game = new Game();
	
	private JPanel mainpanel;
	private JLabel mainlabel;

	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game game = new Game();
					JFrame gameframe = new JFrame("RPG");
					gameframe.setExtendedState(Frame.MAXIMIZED_BOTH);
					gameframe.setUndecorated(true);
		
					gameframe.add(game);
					gameframe.pack();
					gameframe.setResizable(false);
					gameframe.setLocationRelativeTo(null);
					gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					gameframe.setVisible(true);

					game.start();
					
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Loadingscreen() {
		

		
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
		
		
		
		mainpanel = new JPanel();
		mainpanel.setSize(getWidth(),getHeight());
		mainpanel.setVisible(true);
		mainpanel.setLayout(null);
		super.add(mainpanel);
		
		Image image1 = null;
		try {
			image1 = ImageIO.read(Loadingscreen.class.getResourceAsStream("/Menu/homescreen.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mainlabel = new JLabel(new ImageIcon(image1));
		mainlabel.doLayout();
		mainlabel.setBounds(0,0,getWidth(),getHeight());
		mainpanel.add(mainlabel);
		
		
	}

	public void Close(){
		super.dispose();
	}
}
