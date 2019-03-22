package com.cihan.swing.ui.menu;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cihan.swing.model.user.Role;
import com.cihan.swing.ui.excel.ProductExcel;
import com.cihan.swing.ui.log.LogFrame;
import com.cihan.swing.ui.order.OrderFrame;
import com.cihan.swing.ui.product.ProductFrame;
import com.cihan.swing.ui.product.ProductSave;
import com.cihan.swing.ui.user.LoginFrame;
import com.cihan.swing.ui.user.UserEnterFrame;
import com.cihan.swing.ui.user.UserFrame;
import com.cihan.swing.utils.ProductUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class MenuFrame extends JFrame{
	private JMenuBar menuBar ;
	
	public MenuFrame() {
		initializeMenuFrame();
		
		if(ProductUtil.user.getRol().equals(Role.ADMIN))
		{
			initialiazeAdmin();
	    }

	}
	private void initializeMenuFrame() {
		setTitle("MENÜ");
		getContentPane().setLayout(null);
		setBounds(ProductUtil.x1, ProductUtil.y1, 800, 600);
		setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(10, 45, 760, 436);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(41, 25, 688, 377);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ÜRÜN VE STOK TAKİP PROJESİ ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(106, 146, 450, 40);
		panel.add(lblNewLabel);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 950, 26);
		getContentPane().add(menuBar);
		
		JMenu mnProduct = new JMenu("Ürün ve Stok İşlemleri  |");
		menuBar.add(mnProduct);
		
		JMenuItem mntProduct = new JMenuItem("Ürün ve Stok İşlemleri");
		mntProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductFrame p=new ProductFrame();
				p.setVisible(true);
				MenuFrame.this.setVisible(false);
			}
		});
		mnProduct.add(mntProduct);
		
		JMenuItem mntProductEnter = new JMenuItem("Ürün ve Stok Giriş");
		mntProductEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductSave p=new ProductSave();
				p.setVisible(true);
				MenuFrame.this.setVisible(false);
			}
		});
		mnProduct.add(mntProductEnter);
		
		JMenu mnSatIlemleri = new JMenu("Satış İşlemleri  |");
		menuBar.add(mnSatIlemleri);
		
		JMenuItem mntmSatIlemleri = new JMenuItem("Satış İşlemleri");
		mntmSatIlemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame p=new OrderFrame();
				p.setVisible(true);
				MenuFrame.this.setVisible(false);
			}
		});
		mnSatIlemleri.add(mntmSatIlemleri);
		JButton btnExit = new JButton("MENÜDEN ÇIK");
		btnExit.setBounds(494, 494, 254, 25);
		getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void initialiazeAdmin() {
	JMenu mnUser = new JMenu("Kullanıcı İşlemleri  |");
	menuBar.add(mnUser);
	
	JMenuItem mntUser = new JMenuItem("Kullanıcı Görüntüleme ve Güncelleme");
	mntUser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserFrame p=new UserFrame();
			p.setVisible(true);
			MenuFrame.this.setVisible(false);
		}
	});
	mnUser.add(mntUser);
	
	JMenuItem mntUserEnter = new JMenuItem("Kullanıcı Girişi");
	mntUserEnter.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			UserEnterFrame p=new UserEnterFrame();
			p.setVisible(true);
			MenuFrame.this.setVisible(false);
		}
	});
	mnUser.add(mntUserEnter);
	
	JMenu mnLog = new JMenu("Log İşlemleri  |");
	menuBar.add(mnLog);
	
	JMenuItem mntLog = new JMenuItem("Log Görüntüleme");
	mntLog.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			LogFrame p=new LogFrame();
			p.setVisible(true);
			MenuFrame.this.setVisible(false);
		}
	});
	mnLog.add(mntLog);
}
}