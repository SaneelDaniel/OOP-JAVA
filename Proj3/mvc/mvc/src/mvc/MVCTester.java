package mvc;

/**
 * Tester Class with main method to test the mvc app
 * 
 * @author danielsaneel
 *
 */
public class MVCTester {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(500,500);
		Controller control = new Controller(model,view);
	}
}
