/**
 * 
 */
package Frame;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Order;
import Entity.Userinfo;
import SqlManage.CarManager;
import SqlManage.OrderManager;
import SqlManage.UserManager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

/**
 * @author 78486
 *
 */
public class Buyinfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private CarManager manager = new CarManager();
	private OrderManager om = new OrderManager();
	private CarManager cm = new CarManager();
	private Userinfo user = new Userinfo();
	private UserManager um = new UserManager();
	private List<String> images = new ArrayList<String>();
	private List<String> cars = new ArrayList<String>();
	private List<String> oids = new ArrayList<String>();
	private List<Order> orders = new ArrayList<Order>();
	/**
	 * Create the dialog.
	 */
	public Buyinfo(String uid) {
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		cars = om.selectCids(uid);
		user = um.findUser(uid);
		images = cm.selectimage(cars);
		oids = om.selectOids(uid);
		int m = 0;
		for(String i:images) {
			Order o = new Order(oids.get(m),images.get(m));
			orders.add(o);
			m++;
		}

		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setBounds(75, 60, 72, 25);
		contentPanel.add(lblNewLabel);
		textField = new JTextField();
		textField.setText(user.getTname());
		textField.setEnabled(false);
		textField.setBounds(170, 60, 100, 25);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("账户余额：");
		lblNewLabel_1.setBounds(75, 90, 80, 25);
		contentPanel.add(lblNewLabel_1);
		textField_1 = new JTextField();
		textField_1.setText(user.getAmount().toString());
		textField_1.setEnabled(false);
		textField_1.setBounds(170, 90, 100, 25);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 140, 416, 120);
		contentPanel.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		for (Order o:orders) {
			JLabel lblNewLabel1 = new JLabel();
			lblNewLabel1.setIcon(new ImageIcon(Buyinfo.class.getResource("/res/" + o.getImage())));
			lblNewLabel1.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					Buycar c = new Buycar(o.getOid(),o.getImage());
					c.setVisible(true);
				}
			});
			panel.add(lblNewLabel1);
		
		}

		JLabel label = new JLabel("车辆信息");
		label.setBounds(180, 114, 80, 25);
		contentPanel.add(label);

	}
}
