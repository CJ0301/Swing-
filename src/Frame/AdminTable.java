package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Entity.Car;
import SqlManage.CarManager;

public class AdminTable extends JFrame {

	private static final long serialVersionUID = -4009218492492474402L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	private DefaultTableModel tableModel;// 定义表格模型对象
	private MTable table;// 定义表格对象
	private JTextField nameTextField;
	private JTextField typeTextField;
	private JTextField priceTextField;
	private JTextField imageTextField;
	private JTextField insureTextField;
	private int rowCount;
	private CarManager cm = new CarManager();
	private List<String> unique = new ArrayList<String>();

	public AdminTable() {
		// TODO Auto-generated constructor stub

		super();
		setTitle("租车管理系统");
		setSize(1400, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		try {
			Image image = ImageIO.read(HomePage.class.getResource("/res/title.png"));
			setIconImage(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		TextField text = new TextField(10);
		String[] columnNames = { "车名", "车型", "价格", "图片地址", "保险" };// 定义表格列名数组
		JList<String> jli = new JList<String>(new MyListModel(columnNames));
		String[][] tableValues = {};// 定义表格数据数组
		// 创建指定表格列名和表格数据的表格模型
		tableModel = new DefaultTableModel(tableValues, columnNames);
		table = new MTable(tableModel);// 创建指定表格模型的表格
		table.setRowSorter(new TableRowSorter<>(tableModel));// 设置表格的排序器
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false); // 设置表格列不可重排
		table.setSelectionBackground(Color.YELLOW);
		// 设置表格的选择模式为单选
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// 为表格添加鼠标事件监听器
		// 为表格添加鼠标事件监听器
		table.addMouseListener(new MouseAdapter() {
			// 发生了点击事件
			public void mouseClicked(MouseEvent e) {
				// 获得被选中行的索引
				int selectedRow = table.getSelectedRow();
				// 从表格模型中获得指定单元格的值
				Object oname = tableModel.getValueAt(selectedRow, 0);
				// 从表格模型中获得指定单元格的值
				Object otype = tableModel.getValueAt(selectedRow, 1);
				Object oprice = tableModel.getValueAt(selectedRow, 2);
				Object oimage = tableModel.getValueAt(selectedRow, 3);
				Object oinsure = tableModel.getValueAt(selectedRow, 4);
				nameTextField.setText(oname.toString());// 将值赋值给文本框
				typeTextField.setText(otype.toString());// 将值赋值给文本框
				priceTextField.setText(oprice.toString());// 将值赋值给文本框
				imageTextField.setText(oimage.toString());// 将值赋值给文本框
				insureTextField.setText(oinsure.toString());// 将值赋值给文本框
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.add(jli);
		panel.add(text);

		JButton b2 = new JButton("", new ImageIcon(this.getClass().getResource("/res/search.png")));
		b2.setContentAreaFilled(false);//除去默认的背景填充
		b2.setToolTipText("搜索");
		panel.add(b2);

		panel.add(new JLabel("车名:"));
		nameTextField = new JTextField("", 10);
		panel.add(nameTextField);

		panel.add(new JLabel("车型:"));
		typeTextField = new JTextField("", 10);
		panel.add(typeTextField);

		panel.add(new JLabel("价格:"));
		priceTextField = new JTextField("", 10);
		panel.add(priceTextField);

		panel.add(new JLabel("图片地址:"));
		imageTextField = new JTextField("", 10);
		panel.add(imageTextField);

		panel.add(new JLabel("保险:"));
		insureTextField = new JTextField("", 10);
		panel.add(insureTextField);

		List<Car> li = new ArrayList<Car>();
		li = cm.selectAll();
		for (Car c : li) {
			String[] rowValues = { c.getName(), c.getType(), c.getPrice(), c.getImage(),
					String.valueOf(c.getInsure()) };// 创建表格行数组
			tableModel.addRow(rowValues);// 向表格模型中添加一行
			rowCount++;
			unique = cm.selectid();
		}

		final JButton addButton = new JButton("", new ImageIcon(this.getClass().getResource("/res/add.png")));
		addButton.setContentAreaFilled(false);//除去默认的背景填充
		addButton.setToolTipText("增加");
		addButton.setBorderPainted(false);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowValues = { nameTextField.getText(), typeTextField.getText(), priceTextField.getText(),
						imageTextField.getText(), insureTextField.getText() };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				rowCount++;

				cm.insert(new Car(nameTextField.getText(), typeTextField.getText(), priceTextField.getText(),
						imageTextField.getText(), Float.valueOf(insureTextField.getText())));

			}
		});
		panel.add(addButton);

		final JButton updButton = new JButton("", new ImageIcon(this.getClass().getResource("/res/update.png")));
		updButton.setContentAreaFilled(false);//除去默认的背景填充
		updButton.setToolTipText("更新");
		updButton.setBorderPainted(false);
		updButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// 获得被选中行的索引
				String id = unique.get(selectedRow);

				cm.update(new Car(nameTextField.getText(), typeTextField.getText(), priceTextField.getText(),
						imageTextField.getText(), Float.valueOf(insureTextField.getText())), id);
				tableModel.setValueAt(nameTextField.getText(), selectedRow, 0);// 修改表格模型当中的指定值
				tableModel.setValueAt(typeTextField.getText(), selectedRow, 1);// 修改表格模型当中的指定值
				tableModel.setValueAt(priceTextField.getText(), selectedRow, 2);// 修改表格模型当中的指定值
				tableModel.setValueAt(imageTextField.getText(), selectedRow, 3);// 修改表格模型当中的指定值
				tableModel.setValueAt(insureTextField.getText(), selectedRow, 4);// 修改表格模型当中的指定值
			}

		});
		panel.add(updButton);

