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

public class ProductUpdate extends JFrame {
	private Container c = getContentPane();
	private JComboBox cmbMarka ;
	private JComboBox cmbType;
	private JComboBox cmbColor;
	private JTextField txtProductName;
	private JPanel panel;
	private JPanel panel_2;
	private JTextField txtCount;
	private JTextField txtUnitPrize;
	private JLabel lblrnBeden;
	private JLabel lblDedi;
	private JLabel lblBirimFiyat;
	private JComboBox cmbSizeNo ;
	private JDateChooser dateProduct;
	private JLabel lblImage;
	private String filePath = null;
	private JLabel lblIndirimOran;
	private JLabel lblSonFiyat;
	private JTextField txtSaleRate;
	private JTextField txtFinalPrize;
	private Product product;
	private ProductStock productStock ;
    private ProductDao productService=new ProductDao();
    private ProductStockDao productStockService=new ProductStockDao();
    private ProductImageDao productImageService=new ProductImageDao();
	private BufferedImage img;
	private JTextField txtProductStockNo;
	private JTextField textField;
	private Integer productID;
	private Integer productStockID;
	private Product productList;
	private ProductStock productStockList;
	
	public ProductUpdate(Integer productID,Integer productStockID) {
		this.productID=productID;
		this.productStockID=productStockID;
		
		InitializeProductSave();
		getMarkaCombo() ;
		getProductTypeCombo();
		getColorCombo() ;
		getSizeCombo() ;
	
	}
	
