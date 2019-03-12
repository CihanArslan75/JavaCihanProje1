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

public class UserEnterFrame extends JFrame {
	private JTextField txtUserName;
	private JTextField txtUname;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JPanel panel_2;
    private List<User> userList;
    private JTextField txtPassword;
    private JComboBox cmbRol;
    private Container c=getContentPane();
    DatabaseBaseService<User> userServis=new DatabaseBaseService<User>();
    private JButton btnMenu;
      
	public UserEnterFrame() {
		 initializeUserFrame();
		 getRolCombo() ;
	}
	
	private void initializeUserFrame() {
		setTitle("KULLANICI GİRİŞ İŞLEMLERİ");
		setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(null);
		
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBounds(35, 26, 821, 561);
		c.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUserName = new JLabel("Kullanıcı Adı :");
		lblUserName.setBounds(150, 41, 132, 16);
		panel_2.add(lblUserName);
			
		txtUserName = new JTextField();
		txtUserName.setBounds(300, 41, 200, 22);
		panel_2.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Şifresi :");
		lblPassword.setBounds(150, 76, 56, 16);
		panel_2.add(lblPassword);
		
		JLabel lblUname = new JLabel("Adı :");
		lblUname.setBounds(150, 111, 56, 16);
		panel_2.add(lblUname);
	
		
		txtUname = new JTextField();
		txtUname.setBounds(300, 111, 200, 22);
		panel_2.add(txtUname);
		txtUname.setColumns(10);
		
		JLabel lblSurname = new JLabel("Soyadı :");
		lblSurname.setBounds(150, 146, 56, 16);
		panel_2.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(300, 146, 200, 22);
		panel_2.add(txtSurname);
		txtSurname.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(150, 181, 56, 16);
		panel_2.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(300, 181, 200, 22);
		panel_2.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol :");
		lblRol.setBounds(150, 216, 56, 16);
		panel_2.add(lblRol);
		
		JButton btnSave = new JButton("YENİ KAYIT");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setEditable(true);
				if(txtUserName.getText()==null) {
					JOptionPane.showMessageDialog(UserEnterFrame.this, "Kullanıcı Adı boş olamaz !"); 
				}
				else
				{
					if(	userSave())
					{
						JOptionPane.showMessageDialog(UserEnterFrame.this, "Kaydetme İşlemi Başarılı"); 
						formuTemizle();
					} else {	
						JOptionPane.showMessageDialog(UserEnterFrame.this, "Kaydetme İşleminde Hata Oluştu !"); 
					}
				}
			
			}
		});
		btnSave.setBounds(350, 300, 150, 25);
		panel_2.add(btnSave);
		
		cmbRol = new JComboBox();
		cmbRol.setBounds(300, 216, 200, 22);
		panel_2.add(cmbRol);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(300, 76, 200, 22);
		panel_2.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnClear = new JButton("FORMU TEMİZLE");
		btnClear.setBounds(150, 300, 150, 25);
		panel_2.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formuTemizle();
			}
		});
		
		btnMenu = new JButton("MENÜYE DÖN");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame m=new MenuFrame();
				m.setVisible(true);
				UserEnterFrame.this.setVisible(false);
			}
		});
		btnMenu.setBounds(692, 599, 164, 25);
		getContentPane().add(btnMenu);
		
	}
	
	
	
	private void getRolCombo() {
	      cmbRol.setModel(new DefaultComboBoxModel(Role.values()));
	 }
	 
	private void panel_2_remove() {
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
		 getRolCombo() ;
	 }
	 
	 public boolean userSave(){
		 User user = new User();
		 if(txtUserName.getText()!=null) user.setUsername(txtUserName.getText()); 
		 if(txtPassword.getText()!=null) user.setPassword(txtPassword.getText());	
		 if(txtUname.getText()!=null)    user.setUname(txtUname.getText());
		 if(txtSurname.getText()!=null)  user.setSurname(txtSurname.getText());
		 if(txtEmail.getText()!=null)    user.setEmail(txtEmail.getText());
		 if(cmbRol.getSelectedItem()!=null) user.setRol((Role) cmbRol.getSelectedItem());
		 user.setInsertDate(new Date());
		 user.setInsertUser(ProductUtil.user.getId());
		 user.setState(1);
		 
		 return userServis.save(user);
		
		 
	 }

}
