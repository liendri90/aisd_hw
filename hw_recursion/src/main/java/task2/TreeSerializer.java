package task2;

public class TreeSerializer {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return "NULL";
        }
        String leftStr = serialize(root.left);
        String rightStr = serialize(root.right);

        return "(" + root.val + ", " + leftStr + ", " + rightStr + ")";
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        TreeSerializer serializer = new TreeSerializer();
        String result = serializer.serialize(root);
        System.out.println(result);
    }
}

