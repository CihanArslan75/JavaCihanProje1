package com.cihan.swing.ui.order;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.cihan.swing.dao.product.ProductOrderDao;
import com.cihan.swing.model.product.ProductOrder;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.utils.ProductUtil;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.util.List;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;


public class OrderFrame extends JFrame{
	
    private Container c=getContentPane();
    private JTable table;
    private JScrollPane scrollPane;
    private static final String FILE_NAME = "C:\\Users\\Cihan\\Desktop\\Cihan\\SatisListesi.xlsx";
    private String[] columnNames= {"Ürün Stok NO","Ürün Adı" ,"Ürün Renk","Ürün Beden","Birim Fiyatı","İndirim Oranı(%)","Son Fiyat","Ürün Adedi", "Satış Adedi","Satış Tarihi"};
	private List<ProductOrder> orderList;
    
	public OrderFrame() {
		initialize();
		 initTableOrder();
	}
	private void initialize() {
		setTitle("Satış Listesi  ");
		setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(20, 13, 900, 74);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnOrder = new JButton("SATIŞ LİSTESİ");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 getTableOrder();
			}
		});
		btnOrder.setBounds(12, 23, 200, 25);
		panel_1.add(btnOrder);
		
		JButton btnOrderExcel = new JButton("SATIŞ LİSTESİ EXCEL");
		btnOrderExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeExcel();
				JOptionPane.showMessageDialog(OrderFrame.this, "Excel Hazırlandı !");
			}
		});
		btnOrderExcel.setBounds(274, 23, 200, 25);
		panel_1.add(btnOrderExcel);
		
		JPanel panel = new JPanel();
		c.add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(20, 100, 900, 314);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{876, 0};
		gbl_panel.rowHeights = new int[]{288, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JButton button = new JButton("MENÜYE DÖN");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame m=new MenuFrame();
				m.setVisible(true);
				OrderFrame.this.setVisible(false);
			}
		});
		button.setBounds(754, 476, 164, 25);
		getContentPane().add(button);
		
	}
	
	private void initTableOrder() {
		 table.setModel(new javax.swing.table.DefaultTableModel(
	             new Object [][] {
	                 {null, null, null, null, null, null, null, null, null},
	                 {null, null, null, null, null, null, null, null, null},
	                 {null, null, null, null, null, null, null, null, null},
	                 {null, null, null, null, null, null, null, null, null}
	             },columnNames 
	         ));
	    scrollPane.setViewportView(table);
	}
	
	private void getOrderList() {
		ProductOrder order =new ProductOrder();
	    ProductOrderDao productOrderService=new ProductOrderDao();
		orderList=productOrderService.getAllList(order);
	}
	private void getTableOrder() {
		getOrderList();
		String[][] data = new String[orderList.size()][10];
				
		for (int i = 0; i < orderList.size(); i++) {
			
			if(orderList.get(i).getId()!=null) 
				data[i][0] = ""+orderList.get(i).getProductStock().getId(); 
			else 
				data[i][0]=null;
			if(orderList.get(i).getId()!=null) 
				data[i][1] = orderList.get(i).getProductStock().getProduct().getProductName();
			else 
				data[i][1]=null;
			if(orderList.get(i).getProductStock().getProductColor()!=null) 
				data[i][2] = ""+orderList.get(i).getProductStock().getProductColor();
			else 
				data[i][2]=null;
			if(orderList.get(i).getProductStock().getSizeList()!=null) 
				data[i][3] = orderList.get(i).getProductStock().getSizeList()+"  ("+orderList.get(i).getProductStock().getSizeList().getSizeNo()+")";
			else 
				data[i][3]=null;
			if(orderList.get(i).getProductStock().getUnitPrize()!=null) 
             	data[i][4]=""+orderList.get(i).getProductStock().getUnitPrize();
            else 
				data[i][4]=null;
			if(orderList.get(i).getProductStock().getSaleRate()!=null) 
             	data[i][5]="% "+orderList.get(i).getProductStock().getSaleRate();
            else 
				data[i][5]=null;
			if(orderList.get(i).getProductStock().getFinalPrize()!=null) 
             	data[i][6]=""+orderList.get(i).getProductStock().getFinalPrize();
            else 
				data[i][6]=null;
			if(orderList.get(i).getProductStock().getCount()!=null) 
				data[i][7]=""+orderList.get(i).getProductStock().getCount();
			else 
				data[i][7]=null;
			if(orderList.get(i).getOrderCount()!=null) 
             	data[i][8]=""+orderList.get(i).getOrderCount();
            else 
				data[i][8]=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(orderList.get(i).getInsertDate()!=null) 
				data[i][9]=sdf.format(orderList.get(i).getInsertDate());
			else 
				data[i][9]=null;
		
			}
			table = new JTable(data,columnNames);
			scrollPane.setViewportView(table);
			
			
	 }
	
	 private void initializeExcel() {    
		    if(orderList==null) {
		    	getOrderList() ;
		    }
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Satış Listesi");
	        CreationHelper createHelper = workbook.getCreationHelper();
	        DataFormat format = workbook.createDataFormat();
	          
	        // Create a Font for styling header cells
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());

	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);

	        // Create a Row
	        Row headerRow = sheet.createRow(0);
	        
	        // Create cells
	        for(int i = 0; i < columnNames.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columnNames[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        
	     // Create Cell Style for formatting Date
	        CellStyle dateCellStyle = workbook.createCellStyle();
	        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	        int rowNum = 1;
	      
				for(int i=0 ;i<orderList.size();i++) {
					Row row = sheet.createRow(rowNum++);		
					row.createCell(0).setCellValue(orderList.get(i).getProductStock().getId());
					if( orderList.get(i).getProductStock().getProduct().getProductName()!=null)
						row.createCell(1).setCellValue( orderList.get(i).getProductStock().getProduct().getProductName());
					else
						row.createCell(1).setCellValue("");
					
					if(orderList.get(i).getProductStock().getProductColor()!=null)
						row.createCell(2).setCellValue(orderList.get(i).getProductStock().getProductColor().toString());
					else
						row.createCell(2).setCellValue("");
					if(orderList.get(i).getProductStock().getSizeList()!=null)
						row.createCell(3).setCellValue( orderList.get(i).getProductStock().getSizeList()+"  ("+orderList.get(i).getProductStock().getSizeList().getSizeNo()+")");
					else
						row.createCell(3).setCellValue("");
					if(orderList.get(i).getProductStock().getUnitPrize()!=null)
						row.createCell(4).setCellValue(orderList.get(i).getProductStock().getUnitPrize());
					else
						row.createCell(4).setCellValue("");
					
					if(orderList.get(i).getProductStock().getSaleRate()!=null)
						row.createCell(5).setCellValue(orderList.get(i).getProductStock().getSaleRate());
					else
						row.createCell(5).setCellValue("");
					
					if(orderList.get(i).getProductStock().getFinalPrize()!=null)
						row.createCell(6).setCellValue(orderList.get(i).getProductStock().getFinalPrize());
					else
						row.createCell(6).setCellValue("");
					
					if(orderList.get(i).getProductStock().getCount()!=null)
						row.createCell(7).setCellValue(orderList.get(i).getProductStock().getCount());
					else
						row.createCell(7).setCellValue("");
					
					if(orderList.get(i).getOrderCount()!=null)
						row.createCell(8).setCellValue(orderList.get(i).getOrderCount());
					else
						row.createCell(8).setCellValue("");
					
					
					if(orderList.get(i).getInsertDate()!=null) 
					{
						Cell dateOfCell = row.createCell(9);
				        dateOfCell.setCellValue(orderList.get(i).getInsertDate());
				        dateOfCell.setCellStyle(dateCellStyle);
					}
					else
						row.createCell(9).setCellValue("");
					
				}
		

		 // Resize all columns to fit the content size
		    for(int i = 0; i < columnNames.length; i++) {
		        sheet.autoSizeColumn(i);
		    }
		
		    try {
	            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
	            workbook.write(outputStream);
	            workbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
}
