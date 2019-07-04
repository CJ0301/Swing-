/**
 * 
 */
package Frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Order;
import SqlManage.CarManager;
import SqlManage.OrderManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;

/**
 * @author 78486
 *
 */
public class Buycar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private CarManager manager = new CarManager();
	private OrderManager om = new OrderManager();
	private Order order = new Order();

	/**
	 * Create the dialog.
	 */
	public Buycar(String orders, String i) {
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		order = om.orderInfo(orders);
		JLabel lblNewLabel_1 = new JLabel("开始时间");
		lblNewLabel_1.setBounds(75, 60, 72, 25);
		contentPanel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setText(order.getStarttime().toString());
		textField_1.setEnabled(false);
		textField_1.setBounds(186, 60, 100, 25);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("结束时间");
		lblNewLabel_2.setBounds(75, 98, 72, 25);
		contentPanel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setText(order.getEndtime().toString());
		textField_2.setEnabled(false);
		textField_2.setBounds(186, 98, 100, 25);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon(Carinfo.class.getResource("/res/" + i)));
		lblNewLabel_3.setBounds(300, 60, 118, 103);
		contentPanel.add(lblNewLabel_3);
		
		JLabel label = new JLabel("评价一下：");
		label.setBounds(172, 148, 88, 18);
		contentPanel.add(label);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(106, 176, 211, 57);
		contentPanel.add(editorPane);
		
		JButton btnNewButton = new JButton("提交评价");
		btnNewButton.setBounds(158, 238, 113, 27);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.update(order.getCid(), editorPane.getText());	
				JOptionPane.showMessageDialog(null, "感谢您的评价");
			}
		});
		contentPanel.add(btnNewButton);
	}
}
