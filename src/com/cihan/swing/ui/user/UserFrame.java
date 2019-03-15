package com.cihan.swing.ui.user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.table.DefaultTableModel;

import com.cihan.swing.model.user.Role;
import com.cihan.swing.model.user.StateEnum;
import com.cihan.swing.model.user.User;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.utils.DatabaseBaseService;
import com.cihan.swing.utils.ProductUtil;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

public class UserFrame extends JFrame {
	private JTextField txtUserFind;
	private JTextField txtUserName;
	private JTextField txtUname;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JTable table;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane_1;
    private List<User> userList;
    private JTextField txtPassword;
    private JComboBox cmbRol;
    private Container c=getContentPane();
    private JTextField txtId;
    private JButton btnMenu;
	private Integer insertUser;
	private Date    insertDate;
	private Integer updateUser;
	private Date    updateDate;
	private Integer deleteUser;
	private Date    deleteDate;
      
	public UserFrame() {
		 initializeUserFrame();
		 initTableUser() ;
		 getRolCombo() ;
	}
	
	private void initializeUserFrame() {
		setTitle("KULLANICI İŞLEMLERİ");
		setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(null);
				
		panel = new JPanel();
		panel.setBounds(12, 13, 820, 60);
		c.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserFind = new JLabel("Kullanıcı Adı :");
		lblUserFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserFind.setBounds(12, 13, 106, 21);
		panel.add(lblUserFind);
		
		txtUserFind = new JTextField();
		txtUserFind.setBounds(110, 13, 200, 22);
		panel.add(txtUserFind);
		txtUserFind.setColumns(10);
		
		table = new JTable();
		panel_1 = new JPanel();
		panel_1.setBounds(12, 94, 820, 150);
		c.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{820, 0};
		gbl_panel_1.rowHeights = new int[]{150, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
				
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		       
      	JButton btnUserFind = new JButton("ARA");
      	btnUserFind.setBounds(350, 12, 97, 25);
		panel.add(btnUserFind);
		btnUserFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 panel_2_remove();
				 getTable();
				 
			}
		});
		
		JButton btnClear = new JButton("FORMU TEMİZLE");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formuTemizle();
			}
		});
		btnClear.setBounds(480, 12, 169, 25);
		panel.add(btnClear);
		
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBounds(130, 267, 569, 320);
		c.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUserName = new JLabel("Kullanıcı Adı :");
		lblUserName.setBounds(100, 30, 132, 16);
		panel_2.add(lblUserName);
		
		txtId = new JTextField();
		txtId.setVisible(false);
    	txtId.setBounds(441, 27, 116, 22);
		panel_2.add(txtId);
			
		txtUserName = new JTextField();
		txtUserName.setEditable(false);
		txtUserName.setBounds(200, 30, 200, 22);
		panel_2.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Şifresi :");
		lblPassword.setBounds(100, 60, 56, 16);
		panel_2.add(lblPassword);
		
		JLabel lblUname = new JLabel("Adı :");
		lblUname.setBounds(100, 90, 56, 16);
		panel_2.add(lblUname);
	
		
		txtUname = new JTextField();
		txtUname.setBounds(200, 90, 200, 22);
		panel_2.add(txtUname);
		txtUname.setColumns(10);
		
		JLabel lblSurname = new JLabel("Soyadı :");
		lblSurname.setBounds(100, 120, 56, 16);
		panel_2.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(200, 120, 200, 22);
		panel_2.add(txtSurname);
		txtSurname.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(100, 150, 56, 16);
		panel_2.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(200, 150, 200, 22);
		panel_2.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol :");
		lblRol.setBounds(100, 180, 56, 16);
		panel_2.add(lblRol);
		
			
		JButton btnDelete = new JButton("SİL");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userDelete(Integer.parseInt(txtId.getText())))
				{
					JOptionPane.showMessageDialog(UserFrame.this, "Silme İşlemi Başarılı"); 
					formuTemizle();
				} else {
					JOptionPane.showMessageDialog(UserFrame.this, "Silme İşleminde Hata Oluştu !"); 
				}
				
			}
		});
		btnDelete.setBounds(100, 235, 150, 25);
		panel_2.add(btnDelete);
		
		cmbRol = new JComboBox();
		cmbRol.setBounds(200, 180, 200, 22);
		panel_2.add(cmbRol);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(200, 60, 200, 22);
		panel_2.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnUpdate = new JButton("GÜNCELLE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userUpdate(Integer.parseInt(txtId.getText())))
				{
					JOptionPane.showMessageDialog(UserFrame.this, "Güncelleme İşlemi Başarılı"); 
					formuTemizle();
					
				} else  {
				
					JOptionPane.showMessageDialog(UserFrame.this, "Güncelleme İşleminde Hata Oluştu !"); 
				}
				
			}
		});
		btnUpdate.setBounds(330, 235, 150, 25);
		panel_2.add(btnUpdate);
		
		btnMenu = new JButton("MENÜYE DÖN");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame m=new MenuFrame();
				m.setVisible(true);
				UserFrame.this.setVisible(false);
			}
		});
		btnMenu.setBounds(753, 595, 164, 25);
		getContentPane().add(btnMenu);
		
	}
	
	private void initTableUser() {
		 table.setModel(new javax.swing.table.DefaultTableModel(
	             new Object [][] {
	                 {null, null, null, null, null, null,null},
	                 {null, null, null, null, null, null,null},
	                 {null, null, null, null, null, null,null},
	                 {null, null, null, null, null, null,null}
	             },
	             new String [] {"Kullanıcı Adı","Adı","Soyadı","Email" ,"Rol","Kayıt Tarihi","Durumu"}
	         ));
	    scrollPane_1.setViewportView(table);
	}
	
	private void getTable() {
		User user =new User();
		DatabaseBaseService<User> userServis=new DatabaseBaseService<User>();
		user.setUsername(txtUserFind.getText());
		user.setState(StateEnum.NORMAL);
		userList=userServis.search(user);
		String[] columnNames= {"Kullanıcı Adı","Adı","Soyadı","Email" ,"Rol","Kayıt Tarihi","Durumu"};
		String[][] data = new String[userList.size()][7];
		for (int i = 0; i < userList.size(); i++) {
				
			if(userList.get(i).getUsername()!=null) 
				data[i][0] = userList.get(i).getUsername(); 
			else 
				data[i][0]=null;
			if(userList.get(i).getUname()!=null) 
				data[i][1] = userList.get(i).getUname();
			else 
				data[i][1]=null;
			if(userList.get(i).getSurname()!=null) 
				data[i][2] = userList.get(i).getSurname();
			else 
				data[i][2]=null;
			if(userList.get(i).getEmail()!=null) 
				data[i][3] = userList.get(i).getEmail();
			else 
				data[i][3]=null;
			if(userList.get(i).getRol()!=null) 
				data[i][4] = ""+userList.get(i).getRol();
			else 
				data[i][4]=null;
			
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(userList.get(i).getInsertDate()!=null) 
             	data[i][5]=sdf.format(userList.get(i).getInsertDate());
            else 
				data[i][5]=null;
			
			if(userList.get(i).getState()!=null) 
				data[i][6] = ""+userList.get(i).getState();
			else 
				data[i][6]=null;
		    }
			table = new JTable(data,columnNames);
			scrollPane_1.setViewportView(table);
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
						public void valueChanged(ListSelectionEvent e) {
							if (table.getSelectedRow() > -1) {
							    selectTable();
							 }
						} 
					 });

	}
	
	private void selectTable() {
		int i=table.getSelectedRow();
		txtId.setText(String.valueOf(userList.get(i).getId()));
		if(userList.get(i).getUsername()!=null) 
			txtUserName.setText(userList.get(i).getUsername()); 
		else 
			txtSurname.setText(null);
		if(userList.get(i).getPassword()!=null) 
		   	txtPassword.setText(userList.get(i).getPassword()); 
		else 
			txtPassword.setText(null);
		if(userList.get(i).getUname()!=null) 
			txtUname.setText(userList.get(i).getUname());
		else 
			txtUname.setText(null);
		if(userList.get(i).getSurname()!=null) 
			txtSurname.setText(userList.get(i).getSurname());
		else 
			txtSurname.setText(null);
		if(userList.get(i).getEmail()!=null) 
			txtEmail.setText(userList.get(i).getEmail());
		else 
			txtEmail.setText(null);
		if(userList.get(i).getRol()!=null) 
			cmbRol.setSelectedItem(userList.get(i).getRol());
		else 
		cmbRol.setSelectedItem(null);
		
		insertUser=userList.get(i).getInsertUser();
		insertDate=userList.get(i).getInsertDate();
		updateUser=userList.get(i).getUpdateUser();
		updateDate=userList.get(i).getUpdateDate();
		deleteUser=userList.get(i).getDeleteUser();
		deleteDate=userList.get(i).getDeleteDate();
		
	}
	
	private void getRolCombo() {
	      cmbRol.setModel(new DefaultComboBoxModel(Role.values()));
	 }
	 
	private void panel_2_remove() {
		txtId.setText(null);
		txtUserName.setText(null);
		txtPassword.setText(null);
		txtUname.setText(null);
		txtSurname.setText(null);
		txtEmail.setText(null);
		cmbRol.setSelectedItem(null);
	}
	
	public void formuTemizle() {
		 c.removeAll();
		 c.repaint();
		 initializeUserFrame();
		 initTableUser();
		 getRolCombo() ;
	 }
	 
	 public boolean userUpdate(Integer id) {
		 DatabaseBaseService<User> userServis=new DatabaseBaseService<User>();
		 User user = new User();
		 user.setId(id);
		 if(txtUserName.getText()!=null) user.setUsername(txtUserName.getText()); 
		 if(txtPassword.getText()!=null) user.setPassword(txtPassword.getText());	
		 if(txtUname.getText()!=null)    user.setUname(txtUname.getText());
		 if(txtSurname.getText()!=null)  user.setSurname(txtSurname.getText());
		 if(txtEmail.getText()!=null)    user.setEmail(txtEmail.getText());
		 if(cmbRol.getSelectedItem()!=null) user.setRol((Role) cmbRol.getSelectedItem());
		 user.setUpdateDate(new Date());
		 user.setUpdateUser(ProductUtil.user.getId());
		 if(insertDate!=null) user.setInsertDate(insertDate);
		 if(insertUser!=null) user.setInsertUser(insertUser);
		 if(deleteDate!=null) user.setDeleteDate(deleteDate);
		 if(deleteUser!=null) user.setDeleteUser(deleteUser);
		 user.setState(StateEnum.NORMAL);
		 return  userServis.update(user);
		
			 
	 }
	 public boolean userDelete(Integer id) {
		 DatabaseBaseService<User> userServis=new DatabaseBaseService<User>();
		 User user = new User();
		 user.setId(id);
		 if(txtUserName.getText()!=null) user.setUsername(txtUserName.getText()); 
		 if(txtPassword.getText()!=null) user.setPassword(txtPassword.getText());	
		 if(txtUname.getText()!=null)    user.setUname(txtUname.getText());
		 if(txtSurname.getText()!=null)  user.setSurname(txtSurname.getText());
		 if(txtEmail.getText()!=null)    user.setEmail(txtEmail.getText());
		 if(cmbRol.getSelectedItem()!=null) user.setRol((Role) cmbRol.getSelectedItem());
		 user.setDeleteDate(new Date());	
		 user.setDeleteUser(ProductUtil.user.getId());
		 if(insertDate!=null) user.setInsertDate(insertDate);
		 if(insertUser!=null) user.setInsertUser(insertUser);
		 if(updateDate!=null) user.setUpdateDate(updateDate);
		 if(updateUser!=null) user.setUpdateUser(updateUser);
		 user.setState(StateEnum.SILINMIS);  // delete kayıt
		 return userServis.update(user);
         		 
	 }
}
