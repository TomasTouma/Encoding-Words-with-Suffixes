package ie.atu.sw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Tomasz Touma
 * @version 1.0
 * @since 1.8
 * 
 *        The class Menu is the class that contain the main menu of the program,
 *        when an object of it is generated, its constructor get called that
 *        handle the menu and display it. it contains 3 methods,<b> loadHeader,
 *        loadMenu and handleMenu</b>.
 * 
 * @see Runner
 * 
 */
public class Menu {
	Scanner sc = new Scanner(System.in);
	FileProcessor fileProcessor = new FileProcessor();
	Encode encode = new Encode();
	Decode decode = new Decode();

	/**
	 * 
	 * Constructor of the class, that calls the method <b>handleMenu</b>.
	 * 
	 * @throws Exception
	 */
	public Menu() throws Exception {
		handleMenu();
	}

	/**
	 * 
	 * <b>handleMenu</b> is a void method that handle user choice from the loadMenu
	 * method. First it display the menu header by calling <b>loadHeader</b> method.
	 * It call <b>loadMenu</b> method that return an integer which is the user
	 * choice from the menu It loop through the choices until -1 is selected.
	 * 
	 * if choice is 1, it let the user select the mapping file. if choice is 2, it
	 * ask the user to add path of the file that they want to encode. if choice is
	 * 3, it ask the user for the output file path. if choice is 4, it encode the
	 * chosen file. if choice is 5, it decode the encoded file.
	 * 
	 * @see loadHeader()
	 * @see laodMenu()
	 * @throws Exception
	 */
	private void handleMenu() throws Exception {
		loadHeader();

		int choice = Integer.parseInt(loadMenu());

		while (choice != -1) {

			if (choice == 1) {
				System.out.println("Please enter the path of the mapping file, or press 1 to use default file.");
				fileProcessor.setMappingFile(sc.nextLine());
				// lm.load(fp.getMappingFilePath());

			} else if (choice == 2) {
				// Write the path of the text file that want to be encoded
				System.out.println("Please enter the path of the file that you want to Encode.");
				fileProcessor.setEncodingFile(sc.nextLine());

			} else if (choice == 3) {
				// Write the path of the text file that want to be decode
				System.out.println("Please enter the path of the file that you want to Decode.");
				fileProcessor.setDecodingFile(sc.nextLine());

			} else if (choice == 4) {
				// choose the encode output file or where the encoded file will be stored
				System.out.println("Please enter the path of the Encode output file, or press 1 to use default file.");
				fileProcessor.setEncodeOutputFile(sc.nextLine());

			} else if (choice == 5) {
				// choose the decode output file or where the encoded file will be stored
				System.out.println("Please enter the path of the Decode output file, or press 1 to use default file.");
				fileProcessor.setDecodeOutputFile(sc.nextLine());

			} else if (choice == 6) {
				encode.validateEncodingFile(fileProcessor.getEncodingFilePath(), fileProcessor.getMappingFilePath(),
						fileProcessor.getEncodeOutputFilePath());

			} else if (choice == 7) {
				// decode back the encoded file from the output file
				decode.validateDecodingFile(fileProcessor.getDecodingFilePath(), fileProcessor.getMappingFilePath(),
						fileProcessor.getDecodeOutputFilePath());

			} else {
				System.out.println("Wrong input!");
				System.out.println("Please select option [1-7] or -1 to Quit.");
			}
			choice = Integer.parseInt(loadMenu());
		}

	}

	/**
	 * <b>loadHeader</b> is a void method that doesn't take parameters it show the
	 * header of the program.
	 * 
	 */
	private void loadHeader() {
		System.out.println(ConsoleColour.GREEN_BOLD);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*              Encoding Words with Suffixes                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
	}

	/**
	 * 
	 * <b>loadMenu</b> display the menu of choices for the user and ask the user for
	 * an input or choice, it validate the choice in by comparing it to the values
	 * in an array that contain valid choices, and return the choice to be used in
	 * <b>handleMenu</b> method.
	 * 
	 * @see handleMenu()
	 * 
	 * @return choice
	 * @throws Exception
	 */
	private String loadMenu() throws Exception {
		System.out.println(ConsoleColour.WHITE_BOLD);
		System.out.println("(1) Specify Mapping File (default: encoding-10000.csv)");
		System.out.println("(2) Specify Text File to Encode");
		System.out.println("(3) Specify Text File to Decode");
		System.out.println("(4) Specify Encode Output File (default: ./out.txt)");
		System.out.println("(5) Specify Decode Output File (default: ./decode_out.txt)");
		System.out.println("(6) Encode Text File");
		System.out.println("(7) Decode Text File");
		System.out.println("(-1) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-7] or -1 to Quit>");

		System.out.println(ConsoleColour.WHITE_BOLD);
		String choice = sc.nextLine().trim();

		String[] validChoices = { "1", "2", "3", "4", "5", "6", "7", "-1" };

		// While choice is NOT contained in validChoices array
		while (!Arrays.asList(validChoices).contains(choice)) {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("Invalid input! Please enter 1-7 or -1");
			System.out.println("Select Option [1-7] or -1 to Quit> ");
			System.out.print(ConsoleColour.WHITE_BOLD);
			choice = sc.nextLine().trim();
		}

		return choice;

		// -----------------------------------------------------------------------------
		// You may want to include a progress meter in you assignment!
		/*
		 * System.out.print(ConsoleColour.YELLOW); // Change the colour of the console
		 * text int size = 100; // The size of the meter. 100 equates to 100% for (int i
		 * = 0; i < size; i++) { // The loop equates to a sequence of processing steps
		 * printProgress(i + 1, size); // After each (some) steps, update the progress
		 * meter Thread.sleep(10); // Slows things down so the animation is visible } //
		 * Slows things down so the animation is visible
		 */
	}

	/*
	 * Terminal Progress Meter ----------------------- You might find the progress
	 * meter below useful. The progress effect works best if you call this method
	 * from inside a loop and do not call System.out.println(....) until the
	 * progress meter is finished.
	 * 
	 * Please note the following carefully:
	 * 
	 * 1) The progress meter will NOT work in the Eclipse console, but will work on
	 * Windows (DOS), Mac and Linux terminals.
	 * 
	 * 2) The meter works by using the line feed character "\r" to return to the
	 * start of the current line and writes out the updated progress over the
	 * existing information. If you output any text between calling this method,
	 * i.e. System.out.println(....), then the next call to the progress meter will
	 * output the status to the next line.
	 * 
	 * 3) If the variable size is greater than the terminal width, a new line escape
	 * character "\n" will be automatically added and the meter won't work properly.
	 * 
	 * 
	 */
	public static void printProgress(int index, int total) {
		if (index > total)
			return; // Out of range
		int size = 50; // Must be less than console width
		char done = '█'; // Change to whatever you like.
		char todo = '░'; // Change to whatever you like.

		// Compute basic metrics for the meter
		int complete = (100 * index) / total;
		int completeLen = size * complete / 100;

		/*
		 * A StringBuilder should be used for string concatenation inside a loop.
		 * However, as the number of loop iterations is small, using the "+" operator
		 * may be more efficient as the instructions can be optimized by the compiler.
		 * Either way, the performance overhead will be marginal.
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append((i < completeLen) ? done : todo);
		}

		/*
		 * The line feed escape character "\r" returns the cursor to the start of the
		 * current line. Calling print(...) overwrites the existing line and creates the
		 * illusion of an animation.
		 */
		System.out.print("\r" + sb + "] " + complete + "%");

		// Once the meter reaches its max, move to a new line.
		if (done == total)
			System.out.println("\n");
	}

}
