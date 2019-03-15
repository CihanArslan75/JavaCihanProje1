package com.cihan.swing.ui.log;

import java.awt.Container;

import javax.swing.JFrame;

import com.cihan.swing.model.log.LogProduct;
import com.cihan.swing.model.user.User;
import com.cihan.swing.ui.menu.MenuFrame;
import com.cihan.swing.ui.user.UserFrame;
import com.cihan.swing.utils.DatabaseBaseService;
import com.cihan.swing.utils.ProductUtil;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

public class LogFrame extends JFrame{
	
	private Container c=getContentPane();
	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane;
	DatabaseBaseService<LogProduct> logServis=new DatabaseBaseService<LogProduct>();
	private List<LogProduct> logList;
	private JDateChooser dateLog1;
	private JDateChooser dateLog2 ;
	
	 
	public LogFrame() {
		initializeLogFrame();
		initTableLog();
		//getTable();
	}
	

	private void initializeLogFrame() {
		setTitle("LOG İŞLEMLERİ");
		setBounds(ProductUtil.x1, ProductUtil.y1, ProductUtil.width1, ProductUtil.height1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		c.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(20, 100, 950, 426);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{950, 0};
		gbl_panel.rowHeights = new int[]{419, 0};
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
		
		JButton btnAra = new JButton("Ara");
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTable();
			}
		});
		btnAra.setBounds(390, 37, 97, 25);
		getContentPane().add(btnAra);
		
		JButton btnMenu = new JButton("MENÜYE DÖN");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame m=new MenuFrame();
				m.setVisible(true);
				LogFrame.this.setVisible(false);
			}
		});
		btnMenu.setBounds(753, 595, 164, 25);
		getContentPane().add(btnMenu);
		
		dateLog1 = new JDateChooser();
		dateLog1.setBounds(20, 37, 140, 22);
		dateLog1.setDateFormatString("dd/MM/yyyy");
		getContentPane().add(dateLog1);
		
		dateLog2 = new JDateChooser();
		dateLog2.setBounds(199, 37, 140, 22);
		dateLog2.setDateFormatString("dd/MM/yyyy");
		getContentPane().add(dateLog2);
		
	}
	
	private void initTableLog() {
		 table.setModel(new javax.swing.table.DefaultTableModel(
	             new Object [][] {
	                 {null,null, null, null},
	                 {null,null, null, null},
	                 {null,null, null, null},
	                 {null,null, null, null}
	             },
	             new String [] {"","Kullanıcı","Tarih ","Hata Mesajı "}
	         ));
		
		 table.getColumnModel().getColumn(0).setMaxWidth(50);
		 table.getColumnModel().getColumn(1).setMaxWidth(100);
		 table.getColumnModel().getColumn(2).setMaxWidth(150);
		 table.getColumnModel().getColumn(3).setMaxWidth(700);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		 scrollPane.setViewportView(table);
	}
	
	private void getTable() {
		if(dateLog1.getDate()!=null && dateLog2.getDate()!=null) {
			logList=logServis.searchDate("logDate",dateLog1.getDate(),dateLog2.getDate(),new LogProduct());
		}
		else
		{
			logList=logServis.search(new LogProduct());
		}
		String[] columnNames= {"","Kullanıcı","Tarih ","Hata Mesajı "};
		String[][] data = new String[logList.size()][4];
		int sayi=0;
		for (int i = 0; i < logList.size(); i++) {
			sayi++;
			    data[i][0] = ""+sayi; 
  		  if(logList.get(i).getUser().getUsername()!=null) 
				data[i][1] = logList.get(i).getUser().getUsername(); 
			else 
				data[i][1]=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if(logList.get(i).getLogDate()!=null) 
             	data[i][2]=sdf.format(logList.get(i).getLogDate());
            else 
				data[i][2]=null;
			if(logList.get(i).getText()!=null) 
				data[i][3] = logList.get(i).getText();
			else 
				data[i][3]=null;
			
		}
		table = new JTable(data,columnNames);
		 table.getColumnModel().getColumn(0).setMaxWidth(50);
		 table.getColumnModel().getColumn(1).setMaxWidth(100);
		 table.getColumnModel().getColumn(2).setMaxWidth(150);
		 table.getColumnModel().getColumn(3).setMaxWidth(700);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrollPane.setViewportView(table);
		
	}
	
	private String getUserName(int id) {
		DatabaseBaseService<User> userServis=new DatabaseBaseService<User>();
		User user=userServis.findId(id,new User());
		if (user != null ) return user.getUsername();
		else return null;
	}
	
}

