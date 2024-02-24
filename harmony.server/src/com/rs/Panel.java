package com.rs;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.*;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.utils.IPBanL;
import com.rs.utils.Utils;

public class Panel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel frame = new Panel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Panel() {
		setTitle("Hydrix CP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(87, 25, 194, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setBounds(156, 11, 92, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPunishment = new JLabel("Punishments");
		lblPunishment.setBounds(73, 60, 104, 14);
		contentPane.add(lblPunishment);
		
		JButton btnIpban = new JButton("I.P-Ban");
		btnIpban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IPban();
			}
		});
		btnIpban.setBounds(0, 85, 89, 23);
		contentPane.add(btnIpban);
		
		JButton btnMute = new JButton("Mute");
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mute();
			}
		});
		btnMute.setBounds(99, 85, 89, 23);
		contentPane.add(btnMute);
		
		JButton btnBan = new JButton("Ban");
		btnBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ban();
			}
		});
		btnBan.setBounds(0, 119, 89, 23);
		contentPane.add(btnBan);
		
		JButton btnJail = new JButton("Jail");
		btnJail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jail();
			}
		});
		btnJail.setBounds(0, 153, 89, 23);
		contentPane.add(btnJail);
		
		JButton btnKill = new JButton("Kill");
		btnKill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kill();
			}
		});
		btnKill.setBounds(99, 119, 89, 23);
		contentPane.add(btnKill);
		
		JButton btnFreezeunfreeze = new JButton("Freeze");
		btnFreezeunfreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				freeze();
			}
		});
		btnFreezeunfreeze.setBounds(99, 153, 89, 23);
		contentPane.add(btnFreezeunfreeze);
		
		JLabel lblItemManagment = new JLabel("Item St00f");
		lblItemManagment.setBounds(261, 60, 104, 14);
		contentPane.add(lblItemManagment);
		
		JButton btnGiveItem = new JButton("Give Item");
		btnGiveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giveItem();
			}
		});
		btnGiveItem.setBounds(204, 85, 89, 23);
		contentPane.add(btnGiveItem);
		
		JButton btnTakeItem = new JButton("Take Item");
		btnTakeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeItem();
			}
		});
		btnTakeItem.setBounds(303, 85, 89, 23);
		contentPane.add(btnTakeItem);
		
		JButton btnGiveAll = new JButton("Give Item All");
		btnGiveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giveAll();
			}
		});
		btnGiveAll.setBounds(204, 119, 89, 23);
		contentPane.add(btnGiveAll);
		
		JButton btnTakeAll = new JButton("Take Item All");
		btnTakeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAll();
			}
		});
		btnTakeAll.setBounds(303, 119, 89, 23);
		contentPane.add(btnTakeAll);
		
		JLabel lblTeleportation = new JLabel("Teleportation");
		lblTeleportation.setBounds(73, 206, 104, 14);
		contentPane.add(lblTeleportation);
		
		JButton btnTeleport = new JButton("Teleport Player");
		btnTeleport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleport();
			}
		});
		btnTeleport.setBounds(57, 231, 89, 23);
		contentPane.add(btnTeleport);
		
		JButton btnSendhome = new JButton("Teleport All");
		btnSendhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleAll();
			}
		});
		btnSendhome.setBounds(57, 265, 89, 23);
		contentPane.add(btnSendhome);
		
		JLabel lblFunPanel = new JLabel("EXTRAS");
		lblFunPanel.setBounds(261, 177, 56, 14);
		contentPane.add(lblFunPanel);
		
		JButton btnMakeDance = new JButton("Make Player Dance");
		btnMakeDance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeDance();
			}
		});
		btnMakeDance.setBounds(231, 202, 114, 23);
		contentPane.add(btnMakeDance);
		
		JButton btnDanceAll = new JButton("Make All Dance");
		btnDanceAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				danceAll();
			}
		});
		btnDanceAll.setBounds(231, 236, 114, 23);
		contentPane.add(btnDanceAll);
		
		JButton btnForceChat = new JButton("Force Talk");
		btnForceChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forceChat();
			}
		});
		btnForceChat.setBounds(231, 270, 114, 23);
		contentPane.add(btnForceChat);
		
		JButton btnSmite = new JButton("Smite Player");
		btnSmite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smite();
			}
		});
		btnSmite.setBounds(231, 304, 114, 23);
		contentPane.add(btnSmite);
		
		JButton btncorruptUp = new JButton("Corrupt Account");
		btncorruptUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corruptUp();
			}
		});
		btncorruptUp.setBounds(231, 338, 114, 23);
		contentPane.add(btncorruptUp);
		
		JButton btnNewButton = new JButton("Shutdown Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shutdown();
			}
		});
		btnNewButton.setBounds(10, 299, 194, 62);
		contentPane.add(btnNewButton);
		
	}
	
	public String getUsernameInput() {
		return textField.getText();
	}
	
	public void ban() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.getSession().getChannel().close();
		World.removePlayer(target);
		System.out.println("PARASCAPE: BANNED PLAYER: "+ name);
		JOptionPane.showMessageDialog(null, "BANNED PLAYER: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}

	public void forceChat() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		String chat = JOptionPane.showInputDialog("WHAT DO YOU WANT PLAYER TO SAY?");
		target.setNextForceTalk(new ForceTalk(chat));
		System.out.println("PARASCAPE: FORCING PLAYER: "+name+" TO SAY "+chat);
		JOptionPane.showMessageDialog(null, "FORCING PLAYER: "+name+" TO SAY "+chat, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void shutdown() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
		String sht = JOptionPane.showInputDialog("SHUTDOWN DELAY?");
		int delay = Integer.parseInt(sht);
		World.safeShutdown(false, delay);
		System.out.println("PARASCAPE: SHUTTING DOWN SERVER....");
		JOptionPane.showMessageDialog(null, "SHUTTING SERVER DOWN....", "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void makeDance() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.setNextAnimation(new Animation(7071));
		System.out.println("PARASCAPE: MADE PLAYER:  "+name+" DANCE");
		JOptionPane.showMessageDialog(null, "MADE PLAYER: "+name+" DANCE", "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void smite() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.setPrayerDelay(999999999);
		System.out.println("PARASCAPE: SMITED PLAYER: "+name);
		JOptionPane.showMessageDialog(null, "SMITED PLAYER: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void corruptUp() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.setPrayerDelay(999999999);
		target.addFreezeDelay(999999999);
		for (int i = 0; i < 10; i++)
			target.getCombatDefinitions().getBonuses()[i] = 0;
		for (int i = 14; i < target.getCombatDefinitions().getBonuses().length; i++)
			target.getCombatDefinitions().getBonuses()[i] = 0;
		System.out.println("PARASCAPE: CORRUPTED PLAYER ACCOUNT: "+name);
		JOptionPane.showMessageDialog(null, "CORRUPTED PLAYER ACCOUNT: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void IPban() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		boolean loggedIn = true;
		IPBanL.ban(target, loggedIn);
		System.out.println("PARASCAPE: I.P. BANNED PLAYER: "+ name);
		JOptionPane.showMessageDialog(null, "I.P. BANNED PLAYER: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void mute() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.setMuted(Utils.currentTimeMillis()
				+ (48 * 60 * 60 * 1000));
		System.out.println("PARASCAPE: MUTED PLAYER: "+ name);
		JOptionPane.showMessageDialog(null, "MUTED PLAYER: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void kill() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.applyHit(new Hit(target, target.getHitpoints(),
				HitLook.REGULAR_DAMAGE));
		target.stopAll();
		System.out.println("PARASCAPE: KILLED PLAYER: "+ name);
		JOptionPane.showMessageDialog(null, "KILLED PLAYER: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	public void jail() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.setJailed(Utils.currentTimeMillis()
				+ (24 * 60 * 60 * 1000));
		target.getControlerManager().startControler("JAIL CONTROLER");
		System.out.println("PARASCAPE: JAILED PLAYER: "+ name);
		JOptionPane.showMessageDialog(null, "JAILED PLAYER "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void freeze() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		target.addFreezeDelay(999999999);
		System.out.println("PARASCAPE: FROZE PLAYER: "+ name);
		JOptionPane.showMessageDialog(null, "FROZE PLAYER: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void giveItem() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		String id = JOptionPane.showInputDialog("ITEM I.D.:");
		String quantity = JOptionPane.showInputDialog("ITEM AMOUNT:");
		int item = Integer.parseInt(id);
		int amount = Integer.parseInt(quantity);
		target.getInventory().addItem(item, amount);
		System.out.println("PARASCAPE: GAVE ITEM (I.D.): "+item+" TO PLAYER: "+name);
		JOptionPane.showMessageDialog(null, "Given Item To "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void teleport() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		String x = JOptionPane.showInputDialog("COORDINATE X:");
		String y = JOptionPane.showInputDialog("COORDINATE Y:");
		String h = JOptionPane.showInputDialog("HEIGHT LEVEL:");
		int coordx = Integer.parseInt(x);
		int coordy = Integer.parseInt(y);
		int height = Integer.parseInt(h);
		Magic.sendNormalTeleportSpell(target, 0, 0, new WorldTile(coordx, coordy, height));
		System.out.println("PARASCAPE: TELEPORT PLAYER: "+name+" TO COORDS:"+coordx+", "+coordy+", "+height);
		JOptionPane.showMessageDialog(null, "PARASCAPE: TELEPORTED PLAYER: "+name+" TO COORDS: "+coordx+", "+coordy+", "+height, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void teleAll() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
		String x = JOptionPane.showInputDialog("COORDINATE X:");
		String y = JOptionPane.showInputDialog("COORDINATE Y:");
		String h = JOptionPane.showInputDialog("HEIGHT LEVEL:");
		int coordx = Integer.parseInt(x);
		int coordy = Integer.parseInt(y);
		int height = Integer.parseInt(h);
		for (Player teleall : World.getPlayers()) {
		Magic.sendNormalTeleportSpell(teleall, 0, 0, new WorldTile(coordx, coordy, height));
		}
		System.out.println("PARASCAPE: TELEPORTED ALL PLAYER TO COORDS: "+coordx+", "+coordy+", "+height);
		JOptionPane.showMessageDialog(null, "PARASCAPE: TELEPORTED ALL PLAYER TO COORDS: "+coordx+", "+coordy+", "+height, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
}
	
	public void danceAll() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
		for (Player danceAll : World.getPlayers()) {
			danceAll.setNextAnimation(new Animation(7071));
		}
		System.out.println("PARASCAPE: FORCING ALL PLAYER TO DANCE");
		JOptionPane.showMessageDialog(null, "PARASCAPE: FORCING ALL PLAYER TO DANCE", "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
}
	
	public void takeItem() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
	if (target != null) {
		String id = JOptionPane.showInputDialog("ITEM I.D.:");
		String quantity = JOptionPane.showInputDialog("ITEM AMOUNT:");
		int item = Integer.parseInt(id);
		int amount = Integer.parseInt(quantity);
		target.getInventory().deleteItem(item, amount);
		System.out.println("PARASCAPE: TAKEN ITEM (I.D.): "+item+" FROM PLAYER: "+ name);
		JOptionPane.showMessageDialog(null, "TAKEN ITEM (I.D.): "+item+" FROM PLAYER: "+name, "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	} else {
		JOptionPane.showMessageDialog(null, name+"-PLAYER DOES NOT EXIST", "CONTROL PANEL", JOptionPane.ERROR_MESSAGE);
		System.out.println("PARASCAPE: "
			+ Utils.formatPlayerNameForDisplay(name) + "PLAYER DOES NOT EXIST");
		}
	}
	
	public void giveAll() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
		String id = JOptionPane.showInputDialog("ITEM I.D.:");
		String quantity = JOptionPane.showInputDialog("ITEM AMOUNT:");
		int item = Integer.parseInt(id);
		int amount = Integer.parseInt(quantity);
		for (Player giveall : World.getPlayers()) {
		giveall.getInventory().addItem(item, amount);
		}
		System.out.println("PARASCAPE: GAVE ITEM (I.D.): "+item+" TO ALL PLAYER");
		JOptionPane.showMessageDialog(null, "GAVE ITEM (I.D.): "+item+" TO ALL PLAYER", "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void takeAll() {
		String name = getUsernameInput();
	Player target = World.getPlayerByDisplayName(name);
		String id = JOptionPane.showInputDialog("ITEM I.D.:");
		String quantity = JOptionPane.showInputDialog("ITEM AMOUNT:");
		int item = Integer.parseInt(id);
		int amount = Integer.parseInt(quantity);
		for (Player takeall : World.getPlayers()) {
		takeall.getInventory().deleteItem(item, amount);
		}
		System.out.println("PARASCAPE: TAKEN ITEM (I.D.): "+item+" FROM ALL PLAYER");
		JOptionPane.showMessageDialog(null, "TAKEN ITEM (I.D.): "+item+" FROM ALL PLAYER", "CONTROL PANEL", JOptionPane.PLAIN_MESSAGE);
	}
}