package com.rs.tools.pricetable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleContext;

import com.rs.tools.pricetable.utils.*;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.tools.pricetable.*;
import com.rs.utils.Utils;

public class PriceEditorLauncher {
	private static PriceEditorLauncher instance;
	private static JFrame frame;
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			frame = new JFrame("Item Manager");
			instance = new PriceEditorLauncher();
			instance.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	JPanel panel = new JPanel();
	JButton searchButton = new JButton("Search");
	JTable table = new JTable();
	JScrollPane scrollPane;
    
    static JTextPane outputPanel = new JTextPane();
    
	public void initialize() {
		System.out.println("[Price Editor] Price Guide is loading, please be patient...");	
		ItemManager.inits();	
		long start, end; start = System.currentTimeMillis();
				
		frame.setPreferredSize(new Dimension(500, 710));
		
		frame.setLocation(500, 280);
		panel.setLayout(new FlowLayout());
		frame.setResizable(false);
				
		searchField = new JTextField();
		table = new JTable();
		table.setEnabled(true);
			    
		searchField.setBounds(35, 440, 340, 25);
		searchButton.setBounds(390, 440, 75, 24);
		table.setBounds(35, 440, 340, 400);
		
		addTableListener();
		addButtonListener();
		addSearchListener();
			    
		frame.add(searchButton);
		frame.add(searchField);
		styleContext = new StyleContext();
		
		loadTable();
		
		JScrollPane scrollPane = new JScrollPane(outputPanel);
		
		scrollPane.setBounds(15, 490, 465, 175);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);
		
		end = System.currentTimeMillis();
		
		append("[Price Editor] Done loading Price Guide. (Execution Time: "+((double)(end - start) / 1000)+" seconds)");
		append("[Price Editor] Type a search term in the field above. Results are shown here.");
		append("[Price Editor] If you can't edit a price go to ItemManager and add the item manualy");
	}
	
	public void addTableListener() {
		Action action = new AbstractAction() {
			private static final long serialVersionUID = -1558313811715863257L;
			public void actionPerformed(ActionEvent e) {
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        
		        int row = tcl.getRow();
		        int column = tcl.getColumn();
		        if (column !=1 && column != 2 && column != 3) {
		        	append("This data will not save.");
		        	return;
		        }
		        int itemId = Integer.valueOf((String)table.getValueAt(row, 0).toString().replace(",", ""));
		        
		        String name = table.getValueAt(row, 1).toString();
		        int newValue = 0;
		        String newName = "";
		        if(column ==2 || column ==3)
		        	newValue = Integer.valueOf(tcl.getNewValue().toString().replace(",", ""));
		        else
		        	newName = tcl.getNewValue().toString();
		        if(column ==1) 
		        {
		        	if (ItemManager.updateName(itemId, newName)) {
		        	append("Saved Item: "+name+" (New Name: "+ItemManager.getName(itemId)+")");
		        	}
		        }
		        if(column ==2) 
		        {
		        	if (ItemManager.update(itemId, newValue)) {
		        	append("Saved Item: "+name+" (New Value: "+ItemManager.getPrice(itemId)+")");
		        	}
		        }
		        if(column ==3) 
		        {
		        	if (ItemManager.updateStackable(itemId, newValue)) {
		        	append("Saved Item: "+name+" (Stackable: "+ItemManager.getStackable(itemId)+")");
		        	}
		        }
		}
		};
		
		new TableCellListener(table, action);
	}
	
	private JTextField searchField;
	
	public boolean isNumeric(String s) {  
		try  {  
	      @SuppressWarnings("unused")
		double d = Double.parseDouble(s);  
	    } catch(NumberFormatException nfe) {  
	      return false;  
	    }  
	    return true;  
	}  
	
	public void search(String itemName) {
		outputPanel.setText("");
        int found = 0;
        for (int row = 0; row <= table.getRowCount() - 1; row++) {
     	   if (table.getValueAt(row, 1).toString().toLowerCase().contains(itemName.toLowerCase())) {
     		   if (found >= 1000) {
     			   append("Found 1000+ Results. Limited to prevent long search times.");
     			   return;
     		   }
     		   append(""+table.getValueAt(row, 0)+" - "+table.getValueAt(row, 1)+" (Value: "+table.getValueAt(row, 2)+")");
     		   found++;
     	   }
        }
        append("Found a total of "+found+" results for '"+itemName+"'");
        searchField.setText("");
	}
	
	public void searchId(String value) {
		int searchId = Integer.parseInt(value);
        for (int row = 0; row <= table.getRowCount() - 1; row++) {
        	int found = Integer.parseInt(table.getValueAt(row, 0).toString());
        	if (found == searchId) {
        		table.scrollRectToVisible(table.getCellRect(row, 0, true));
        		table.setRowSelectionInterval(row, row);
        	}
        }
        searchField.setText("");
	}
	
	public void loadTable() {
		 
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.addColumn("Item Id");
		model.addColumn("Item Name");
		model.addColumn("Value");
		model.addColumn("Stackable");
		
		// sets columns widths and keeps them from resizing
		table.getColumn("Item Id").setMinWidth(75);
		table.getColumn("Item Id").setMaxWidth(75);
		table.getColumn("Stackable").setMaxWidth(50);
		table.getColumn("Stackable").setMinWidth(50);
		table.getColumn("Value").setMinWidth(110);
		table.getColumn("Value").setMaxWidth(110);
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		
		for (int i = 1; i < 50000; i++) {
			try {
				
				if (ItemManager.values[i] == null) {
					continue;
				}
				
				int itemId = i;
				String name = ItemManager.getName(i);
				int value = ItemManager.getPrice(i);
				int stackable = ItemManager.getStackable(i);
				
				if (ItemManager.getName(i) == null || ItemManager.getName(i) == "null" || ItemManager.getName(i).trim() == "") {
					System.out.println("null "+i+"");
					continue;
				}
				
				list.add(new Object[]{""+itemId+"", ""+name+"", ""+formatNumber(value)+"", ""+formatNumber(stackable)+""});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (Object[] o : list) {
			model.addRow(o);
		}
		
		scrollPane = new JScrollPane(table);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(490, 520));
		panel.add(scrollPane);
	}
	
	private static StyleContext styleContext;
	
	public static void append(final String message) {       
        try {
            outputPanel.getDocument().insertString(outputPanel.getDocument().getLength(), message + "\r\n", styleContext.getStyle("black"));
            outputPanel.setCaretPosition(outputPanel.getDocument().getLength());
        } catch (BadLocationException e) {
           
        }
    }
	public void addSearchListener() {
		searchField.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	           String value = searchField.getText();
	         
	           if (value == "" || value == null) {
	        	   return;
	           }
	          
	           if (isNumeric(value)) {
	        	   searchId(value);
	        	   return;
	           }
	           
	           if (value.equalsIgnoreCase("reset")) {
	        	   outputPanel.setText("");
	        	   searchField.setText("");
	        	   return;
	           }
	           
	           search(value);
	        }
	    });
	}
	
	public void addButtonListener() {
		searchButton.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e) { 
	        	if (searchField.getText() == null || searchField.getText() == "") {
	        		System.out.println("Please enter a valid search term.");
	        		return;
	        	}
	            search(searchField.getText());
	        } 
	    });
	}

	public static String formatNumber(int num) {
		return NumberFormat.getInstance().format(num);
	}

	
}

