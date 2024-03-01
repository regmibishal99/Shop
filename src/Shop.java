import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
//
//import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Shop {

	private JFrame frame;
	private JTextField textbname;
	private JTextField textedition;
	private JTextField textprice;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop window = new Shop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Shop() {
		initialize();
		connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
public void connect()
{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/shop","root","");
	}
	catch(ClassNotFoundException ex)
	{
		
	}
	catch (SQLException ex)
	{
		
	}
}
	
	public void table_load()
	{
		try
		{
			pst=con.prepareStatement("select * from book");
			rs=pst.executeQuery();
//table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 833, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(360, 11, 146, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 78, 281, 176);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name:");
		lblNewLabel_1.setBounds(22, 41, 69, 14);
		panel.add(lblNewLabel_1);
		
		textbname = new JTextField();
		textbname.setBounds(81, 38, 138, 20);
		panel.add(textbname);
		textbname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition:");
		lblNewLabel_1_1.setBounds(22, 74, 69, 14);
		panel.add(lblNewLabel_1_1);
		
		textedition = new JTextField();
		textedition.setColumns(10);
		textedition.setBounds(81, 71, 138, 20);
		panel.add(textedition);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setBounds(22, 100, 69, 14);
		panel.add(lblNewLabel_1_1_1);
		
		textprice = new JTextField();
		textprice.setColumns(10);
		textprice.setBounds(81, 99, 138, 20);
		panel.add(textprice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 309, 281, 59);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book ID:");
		lblNewLabel_1_2.setBounds(10, 22, 69, 14);
		panel_1.add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(73, 19, 138, 20);
		panel_1.add(textField);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname=textbname.getText();
				edition=textedition.getText();
				price=textprice.getText();
				try {
					pst=con.prepareStatement("Insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2,edition);
					pst.setString(3, price);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added");
					textbname.setText("");
					textedition.setText("");
					textprice.setText(""); 
                    textbname.requestFocus() ;   					
					
				}catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(25, 265, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit\r\n");
		btnExit.setBounds(116, 265, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(209, 265, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(370, 78, 382, 253);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(417, 345, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete\r\n");
		btnDelete.setBounds(577, 345, 89, 23);
		frame.getContentPane().add(btnDelete);
	}

}