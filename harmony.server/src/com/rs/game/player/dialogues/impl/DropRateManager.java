package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

/**
 * 
 * 
 * @author Connor - Mr Joopz
 *
 */
public class DropRateManager extends Dialogue {

	public DropRateManager() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Choose an npc genre", "Slayer Monsters",
				"Bosses", "Random Monsters", "Nevermind");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
			sendOptionsDialogue("Slayer Monsters", "Abyssal Demon",
					"Dark Beast", "Edimmu", "Ganodermic Beasts",
					"Next Page");
			stage = 2;
		} else if (componentId == OPTION_2) {
			stage = 4;
			sendOptionsDialogue("Bosses", "King Black Dragon",
					"Blink", "Corporeal Beast", "Sirenic",
					"Next Page");
		}
		else if (componentId == OPTION_3) {
			stage = 9;
			sendOptionsDialogue("Other Monsters", "Black Dragon",
					"Iron Dragon", "Steel Dragon", "Barrows (Standard)",
					"Nothing");
		}
		else if (componentId == OPTION_4) {
			end();
		}
		} //this is a shit way they had it done before to check drop rates lol i think the file is like dropmanager??
	 else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.getInterfaceManager().sendDropRates(1615); //abby demon
				end();
			} else if (componentId == OPTION_2) {
				player.getInterfaceManager().sendDropRates(2783); //dark beast
				end();
			} else if (componentId == OPTION_3) {
				player.getInterfaceManager().sendDropRates(30027); //edimmu
				end();
			}  else if (componentId == OPTION_4) {
				player.getInterfaceManager().sendDropRates(14696); //gano
				end();
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Slayer Monsters", "Glacors",
						"Gargoyle", "Ice Strykewyrms", "Kuradal Brothers",
						"Mithril Dragon");
				stage = 3;
			}
			} else if (stage == 3) {
				if (componentId == OPTION_1) {
					player.getInterfaceManager().sendDropRates(14301); //glacor
					end();
				} else if (componentId == OPTION_2) {
					player.getInterfaceManager().sendDropRates(1827); //gargoyle
					end();
				} else if (componentId == OPTION_3) {
					player.getInterfaceManager().sendDropRates(9463); //ice strykewyrm
					end();
				}  else if (componentId == OPTION_4) {
					player.getInterfaceManager().sendDropRates(30045); //kuradal bro
					end();
				} else if (componentId == OPTION_5) {
					player.getInterfaceManager().sendDropRates(5363); //mith drag
					end();
				} 
			}
				else if (stage == 4) {
					if (componentId == OPTION_1) {
						player.getInterfaceManager().sendDropRates(50); //kbd
						end();
					} else if (componentId == OPTION_2) {
						player.getInterfaceManager().sendDropRates(12878); //blink
						end();
					} else if (componentId == OPTION_3) {
						player.getInterfaceManager().sendDropRates(8133); //corp
						end();
					}  else if (componentId == OPTION_4) {
						player.getInterfaceManager().sendDropRates(30008); //sirenic
						end();
					} else if (componentId == OPTION_5) {
						stage = 5;
						sendOptionsDialogue("Bosses", "Nex",
								"Rise of Six (standard)", "WildyWyrm", "WildyWyrm (Hardmode)",
								"Next Page");
			}
				}
					else if (stage == 5) {
						if (componentId == OPTION_1) {
							player.getInterfaceManager().sendDropRates(13450); //13450
							end();
						} else if (componentId == OPTION_2) {
							player.getInterfaceManager().sendDropRates(30011); //ROT6
							end();
						} else if (componentId == OPTION_3) {
							player.getInterfaceManager().sendDropRates(3334); //ww
							end();
						}  else if (componentId == OPTION_4) {
							player.getInterfaceManager().sendDropRates(30025); //ww hm
							end();
						} else if (componentId == OPTION_5) {
							stage = 6;
							sendOptionsDialogue("Bosses", "Bandos",
									"Saradomin", "Zamorak", "Armadyl",
									"Next Page");
				}
					}
						else if (stage == 6) {
							if (componentId == OPTION_1) {
								player.getInterfaceManager().sendDropRates(6260); //bandos
								end();
							} else if (componentId == OPTION_2) {
								player.getInterfaceManager().sendDropRates(6247); //saradomin
								end();
							} else if (componentId == OPTION_3) {
								player.getInterfaceManager().sendDropRates(6203); //zamorak
								end();
							}  else if (componentId == OPTION_4) {
								player.getInterfaceManager().sendDropRates(6222); //Armadyl
								end();
							} else if (componentId == OPTION_5) {
								stage = 7;
								sendOptionsDialogue("Bosses", "Vorago",
										"Ava of Destruction", "Night-Gazer", "Dryax",
										"Next Page");
					}
						}
							else if (stage == 7) {
								if (componentId == OPTION_1) {
									player.getInterfaceManager().sendDropRates(30000); //vorago
									end();
								} else if (componentId == OPTION_2) {
									player.getInterfaceManager().sendDropRates(8596); //ava of destruction
									end();
								} else if (componentId == OPTION_3) {
									player.getInterfaceManager().sendDropRates(9752); //night gazer
									end();
								}  else if (componentId == OPTION_4) {
									player.getInterfaceManager().sendDropRates(15174); //dryax
									end();
								} else if (componentId == OPTION_5) {
									sendOptionsDialogue("Bosses", "Kalphite King",
											"Tectonic the Beast", "Hope Devourer", "Sunfreet",
											"Next Page");
									stage = 8;
						}
							}
								else if (stage == 8) {
									if (componentId == OPTION_1) {
										player.getInterfaceManager().sendDropRates(29993); //kk
										end();
									} else if (componentId == OPTION_2) {
										player.getInterfaceManager().sendDropRates(10106); //tectonic
										end();
									} else if (componentId == OPTION_3) {
										player.getInterfaceManager().sendDropRates(12900); //Drygonic
										end();
									}  else if (componentId == OPTION_4) {
										player.getInterfaceManager().sendDropRates(15222); //Sunfreet
										end();
									} else if (componentId == OPTION_5) {
										sendOptionsDialogue("Bosses", "Bad Santa",
												"Anivia", "Necrolord (Vet boss)", "Yt'Lagor the Thunderous",
												"Next page");
										stage = 11;
							}
								}
									else if (stage == 11) {
										if (componentId == OPTION_1) {
											player.getInterfaceManager().sendDropRates(1552); //bad santa
											end();
										} else if (componentId == OPTION_2) {
											player.getInterfaceManager().sendDropRates(30082); //Anivia
											end();
										} else if (componentId == OPTION_3) {
											player.getInterfaceManager().sendDropRates(11751); //NecroLord
											end();
										}  else if (componentId == OPTION_4) {
											player.getInterfaceManager().sendDropRates(11872); //Yt'Lagor the Thunderous
											end();
										} else if (componentId == OPTION_5) {
											sendOptionsDialogue("Bosses", "Sliske",
													"Bork", "Icy Bones", "VIP Boss",
													"The Raptor");
											stage = 12;
									}
								}
									else if (stage == 12) {
										if (componentId == OPTION_1) {
											player.getInterfaceManager().sendDropRates(14262); //Sliske
											end();
										} else if (componentId == OPTION_2) {
											player.getInterfaceManager().sendDropRates(7134); //Bork
											end();
										} else if (componentId == OPTION_3) {
											player.getInterfaceManager().sendDropRates(10057); //IcyBones
											end();
										}  else if (componentId == OPTION_4) {
											player.getInterfaceManager().sendDropRates(8281); //vip boss
											end();
										} else if (componentId == OPTION_5) {
											player.getInterfaceManager().sendDropRates(13955); //The Raptor
											end();
									}
								}
									else if (stage == 9) {
										if (componentId == OPTION_1) {
											player.getInterfaceManager().sendDropRates(54); //Black drag
											end();
										} else if (componentId == OPTION_2) {
											player.getInterfaceManager().sendDropRates(1591); //Iron drag
											end();
										} else if (componentId == OPTION_3) {
											player.getInterfaceManager().sendDropRates(1592); //Steel drag
											end();
										}  else if (componentId == OPTION_4) {
											player.getInterfaceManager().sendDropRates(2026); //Barrows
											end();
										} else if (componentId == OPTION_5) {
											end();
									}
								}
							}
		

	@Override
	public void finish() {
	}

}
