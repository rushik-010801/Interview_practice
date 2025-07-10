package low_level_design;

/*
* Design a elevator (lift) which stops on preesing outside button and takes the passengers
* above or below the respective floor.
*
* Use Cases / Function :
* - Multiple Destinations can be triggered from a input floor
* - Every floor should be served before the elevator get to halt
*
* concrete questions :
 * - Should elevator also consider weigth check and should it stop if weight exceeds? - Yes
 * - should the input floor button up and down direction button? - Yes
 *
* Real Life case - For suppose, at 1st floor two input transaction came with 6 and 7th and 2nd floor
* we got 4 and 5 floors. Now, lift should stop at 4, 5, 6, and 7 in sorted order as from the direction
* of the lift.
*
 */

/*
* Identified Entities : DoorStatus, Direction, RunningStatus, WeightValidation(methods: validation),
* Elevator(addStop, openDoors, closeDoors, Alarm, upwardInput, downwardInput),
* ElevatorAccessories (soundAlarm, halt, move, closeDoors, openDoors, autoDoorTrigger)
*
* Realtime Object : ElevatorState
 */

/*
* Approaches for Elevator
*
* Approach 1 : Using Booleans array of number of floors. After crossing every floor,
 * elevator checks if any floor got activated to stop in the direction it is
 * going. - Logic for this, it checks its Direction and then checks if there any
 * floor active from current floor (in a loop) in the same direction. If not, Change to the new
 * direction. Also, if there is no active floor, direction is halted. Basically,
 * Direction is the main priority.
 * - Time Complexity : O(n)
 *
 * Approach 2 : using two priority Queues - Ascending Upward Queue and Descending Downward Queue
 * Suppose in above real life case : when another person presses floor 3 and 4 then they get added
 * into upward queue only if the lift haven't served the floor yet. But if it served it already like
 * when another person comes at floor 4 and presses 1, elevator has to server 6 and 7 first and adding
 * 1 to upward queue will destroy the logic. So, we will add it to the downward queue. When lift serves the
 * above floors or when the upward queue is empty, it changes the direction downward and uses downward
 * queue in descending order to server below floors.
 * -Time Complexity - O(log(n))
 */
import java.util.List;

enum DoorStatus {
	DOORS_OPEN, DOORS_CLOSED
}

enum Direction {
	UPWARD, DOWNWARD, HALTED
}

enum RunningStatus {
	HALT, RUNNING, ERROR
}

class ElevatorState {

	// this constant you can configure in Application config
	private static final int TOTAL_FLOORS = 7;
	private DoorStatus doorStatus;
	private Direction direction;
	private RunningStatus runningStatus;
	private int currentWeight;
	private int currentHaltedFloor;
	private boolean[] floorQueue;

	private static final ElevatorState elevatorState = new ElevatorState();

	private ElevatorState() {
		// fill all the statuses from hardware
		this.doorStatus = DoorStatus.DOORS_CLOSED;
		this.currentHaltedFloor = 0;
		this.direction = Direction.HALTED;
		this.runningStatus = RunningStatus.HALT;
		this.currentWeight = 0;
		this.floorQueue = new boolean[TOTAL_FLOORS + 1];
	}

	public static ElevatorState getInstance() {
		return elevatorState;
	}
	// Getters and Setters
}

interface WeightValidation {

	int MAX_WEIGHT = 450;

	public boolean validate(int currentWeight);
}

class WeightValidationImpl implements WeightValidation {

	@Override
	public boolean validate(int currentWeight) {
		return currentWeight < 450;
	}
}

interface ElevatorAccessories {

	// for sounding alarm
	boolean soundAlarm();

	// returns on which floor is the left halted
	int halt();

	// param : to which floor should the lift go
	// returns: if true it is successful
	boolean move(int floor);

	// Door Accessories : return true if the doors are about to perform
	boolean closeDoors();

	boolean openDoors();

	// Trigger timer to close the doors : return true if timer is set to closed
	boolean autoDoorTrigger();

	/*
	 * This function is the main logic of elevator - you can check the approaches
	 * with which you can implement.
	 */
	int nextStopCheck();

}

public class Elevator {

	private final ElevatorAccessories elevatorAccessories;
	private final WeightValidation weightValidation;
	private ElevatorState elevatorState = ElevatorState.getInstance();

	Elevator(ElevatorAccessories elevatorAccessories, WeightValidation weightValidation) {
		this.elevatorAccessories = elevatorAccessories;
		this.weightValidation = weightValidation;
	}

	public boolean addStop(List<Integer> floor) {
		// Simple make the boolean array of index floor true
		return true;
	}

	public boolean openDoors() {
		if (elevatorAccessories.openDoors()) {
			return elevatorAccessories.autoDoorTrigger();
		}
		return false;
	}

	public boolean closeDoors() {
		return elevatorAccessories.closeDoors();
	}

	public boolean Alarm() {
		return elevatorAccessories.soundAlarm();
	}

	public void inputStop(int inputFloor) {
		// Simple make the boolean array of index floor true
	}

}