		final JButton delButton = new JButton("", new ImageIcon(this.getClass().getResource("/res/remove.png")));
		delButton.setContentAreaFilled(false);//除去默认的背景填充
		delButton.setToolTipText("删除");
		delButton.setBorderPainted(false);
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				int[] selectedRows = table.getSelectedRows();// 获得所有被选中行的索引
				int len = selectedRows.length;
				List<String> delete = new ArrayList<String>();
				if (len==1) {
					flag = true;
				}else if(selectedRows[0] > selectedRows[1]){
					flag = false;
				}
				int index = len-1;
				for (int i : selectedRows) {
					delete.add(unique.get(i));
					if (flag) {
						tableModel.removeRow(selectedRows[0]);
					} else {
						tableModel.removeRow(selectedRows[index--]);
					}
				}
				for (String id : delete) {
					cm.delete(id);
				}
				JOptionPane.showMessageDialog(null, "成功删除"+len+"条数据");
			}

		});
		panel.add(delButton);

		b2.setBorderPainted(false);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = jli.getSelectedIndex();
				List<Integer> nums = new ArrayList<Integer>();
				for (int i = 0; i < table.getRowCount(); i++) {
					Object onecell = table.getValueAt(i, flag);
					if (((String) onecell).equals(text.getText())) {
						nums.add(i);
					}
				}
				boolean fl = true;
				for (Integer i : nums) {
					if (fl) {
						table.setRowSelectionInterval(i.intValue(), i.intValue());// 设置选中行
						fl = false;
					} else if (!fl) {
						table.addRowSelectionInterval(i.intValue(), i.intValue());
					}

				}
				if (nums.size() != 0) {
					JOptionPane.showMessageDialog(null, "找到" + nums.size() + "条车辆数据");
				} else {
					JOptionPane.showMessageDialog(null, "未找到车辆数据");
				}
			}
		});
		if (li.size() != 0) {
			JOptionPane.showMessageDialog(null, "找到" + li.size() + "条车辆数据");
		} else {
			JOptionPane.showMessageDialog(null, "欢迎使用本系统");
		}
	}

}

class MyListModel extends AbstractListModel<String> {

	private String[] contents = {};

	public MyListModel(String[] contents) {
		this.contents = contents;
	}

	@Override
	public int getSize() {
		return contents.length;
	}

	@Override
	public String getElementAt(int x) {
		if (x < contents.length)
			return contents[x++];
		else
			return null;
	}

}

class MTable extends JTable { // 实现自己的表格类
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MTable(Vector<Vector<String>> rowData, Vector<String> columnNames) {
		super(rowData, columnNames);
	}

	public MTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	@Override
	public JTableHeader getTableHeader() { // 定义表格头
		// 获得表格头对象
		JTableHeader tableHeader = super.getTableHeader();
		tableHeader.setReorderingAllowed(false); // 设置表格列不可重排
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer(); // 获得表格头的单元格对象
		// 设置列名居中显示
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		return tableHeader;
	}

	// 定义单元格
	@Override
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass); // 获得表格的单元格对象
		// 设置单元格内容居中显示
		cr.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		return cr;
	}

	@Override
	public boolean isCellEditable(int row, int column) { // 表格不可编辑
		return false;
	}
}
