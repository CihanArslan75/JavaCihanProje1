package com.cihan.swing.ui.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cihan.swing.dao.product.ProductDao;
import com.cihan.swing.dao.product.ProductStockDao;
import com.cihan.swing.model.product.Product;
import com.cihan.swing.model.product.ProductStock;

public class ProductExcel {
	private ProductDao productService=new ProductDao();
	private  ProductStockDao productStockService=new ProductStockDao();
		    
	private static final String FILE_NAME = "C:\\Users\\Cihan\\Desktop\\Cihan\\UrunRaporu.xlsx";
	private static String[] columns = {"NO","Marka","Ürün Tipi","Ürün Adı","Üretim Tarihi" ,"Ürün Stok NO","Renk","Ürün Beden","Ürün Adedi","Birim Fiyatı","İndirim Oranı","Son Fiyat"};
     
	public ProductExcel() {
		initialize();
	}
    private void initialize() {      
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Ürün Listesi");
        CreationHelper createHelper = workbook.getCreationHelper();
       
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
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
//        int rowNum = 0;
//        for (Object[] datatype : datatypes) {
//            Row row = sheet.createRow(rowNum++);
//            int colNum = 0;
//            for (Object field : datatype) {
//                Cell cell = row.createCell(colNum++);
//                if (field instanceof String) {
//                    cell.setCellValue((String) field);
//                } else if (field instanceof Integer) {
//                    cell.setCellValue((Integer) field);
//                }
//            }
//        }
        
     // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        int rowNum = 1;
        
        Product product =new Product();
		product.setState(1);
		List<Product> productList=productService.search(product);
		
		for (int i = 0; i < productList.size(); i++) {
			ProductStock productStock =new ProductStock();
			int ProductId =productList.get(i).getId();
			product.setId(ProductId);
			product.setState(1);
		    productStock.setProduct(product);
			List<ProductStock> productStockList=productStockService.searchIdAll(productStock);
			for(int j=0 ;j<productStockList.size();j++) {
				Row row = sheet.createRow(rowNum++);
				  
				row.createCell(0).setCellValue(productList.get(i).getId());
				row.createCell(1).setCellValue(productList.get(i).getProductMark().toString());
				row.createCell(2).setCellValue(productList.get(i).getProductType().toString());
				row.createCell(3).setCellValue(productList.get(i).getProductName());
				
				 Cell dateOfCell = row.createCell(4);
		         dateOfCell.setCellValue(productList.get(i).getProductDate());
		         dateOfCell.setCellStyle(dateCellStyle);
					
				row.createCell(5).setCellValue(productStockList.get(j).getId());
				row.createCell(6).setCellValue("");
				//row.createCell(6).setCellValue(productStockList.get(0).getProductColor());
				row.createCell(7).setCellValue(productStockList.get(j).getSizeList().toString());
				row.createCell(8).setCellValue(productStockList.get(j).getCount());
				row.createCell(9).setCellValue(productStockList.get(j).getUnitPrize());
				row.createCell(10).setCellValue(productStockList.get(j).getSaleRate());
				row.createCell(11).setCellValue(productStockList.get(j).getFinalPrize());
			}
			}
	

	 // Resize all columns to fit the content size
	    for(int i = 0; i < columns.length; i++) {
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
