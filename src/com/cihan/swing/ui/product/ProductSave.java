package com.cihan.swing.ui.product;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;

import com.cihan.swing.model.product.ColorList;
import com.cihan.swing.model.product.MarkaList;
import com.cihan.swing.model.product.Product;
import com.cihan.swing.model.product.ProductType;
import com.cihan.swing.model.product.SizeList;
import com.cihan.swing.model.user.Rol;
import com.cihan.swing.model.user.User;
import com.cihan.swing.runner.Runner;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.utils.DatabaseBaseService;
import com.toedter.calendar.JDateChooser;

import Ornek4.Personel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.Date;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ProductSave extends JFrame {
	private Container c = getContentPane();
	private JComboBox cmbMarka ;
	private JComboBox cmbType;
	private JComboBox cmbColor;
	private JTextField txtProductName;
	private JTable table;
	private JPanel panel;
	private JPanel panel_1;
	JScrollPane scrollPane;
	private JPanel panel_2;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblrnBeden;
	private JLabel lblDedi;
	private JLabel lblBirimFiyat;
	private JComboBox cmbSizeNo ;
	private JDateChooser dateProduct;
    DatabaseBaseService<Product> productServis=new DatabaseBaseService<Product>();
	
	public ProductSave() {
		InitializeProductSave();
		getMarkaCombo() ;
		getProductTypeCombo();
		getColorCombo() ;
		getSizeCombo() ;
		initTableProductStock();
	}
	
	private void InitializeProductSave() {
		setTitle("Ürünler ve Ürün Stokları Güncelleme Formu  ");
		setBounds(Runner.x1, Runner.y1, Runner.width1, Runner.height1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(30, 40, 920, 150);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblrnListesi = new JLabel("ÜRÜN KAYIT");
		lblrnListesi.setForeground(Color.RED);
		lblrnListesi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrnListesi.setBounds(30, 15, 183, 16);
		c.add(lblrnListesi);
		
		JLabel lblMarka = new JLabel("Marka  :");
		lblMarka.setBounds(12, 30, 56, 16);
		panel.add(lblMarka);
		
		cmbMarka = new JComboBox();
		cmbMarka.setBounds(110, 30, 180, 22);
		panel.add(cmbMarka);
		
		JLabel lblUrunTipi = new JLabel("Ürün Tipi :");
		lblUrunTipi.setBounds(12, 80, 89, 16);
		panel.add(lblUrunTipi);
		
		cmbType = new JComboBox();
		cmbType.setBounds(110, 80, 180, 22);
		panel.add(cmbType);
		
		JLabel lblrnAd = new JLabel("Ürün Adı :");
		lblrnAd.setBounds(400, 30, 66, 16);
		panel.add(lblrnAd);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(500, 30, 180, 22);
		panel.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblretimTarihi = new JLabel("Üretim Tarihi :");
		lblretimTarihi.setBounds(400, 80, 98, 16);
		panel.add(lblretimTarihi);
		
		dateProduct = new JDateChooser();
		dateProduct.setBounds(500, 80, 180, 22);
		dateProduct.setDateFormatString("dd/MM/yyyy");
		panel.add(dateProduct);
		
		JButton btnProductSave = new JButton("ÜRÜN KAYIT");
		btnProductSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productSave();
			}
		});
		btnProductSave.setBounds(730, 113, 164, 25);
		panel.add(btnProductSave);
		
		JLabel lblrnStokDetayi = new JLabel("ÜRÜN STOK DETAYI ");
		lblrnStokDetayi.setForeground(Color.RED);
		lblrnStokDetayi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrnStokDetayi.setBounds(30, 400, 200, 16);
		c.add(lblrnStokDetayi);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(30, 253, 920, 120);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(500, 30, 180, 22);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(500, 80, 180, 22);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		lblrnBeden = new JLabel("Ürün Beden :");
		lblrnBeden.setBounds(12, 83, 75, 16);
		panel_2.add(lblrnBeden);
		
		lblDedi = new JLabel("Ürünün Adedi :");
		lblDedi.setBounds(400, 33, 93, 16);
		panel_2.add(lblDedi);
		
		lblBirimFiyat = new JLabel("Birim Fiyatı :");
		lblBirimFiyat.setBounds(400, 83, 93, 16);
		panel_2.add(lblBirimFiyat);
		
		JButton btnStockSave = new JButton("STOK KAYIT");
		btnStockSave.setBounds(730, 79, 164, 25);
		panel_2.add(btnStockSave);
		
		cmbSizeNo = new JComboBox();
		cmbSizeNo.setBounds(110, 80, 180, 22);
		panel_2.add(cmbSizeNo);
		
		cmbColor = new JComboBox();
		cmbColor.setBounds(110, 30, 180, 22);
		panel_2.add(cmbColor);
		
		JLabel lblRenk = new JLabel("Renk   :");
		lblRenk.setBounds(12, 33, 56, 16);
		panel_2.add(lblRenk);
		
		panel_1 = new JPanel();
		panel_1.setBounds(30, 420, 920, 150);
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{920, 0};
		gbl_panel_1.rowHeights = new int[]{150, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JLabel lblrnStokKayit = new JLabel("ÜRÜN STOK KAYIT");
		lblrnStokKayit.setBounds(30, 224, 233, 16);
		lblrnStokKayit.setForeground(Color.RED);
		lblrnStokKayit.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblrnStokKayit);
	
		
	}
	
	private void  getProductTypeCombo() {
		DatabaseBaseService<ProductType> productService=new DatabaseBaseService<ProductType>();
		ProductType productType=new ProductType();
		productType.setDurum(1);
		List<ProductType> productTypeList = productService.search(productType);
//		for (int i = 0; i <productTypeList.size(); i++) {
//			cmbType.insertItemAt(productTypeList.get(i).getId(),i);
//			cmbType.insertItemAt(productTypeList.get(i).getProductTypeName(),i);
//		}
		
		DefaultComboBoxModel model = new DefaultComboBoxModel((ProductType)productTypeList);
		cmbType.setModel(model);
		cmbType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int id=((ProductType)cmbType.getSelectedItem()).getId();
			}
		});
		
	}
	
	private void  getMarkaCombo() {
		cmbMarka.setModel(new DefaultComboBoxModel(MarkaList.values()));
	}
	private void  getColorCombo() {
		cmbColor.setModel(new DefaultComboBoxModel(ColorList.values()));
	}
	private void  getSizeCombo() {
		cmbSizeNo.setModel(new DefaultComboBoxModel(SizeList.values()));
	}
	
	private void initTableProductStock() {
		 table.setModel(new javax.swing.table.DefaultTableModel(
	             new Object [][] {
	                 {null, null, null, null},
	                 {null, null, null, null},
	                 {null, null, null, null},
	                 {null, null, null, null}
	             },
	             new String [] {"Ürün Renk","Ürün Beden","Ürün Adedi","Birim Fiyatı"}
	         ));
	    scrollPane.setViewportView(table);
	}

	 public boolean productSave(){
		 Product product = new Product();
		 if(cmbMarka.getSelectedItem()!=null) product.setProductMark((MarkaList) cmbMarka.getSelectedItem()); 
		 if(txtProductName.getText()!=null)   product.setProductName(txtProductName.getText());	
		 if(cmbType.getSelectedItem()!=null)  product.setProductType((ProductType) cmbType.getSelectedItem());
		 if(dateProduct.getDate()!=null)      product.setProductDate(dateProduct.getDate());
		 product.setInsertDate(new Date());
		 product.setInsertUser(Runner.user.getId());
		 product.setDurum(1);
		 
		 return productServis.save(product);
		
		 
	 }
	
}