	private void InitializeProductSave() {
		setTitle("Ürünler ve Ürün Stokları Güncelleme Formu  ");
		setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(30, 60, 920, 150);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblrnListesi = new JLabel("ÜRÜN KAYIT");
		lblrnListesi.setForeground(Color.RED);
		lblrnListesi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrnListesi.setBounds(30, 20, 183, 16);
		c.add(lblrnListesi);
		
		JLabel lblMarka = new JLabel("Marka  :");
		lblMarka.setBounds(12, 45, 56, 16);
		panel.add(lblMarka);
		
		cmbMarka = new JComboBox();
		cmbMarka.setBounds(110, 45, 180, 22);
		panel.add(cmbMarka);
		
		JLabel lblUrunTipi = new JLabel("Ürün Tipi :");
		lblUrunTipi.setBounds(12, 80, 89, 16);
		panel.add(lblUrunTipi);
		
		cmbType = new JComboBox();
		cmbType.setBounds(110, 80, 180, 22);
		panel.add(cmbType);
		
		JLabel lblrnAd = new JLabel("Ürün Adı :");
		lblrnAd.setBounds(400, 45, 66, 16);
		panel.add(lblrnAd);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(500, 45, 180, 22);
		panel.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblretimTarihi = new JLabel("Üretim Tarihi :");
		lblretimTarihi.setBounds(400, 80, 98, 16);
		panel.add(lblretimTarihi);
		
		dateProduct = new JDateChooser();
		dateProduct.setBounds(500, 80, 180, 22);
		dateProduct.setDateFormatString("dd/MM/yyyy");
		panel.add(dateProduct);
		
		JButton btnProductSave = new JButton("ÜRÜN GÜNCELLE");
		btnProductSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(productUpdateMaster()) 
					JOptionPane.showMessageDialog(ProductUpdate.this, "Ürün Güncelle İşlemi Başarılı");
				else
					JOptionPane.showMessageDialog(ProductUpdate.this, "Ürün Güncelle İşlemi Başarısız");
			}
		});
		btnProductSave.setBounds(500, 115, 180, 25);
		panel.add(btnProductSave);
		
		JLabel lblNo = new JLabel("NO :");
		lblNo.setBounds(12, 13, 56, 16);
		panel.add(lblNo);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(110, 10, 180, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(30, 256, 920, 230);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		txtCount = new JTextField();
		txtCount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
				      int  x = Integer.parseInt(txtCount.getText());
				    } catch (NumberFormatException nfe) {
				    	txtCount.setText("");
				    	JOptionPane.showMessageDialog(ProductUpdate.this, "Adet Alanını Sayısal Olarak Giriniz !");
				    }
			}
		});
		
		txtCount.setBounds(110, 110, 180, 22);
		panel_2.add(txtCount);
		txtCount.setColumns(10);
		
		txtUnitPrize = new JTextField();
		txtUnitPrize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
				      int  x = Integer.parseInt(txtUnitPrize.getText());
				    } catch (NumberFormatException nfe) {
				    	txtUnitPrize.setText("");
				    	JOptionPane.showMessageDialog(ProductUpdate.this, "Birim Fiyatı Alanını Sayısal Olarak Giriniz !");
				    }
			}
		});
		txtUnitPrize.setBounds(500, 48, 180, 22);
		panel_2.add(txtUnitPrize);
		txtUnitPrize.setColumns(10);
		
		lblrnBeden = new JLabel("Ürün Beden :");
		lblrnBeden.setBounds(12, 80, 75, 16);
		panel_2.add(lblrnBeden);
		
		lblDedi = new JLabel("Ürünün Adedi :");
		lblDedi.setBounds(12, 110, 93, 16);
		panel_2.add(lblDedi);
		
		lblBirimFiyat = new JLabel("Birim Fiyatı :");
		lblBirimFiyat.setBounds(400, 48, 93, 16);
		panel_2.add(lblBirimFiyat);
		
		JButton btnStockSave = new JButton("STOK GÜNCELLE");
		btnStockSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(productStockUpdate()) 
				{
					productImageSave();
				   	JOptionPane.showMessageDialog(ProductUpdate.this, "Stok Güncelle İşlemi Başarılı");
					
				}
				else
					JOptionPane.showMessageDialog(ProductUpdate.this, "Stok Güncelle İşlemi Başarısız");
			}
		});
		btnStockSave.setBounds(500, 178, 180, 25);
		panel_2.add(btnStockSave);
		
		cmbSizeNo = new JComboBox();
		cmbSizeNo.setBounds(110, 80, 180, 22);
		panel_2.add(cmbSizeNo);
		
		cmbColor = new JComboBox();
		cmbColor.setBounds(110, 48, 180, 22);
		panel_2.add(cmbColor);
		
		JLabel lblRenk = new JLabel("Renk   :");
		lblRenk.setBounds(12, 48, 56, 16);
		panel_2.add(lblRenk);
		
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
		
		lblIndirimOran = new JLabel("İndirim Oranı (Örn : 10) :");
		lblIndirimOran.setBounds(339, 80, 154, 16);
		panel_2.add(lblIndirimOran);
		
		lblSonFiyat = new JLabel("Son Fiyatı :");
		lblSonFiyat.setBounds(407, 110, 75, 16);
		panel_2.add(lblSonFiyat);
		
		txtSaleRate = new JTextField();
		txtSaleRate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
				      int  x = Integer.parseInt(txtSaleRate.getText());
				    } catch (NumberFormatException nfe) {
				    	txtSaleRate.setText("");
				    	JOptionPane.showMessageDialog(ProductUpdate.this, "İndirim Oranı Alanını Sayısal Olarak Giriniz !.");
				    }
				txtFinalPrize.setText(String.valueOf(  Integer.parseInt(txtUnitPrize.getText())- ((Integer.parseInt(txtSaleRate.getText())*Integer.parseInt(txtUnitPrize.getText()))/100)));
			}
		});
		
		txtSaleRate.setBounds(500, 80, 180, 22);
		panel_2.add(txtSaleRate);
		txtSaleRate.setColumns(10);
		
		txtFinalPrize = new JTextField();
		txtFinalPrize.setEditable(false);
		txtFinalPrize.setBounds(500, 110, 180, 22);
		panel_2.add(txtFinalPrize);
		txtFinalPrize.setColumns(10);
		
		txtProductStockNo = new JTextField();
		txtProductStockNo.setEditable(false);
		txtProductStockNo.setBounds(110, 13, 180, 22);
		panel_2.add(txtProductStockNo);
		txtProductStockNo.setColumns(10);
		
		JLabel lblStokNo = new JLabel("Stok No :");
		lblStokNo.setBounds(12, 13, 56, 16);
		panel_2.add(lblStokNo);
		
		JLabel lblrnStokKayit = new JLabel("ÜRÜN STOK KAYIT");
		lblrnStokKayit.setBounds(30, 227, 233, 16);
		lblrnStokKayit.setForeground(Color.RED);
		lblrnStokKayit.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblrnStokKayit);
		
		
		JButton btnNewButton_1 = new JButton("MENÜYE DÖN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame m=new MenuFrame();
				m.setVisible(true);
				ProductUpdate.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(777, 603, 173, 25);
		c.add(btnNewButton_1);
		
	}
	
	private void  getProductTypeCombo() {
		cmbType.setModel(new DefaultComboBoxModel(ProductTypeList.values()));		
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
	
	 private boolean productUpdateMaster(){
		 product = new Product();
		 if(cmbMarka.getSelectedItem()!=null) product.setProductMark((MarkaList) cmbMarka.getSelectedItem()); 
		 if(txtProductName.getText()!=null)   product.setProductName(txtProductName.getText());	
		 if(cmbType.getSelectedItem()!=null)  product.setProductType((ProductTypeList) cmbType.getSelectedItem());
		 if(dateProduct.getDate()!=null)      product.setProductDate(dateProduct.getDate());
		 product.setInsertDate(new Date());
		 product.setInsertUser(ProductUtil.user.getId());
		 product.setState(1);
		 
		 return productService.save(product); 
	 }
	 
	 private boolean productStockUpdate(){
		  if(product==null) {
			 JOptionPane.showMessageDialog(ProductUpdate.this, "Önce Ürünü Kayıt ediniz. ");
		 }
		 else
		 {	
			 productStock = new ProductStock();
			 if(cmbColor.getSelectedItem()!=null) productStock.setProductColor((ColorList) cmbColor.getSelectedItem()); 
			 if(cmbSizeNo.getSelectedItem()!=null)   productStock.setSizeList((SizeList) cmbSizeNo.getSelectedItem());	
			 if(txtCount.getText()!=null)   productStock.setCount(Integer.parseInt(txtCount.getText()));	
			 if(txtUnitPrize.getText()!=null)   productStock.setUnitPrize(Integer.parseInt(txtUnitPrize.getText()));	
			 if(txtSaleRate.getText()!=null)   productStock.setSaleRate(Integer.parseInt(txtSaleRate.getText()));	
			 if(txtFinalPrize.getText()!=null)   productStock.setFinalPrize(Integer.parseInt(txtFinalPrize.getText()));	
			 productStock.setProduct(product);	
			 productStock.setInsertDate(new Date());
			 productStock.setInsertUser(ProductUtil.user.getId());
			 productStock.setState(1);
			 }
		 
		 return productStockService.save(productStock); 
	 }
	 
	 private void getImage() {
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
	 
	 private boolean productImageSave() {
		 ProductImage productImage = new ProductImage();
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 try {
			ImageIO.write( img, "jpg", baos );
			 //baos.flush();
			 byte[] imageInByte = baos.toByteArray();
			 baos.close();
			 System.out.println("imageInByte:"+imageInByte);
			 productImage.setProductImage(imageInByte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
		 productImage.setProductStock(productStock);
		 return productImageService.save(productImage); 
	 }
	
	private Product findProduct() { 
		productList=productService.findId(productID,new Product());
		if(productList!=null)
		 return productList;
		else
		 return null;	
		 
	}
	
	private ProductStock findProductStock() { 
		productStockList=productStockService.findId(productStockID,new ProductStock());
		if(productStockList!=null)
		 return productStockList;
		else
		 return null;	
		 
	}
}











