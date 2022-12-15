/**
 * @author Jemil Patel
 */

package abc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {

	public MorseCodeConverter() {}
	
	public static String printTree() {
		MorseCodeTree tree = new MorseCodeTree();
		ArrayList<String> list = tree.toArrayList();
		String s = "";
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) // check to make sure that a space is not added after the last word
				s += list.get(i) + " ";
			else
				s += list.get(i);
		}
		return s;
	}
	
	public static String convertToEnglish(String code) {
		MorseCodeTree tree = new MorseCodeTree();
		String s = "";
		String[] words = code.split(" / "); // separating words
		for (int i = 0; i < words.length; i++) {
			String[] letters = words[i].split("\\s+"); // separating letters
			for (int j = 0; j < letters.length; j++) {
				s += tree.fetch(letters[j]);
			}
			if (i != words.length - 1) // check to make sure that a space is not added after the last word
				s += " ";
		}
		if (s.contains("Invalid morse code!"))
			s = "Invalid morse code!";
		return s;
	}
	
	public static String convertToEnglish(File file) throws FileNotFoundException {
		Scanner inputFile = new Scanner(file);
		String code = "";
		while (inputFile.hasNextLine()) {
			code += inputFile.nextLine();
		}
		inputFile.close();
		return convertToEnglish(code);
	}
	
}
