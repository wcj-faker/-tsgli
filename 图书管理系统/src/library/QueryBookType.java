package library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bookType.BookType;
import bookType.BookTypeJDBC;
import bookType.BookTypeTableModel;

public class QueryBookType {
	public static void query(JFrame f){
		final JDialog d = new JDialog(f, "查询图书类别", true);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		d.setSize(500, 400);
		d.setLocationRelativeTo(null);
		
		d.setLayout(new BorderLayout());
		
		
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setLayout(new FlowLayout());
		JLabel ltitle = new JLabel("     ---------详细信息---------");
		ltitle.setPreferredSize(new Dimension(d.getWidth(), 60));
		ltitle.setFont(new Font("楷体", Font.BOLD, 25));
		ltitle.setForeground(Color.ORANGE);
		panel1.add(ltitle);
		
		JLabel ltypeid = new JLabel("类别编号 : ");
		ltypeid.setFont(new Font("楷体", Font.BOLD, 30));
		ltypeid.setForeground(Color.CYAN);
		panel1.add(ltypeid);
		final JTextField tftypeid = new JTextField("");
		tftypeid.setEditable(false);
		tftypeid.setFont(new Font("楷体", Font.BOLD, 30));
		tftypeid.setForeground(Color.darkGray);
		tftypeid.setPreferredSize(new Dimension(250, 35));
		panel1.add(tftypeid);
		
		JLabel ltypename = new JLabel("类别名称 : ");
		ltypename.setFont(new Font("楷体", Font.BOLD, 30));
		ltypename.setForeground(Color.CYAN);
		panel1.add(ltypename);
		final JTextField tftypename = new JTextField("");
		tftypename.setEditable(false);
		tftypename.setFont(new Font("楷体", Font.BOLD, 30));
		tftypename.setForeground(Color.darkGray);
		tftypename.setPreferredSize(new Dimension(250, 35));
		panel1.add(tftypename);
		JLabel ltitle1 = new JLabel("     ----请输入图书类别编号----");
		ltitle1.setPreferredSize(new Dimension(d.getWidth(), 30));
		ltitle1.setFont(new Font("楷体", Font.BOLD, 25));
		ltitle1.setForeground(Color.ORANGE);
		panel1.add(ltitle1);
		JLabel ltypeid1 = new JLabel("类别编号: ");
		ltypeid1.setFont(new Font("楷体", Font.BOLD, 25));
		ltypeid1.setForeground(Color.CYAN);
		panel1.add(ltypeid1);
		final JTextField tftypeid1 = new JTextField("");
		
		tftypeid1.setFont(new Font("楷体", Font.BOLD, 25));
		tftypeid1.setForeground(Color.darkGray);
		tftypeid1.setPreferredSize(new Dimension(150, 28));
		panel1.add(tftypeid1);
		//添加提交按钮
		JButton b = new JButton("查询");
		b.setFont(new Font("楷体", Font.BOLD, 25));
		b.setPreferredSize(new Dimension(90, 30));
		panel1.add(b);
		JLabel ltitle2 = new JLabel("    ---------------------------");
		ltitle2.setPreferredSize(new Dimension(d.getWidth(), 30));
		ltitle2.setFont(new Font("楷体", Font.BOLD, 25));
		ltitle2.setForeground(Color.ORANGE);
		panel1.add(ltitle2);
		//面板二，用来放置图书类别信息
		panel2.setPreferredSize(new Dimension(ImageObserver.WIDTH, 80));
		//创建TableModel对象
		final BookTypeTableModel bttm = new BookTypeTableModel();
		//创建Table对象
		final JTable table = new JTable(bttm);
		//将Table加入到滚动面板中
		panel2.setLayout(new BorderLayout());
		JScrollPane sp = new JScrollPane(table);
		//将滚动面板加入到panel2中
		panel2.add(sp,BorderLayout.CENTER);
		d.add(panel1, BorderLayout.CENTER);
		d.add(panel2, BorderLayout.SOUTH);
		
		//设置按钮监听器
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取文本框信息
				String typeid = tftypeid1.getText();
				BookTypeJDBC btj = new BookTypeJDBC();
				List<BookType> lsbooktype = btj.list();
				if(typeid.equals("")){
					JOptionPane.showMessageDialog(d, "输入为空");
				}else{
					int i;
					for(i=0; i<lsbooktype.size(); i++){
						if(typeid.equals(lsbooktype.get(i).getId())){
							//存在，进行查询
							String typename = lsbooktype.get(i).getTypename();
							tftypeid.setText(typeid);
							tftypename.setText(typename);
							break;
						}
					}
					if(i>=lsbooktype.size()){
						JOptionPane.showMessageDialog(d, "不存在该图书类型，请重新编号");
					}
				}
			}
		});
		d.setVisible(true);
	}
}
