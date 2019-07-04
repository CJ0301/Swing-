/**
 * 
 */
package Frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Entity.Car;
import SqlManage.CarManager;
import SqlManage.OrderManager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import javax.swing.JButton;

/**
 * @author 78486
 *
 */
public class UserTable extends JFrame {
	private CarManager cm = new CarManager();
	private JPanel contentPane;
	private List<Car> li;
	private OrderManager om = new OrderManager();
	private final ScrollPane scrollPane = new ScrollPane();
	private List<java.sql.Date> ends;

	/**
	 * Create the frame.
	 */
	public UserTable(String uid) {
		setTitle("租车管理系统");
		setSize(1300, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		JLabel jl = new JLabel();
		jl.setIcon(new ImageIcon(HomePage.class.getResource("/res/top.png")));
		panel.add(jl);
		scrollPane.setPreferredSize(new Dimension(1250, 150));
		contentPane.add(scrollPane);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new FlowLayout());
		li = new ArrayList<Car>();
		li = cm.selectAll();

		 ends =om.check(uid);
		 JOptionPane.showMessageDialog(null, "发现"+ends.size()+"条过期记录");
		 om.deleteEnd(ends);
		 
		for (Car car : li) {
			JLabel lblNewLabel1 = new JLabel();
			lblNewLabel1.setIcon(new ImageIcon(UserTable.class.getResource("/res/" + car.getImage())));
			lblNewLabel1.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					Carinfo c = new Carinfo(uid, car);
					c.setVisible(true);
				}
			});
			panel_2.add(lblNewLabel1);
		}
		scrollPane.add(panel_2);

		
		final JButton bw = new JButton("", new ImageIcon(this.getClass().getResource("/res/user.png")));
		bw.setContentAreaFilled(false);//除去默认的背景填充
		bw.setToolTipText("用户信息");
		bw.setBorderPainted(false);
		bw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Buyinfo buy = new Buyinfo(uid);
				buy.setVisible(true);
			}
		});
		contentPane.add(bw);

		final JButton bc = new JButton("", new ImageIcon(this.getClass().getResource("/res/money.png")));
		bc.setContentAreaFilled(false);//除去默认的背景填充
		bc.setToolTipText("充值");
		bc.setBorderPainted(false);
		bc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Recharge recharge = new Recharge(uid);
				recharge.setVisible(true);

			}
		});
		contentPane.add(bc);

	}

}
