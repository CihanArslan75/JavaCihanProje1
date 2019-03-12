package com.cihan.swing.ui.user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cihan.swing.model.user.Role;
import com.cihan.swing.model.user.User;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.utils.DatabaseBaseService;
import com.cihan.swing.utils.ProductUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {
	private JTextField txtkadi;
	private JPasswordField txtsifre;

	public LoginFrame() {
		InitializeLoginFrame();
	}

	private void InitializeLoginFrame() {
		setTitle("Giriş Ekranı");
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131 :");
		lblKullancAd.setBounds(28, 41, 132, 16);
		getContentPane().add(lblKullancAd);

		JLabel lblSifre = new JLabel("\u015Eifre :");
		lblSifre.setBounds(28, 90, 56, 16);
		getContentPane().add(lblSifre);

		JTextField txtUsername = new JTextField();
		txtUsername.setText("admin");
		txtUsername.setBounds(171, 38, 116, 22);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(171, 87, 116, 22);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnEnter = new JButton("G\u0130R\u0130\u015E");
		 btnEnter.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 System.out.println("aaa:"+txtUsername.getText());
		 if(txtUsername.getText().equals("") || txtUsername.getText()==null  || txtPassword.getText().equals("") ) {
			 JOptionPane.showMessageDialog(LoginFrame.this, "Kullanıcı Adı veya Şifre Boş olamaz !!");
		 }
		 else
		 {
			 DatabaseBaseService<User> userService=new DatabaseBaseService<User>();
			 List<User> user = null;
			 try {
				user = userService.search("username",txtUsername.getText(),new User());
				System.out.println("bbbbb:"+user);
			    if(user==null ) {
			    	JOptionPane.showMessageDialog(LoginFrame.this, "Kullanıcı Yok !!");
					 
				 }
				 else
				 {
					 if(!user.get(0).getPassword().equals(txtPassword.getText()))
					 {
						 JOptionPane.showMessageDialog(LoginFrame.this, "Şifre Yanlış !!");
					 }
					 else {
						
						 MenuFrame menuFrame=new MenuFrame();
						 menuFrame.setVisible(true);
						 LoginFrame.this.setVisible(false);
						 ProductUtil.user=user.get(0);
						 }
				
				 }
			
			
			 } catch (Exception e1) {
				 e1.printStackTrace();
				 if( txtUsername.getText().equals("admin"))
				 { JOptionPane.showMessageDialog(LoginFrame.this, "admin olarak  bağlandınız...!!!");
					 User userAdmin=new User();
					 userAdmin.setUsername(txtUsername.getText());
					 userAdmin.setUname(txtUsername.getText());
					 userAdmin.setPassword(txtPassword.getText());
					 userAdmin.setInsertDate(new Date());
					 userAdmin.setState(1);
					 userAdmin.setRol(Role.ADMIN);
					 userService.save(userAdmin);
					 MenuFrame menuFrame=new MenuFrame();
					 menuFrame.setVisible(true);
					 LoginFrame.this.setVisible(false);
					 try {
						user = userService.search("username",txtUsername.getText(),new User());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					 ProductUtil.user=user.get(0);
					
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(LoginFrame.this, "Kullanıcı Yok !!"); 
				 }
								  
			 }
			 
			 }
		 }
		
		 });
		btnEnter.setBounds(233, 159, 97, 25);
		getContentPane().add(btnEnter);

		JButton btnCancel = new JButton("\u0130PTAL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame.this.dispose();
			}
		});
		btnCancel.setBounds(75, 159, 97, 25);
		getContentPane().add(btnCancel);
	}
}
