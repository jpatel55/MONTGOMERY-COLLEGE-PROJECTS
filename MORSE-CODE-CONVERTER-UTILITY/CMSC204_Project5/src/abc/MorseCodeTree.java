/**
 * @author Jemil Patel
 */

package abc;

import java.util.ArrayList;

public class MorseCodeTree extends Object implements LinkedConverterTreeInterface<String> {

	TreeNode<String> root;
	
	public MorseCodeTree() {
		buildTree();
	}
	
	public TreeNode<String> getRoot() {
		return root;
	}
	
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}
	
	public void insert(String code, String letter) {
		addNode(root, code, letter);
	}
	
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.isEmpty()) // letter is assigned to the node once all the traversal is complete
			root.data = letter;
		else {
			if (code.charAt(0) == '.') {
				if (root.left == null) // if the left node does not exist then we create one
					root.left = new TreeNode<String>("");				
				addNode(root.left, code.substring(1), letter);
			} else if (code.charAt(0) == '-') {
				if (root.right == null) // if the right node does not exist then we create one
					root.right = new TreeNode<String>("");
				addNode(root.right, code.substring(1), letter);
			}
		}
	}
	
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	
	public String fetchNode(TreeNode<String> root, String code) {
		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) != '.' && code.charAt(i) != '-') // check to see if the morse code is valid
				return "Invalid morse code!";
		}
		try {
			while (!code.isEmpty()) {
				if (code.charAt(0) == '.')
					root = root.left;
				if (code.charAt(0) == '-')
					root = root.right;
				code = code.substring(1);
			}
			return root.data;
		} catch (NullPointerException e) {
			return "Invalid morse code!";
		}
	}
	
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	public MorseCodeTree update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	public void buildTree() {	
		root = new TreeNode<String>("");
		// level 1
		insert(".", "e");
		insert("-", "t");
		// level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		// level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		// level 4
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
	
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null)
			return;
		LNRoutputTraversal(root.left, list); 
		list.add(root.data); 
		LNRoutputTraversal(root.right, list); 
	}
	
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<String>();
	    LNRoutputTraversal(root, list);
	    return list;
	}
	
}
