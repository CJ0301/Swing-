/**
 * 
 */
package Frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Entity.Userinfo;
import SqlManage.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButton;

/**
 * @author 78486
 *
 */
public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("New label");
	private final UserManager um = new UserManager();

	public Login() {
		setBounds(100, 100, 450, 300);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		try {
			Image image = ImageIO.read(HomePage.class.getResource("/res/title.png"));
			setIconImage(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label.setBounds(77, 83, 80, 24);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_1.setBounds(77, 114, 80, 24);
		contentPanel.add(label_1);

		TextField textField = new TextField();
		textField.setBounds(163, 83, 167, 24);
		contentPanel.add(textField);

		JPasswordField textField_1 = new JPasswordField();
		textField_1.setBounds(163, 114, 167, 24);
		contentPanel.add(textField_1);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/res/bg.png")));
		lblNewLabel.setBounds(0, -25, 444, 303);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			JRadioButton radioButton_1 = new JRadioButton("管理员");
			radioButton_1.setSelected(true);
			buttonPane.add(radioButton_1);
			JRadioButton radioButton = new JRadioButton("用户");
			buttonPane.add(radioButton);
			radioButton_1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (radioButton_1.isSelected()) {
						radioButton.setSelected(false);
					}

				}
			});
			radioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (radioButton.isSelected()) {
						radioButton_1.setSelected(false);
					}

				}
			});
			{
				final JButton okButton = new JButton("", new ImageIcon(this.getClass().getResource("/res/confirm.png")));
				okButton.setContentAreaFilled(false);//除去默认的背景填充
				okButton.setToolTipText("确认");
				okButton.setBorderPainted(false);
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						boolean flag = false;
						if (radioButton.isSelected()) {
							List<Userinfo> list = new ArrayList<Userinfo>();
							String id = null;
							Userinfo user = new Userinfo(textField.getText(), textField_1.getText());
							list = um.findByTname(user.getTname());
							for (Userinfo s : list) {
								if (s.getPassword().equals(user.getPassword())) {
									flag = true;
									JOptionPane.showMessageDialog(null, "登陆成功");
									id = s.getId();
									UserTable jtable = new UserTable(id);
									jtable.setVisible(flag);
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "登陆失败");
								}
							}
						} else if (radioButton_1.isSelected()) {
							flag = true;
							if (textField.getText().equals("admin") && textField_1.getText().equals("admin")) {
								JOptionPane.showMessageDialog(null, "登陆成功");
								AdminTable jtable = new AdminTable();
								jtable.setVisible(flag);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "登陆失败");
							}
						}
					}
				});

				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JButton cancelButton = new JButton("", new ImageIcon(this.getClass().getResource("/res/cancel.png")));
				cancelButton.setContentAreaFilled(false);//除去默认的背景填充
				cancelButton.setToolTipText("取消");
				cancelButton.setBorderPainted(false);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
