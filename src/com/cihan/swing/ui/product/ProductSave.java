package com.cihan.swing.ui.product;

import java.awt.Container;

import javax.swing.JFrame;

import com.cihan.swing.model.product.MarkaList;
import com.cihan.swing.model.product.Product;
import com.cihan.swing.model.product.ProductType;
import com.cihan.swing.runner.Runner;
import com.cihan.swing.utils.DatabaseBaseService;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.UIManager;

public class ProductSave extends JFrame {
	private Container c = getContentPane();
	JComboBox cmbMarka ;
	JComboBox cmbType;
	
	public ProductSave() {
		InitializeProductSave();
		getMarkaCombo() ;
		getProductTypeCombo();
	}
	
	private void InitializeProductSave() {
		setTitle("Ürünler ve Ürün Stokları Güncelleme Formu  ");
		setBounds(Runner.x1, Runner.y1, Runner.width1, Runner.height1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.light"));
		panel.setForeground(Color.WHITE);
		panel.setBounds(12, 13, 958, 195);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMarka = new JLabel("Marka  :");
		lblMarka.setBounds(12, 13, 56, 16);
		panel.add(lblMarka);
		
		cmbMarka = new JComboBox();
		cmbMarka.setBounds(90, 13, 180, 22);
		panel.add(cmbMarka);
		
		JLabel lblUrunTipi = new JLabel("Ürün Tipi :");
		lblUrunTipi.setBounds(12, 62, 89, 16);
		panel.add(lblUrunTipi);
		
		cmbType = new JComboBox();
		cmbType.setBounds(90, 62, 180, 22);
		panel.add(cmbType);
	}
	
	private void  getMarkaCombo() {
		cmbMarka.setModel(new DefaultComboBoxModel(MarkaList.values()));
	}
	
	private void  getProductTypeCombo() {
		DatabaseBaseService<ProductType> productService=new DatabaseBaseService<ProductType>();
		ProductType productType=new ProductType();
		productType.setDurum(1);
		List<ProductType> productTypeList = productService.search(productType);
		for (int i = 0; i <productTypeList.size(); i++) {
			cmbType.insertItemAt(productTypeList.get(i).getProductTypeName(),i);
		}
		
	}
	
}
