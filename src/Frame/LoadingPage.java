package Frame;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LoadingPage extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoadingPage window = new LoadingPage();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public LoadingPage() {

		initialize();

		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				Image image;
				try {
					image = ImageIO.read(HomePage.class.getResource("../res/title.png"));
					setIconImage(image);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int i = 0;
				while (i < 100) {
					i += 10;

					progressBar.setValue(i);

					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// loading 
				setVisible(false);
				HomePage mf = new HomePage();
				mf.setVisible(true);

				dispose();

			}
		});

		th.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	JProgressBar progressBar;

	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		setSize(450, 355);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);

		JLabel showimage = new JLabel();
		showimage.setLocation(0, 0);
		showimage.setBorder(null);
		showimage.setSize(450, 336);
		String path = LoadingPage.class.getResource("../res/timg.gif").getPath();
		Icon icon = new ImageIcon(path);
		getContentPane().setLayout(null);
		showimage.setIcon(icon);

		getContentPane().add(showimage);

		progressBar = new JProgressBar();
		progressBar.setBounds(0, 336, 450, 14);
		progressBar.setStringPainted(true);
		getContentPane().add(progressBar);
	}
}
