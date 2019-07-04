/**
 * 
 */
package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author 78486
 *
 */
public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setTitle("租车管理系统");
		setSize(600, 260);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image image;
		try {
			image = ImageIO.read(HomePage.class.getResource("/res/title.png"));
			setIconImage(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel top_panel = new JPanel();
		top_panel.setBounds(0, 0, 1000, 210);
		top_panel.setBackground(Color.ORANGE);
		getContentPane().add(top_panel);
		top_panel.setLayout(null);
		JLabel jl = new JLabel("租车管理系统");
		jl.setForeground(Color.BLACK);
		jl.setBounds(150, 52, 300, 66);
		jl.setFont(new Font("微软雅黑", Font.BOLD, 50));
		top_panel.add(jl);

		final JButton b1 = new JButton("", new ImageIcon(this.getClass().getResource("/res/login.png")));
		b1.setContentAreaFilled(false);//除去默认的背景填充
		b1.setToolTipText("登陆");
		b1.setBorderPainted(false);
		b1.setFont(new Font("宋体", Font.BOLD, 20));
		top_panel.add(b1);
		b1.setBounds(125, 155, 125, 63);

		final JButton b2 = new JButton("", new ImageIcon(this.getClass().getResource("/res/Register.png")));
		b2.setContentAreaFilled(false);//除去默认的背景填充
		b2.setToolTipText("注册");
		b2.setBorderPainted(false);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog regist = new regist();// 创建登陆
				regist.setLocationRelativeTo(null);
				regist.setVisible(true);
			}
		});
		b2.setFont(new Font("宋体", Font.BOLD, 20));
		b2.setBounds(350, 155, 125, 63);
		top_panel.add(b2);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/res/bg.png")));
		lblNewLabel.setBounds(0, 0, 600, 250);
		top_panel.add(lblNewLabel);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog login = new Login();// 创建登陆
				login.setLocationRelativeTo(null);
				login.setVisible(true);
			}
		});

	}

}
