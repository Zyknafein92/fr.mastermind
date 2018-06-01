package start;

import java.util.Arrays;

public abstract class Game extends Board{

	protected Integer playerSecret[]; 
	protected Integer computerSecret[];
	protected Integer combinaisonPlayer[];
	protected Integer combinaisonComputer[];
	protected boolean win = false;
	protected boolean loose = false;
	
	public Game () {
		
		playerSecret = new Integer [Board.optM];
		computerSecret = new Integer [Board.optM];
		combinaisonPlayer = new Integer [Board.optM];
		combinaisonComputer = new Integer [Board.optM];
		
	}
	
	public abstract Integer[] inputPlayer();
	
	public abstract Integer[] randomComputer();

	
	private boolean win () {

		if (Arrays.equals(this.getCombinaisonPlayer(),this.getComputerSecret())) {
			win = true;
		}else {
			win = false;
		}
		return win;
	}
	
	private boolean loose () {
		if (Arrays.equals(this.getCombinaisonComputer(), this.getPlayerSecret()))
			loose = true;
		else
			loose = false;

		return loose;
	}

	/**
	 * @return the playerSecret
	 */
	private Integer[] getPlayerSecret() {
		return playerSecret;
	}

	/**
	 * @return the computerSecret
	 */
	private Integer[] getComputerSecret() {
		return computerSecret;
	}

	/**
	 * @return the combinaisonPlayer
	 */
	private Integer[] getCombinaisonPlayer() {
		return combinaisonPlayer;
	}

	/**
	 * @return the combinaisonComputer
	 */
	private Integer[] getCombinaisonComputer() {
		return combinaisonComputer;
	}

	/**
	 * @param playerSecret the playerSecret to set
	 */
	private void setPlayerSecret(Integer[] playerSecret) {
		this.playerSecret = playerSecret;
	}

	/**
	 * @param computerSecret the computerSecret to set
	 */
	private void setComputerSecret(Integer[] computerSecret) {
		this.computerSecret = computerSecret;
	}

	/**
	 * @param combinaisonPlayer the combinaisonPlayer to set
	 */
	private void setCombinaisonPlayer(Integer[] combinaisonPlayer) {
		this.combinaisonPlayer = combinaisonPlayer;
	}

	/**
	 * @param combinaisonComputer the combinaisonComputer to set
	 */
	private void setCombinaisonComputer(Integer[] combinaisonComputer) {
		this.combinaisonComputer = combinaisonComputer;
	}
	
}
	
	
	
	
	