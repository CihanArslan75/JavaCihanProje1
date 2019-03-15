package com.cihan.swing.ui.user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cihan.swing.model.user.Role;
import com.cihan.swing.model.user.StateEnum;
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
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class LoginFrame extends JFrame {
	private JTextField txtkadi;
	private JPasswordField txtsifre;

	public LoginFrame() {
		InitializeLoginFrame();
	}

	private void InitializeLoginFrame() {
		setTitle("Giriş Ekranı");
		setBounds(100, 100, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 28, 458, 291);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131 :");
		lblKullancAd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKullancAd.setBounds(36, 42, 132, 16);
		panel.add(lblKullancAd);
				
		JTextField txtUsername = new JTextField();
		txtUsername.setBounds(179, 39, 116, 22);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
						
		JLabel lblSifre = new JLabel("\u015Eifre :");
		lblSifre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSifre.setBounds(36, 91, 56, 16);
		panel.add(lblSifre);
								
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(179, 88, 116, 22);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
										
		JButton btnCancel = new JButton("\u0130PTAL");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(83, 160, 97, 25);
		panel.add(btnCancel);
												
		JButton btnEnter = new JButton("G\u0130R\u0130\u015E");
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnter.setBounds(241, 160, 97, 25);
		panel.add(btnEnter);
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
					 userAdmin.setState(StateEnum.NORMAL);
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
			btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					LoginFrame.this.dispose();
			}
		});
	}
}
