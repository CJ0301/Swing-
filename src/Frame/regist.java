package Frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import Entity.Userinfo;
import SqlManage.UserManager;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;

public class regist extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int genda = 1;
	private UserManager um = new UserManager();

	/**
	 * Create the dialog.
	 */
	public regist() {
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

		JLabel label = new JLabel("用户名");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label.setBounds(77, 20, 80, 24);
		contentPanel.add(label);

		TextField tname = new TextField();
		tname.setBounds(163, 20, 167, 24);
		contentPanel.add(tname);

		JLabel label_1 = new JLabel("密码");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_1.setBounds(77, 60, 80, 24);
		contentPanel.add(label_1);

		JPasswordField password = new JPasswordField();
		password.setBounds(163, 60, 167, 24);
		contentPanel.add(password);

		JLabel label_2 = new JLabel("姓名：");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_2.setBounds(77, 100, 80, 24);
		contentPanel.add(label_2);

		TextField name = new TextField();
		name.setBounds(163, 100, 167, 24);
		contentPanel.add(name);

		JLabel label_3 = new JLabel("身份证：");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_3.setBounds(77, 140, 80, 24);
		contentPanel.add(label_3);

		TextField id = new TextField();
		id.setBounds(163, 140, 167, 24);
		contentPanel.add(id);

		JLabel label_4 = new JLabel("手机号：");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_4.setBounds(77, 180, 80, 24);
		contentPanel.add(label_4);

		TextField phone = new TextField();
		phone.setBounds(163, 180, 167, 24);
		contentPanel.add(phone);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(regist.class.getResource("/res/bg.png")));
		lblNewLabel.setBounds(0, -25, 444, 303);
		contentPanel.add(lblNewLabel);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				final JButton okButton = new JButton("", new ImageIcon(this.getClass().getResource("/res/confirm.png")));
				okButton.setContentAreaFilled(false);//除去默认的背景填充
				okButton.setToolTipText("确认");
				okButton.setBorderPainted(false);
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Userinfo user = new Userinfo(phone.getText(), password.getText(), tname.getText(), id.getText(),
								Character.valueOf((char) genda));
						if (um.save(user) == 1) {
							JOptionPane.showMessageDialog(null, "注册成功");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "注册失败");
						}
					}
				});

				JRadioButton r1 = new JRadioButton("男");
				JRadioButton r2 = new JRadioButton("女");
				JRadioButton r3 = new JRadioButton("其他");
				buttonPane.add(r1);
				r1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (r1.isSelected()) {
							r2.setSelected(false);
							r3.setSelected(false);
							genda = 1;
						}

					}
				});

				buttonPane.add(r2);
				r2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (r2.isSelected()) {
							r1.setSelected(false);
							r3.setSelected(false);
							genda = 2;
						}

					}
				});

				buttonPane.add(r3);
				r3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (r3.isSelected()) {
							r1.setSelected(false);
							r2.setSelected(false);
							genda = 3;
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
