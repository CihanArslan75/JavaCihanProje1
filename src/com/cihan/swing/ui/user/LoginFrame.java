package com.cihan.swing.ui.user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.cihan.swing.model.user.User;
import com.cihan.swing.runner.Runner;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.utils.DatabaseBaseService;

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

		JTextField txtKullAdi = new JTextField();
		txtKullAdi.setText("admin");
		txtKullAdi.setBounds(171, 38, 116, 22);
		getContentPane().add(txtKullAdi);
		txtKullAdi.setColumns(10);

		JPasswordField txtSifre = new JPasswordField(10);
		txtSifre.setBounds(171, 87, 116, 22);
		getContentPane().add(txtSifre);
		txtSifre.setColumns(10);

		JButton btnGiris = new JButton("G\u0130R\u0130\u015E");
		 btnGiris.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
		 DatabaseBaseService<User> userService=new DatabaseBaseService<User>();
		 List<User> user = null;
		
		 try {
			 user = userService.search("username",txtKullAdi.getText(),new User());
			
			 if(user!=null ) {
				 if(!user.get(0).getPassword().equals(txtSifre.getText()))
				 {
					 JOptionPane.showMessageDialog(LoginFrame.this, "Şifre Yanlış !!");
				 }
				 else {
					
					 MenuFrame menuFrame=new MenuFrame();
					 menuFrame.setVisible(true);
					 LoginFrame.this.setVisible(false);
					 Runner.user=user.get(0);
					 }
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(LoginFrame.this, "Kullanıcı Yok !!");
			 }
		
		
		 } catch (Exception e1) {
			
			 e1.printStackTrace();
//			 if( txtKullAdi.getText().equals("admin"))
//			 {
//				 User userAdmin=new User();
//				 userAdmin.setUsername(txtKullAdi.getText());
//				 userAdmin.setPassword(txtSifre.getText());
//				 userAdmin.setUserInsertDate(new Date());
//				 userService.save(userAdmin);
//				 ProductFrame urun=new ProductFrame();
//				 urun.setVisible(true);
//				 LoginFrame.this.setVisible(false);
//				 Runner.user=user.get(0).getId();
//				 //JOptionPane.showMessageDialog(LoginFrame.this, "admin olarak  bağlandınız...!!!");
//			 }
//			 else
//			 {
				 JOptionPane.showMessageDialog(LoginFrame.this, "SQL çalıŞtırılırken bir hata oldu...!!!");
//			 }
		 }
		 }
		
		 });
		btnGiris.setBounds(233, 159, 97, 25);
		getContentPane().add(btnGiris);

		JButton btnIptal = new JButton("\u0130PTAL");
		btnIptal.setBounds(75, 159, 97, 25);
		getContentPane().add(btnIptal);
	}
}
