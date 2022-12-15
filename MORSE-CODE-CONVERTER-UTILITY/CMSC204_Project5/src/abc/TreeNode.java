/**
 * @author Jemil Patel
 */

package abc;

public class TreeNode<T> {

	T data;
	TreeNode<T> left;
	TreeNode<T> right;
	
	public TreeNode(T dataNode) {
		data = dataNode;
		left = null;
		right = null;
	}
	
	public TreeNode(TreeNode<T> node) {
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
	}
	
	public T getData() {
		return data;
	}
	
}
