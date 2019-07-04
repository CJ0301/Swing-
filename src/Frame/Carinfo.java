/**
 * 
 */
package Frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Car;
import SqlManage.CarManager;
import SqlManage.OrderManager;
import SqlManage.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;

/**
 * @author 78486
 *
 */
public class Carinfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private OrderManager om = new OrderManager();
	private UserManager um = new UserManager();

	/**
	 * Create the dialog.
	 */
	public Carinfo(String uid, Car c) {
		setSize(450, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("车名");
		lblNewLabel.setBounds(75, 20, 72, 25);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setText(c.getName());
		textField.setEnabled(false);
		textField.setBounds(170, 20, 100, 25);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("类型");
		lblNewLabel_1.setBounds(75, 60, 72, 25);
		contentPanel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setText(c.getType());
		textField_1.setEnabled(false);
		textField_1.setBounds(170, 60, 100, 25);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("价格");
		lblNewLabel_2.setBounds(75, 100, 72, 25);
		contentPanel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setText(c.getPrice());
		textField_2.setEnabled(false);
		textField_2.setBounds(170, 100, 100, 25);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon(Carinfo.class.getResource("/res/" + c.getImage())));
		lblNewLabel_3.setBounds(300, 60, 118, 103);
		contentPanel.add(lblNewLabel_3);

		JLabel label = new JLabel("保险");
		label.setBounds(75, 140, 72, 25);
		contentPanel.add(label);

		textField_3 = new JTextField();
		textField_3.setText(String.valueOf(c.getInsure()));
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(170, 140, 100, 25);
		contentPanel.add(textField_3);
		
		Vector<String> items = new Vector<String>();
		for(int i=1;i<=12;i++) {
			items.add(i+"");
		}
		JComboBox comboBox = new JComboBox(items);
		comboBox.setBounds(193, 191, 63, 24);
		contentPanel.add(comboBox);
		
		JLabel label_1 = new JLabel("选择租用时间:");
		label_1.setBounds(79, 194, 100, 18);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("月");
		label_2.setBounds(270, 194, 72, 18);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("(*一个月按31天计算)");
		label_3.setBounds(300, 191, 160, 25);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("最近评价");
		label_4.setBounds(184, 228, 72, 18);
		contentPanel.add(label_4);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(79, 246, 263, 69);
		editorPane.setText(c.getDesp());
		editorPane.setEditable(false);
		contentPanel.add(editorPane);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			JButton btnNewButton_2 = new JButton("租用");
			btnNewButton_2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Float f = um.recharge(uid);
					boolean flag = false;
					int i = 0;
					String mounth = comboBox.getSelectedItem().toString();
					float sum = Float.valueOf(mounth)*Float.valueOf(textField_2.getText())+Float.valueOf(textField_3.getText());
					if (f >= sum) {
						flag = true;
						i = um.update(sum, uid);
					} else {
						JOptionPane.showMessageDialog(null, "余额不足");
					}
					if (i == 1 && flag) {
						JOptionPane.showMessageDialog(null, "订单提交成功");
						om.insertOder(sum,uid, c,mounth);
					}
				}
			});
			buttonPane.add(btnNewButton_2);

		}
	}
}
