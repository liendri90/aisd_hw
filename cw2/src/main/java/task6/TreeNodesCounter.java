package task6;

public class TreeNodesCounter {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int count;

        TreeNode(int val) {
            this.val = val;
            this.count = 1;
        }

        public static int countNodes(TreeNode node) {
            if(node == null) {
                return 0;
            }
            int leftCount = countNodes(node.left);
            int rightCount = countNodes(node.right);

            node.count = 1 + leftCount + rightCount;

            return node.count;
        }

        public static void printCounts(TreeNode root) {
            if (root == null) return;

            printCounts(root.left);
            System.out.println(root.count);
            printCounts(root.right);
        }

    }

}
