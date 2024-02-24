package com.rs.tools;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

/**
 * @author Jake | Santa Hat @Rune-Server
 */
public class ItemCheckLocal extends JFrame {

	static JTextArea txt = new JTextArea();
	JScrollPane scrolltxt = new JScrollPane(txt);
	static JTextField txtItemField;
	static JLabel lblThereAre;
	
	private static int amountOfItems;
	private static JTextField textPlayerName;
	private static JRadioButton viewBank;
	
	private String text;
	
	public ItemCheckLocal() {
		setResizable(false);
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception j) {
			System.out.println("No such look and feel.");
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		scrolltxt.setBounds(71, 38, 400, 200);
		getContentPane().add(scrolltxt);	
		txt.setEditable(false);
		
		txtItemField = new JTextField();
		txtItemField.setBounds(223, 280, 86, 28);
		getContentPane().add(txtItemField);
		txtItemField.setColumns(10);
		
		JLabel lblItemId = new JLabel("Item:");
		lblItemId.setBounds(186, 291, 46, 14);
		getContentPane().add(lblItemId);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setText("");
				lblThereAre.setText("Enter an item id into the search field.");
				//amountOfItems = "Enter an item id into the search field.";
				if (textPlayerName.getText().length() == 0)
					checkForItem();
				else
					checkPlayerForItems();
			}
		});
		btnSearch.setBounds(438, 285, 89, 23);
		getContentPane().add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt.setText("");
				lblThereAre.setText("Enter an item id into the search field.");
				//amountOfItems = "Enter an item id into the search field.";
			}
		});
		btnClear.setBounds(438, 255, 89, 23);
		getContentPane().add(btnClear);
		
		lblThereAre = new JLabel("Enter an item id into the search field.");
		lblThereAre.setBounds(174, 13, 280, 14);
		getContentPane().add(lblThereAre);
		
		textPlayerName = new JTextField();
		textPlayerName.setBounds(223, 249, 86, 28);
		getContentPane().add(textPlayerName);
		textPlayerName.setColumns(10);
		textPlayerName.setText("");
		
		JLabel lblPlayerName = new JLabel("Player Name:");
		lblPlayerName.setBounds(148, 259, 75, 14);
		getContentPane().add(lblPlayerName);
		
		JLabel lblNotice = new JLabel("[Notice] Leave \"Player Name\" blank to search every player.");
		lblNotice.setBounds(71, 319, 456, 14);
		getContentPane().add(lblNotice);
		
		viewBank = new JRadioButton("View Bank");
		viewBank.setBounds(315, 255, 109, 23);
		getContentPane().add(viewBank);
	}
	
	public static void checkForItem() {
		if (txtItemField.getText().length() == 0)
			return;
		  int itemId = 0;
		  File dir = new File("data/characters/");
		  for (File child : dir.listFiles()) {
			  Player target = null;
				if (target == null) {
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(child);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
				if (target != null)
					SerializableFilesManager.savePlayer(target);
				if (target == null)
					continue;
				if (!isNumeric(txtItemField.getText())) {
					for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
						ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
						if (def.getName().toLowerCase().equalsIgnoreCase(txtItemField.getText())) {
							itemId = i;
							break;
						}
					}
				} else
					itemId = Integer.parseInt(txtItemField.getText());

				if (target.getInventory().containsItem((itemId + 1), 1) || target.getInventory().containsItem(itemId, 1)) {
					if (target.getInventory().containsItem((itemId + 1), 1))
						amountOfItems += target.getInventory().getNumberOf((itemId + 1));
						
					amountOfItems += target.getInventory().getNumberOf(itemId);
					txt.append(""+Utils.formatPlayerNameForDisplay(child.getName().replace(".p", ""))+" has "+( target.getInventory().getNumberOf(itemId) + target.getInventory().getNumberOf((itemId + 1)) )+" "+Item.getItemName(itemId)+" in their Inventory. \n");
				}
				//if (target.getEquipment().getItems().contains(new Item(itemId))) {
				//	amountOfItems++;
				//	txt.append(""+Utils.formatPlayerNameForDisplay(child.getName().replace(".p", ""))+" has "+Utils.convertString(Item.getItemName(itemId))+" "+Item.getItemName(itemId)+" in their Equipment. \n");
			//	}
				for (Item item : target.getBank().getContainerCopy()) {
					if (item.getId() == itemId) {
						amountOfItems += item.getAmount();
						txt.append(""+Utils.formatPlayerNameForDisplay(child.getName().replace(".p", ""))+" has "+item.getAmount()+" "+Item.getItemName(itemId)+" in their bank. \n");
					}
				}
		  	}
		  lblThereAre.setText("There are "+amountOfItems+" "+Item.getItemName(itemId)+" in the game.");
		  amountOfItems = 0;
	}
	
	private static void checkPlayerForItems() {
		if (txtItemField.getText().length() == 0 && !viewBank.isSelected())
			return;
		int itemId = 0;
		Player target;
		String name;
		target = World.getPlayerByDisplayName(textPlayerName.getText());
		if (target == null) {
			name = Utils.formatPlayerNameForProtocol(textPlayerName.getText());
				if(!SerializableFilesManager.containsPlayer(name)) 
					return;
			target = SerializableFilesManager.loadPlayer(name);
		}
		if (target != null)
			SerializableFilesManager.savePlayer(target);
		if (viewBank.isSelected()) {
			for (Item item : target.getBank().getContainerCopy()) {
				if (target.getBank().getContainerCopy() != null)
					txt.append(""+Utils.formatPlayerNameForDisplay(textPlayerName.getText())+" has "+item.getAmount()+" "+Item.getItemName(item.getId())+" in their Bank. \n");
			}
			return;
		}
		if (!isNumeric(txtItemField.getText())) {
			for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
				ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
				if (def.getName().toLowerCase().equalsIgnoreCase(txtItemField.getText())) {
					itemId = i;
					break;
				}
			}
		} else
			itemId = Integer.parseInt(txtItemField.getText());
		if (target.getInventory().containsItem((itemId + 1), 1) || target.getInventory().containsItem(itemId, 1)) {
			txt.append(""+Utils.formatPlayerNameForDisplay(textPlayerName.getText())+" has "+( target.getInventory().getNumberOf(itemId) + target.getInventory().getNumberOf((itemId + 1)) )+" "+Item.getItemName(itemId)+" in their Inventory. \n");
		}
		//if (target.getEquipment().getItems().contains(new Item(itemId))) {
		//	txt.append(""+Utils.formatPlayerNameForDisplay(textPlayerName.getText())+" has "+Utils.convertString(Item.getItemName(itemId))+" "+Item.getItemName(itemId)+" in their Equipment. \n");
		//}
		for (Item item : target.getBank().getContainerCopy()) {
			if (item.getId() == itemId) 
				txt.append(""+Utils.formatPlayerNameForDisplay(textPlayerName.getText())+" has "+item.getAmount()+" "+Item.getItemName(itemId)+" in their Bank. \n");
		}
	}
	
	public static boolean isNumeric(String str) {
	    for (char c : str.toCharArray()) {
	        if (!Character.isDigit(c)) 
	        	return false;
	    }
	    return true;
	}
	
	
	public static void main(String[] args) {
		ItemCheckLocal sta = new ItemCheckLocal();
		sta.setSize(550, 380);
		sta.setTitle("Project Z");
		sta.show();		
	}
}