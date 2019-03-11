package com.cihan.swing.ui.product;

/** @author Cihan */
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.cihan.swing.dao.product.ProductDao;
import com.cihan.swing.dao.product.ProductStockDao;
import com.cihan.swing.model.product.Product;
import com.cihan.swing.model.product.ProductStock;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.utils.ProductUtil;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ProductFrame extends JFrame{
         
    private JButton btnMenu;
    private Container c=getContentPane();
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JTable table2;
    private JTextField txtProductSearch;
    ProductDao productService=new ProductDao();
    ProductStockDao productStockService=new ProductStockDao();
    List<Product> productList;
    private JButton btnKaytIlemleri;
    
    public ProductFrame() {
        urunInitialize();
        initTableProduct();
        initTableProductStock();
    }   
    
    private void urunInitialize() {
	setTitle("Ürünler ve Ürün Stokları Listesi  ");
	setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	c.setLayout(null);
	
	panel = new JPanel();
	panel.setBounds(12, 13, 950, 50);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	JLabel lblProductName = new JLabel("Ürün Adı :");
	lblProductName.setBounds(12, 13, 123, 16);
	panel.add(lblProductName);
	
	txtProductSearch = new JTextField();
	txtProductSearch.setBounds(130, 10, 283, 22);
	panel.add(txtProductSearch);
	txtProductSearch.setColumns(10);
	
	JButton btnProductSearch = new JButton("ARA");
	btnProductSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			getTableProduct();
		}
	});
	btnProductSearch.setBounds(467, 9, 162, 25);
	panel.add(btnProductSearch);
	
	btnKaytIlemleri = new JButton("Kayıt İşlemleri");
	btnKaytIlemleri.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    ProductSave u=new ProductSave();
			u.setVisible(true);
			ProductFrame.this.setVisible(false);
		}
	});
	btnKaytIlemleri.setBounds(705, 9, 162, 25);
	panel.add(btnKaytIlemleri);
	
	JLabel lblrnListesi = new JLabel("ÜRÜN LİSTESİ");
	lblrnListesi.setForeground(Color.RED);
	lblrnListesi.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblrnListesi.setBounds(12, 80, 183, 16);
	c.add(lblrnListesi);
			
	panel1 = new JPanel();
	panel1.setBounds(10, 110, 950, 200);
	getContentPane().add(panel1);
	GridBagLayout gbl_panel1 = new GridBagLayout();
	gbl_panel1.columnWidths = new int[]{950, 0};
	gbl_panel1.rowHeights = new int[]{200, 0};
	gbl_panel1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	gbl_panel1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panel1.setLayout(gbl_panel1);
				
	scrollPane1 = new JScrollPane();
	GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
	gbc_scrollPane1.fill = GridBagConstraints.BOTH;
	gbc_scrollPane1.gridx = 0;
	gbc_scrollPane1.gridy = 0;
	panel1.add(scrollPane1, gbc_scrollPane1);
	scrollPane1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, null, null, null));
				
	table1 = new JTable();
	scrollPane1.setViewportView(table1);
				
	JLabel lblrnStokDetayi = new JLabel("ÜRÜN STOK DETAYI ");
	lblrnStokDetayi.setForeground(Color.RED);
	lblrnStokDetayi.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblrnStokDetayi.setBounds(12, 325, 215, 16);
	c.add(lblrnStokDetayi);
				
	panel2 = new JPanel();
	panel2.setBounds(10, 350, 950, 200);
	getContentPane().add(panel2);
	GridBagLayout gbl_panel2 = new GridBagLayout();
	gbl_panel2.columnWidths = new int[]{950, 0};
	gbl_panel2.rowHeights = new int[]{200, 0};
	gbl_panel2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	gbl_panel2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panel2.setLayout(gbl_panel2);
				
	scrollPane2 = new JScrollPane();
	GridBagConstraints gbc_scrollPane2 = new GridBagConstraints();
	gbc_scrollPane2.fill = GridBagConstraints.BOTH;
	gbc_scrollPane2.gridx = 0;
	gbc_scrollPane2.gridy = 0;
	panel2.add(scrollPane2, gbc_scrollPane2);
	scrollPane2.setViewportBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, null, null, null));
				
	table2 = new JTable();
	scrollPane2.setColumnHeaderView(table2);
				
	btnMenu = new JButton("MENÜYE DÖN");
	btnMenu.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		MenuFrame m=new MenuFrame();
		m.setVisible(true);
		ProductFrame.this.setVisible(false);
		}
	});
	btnMenu.setBounds(798, 584, 164, 25);
	c.add(btnMenu);
}
    
	private void initTableProduct() {
		 table1.setModel(new javax.swing.table.DefaultTableModel(
	             new Object [][] {
	                 {null, null, null, null, null},
	                 {null, null, null, null, null},
	                 {null, null, null, null, null},
	                 {null, null, null, null, null}
	             },
	             new String [] {"NO","Marka","Ürün Tipi","Ürün Adı","Üretim Tarihi" }
	         ));
	    scrollPane1.setViewportView(table1);
	}
	
	private void initTableProductStock() {
		 table2.setModel(new javax.swing.table.DefaultTableModel(
	             new Object [][] {
	                 {null, null, null, null, null, null, null},
	                 {null, null, null, null, null, null, null},
	                 {null, null, null, null, null, null, null},
	                 {null, null, null, null, null, null, null}
	             },
	             new String [] {"Ürün Stok NO","Renk","Ürün Beden","Ürün Adedi","Birim Fiyatı","İndirim Oranı","Son Fiyat"}
	         ));
	    scrollPane2.setViewportView(table2);
	}
	
	private void getTableProduct() {
		Product product =new Product();
		product.setProductName(txtProductSearch.getText());
		product.setState(1);
		productList=productService.search(product);
		String[] columnNames1= {"NO","Marka","Ürün Tipi","Ürün Adı","Üretim Tarihi" };
		String[][] data1 = new String[productList.size()][5];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 0; i < productList.size(); i++) {
				
			if(productList.get(i).getId()!=null) 
				data1[i][0] = ""+productList.get(i).getId(); 
			else 
				data1[i][0]=null;
			if(productList.get(i).getProductMark()!=null) 
             	data1[i][1]=""+productList.get(i).getProductMark();
            else 
				data1[i][1]=null;
			if(productList.get(i).getProductType()!=null) 
			    data1[i][2]=""+productList.get(i).getProductType();
			else 
			data1[i][2]=null;
			
			if(productList.get(i).getProductName()!=null) 
				data1[i][3] = productList.get(i).getProductName();
			else 
				data1[i][3]=null;
			
			if(productList.get(i).getProductDate()!=null) 
				data1[i][4]=sdf.format(productList.get(i).getProductDate());
			else 
				data1[i][4]=null;
		
			}
			table1 = new JTable(data1,columnNames1);
			scrollPane1.setViewportView(table1);
			
			table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
						public void valueChanged(ListSelectionEvent e) {
							if (table1.getSelectedRow() > -1) {
								getTableProductStock();
							 }
						} 
					 });
	 }
	
	private void getTableProductStock() {
		int ii=table1.getSelectedRow() ;
		int ProductId =productList.get(ii).getId();
	    ProductStock productStock =new ProductStock();
	    Product product =new Product();
	    product.setState(1);
	    product.setId(ProductId);
		productStock.setProduct(product);
		productStock.setState(1);
		List<ProductStock> productStockList=productStockService.searchIdAll(productStock);
		String[] columnNames2= {"Ürün Stok NO","Ürün Renk","Ürün Beden","Ürün Adedi","Birim Fiyatı","İndirim Oranı","Son Fiyat"};
		String[][] data2 = new String[productStockList.size()][7];
				
		for (int i = 0; i < productStockList.size(); i++) {
				
			if(productStockList.get(i).getId()!=null) 
				data2[i][0] = ""+productStockList.get(i).getId(); 
			else 
				data2[i][0]=null;
			if(productStockList.get(i).getProductColor()!=null) 
				data2[i][1] = ""+productStockList.get(i).getProductColor();
			else 
				data2[i][1]=null;
			if(productStockList.get(i).getSizeList()!=null) 
				data2[i][2] = productStockList.get(i).getSizeList()+"  ("+productStockList.get(i).getSizeList().getSizeNo()+")";
			else 
				data2[i][2]=null;
			if(productStockList.get(i).getCount()!=null) 
				data2[i][3]=""+productStockList.get(i).getCount();
			else 
				data2[i][3]=null;
			if(productStockList.get(i).getUnitPrize()!=null) 
             	data2[i][4]=""+productStockList.get(i).getUnitPrize();
            else 
				data2[i][4]=null;
			if(productStockList.get(i).getSaleRate()!=null) 
             	data2[i][5]="% "+productStockList.get(i).getSaleRate();
            else 
				data2[i][5]=null;
			if(productStockList.get(i).getFinalPrize()!=null) 
             	data2[i][6]=""+productStockList.get(i).getFinalPrize();
            else 
				data2[i][6]=null;
			}
			table2 = new JTable(data2,columnNames2);
			scrollPane2.setViewportView(table2);
			
	
	 }
}
