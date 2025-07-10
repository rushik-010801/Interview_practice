package low_level_design;

/*
* Step - 1: Problem Statement and Clarification
* This is a machine that replicates general shop which provide a existing product after payment.
* 1) Software should be able to display all the available items
* 2) Customer can select multiple items
* 3) Payments options should be available - might be using UPI, Card RFID
* 4) Timer should be given to complete the payment
* 5) after successfull payment customer should get the requested products.
* 6) Once the machine restarts it should ask for the Added products from the vendor and its Price
* 7) Vendor should authenticate himself before adding the products.
*
* Backend Checks :
* - Once the products are dispatched availability of specific products should be updated
*
* Pre_identified Entities : PaymentType(interface) -> UPIPayment(Impl) and RFIDPayment(impl),
*       ProductContext(availableItems, positionItemMap, cart),
*       Item(itemName, itemPrice), cartItem(Item, count, position), Position
*       VendingProcessor(DisplayItems, addItemToCart, Checkout, makePayment, DispatchCart, updateAvailableItems, resetCart),
*       VendingMachine - UserInterface
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Item {
	String itemName;
	double itemPrice;
	int availability;

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
}

class Position {
	int row;
	int col;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}

class CartItem {
	Item item;
	int count;
	Position position;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}

interface PaymentMethod {

	// this method is to display payment related info
	void displayDetails();

	// to check if the payment is successfull
	boolean checkPaymentStatus();

}

class UPIPayment implements PaymentMethod {

	private static final PaymentMethod instance = new UPIPayment();

	@Override
	public void displayDetails() {
		// this display QR code;
	}

	@Override
	public boolean checkPaymentStatus() {
		return false;
	}

	public static PaymentMethod getInstance() {
		return instance;
	}
}

class RFIDPayment implements PaymentMethod {

	private static final PaymentMethod instance = new RFIDPayment();

	@Override
	public void displayDetails() {
		// Display statement "Tap on the WIFI symbol to scan the card"
	}

	@Override
	public boolean checkPaymentStatus() {
		return false;
	}

	public static PaymentMethod getInstance() {
		return instance;
	}
}

abstract class VendingProcessor {

	private final ProductContext productContext;

	VendingProcessor(final ProductContext productContext) {
		this.productContext = productContext;
	}

	// Display all the avoilable Items
	abstract Item[][] DisplayItems();

	// updates ProductContext's cart using positionMap
	abstract boolean addItemtoCart(int row, int col, int frequency);

	// returns total amount to be paid
	abstract int checkout();

	// Takes the option of payment method and return if the payment is succesfull
	abstract boolean makePayment(PaymentMethod paymentMethod);

	// dispatches all the items in the cart and return if that is successfull
	abstract boolean dispatchCart();

	// updates the available items
	abstract void updateAvailableItems();

	// clears the CartList in the productContext
	abstract void resetCart();
}

class BootupProcessor {
	private final ProductContext productContext;
	int row = 0, col = 0;

	BootupProcessor(final ProductContext productContext) {
		this.productContext = productContext;
	}

	// add new Items at the startup time
	void addNewItem(int id, Item item) {
		productContext.positionMap[row][col] = id;
		productContext.itemMap.put(id, item);
		col++;
		if (col == 5)
			row++;
		col = col % 5;
	}

}

class ProductContext {
	int[][] positionMap = new int[5][5];
	Map<Integer, Item> itemMap = new HashMap<>();
	List<CartItem> cartList = new ArrayList<>();

	// Getter and Setters
}

public class VendingMachine {

	public static void main(String args[]) {
		ProductContext productContext = new ProductContext();
		BootupProcessor bootupProcessor = new BootupProcessor(productContext);
		PaymentMethod UPIpaymentMethod = UPIPayment.getInstance();
		PaymentMethod RFIDpaymentMethod = RFIDPayment.getInstance();

		Scanner sc = new Scanner(System.in);
		// Take the input and add them to product context
		for (int i = 0; i < 25; i++) {
			Item item = new Item();
			System.out.println("Enter the item name: ");
			item.setItemName(sc.nextLine());
			System.out.println("Enter the item price: ");
			item.setItemPrice(sc.nextInt());
			System.out.println("Enter the item Availability: ");
			item.setAvailability(sc.nextInt());
			bootupProcessor.addNewItem(i, item);
		}

		while (true) {
			// this is the App Runner and runs according to the Customer
		}

	}
}

/*
 * Few insights from chat gpt
 *
 * - You can take the methods - getItems, clearCart and calculateTotal to
 * cartItem because those are of that class
 *
 * - You missed the Vendor class which should be used for authentication -
 * Vendor -> (authenticate and restockProduct
 *
 * - Strategy Patterns and Factory Patterns can be used for Payment methods
 * 
 */
