package task1;

public class HeightSubTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public int getSubtreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getSubtreeHeight(root.left);
        int rightHeight = getSubtreeHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);

        HeightSubTree heightSubTree = new HeightSubTree();
        System.out.println(heightSubTree.getSubtreeHeight(tree));
        System.out.println(heightSubTree.getSubtreeHeight(tree.left));
        System.out.println(heightSubTree.getSubtreeHeight(tree.right));
    }
}

