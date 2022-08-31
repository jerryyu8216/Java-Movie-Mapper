import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Frontend extends Backend {
	// Filepath to csv file
	private static String[] filePath = new String[] { "movies.csv" };
	// Constantly updating on which index Frontend.getThreeMovies() will start
	// counting from
	private static int index = 0;

	private static List<Integer> unselectedRatings;
	private static List<Integer> selectedRatings;
	private static List<String> unselectedGenres;
	private static List<String> selectedGenres;

	/**
	 * Default constructor making a call to super class Backend
	 * 
	 * @param args array of strings containing the file path to the csv file
	 */
	public Frontend(String[] args) {
		super(args);
		unselectedRatings = new ArrayList<Integer>();
		selectedRatings = new ArrayList<Integer>();
		unselectedGenres = this.getAllGenres();
		selectedGenres = new ArrayList<String>();
	}

	/**
	 * Default constructor making a call to super class Backend
	 * 
	 * @param r reader to read data from csv file
	 */
	public Frontend(Reader r) {
		super(r);
		unselectedRatings = new ArrayList<Integer>();
		selectedRatings = new ArrayList<Integer>();
		unselectedGenres = this.getAllGenres();
		selectedGenres = new ArrayList<String>();
	}

	/**
	 * Private helper method that is called when a screen is exited. Shows the
	 * movies with the criteria the user inputs from the genre selection screen and
	 * the ratings selection screen.
	 * 
	 * @param f the Frontend class
	 */
	private static void backToHomeScreen(Frontend f) {
		// Resets the index to begin at index 0
		index = 0;

		// Prints out ** Home Screen **, directions, number of movies, and the list of
		// movies
		System.out.println("\n** Home Screen **");
		System.out.println("\n(Press 'g' to enter genres screen)");
		System.out.println("(Press 'r' to enter ratings screen)");
		System.out.println("(Press 'x' to exit the program)");
		System.out.println("(Type '3' to see what's next on list)");
		System.out.println("\n-- There are " + f.getNumberOfMovies() + " movies --");
		System.out.println("\n-- List of movies --");

		List<MovieInterface> m = f.getThreeMovies(0);
		for (int i = 0; i < m.size(); i++) {
			System.out.println(m.get(i));
		}

		System.out.print("\nWhat do you want to do? ");
	}

	/**
	 * Private helper method that is called when the user inputs 3. Index is
	 * icremented by three and the next three movies are showed. Shows two different
	 * screens depending whether index is greater than the size of amount of moives.
	 * 
	 * @param m        list of MovieInterface
	 * @param frontend Frontend object
	 */
	private static void inputOf3(List<MovieInterface> m, Frontend frontend) {
		index = index + 3;
		// Will show two different screens depending wheter or not index is less than or
		// greater than the number of movies.
		if (index < frontend.getNumberOfMovies()) {
			m = frontend.getThreeMovies(index);

			// Home screen, directions, number of movies, and movie list
			System.out.println("\n** Home Screen **");
			System.out.println("\n(Press 'g' to enter genres screen)");
			System.out.println("(Press 'r' to enter ratings screen)");
			System.out.println("(Press 'x' to exit the program)");
			System.out.println("(Type '3' to see what's next on list)");
			System.out.println("(Type '4' to see previous three movies)");
			System.out.println("\n-- There are " + frontend.getNumberOfMovies() + " movies --");
			System.out.println("\n-- Movies List --");

			for (int i = 0; i < m.size(); i++) {
				System.out.println(m.get(i));
			}

			System.out.print("\nWhat do you want to do? ");
		} else if (index >= frontend.getNumberOfMovies()) {
			// index is greater than or equal to getNumberOfMovies(), index is equal to
			// getNumberOfMovies -1
			index = frontend.getNumberOfMovies() - 1;
			m = frontend.getThreeMovies(index);

			// Home screen, directions, number of movies, and movie list
			System.out.println("\n** Home Screen **");
			System.out.println("\n(Press 'g' to enter genres screen)");
			System.out.println("(Press 'r' to enter ratings screen)");
			System.out.println("(Press 'x' to exit the program)");
			System.out.println("(Type '4' to see previous three movies)");
			System.out.println("\n-- There are " + frontend.getNumberOfMovies() + " movies --");
			System.out.println("\n-- Movies List --");

			for (int i = 0; i < m.size(); i++) {
				System.out.println(m.get(i));
			}

			System.out.print("\nWhat do you want to do? ");
		}
	}

	/**
	 * Private helper method that is called when the user inputs 4. 3 is subtracted
	 * from index and the next previous three movies are showed. Shows two different
	 * screens depending whether index is less than or equal to 0.
	 * 
	 * @param m        list of MovieInterface
	 * @param frontend Frontend object
	 */
	private static void inputOf4(List<MovieInterface> m, Frontend frontend) {
		index = index - 3;
		if (index <= 0) {
			// If index is less than or equal to 0, index is equal to 0.
			index = 0;
			m = frontend.getThreeMovies(index);

			// Home screen, directions, number of movies, and movie list
			System.out.println("\n** Home Screen **");
			System.out.println("\n(Press 'g' to enter genres screen)");
			System.out.println("(Press 'r' to enter ratings screen)");
			System.out.println("(Press 'x' to exit the program)");
			System.out.println("(Type '3' to see what's next on list)");
			System.out.println("\n-- There are " + frontend.getNumberOfMovies() + " movies --");
			System.out.println("\n-- Movies List --");

			for (int i = 0; i < m.size(); i++) {
				System.out.println(m.get(i));
			}

			System.out.print("\nWhat do you want to do? ");
		} else {
			m = frontend.getThreeMovies(index);

			// Home screen, directions, number of movies, and movie list
			System.out.println("\n** Home Screen **");
			System.out.println("\n(Press 'g' to enter genres screen)");
			System.out.println("(Press 'r' to enter ratings screen)");
			System.out.println("(Press 'x' to exit the program)");
			System.out.println("(Type '3' to see what's next on list)");
			System.out.println("(Type '4' to see previous three movies)");
			System.out.println("\n-- There are " + frontend.getNumberOfMovies() + " movies --");
			System.out.println("\n-- Movies List --");

			for (int i = 0; i < m.size(); i++) {
				System.out.println(m.get(i));
			}

			System.out.print("\nWhat do you want to do? ");
		}
	}

	private static void intialHomeScreen(List<MovieInterface> m, Frontend frontend) {
		// Welcome statement, lists all commands the user can do, and lists
		// first 3 movies based on their ratings
		System.out.println("** Welcome to Movie Mapper **");
		System.out.println("\n(Press 'g' to enter genres screen)");
		System.out.println("(Press 'r' to enter ratings screen)");
		System.out.println("(Press 'x' to exit the program)");
		System.out.println("(Type '3' to see what's next on list)");
		System.out.println("\n-- List of movies --");

		// By default, all ratings 0-10 will be selected
		frontend.addAvgRating("0");
		frontend.addAvgRating("1");
		frontend.addAvgRating("2");
		frontend.addAvgRating("3");
		frontend.addAvgRating("4");
		frontend.addAvgRating("5");
		frontend.addAvgRating("6");
		frontend.addAvgRating("7");
		frontend.addAvgRating("8");
		frontend.addAvgRating("9");
		frontend.addAvgRating("10");

		// Prints out the movies selected from index 0.
		m = frontend.getThreeMovies(index);
		for (int i = 0; i < m.size(); i++) {
			System.out.println(m.get(i));
		}
	}

	/**
	 * Private helper method that is called when the user inputs "g". Takes the user
	 * to the genre screen, and will continue to run until the user inputs "x" to
	 * terminate the method.
	 * 
	 * @param frontend Frontend object
	 * @param scanner Scanner object
	 * @param userInput String of user's input
	 */
	private static void genreScreen(Frontend frontend, Scanner scanner, String userInput) {
		// Starting screen with directions
		System.out.println("\n** Genre Selection Screen **");
		System.out.println("\n(Type in 'select' to select genres)");
		System.out.println("(Type in 'remove' to remove genres)");
		System.out.println("(Type in 'x' to go to home screen)");
		System.out.println("\nSelected genres: " + selectedGenres.toString());
		System.out.println("Unselected genres: " + unselectedGenres.toString());
		System.out.print("\nWhat do you want to do? ");

		// Certain screens will appear based on user input. Screens will continue to run
		// until userInput is equal to "x".
		userInput = scanner.next().toLowerCase();
		while (!userInput.equals("x")) {
			// Takes the user to the selection screen where they can select the genres
			if (userInput.equals("select")) {
				System.out.println("\n** Selection Screen **");
				System.out.println("\n(Select genres based on the index)");
				System.out.println("(Type '-1' to stop selecting genres)");
				System.out.println("\nSelected genres: " + selectedGenres.toString());
				System.out.println("Unselected genres: " + unselectedGenres.toString());

				System.out.print("\nWhat do you want to do? ");

				// Takes and int from the user in order to select the index of desired genre.
				int userInt = scanner.nextInt();
				while (userInt != -1) {
					String genre = unselectedGenres.get(userInt);
					selectedGenres.add(unselectedGenres.remove(userInt));
					frontend.addGenre(genre);

					// Default screen with directions
					System.out.println("\n** Selection Screen **");
					System.out.println("\n(Select genres by typing the index)");
					System.out.println("(Type '-1' to stop selecting genres)");
					System.out.println("\nSelected genres: " + selectedGenres.toString());
					System.out.println("Unselected genres: " + unselectedGenres.toString());
					System.out.print("\nWhat do you want to do? ");

					userInt = scanner.nextInt();
				}
			}

			// Takes the user to the removal screen where they can unselect genres
			if (userInput.equals("remove")) {
				System.out.println("\n** Removal Screen **");
				System.out.println("\n(Remove a genre based on the index)");
				System.out.println("(Type '-1' to stop removing genres)");
				System.out.println("\nSelected genres: " + selectedGenres.toString());
				System.out.print("\nWhat do you want to do? ");

				int userInt = scanner.nextInt();
				while (userInt != -1) {
					String genre = selectedGenres.get(userInt);
					unselectedGenres.add(selectedGenres.remove(userInt));
					frontend.removeGenre(genre);

					// Default screen with directions
					System.out.println("\n** Removal Screen **");
					System.out.println("\n(Remove a genre based on the index)");
					System.out.println("(Type '-1' to stop selecting genres)");
					System.out.println("\nSelected genres: " + selectedGenres.toString());
					System.out.print("\nWhat do you want to do? ");

					userInt = scanner.nextInt();
				}
			}

			// Screen that will print out when user 
			System.out.println("\n** Genre Selection Screen **");
			System.out.println("\n(Type in 'select' to select genres)");
			System.out.println("(Type in 'remove' to remove genres)");
			System.out.println("(Type in 'x' to go to home screen)");
			System.out.println("\nSelected genres: " + selectedGenres.toString());
			System.out.println("Unselected genres: " + unselectedGenres.toString());
			System.out.print("\nWhat do you want to do? ");

			userInput = scanner.next().toLowerCase();
		}
		backToHomeScreen(frontend);
	}

	private static void ratingScreen(Frontend frontend, Scanner scanner, String userInput) {
		selectedRatings = new ArrayList<Integer>();

		for (String s : frontend.getRatings) {
			selectedRatings.add(Integer.parseInt(s));
		}

		Collections.sort(selectedRatings);
		Collections.sort(unselectedRatings);

		// Starting screen with directions
		System.out.println("\n** Ratings Selection Screen **");
		System.out.println("\n(Type 'select' to select a rating between 0-10)");
		System.out.println("(Type 'deselect' to deselect ratings)");
		System.out.println("(Press 'x' to go back to home screen)");
		System.out.println("\nSelected ratings: " + selectedRatings.toString());
		System.out.println("Unselected ratings: " + unselectedRatings.toString());

		System.out.print("\nWhat do you want to do? ");

		userInput = scanner.next().toLowerCase();
		while (!userInput.equals("x")) {

			if (userInput.equals("select")) {
				System.out.println("\n** Selection Screen **");
				System.out.println("\n(Select ratings based on number)");
				System.out.println("(Type 'x' to stop selecting ratings)");
				System.out.println("\nSelected ratings: " + selectedRatings.toString());
				System.out.println("Unselected ratings: " + unselectedRatings.toString());
				System.out.print("\nWhat do you want to do? ");

				userInput = scanner.next();
				while (!userInput.equals("x")) {
					selectedRatings
							.add(unselectedRatings.remove(unselectedRatings.indexOf(Integer.parseInt(userInput))));
					frontend.addAvgRating(userInput);

					Collections.sort(unselectedRatings);
					Collections.sort(selectedRatings);

					System.out.println(frontend.getAvgRatings());
					System.out.println("\n** Selection Screen **");
					System.out.println("\n(Select ratings based on their number)");
					System.out.println("(Type 'x' to stop selecting genres)");
					System.out.println("\nSelected ratings: " + selectedRatings.toString());
					System.out.println("Unselected ratings: " + unselectedRatings.toString());
					System.out.print("\nWhat do you want to do? ");

					userInput = scanner.next();
				}
			}

			if (userInput.equals("deselect")) {
				System.out.println("\n** Selection Screen **");
				System.out.println("\n(Deselect ratings based on number)");
				System.out.println("(Type 'x' to stop selecting ratings)");
				System.out.println("\nSelected ratings: " + selectedRatings.toString());
				System.out.println("Unselected ratings: " + unselectedRatings.toString());
				System.out.print("\nWhat do you want to do? ");

				userInput = scanner.next();
				while (!userInput.equals("x")) {
					unselectedRatings.add(selectedRatings.remove(selectedRatings.indexOf(Integer.parseInt(userInput))));
					frontend.removeAvgRating(userInput);

					Collections.sort(unselectedRatings);
					Collections.sort(selectedRatings);

					System.out.println(frontend.getAvgRatings());
					System.out.println("\n** Selection Screen **");
					System.out.println("\n(Type 'x' to stop selecting genres)");
					System.out.println("\nSelected ratings: " + selectedRatings.toString());
					System.out.println("Unselected ratings: " + unselectedRatings.toString());
					System.out.print("\nWhat do you want to do? ");

					userInput = scanner.next();
				}
			}

			Collections.sort(unselectedRatings);
			Collections.sort(selectedRatings);

			System.out.println("\n** Ratings Selection Screen **");
			System.out.println("\n(Type 'select' to select a rating between 0-10)");
			System.out.println("(Type 'deselect' to deselect ratings)");
			System.out.println("(Press 'x' to go back to home screen)");
			System.out.println("\nSelected ratings: " + frontend.getAvgRatings().toString());
			System.out.println("Unselected ratings: " + unselectedRatings.toString());
			System.out.print("\nWhat do you want to do? ");

			userInput = scanner.next().toLowerCase();
		}

		backToHomeScreen(frontend);
	}

	/**
	 * Main method of the Movie Mapper app that will run all of the functions.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize a scanner and the Frontend with a path to the csv file
		Scanner scanner = new Scanner(System.in);
		Frontend frontend = new Frontend(filePath);
		List<MovieInterface> m = new ArrayList<MovieInterface>();

		intialHomeScreen(m, frontend);

		// Prompts the user on what they want to do.
		System.out.print("\nWhat do you want to do? ");
		String userInput = scanner.next().toLowerCase();

		// The main poriton of the program. The program will continue to run until
		// userInput is equal to "x".
		while (!userInput.equals("x")) {
			// If the userInput is equal to 3, index is incremented by 3 and it will show
			// the next three movies based on ratings.
			if (userInput.equals("3")) {
				inputOf3(m, frontend);
			}

			// If the userInput is equal to 4, 3 is subracted from index and it will show
			// the next three movies based on ratings.
			if (userInput.equals("4")) {
				inputOf4(m, frontend);
			}

			// input of g takes the user to the genre screen where the user can select and
			// deselect genres.
			if (userInput.equals("g")) {
				genreScreen(frontend, scanner, userInput);
			}

			// input of r takes the user to the rating screen where the user can select and
			// deselect ratings
			if (userInput.equals("r")) {
				ratingScreen(frontend, scanner, userInput);
			}

			userInput = scanner.next();
		}

		System.out.println("\nTHE PROGRAM HAS TERMINATED");
	}
}
