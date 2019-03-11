package com.cihan.swing.ui.menu;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cihan.swing.ui.log.LogFrame;
import com.cihan.swing.ui.product.ProductFrame;
import com.cihan.swing.ui.user.LoginFrame;
import com.cihan.swing.ui.user.UserFrame;
import com.cihan.swing.utils.ProductUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuFrame extends JFrame{
	public MenuFrame() {
		initializeMenuFrame();
	}
	private void initializeMenuFrame() {
		setTitle("MENÜ");
		getContentPane().setLayout(null);
		setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
		setVisible(true);
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 520, 346);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnKullaniciMenu = new JButton("KULLANICI İŞLEMLERİ");
		btnKullaniciMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame u=new UserFrame();
				u.setVisible(true);
				MenuFrame.this.setVisible(false);
			}
		});
		btnKullaniciMenu.setBounds(90, 40, 300, 25);
		panel.add(btnKullaniciMenu);
		
		JButton btnLogMenu = new JButton("LOG GÖRÜNTÜLEME");
		btnLogMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogFrame u=new LogFrame();
				u.setVisible(true);
				MenuFrame.this.setVisible(false);
			}
		});
		btnLogMenu.setBounds(90, 90, 300, 25);
		panel.add(btnLogMenu);
		
		JButton btnProductMenu = new JButton("ÜRÜN İŞLEMLERİ");
		btnProductMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductFrame p=new ProductFrame();
				p.setVisible(true);
				MenuFrame.this.setVisible(false);
			}
		});
		btnProductMenu.setBounds(90, 140, 300, 25);
		panel.add(btnProductMenu);
		
		JButton btnExit = new JButton("MENÜDEN ÇIK");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(90, 190, 300, 25);
		panel.add(btnExit);
	}
}
