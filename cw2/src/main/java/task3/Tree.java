package task3;

public class Tree<T> {
    private static class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public void preOrder(TreeNode<T> root) {
            if (root == null) return;
            System.out.print(root.value + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        public void inOrder(TreeNode<T> root) {
            if (root == null) return;
            inOrder(root.left);
            System.out.print(root.value + " ");
            inOrder(root.right);
        }

        public void postOrder(TreeNode<T> root) {
            if (root == null) return;
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.value + " ");
        }
    }

}