package com.rs.game.player.content.grandExchange;

public class GrandExchangeConfigurations {

	/**
	 * The interface id of the main interface
	 */
	protected static final int MAIN_INTERFACE = 105;

	/**
	 * The interface id of the sell interface
	 */
	protected static final int SELL_INTERFACE = 107;

	/**
	 * The array of the buttons used to send the buying interfaces
	 */
	public static final int[] BUY_BUTTON_IDS = { 31, 82, 101, 47, 63, 120 };

	/**
	 * The array of the buttons used to initaliaze the selling process
	 */
	public static final int[] SELL_BUTTON_IDS = { 83, 32, 48, 102, 121, 64 };

	public enum Progress {

		BUY_ABORTED(5),
		SELL_ABORTED(-3),
		RESET(0),
		BUY_PROGRESSING(4),
		FINISHED_BUYING(5),
		SELL_PROGRESSING(11),
		FINISHED_SELLING(13);

		Progress(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		private final int value;
	}
}
