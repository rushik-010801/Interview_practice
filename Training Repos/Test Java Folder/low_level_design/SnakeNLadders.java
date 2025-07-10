package low_level_design;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
* Step 1: Clarification
* 1) Need to get number of players ? Yes
* 2) Does Snake and Ladder positions be random in every different game? Yes
*
* Optional Question can be:
* Does game should continue after 1st player win?
* Does player get another chance if he/she gets 6 on the dice.
* Note: These can be asked only when the core business logic is asked to implement.
*
* Step 2: Identify Entities
* Pre-Identified Entities : Dice, Snake(kill_position, reach_position), Ladder(step_in, step_out)
*   GameAccessories(rollDice, changePosition), SnakerPositionConfigurer, Board
*   LadderPositionOCnfigurer, SnakeNLadders
*
* post Corrections in Entities :
* 	- Not needed Snake and Ladder entities because we can use Map
* 	- Even Dice is also not needed
* 	- We need whole context to store of the object of the game similar to the Elevator
*
* Main Logic / Data structure used for business usecase
* - I have used Hashmap which stores the player's position to player's id
* - from other resources we can observe that people have used Queue which would be more simpler.
 */
class SnakerPositionConfigurer {
	private static SnakerPositionConfigurer snakerPositionConfigurer;

	private SnakerPositionConfigurer() {

	}

	// This function can randomly assign snakes / we can configure it to take
	// default
	public Map<Integer, Integer> ConfigureSnakes() {
		// logic to randomly set the map
		return null;
	}

	public static SnakerPositionConfigurer getInstance() {
		if (snakerPositionConfigurer == null) {
			snakerPositionConfigurer = new SnakerPositionConfigurer();
		}
		return snakerPositionConfigurer;
	}

}

class LadderPositionConfigurer {

	private static LadderPositionConfigurer ladderPositionConfigurer;

	private LadderPositionConfigurer() {

	}

	// This function can randomly assign ladders / we can configure it to take
	// default
	public Map<Integer, Integer> ConfigureLadders() {
		// logic to randomly set the map
		return null;
	}

	public static LadderPositionConfigurer getInstance() {
		if (ladderPositionConfigurer == null) {
			ladderPositionConfigurer = new LadderPositionConfigurer();
		}
		return ladderPositionConfigurer;
	}

}

abstract class GameAccessories {

	private GameContext gameContext;

	public GameAccessories(GameContext gameContext) {
		this.gameContext = gameContext;
	}

	// this function is role dice which return a random number between 1 to 6
	abstract public int rollDice();

	// This function is to change the position of certain player
	abstract public void changePosition(int player, int toPosition);

	// business logic to check if the position contains snake,
	// if yes it returns reach position else 0
	abstract public int checkSnake(int position);

	// This checks if the position contains ladder, if yes it returns reach position
	// else 0
	abstract public int checkLadder(int position);

}

class GameContext {

	private int totalPlayers;
	private int currentPlayer;
	private Map<Integer, Integer> playerPositionMap;
	private Map<Integer, Integer> ladderPositionMap;
	private Map<Integer, Integer> snakePositionMap;
	private int winner;

	public GameContext(int totalPlayers) {
		this.totalPlayers = totalPlayers;
		// you can initialize the map with deafult 0
		this.playerPositionMap = new HashMap<>(totalPlayers);
		this.currentPlayer = 0;
		this.ladderPositionMap = LadderPositionConfigurer.getInstance().ConfigureLadders();
		this.snakePositionMap = SnakerPositionConfigurer.getInstance().ConfigureSnakes();
		this.winner = -1;
	}

	// Getter & Setters

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Map<Integer, Integer> getPlayerPositionMap() {
		return playerPositionMap;
	}

	public void setPlayerPositionMap(Map<Integer, Integer> playerPositionMap) {
		this.playerPositionMap = playerPositionMap;
	}

	public Map<Integer, Integer> getLadderPositionMap() {
		return ladderPositionMap;
	}

	public void setLadderPositionMap(Map<Integer, Integer> ladderPositionMap) {
		this.ladderPositionMap = ladderPositionMap;
	}

	public Map<Integer, Integer> getSnakePositionMap() {
		return snakePositionMap;
	}

	public void setSnakePositionMap(Map<Integer, Integer> snakePositionMap) {
		this.snakePositionMap = snakePositionMap;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int getTotalPlayers() {
		return totalPlayers;
	}
}

class Board {

	private static GameAccessories gameAccessories;
	private static GameContext gameContext;

	Board(int totalPlayers) {
		// Initialize gameAccessories with its impl
		this.gameAccessories = null;
		this.gameContext = new GameContext(totalPlayers);
	}

	// starts the new instance of the game context
	// note : we can slice different methods to different provate methods
	public void start() {
		while (true) {

			// Rolling Dice
			int diceNum = gameAccessories.rollDice();
			if (diceNum == 6) {
				diceNum += gameAccessories.rollDice();
			}

			// update the position
			Map<Integer, Integer> playerPositionMap = gameContext.getPlayerPositionMap();
			int currentPosition = playerPositionMap.get(gameContext.getCurrentPlayer()) + diceNum;
			playerPositionMap.put(gameContext.getCurrentPlayer(), currentPosition);

			// Check for Ladders and Snakes and update the position
			if (gameContext.getSnakePositionMap().containsKey(currentPosition)) {
				currentPosition = gameContext.getSnakePositionMap().get(currentPosition);
				playerPositionMap.put(gameContext.getCurrentPlayer(), currentPosition);
			} else if (gameContext.getLadderPositionMap().containsKey(currentPosition)) {
				currentPosition = gameContext.getLadderPositionMap().get(currentPosition);
				playerPositionMap.put(gameContext.getCurrentPlayer(), currentPosition);
			}

			// complete the game if any player reaches 100 position
			if (currentPosition == 100) {
				gameContext.setWinner(gameContext.getCurrentPlayer());
				break;
			} else {
				gameContext.setCurrentPlayer((gameContext.getCurrentPlayer() + 1) % gameContext.getTotalPlayers());
			}
		}
	}

	// return winner
	public int end() {
		return gameContext.getWinner() + 1;
	}
}

public class SnakeNLadders {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to SnakeNLadders\nPlease enter the Number of players Playing : ");
		int totalPlayers = sc.nextInt();
		Board board = new Board(totalPlayers);
		board.start();
		System.out.println("Congratulations Player : " + board.end());
	}
}
