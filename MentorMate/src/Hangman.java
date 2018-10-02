import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hangman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int mistakes = 10;
		int score = 0;
		String cat = "";
		Map<String, Set<String>> categories = new HashMap<>();
		Set<String> temp = new HashSet<>();
		List<String> category = new ArrayList<>();

		try (BufferedReader buff = new BufferedReader(new FileReader("C:\\Users\\TALI\\Desktop\\mentormate.txt"))) {
			String line = buff.readLine();
			while (line != null) {
				if (line.contains("_")) {
					category.add(line.substring(1, line.length()).toLowerCase());
					cat = line.substring(1, line.length()).toLowerCase();
					temp.clear();
				} else {
					temp.add(line);
					Set<String> st = new HashSet<>();
					st.addAll(temp);
					categories.put(cat, st);

				}

				line = buff.readLine();

			}

		} catch (IOException e) {
			Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, "Exception while reading file", e);
		}

		System.out.println("Please choose a category:");
		for (String k : categories.keySet()) {
			System.out.println(k);
		}
		Scanner in = new Scanner(System.in);
		String userIn = in.nextLine();
		String word = "";
		int count = 0;
		List<String> letters = new ArrayList<>();
		List<String> wletters = new ArrayList<>();
		while (mistakes > 0) {
			System.out.println("Attempts left:" + mistakes);
			if (category.contains(userIn.toLowerCase())) {
				ArrayList<String> arrWords = new ArrayList<>(categories.get(userIn.toLowerCase()));
				int rand = (int) Math.round(Math.random() * (arrWords.size() - 1));
				word = arrWords.get(rand).toLowerCase();
				char[] wordArr = word.toCharArray();
				System.out.print("Current word:");
				for (char c : wordArr) {
					if (((int) c > 64 && (int) c < 91) || ((int) c > 96 && (int) c < 123)) {
						System.out.print("_ ");
					}
					if (Character.isWhitespace(c)) {
						System.out.print(" ");
					}
				}
				System.out.println("\nEnter a letter:");
				userIn=in.nextLine();
			}
			char[] wordArr = word.toCharArray();
			if (word.contains(userIn)) {
				letters.add(userIn);

				//System.out.println("Attempts left:"+mistakes);
				System.out.print("Current word:");
				for (char c : wordArr) {
					if (letters.contains(Character.toString(c))) {
						System.out.print(c);
					} else if (Character.isWhitespace(c)) {
						System.out.print("  ");
					} else if (!letters.contains(Character.toString(c))) {
						System.out.print("_ ");
					}
				}

			} else {
				mistakes--;
				System.out.println("Attempts left:"+mistakes);
				System.out.print("Current word:");
				for (char c : wordArr) {
					if (letters.contains(Character.toString(c))) {
						System.out.print(c);
					} else if (Character.isWhitespace(c)) {
						System.out.print("  ");
					} else if (!letters.contains(Character.toString(c))) {
						System.out.print("_ ");

					}

				}
			}
			if(letters.size()==word.trim().length()){
				score++;
				System.out.println("Your score is:"+score);
				System.out.println("Enter other category:");
				userIn=in.nextLine();
				letters.clear();
				continue;
			}
			System.out.println("\nEnter a letter:");
			userIn = in.nextLine();
		}
		if (mistakes == 0) {
			System.out.println("Sorry you lost!");
		}

	}

}
