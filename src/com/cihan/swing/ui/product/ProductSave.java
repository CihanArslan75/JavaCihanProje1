package com.cihan.swing.ui.product;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;

import com.cihan.swing.dao.product.ProductDao;
import com.cihan.swing.dao.product.ProductImageDao;
import com.cihan.swing.dao.product.ProductStockDao;
import com.cihan.swing.model.product.ColorList;
import com.cihan.swing.model.product.MarkaList;
import com.cihan.swing.model.product.Product;
import com.cihan.swing.model.product.ProductImage;
import com.cihan.swing.model.product.ProductStock;
import com.cihan.swing.model.product.ProductTypeList;
import com.cihan.swing.model.product.SizeList;
import com.cihan.swing.model.user.StateEnum;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.utils.ProductUtil;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductSave extends JFrame {
	protected Container c = getContentPane();
	protected JComboBox cmbMarka ;
	protected JComboBox cmbType;
	protected JComboBox cmbColor;
	protected JTextField txtProductName;
	protected JTable table;
	protected JPanel panel;
	protected JPanel panel_1;
	protected JScrollPane scrollPane;
	protected JPanel panel_2;
	protected JTextField txtCount;
	protected JTextField txtUnitPrize;
	protected JLabel lblrnBeden;
	protected JLabel lblDedi;
	protected JLabel lblBirimFiyat;
	protected JComboBox cmbSizeNo ;
	protected JDateChooser dateProduct;
	protected JLabel lblImage;
	protected String filePath = null;
	protected JLabel lblIndirimOran;
	protected JLabel lblSonFiyat;
	protected JTextField txtSaleRate;
	protected JTextField txtFinalPrize;
	protected Product product;
	protected ProductStock productStock ;
    protected ProductDao productService=new ProductDao();
    protected ProductStockDao productStockService=new ProductStockDao();
    protected ProductImageDao productImageService=new ProductImageDao();
	protected BufferedImage img;
	private String form;
	
	public ProductSave() {
		this.form=form;
		InitializeProductSave();
		getMarkaCombo() ;
		getProductTypeCombo();
		getColorCombo() ;
		getSizeCombo() ;
		initTableProductStock();
		
	}
	

	protected void InitializeProductSave() {
		if(form=="save")
			setTitle("Ürünler ve Ürün Stokları Giriş Formu  ");
		else
			setTitle("Ürünler ve Ürün Stokları Güncelleme Formu  ");
		
		setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
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
		lblUrunTipi.setBounds(12, 70, 89, 16);
		panel.add(lblUrunTipi);
		
		cmbType = new JComboBox();
		cmbType.setBounds(110, 70, 180, 22);
		panel.add(cmbType);
		
		JLabel lblrnAd = new JLabel("Ürün Adı :");
		lblrnAd.setBounds(400, 30, 66, 16);
		panel.add(lblrnAd);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(500, 30, 180, 22);
		panel.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblretimTarihi = new JLabel("Üretim Tarihi :");
		lblretimTarihi.setBounds(400, 70, 98, 16);
		panel.add(lblretimTarihi);
		
		dateProduct = new JDateChooser();
		dateProduct.setBounds(500, 70, 180, 22);
		dateProduct.setDateFormatString("dd/MM/yyyy");
		panel.add(dateProduct);
		
		JButton btnProductSave = new JButton("ÜRÜN KAYIT");
		btnProductSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(productSaveMaster()) 
					JOptionPane.showMessageDialog(ProductSave.this, "Ürün Kayıt İşlemi Başarılı");
				else
					JOptionPane.showMessageDialog(ProductSave.this, "Ürün Kayıt İşlemi Başarısız");
			}
		});
		btnProductSave.setBounds(500, 112, 180, 25);
		panel.add(btnProductSave);
		
		JLabel lblrnStokDetayi = new JLabel("ÜRÜN STOK DETAYI ");
		lblrnStokDetayi.setForeground(Color.RED);
		lblrnStokDetayi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrnStokDetayi.setBounds(30, 460, 200, 16);
		c.add(lblrnStokDetayi);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(30, 234, 920, 216);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnStokNew = new JButton("YENİ STOK");
		btnStokNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearStokForm();
			}
		});
		btnStokNew.setBounds(110, 8, 180, 25);
		panel_2.add(btnStokNew);
		
		
		txtCount = new JTextField();
		txtCount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
				      int  x = Integer.parseInt(txtCount.getText());
				    } catch (NumberFormatException nfe) {
				    	txtCount.setText("");
				    	JOptionPane.showMessageDialog(ProductSave.this, "Adet Alanını Sayısal Olarak Giriniz !");
				    }
			}
		});
		
		JLabel lblRenk = new JLabel("Renk   :");
		lblRenk.setBounds(12, 40, 56, 16);
		panel_2.add(lblRenk);
		
		cmbColor = new JComboBox();
		cmbColor.setBounds(110, 40, 180, 22);
		panel_2.add(cmbColor);
		
		lblrnBeden = new JLabel("Ürün Beden :");
		lblrnBeden.setBounds(12, 75, 75, 16);
		panel_2.add(lblrnBeden);
						
		cmbSizeNo = new JComboBox();
		cmbSizeNo.setBounds(110, 75, 180, 22);
		panel_2.add(cmbSizeNo);
		
		
		lblDedi = new JLabel("Ürünün Adedi :");
		lblDedi.setBounds(12, 110, 93, 16);
		panel_2.add(lblDedi);
		
		txtCount.setBounds(110, 110, 180, 22);
		panel_2.add(txtCount);
		txtCount.setColumns(10);
		
		lblBirimFiyat = new JLabel("Birim Fiyatı :");
		lblBirimFiyat.setBounds(400, 40, 93, 16);
		panel_2.add(lblBirimFiyat);
		
		txtUnitPrize = new JTextField();
		txtUnitPrize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
				      int  x = Integer.parseInt(txtUnitPrize.getText());
				      txtSaleRate.setText("0");
				    } catch (NumberFormatException nfe) {
				    	txtUnitPrize.setText("");
				    	JOptionPane.showMessageDialog(ProductSave.this, "Birim Fiyatı Alanını Sayısal Olarak Giriniz !");
				    }
				 if(txtSaleRate.getText()!=null && txtFinalPrize!=null)  txtFinalPrize.setText(String.valueOf(  Integer.parseInt(txtUnitPrize.getText())- ((Integer.parseInt(txtSaleRate.getText())*Integer.parseInt(txtUnitPrize.getText()))/100)));
				 else txtFinalPrize.setText(txtUnitPrize.getText());
			}
		});
		txtUnitPrize.setBounds(500, 40, 180, 22);
		panel_2.add(txtUnitPrize);
		txtUnitPrize.setColumns(10);
		
		lblIndirimOran = new JLabel("İndirim Oranı (Örn : 10) :");
		lblIndirimOran.setBounds(339, 75, 154, 16);
		panel_2.add(lblIndirimOran);
		
		txtSaleRate = new JTextField();
		txtSaleRate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
				      Integer  x = Integer.parseInt(txtSaleRate.getText());
				    } catch (NumberFormatException nfe) {
				    	txtSaleRate.setText("");
				    	JOptionPane.showMessageDialog(ProductSave.this, "İndirim Oranı Alanını Sayısal Olarak Giriniz !.");
				    	
				    }
				
				 if(txtSaleRate.getText()!=null && txtFinalPrize!=null)  txtFinalPrize.setText(String.valueOf(  Integer.parseInt(txtUnitPrize.getText())- ((Integer.parseInt(txtSaleRate.getText())*Integer.parseInt(txtUnitPrize.getText()))/100)));
				 else txtFinalPrize.setText(txtUnitPrize.getText());
			}
		});
		
		txtSaleRate.setBounds(500, 75, 180, 22);
		panel_2.add(txtSaleRate);
		txtSaleRate.setColumns(10);
		
		lblSonFiyat = new JLabel("Son Fiyatı :");
		lblSonFiyat.setBounds(407, 110, 75, 16);
		panel_2.add(lblSonFiyat);
		
		txtFinalPrize = new JTextField();
		txtFinalPrize.setEditable(false);
		txtFinalPrize.setBounds(500, 110, 180, 22);
		panel_2.add(txtFinalPrize);
		txtFinalPrize.setColumns(10);
		
		lblImage = new JLabel("");
		lblImage.setBounds(736, 13, 154, 190);
		panel_2.add(lblImage);
		
		JButton btnNewButton = new JButton("RESİM SEÇ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(fc);
					if(returnVal==JFileChooser.APPROVE_OPTION) {
					filePath=fc.getSelectedFile().getAbsolutePath();
				}else {
				
				}
				getImage();
			}
		});
		btnNewButton.setBounds(500, 145, 180, 25);
		panel_2.add(btnNewButton);
		
		JButton btnStockSave = new JButton("STOK KAYIT");
		btnStockSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					productStockSave();
					if(img!=null) 
					{  
						productImageSave();	
						getTableProductStock() ;
						JOptionPane.showMessageDialog(ProductSave.this, "Stok Kayıt İşlemi Başarılı");
					}
					else
					{
						getTableProductStock() ;
						JOptionPane.showMessageDialog(ProductSave.this, "Stok Kayıt İşlemi Başarılı");
					}
				} 
				catch (NumberFormatException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ProductSave.this, "Adet ve Birim Fiyatını  Giriniz ! ");
				}	
				catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(ProductSave.this, "Stok Kayıt İşlemi Başarısız !!!!!!!! ");	
			}
			}
		});
		btnStockSave.setBounds(500, 178, 180, 25);
		panel_2.add(btnStockSave);
		
		
		panel_1 = new JPanel();
		panel_1.setBounds(30, 480, 920, 110);
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{920, 0};
		gbl_panel_1.rowHeights = new int[]{107, 0};
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
		lblrnStokKayit.setBounds(30, 205, 233, 16);
		lblrnStokKayit.setForeground(Color.RED);
		lblrnStokKayit.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblrnStokKayit);
		
		
		JButton btnNewButton_1 = new JButton("MENÜYE DÖN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame m=new MenuFrame();
				m.setVisible(true);
				ProductSave.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(777, 603, 173, 25);
		c.add(btnNewButton_1);
		
		JButton btnClear = new JButton("FORMU TEMİZLE");
		btnClear.setBounds(30, 603, 180, 25);
		getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formuTemizle();
			}
		});
		
	}
	
	protected void  getProductTypeCombo() {
		cmbType.setModel(new DefaultComboBoxModel(ProductTypeList.values()));		
	}
	
	protected void  getMarkaCombo() {
		cmbMarka.setModel(new DefaultComboBoxModel(MarkaList.values()));
	}
	protected void  getColorCombo() {
		cmbColor.setModel(new DefaultComboBoxModel(ColorList.values()));
	}
	protected void  getSizeCombo() {
		cmbSizeNo.setModel(new DefaultComboBoxModel(SizeList.values()));
	}
	
	protected void initTableProductStock() {
		 table.setModel(new javax.swing.table.DefaultTableModel(
	             new Object [][] {
	                 {null, null, null, null, null, null},
	                 {null, null, null, null, null, null},
	                 {null, null, null, null, null, null},
	                 {null, null, null, null, null, null}
	             },
	             new String [] {"Ürün Renk","Ürün Beden","Ürün Adedi","Birim Fiyatı","İndirim Oranı","Son Fiyat"}
	         ));
	    scrollPane.setViewportView(table);
	}
	protected void getTableProductStock() {
		int ProductId =product.getId();
        ProductStock productStock1 =new ProductStock();
	    Product product1 =new Product();
	    product1.setState(StateEnum.NORMAL);
	    product1.setId(ProductId);
		productStock1.setProduct(product1);
		productStock1.setState(StateEnum.NORMAL);
		ProductStockDao productStockService1=new ProductStockDao();
		List<ProductStock> productStockList=productStockService1.searchIdAll(productStock1);
		String[] columnNames= {"Ürün Renk","Ürün Beden","Ürün Adedi","Birim Fiyatı","İndirim Oranı","Son Fiyat"};
		String[][] data2 = new String[productStockList.size()][7];
				
		for (int i = 0; i < productStockList.size(); i++) {
				
			if(productStockList.get(i).getProductColor()!=null) 
				data2[i][0] = ""+productStockList.get(i).getProductColor();
			else 
				data2[i][0]=null;
			if(productStockList.get(i).getSizeList()!=null) 
				data2[i][1] = productStockList.get(i).getSizeList()+"  ("+productStockList.get(i).getSizeList().getSizeNo()+")";
			else 
				data2[i][1]=null;
			if(productStockList.get(i).getCount()!=null) 
				data2[i][2]=""+productStockList.get(i).getCount();
			else 
				data2[i][2]=null;
			if(productStockList.get(i).getUnitPrize()!=null) 
             	data2[i][3]=""+productStockList.get(i).getUnitPrize();
            else 
				data2[i][3]=null;
			if(productStockList.get(i).getSaleRate()!=null) 
             	data2[i][4]="% "+productStockList.get(i).getSaleRate();
            else 
				data2[i][4]=null;
			if(productStockList.get(i).getFinalPrize()!=null) 
             	data2[i][5]=""+productStockList.get(i).getFinalPrize();
            else 
				data2[i][5]=null;
			}
			table = new JTable(data2,columnNames);
			scrollPane.setViewportView(table);
			
	
	 }

	 protected boolean productSaveMaster(){
		 product = new Product();
		 if(cmbMarka.getSelectedItem()!=null) product.setProductMark((MarkaList) cmbMarka.getSelectedItem()); 
		 if(txtProductName.getText()!=null)   product.setProductName(txtProductName.getText());	
		 if(cmbType.getSelectedItem()!=null)  product.setProductType((ProductTypeList) cmbType.getSelectedItem());
		 if(dateProduct.getDate()!=null)      product.setProductDate(dateProduct.getDate());
		 product.setInsertDate(new Date());
		 product.setInsertUser(ProductUtil.user.getId());
		 product.setState(StateEnum.NORMAL);
		 
		 return productService.save(product); 
	 }
	 
	 protected boolean productStockSave() throws Exception{
			 if(product==null) {
				 JOptionPane.showMessageDialog(ProductSave.this, "Önce Ürünü Kayıt ediniz. ");
			 }
			 else
			 {	
				 productStock = new ProductStock();
				 if(cmbColor.getSelectedItem()!=null) productStock.setProductColor((ColorList) cmbColor.getSelectedItem()); 
				 if(cmbSizeNo.getSelectedItem()!=null)   productStock.setSizeList((SizeList) cmbSizeNo.getSelectedItem());	
				 if(txtCount.getText()!=null )   productStock.setCount(Integer.parseInt(txtCount.getText()));	
				 if(txtUnitPrize.getText()!=null)   productStock.setUnitPrize(Integer.parseInt(txtUnitPrize.getText()));	
				 if(txtSaleRate.getText()!=null)   productStock.setSaleRate(Integer.parseInt(txtSaleRate.getText()));	
				 if(txtFinalPrize.getText()!=null)   productStock.setFinalPrize(Integer.parseInt(txtFinalPrize.getText()));	
				 productStock.setProduct(product);	
				 productStock.setInsertDate(new Date());
				 productStock.setInsertUser(ProductUtil.user.getId());
				 productStock.setState(StateEnum.NORMAL);
			 }
	
			 
			 return productStockService.save(productStock); 
	 }
	 
	 protected void getImage() {
		 SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					img=ImageIO.read(new File(filePath));
				} catch (Exception e) {
					e.printStackTrace();
				}
				lblImage.setIcon(new ImageIcon(img));
				
			}
		}); 
	 }
	 
	 protected void clearStokForm() {
		 cmbColor.setSelectedItem(ColorList.SİYAH); 
		 cmbSizeNo.setSelectedItem(SizeList.XXSMALL)  ;	
		 txtCount.setText("")   ;	
		 txtUnitPrize.setText("")   ;	
		 txtSaleRate.setText("")   ;	
		 txtFinalPrize.setText("")  ;
	 }
	 
		public void formuTemizle() {
			c.removeAll();
			c.repaint();
			InitializeProductSave();
			getMarkaCombo() ;
			getProductTypeCombo();
			getColorCombo() ;
			getSizeCombo() ;
			initTableProductStock();
		 }
	 
	 protected boolean productImageSave() {
		 ProductImage productImage = new ProductImage();
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 try {
			ImageIO.write( img, "jpg", baos );
			 baos.flush();
			 byte[] imageInByte = baos.toByteArray();
			 baos.close();
			 productImage.setProductImage(imageInByte);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		 productImage.setProductStock(productStock);
		 return productImageService.save(productImage); 
	 }
}











