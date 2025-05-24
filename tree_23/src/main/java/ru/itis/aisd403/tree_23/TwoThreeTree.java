package ru.itis.aisd403.tree_23;

import java.util.Arrays;

public class TwoThreeTree {
    private TreeNode root;
    private int size;

    public boolean insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            size++;
            return true;
        }

        InsertResult result = insert(root, key);
        if (result.newNode != null) {
            root = result.newNode;
        }
        if (result.inserted) size++;
        return result.inserted;
    }

    public boolean contains(int key) {
        return search(root, key);
    }

    public boolean remove(int key) {
        if (!contains(key)) return false;
        if (root == null) return false;

        boolean[] removed = new boolean[1];
        root = remove(root, key, removed);
        if (root != null && root.isTwoNode && root.left == null && root.right == null) {
            root = null;
        }
        if (removed[0]) size--;
        return removed[0];
    }

    public int size() {
        return size;
    }

    private static class InsertResult {
        TreeNode newNode;
        boolean inserted;

        InsertResult(TreeNode newNode, boolean inserted) {
            this.newNode = newNode;
            this.inserted = inserted;
        }
    }

    private boolean search(TreeNode node, int key) {
        if (node == null) return false;

        if (node.isTwoNode) {
            if (key == node.key1) return true;
            return key < node.key1 ? search(node.left, key) : search(node.right, key);
        } else {
            if (key == node.key1 || key == node.key2) return true;
            if (key < node.key1) return search(node.left, key);
            return key < node.key2 ? search(node.middle, key) : search(node.right, key);
        }
    }

    private InsertResult insert(TreeNode node, int key) {
        if (node.isLeaf()) {
            return insertIntoLeaf(node, key);
        }
        return insertIntoInternal(node, key);
    }

    private InsertResult insertIntoLeaf(TreeNode node, int key) {
        if (node.isTwoNode) {
            if (key == node.key1) return new InsertResult(null, false);

            if (key < node.key1) {
                node.key2 = node.key1;
                node.key1 = key;
            } else {
                node.key2 = key;
            }
            node.isTwoNode = false;
            return new InsertResult(null, true);
        } else {
            if (key == node.key1 || key == node.key2) return new InsertResult(null, false);
            return splitLeaf(node, key);
        }
    }

    private InsertResult splitLeaf(TreeNode node, int key) {
        int[] keys = {node.key1, node.key2, key};
        Arrays.sort(keys);

        TreeNode newRoot = new TreeNode(keys[1]);
        newRoot.left = new TreeNode(keys[0], null, null); // Явно указываем null для потомков
        newRoot.right = new TreeNode(keys[2], null, null);
        return new InsertResult(newRoot, true);
    }

    private InsertResult insertIntoInternal(TreeNode node, int key) {
        if (node.isTwoNode) {
            if (key == node.key1) return new InsertResult(null, false);

            if (key < node.key1) {
                InsertResult result = insert(node.left, key);
                return processChildResult(node, result, 0);
            } else {
                InsertResult result = insert(node.right, key);
                return processChildResult(node, result, 1);
            }
        } else {
            if (key == node.key1 || key == node.key2) return new InsertResult(null, false);

            if (key < node.key1) {
                InsertResult result = insert(node.left, key);
                return processChildResult(node, result, 0);
            } else if (key < node.key2) {
                InsertResult result = insert(node.middle, key);
                return processChildResult(node, result, 1);
            } else {
                InsertResult result = insert(node.right, key);
                return processChildResult(node, result, 2);
            }
        }
    }

    private InsertResult processChildResult(TreeNode node, InsertResult result, int childPos) {
        if (result.newNode == null) return result;

        if (node.isTwoNode) {
            if (childPos == 0) {
                node.key2 = node.key1;
                node.key1 = result.newNode.key1;
                node.left = result.newNode.left;
                node.middle = result.newNode.right;
            } else {
                node.key2 = result.newNode.key1;
                node.middle = result.newNode.left;
                node.right = result.newNode.right;
            }
            node.isTwoNode = false;
            return new InsertResult(null, result.inserted);
        } else {
            TreeNode newRoot;
            if (childPos == 0) {
                newRoot = new TreeNode(node.key1);
                newRoot.left = new TreeNode(result.newNode.key1, result.newNode.left, result.newNode.right);
                newRoot.right = new TreeNode(node.key2, node.middle, node.right);
            } else if (childPos == 1) {
                newRoot = new TreeNode(result.newNode.key1);
                newRoot.left = new TreeNode(node.key1, node.left, result.newNode.left);
                newRoot.right = new TreeNode(node.key2, result.newNode.right, node.right);
            } else {
                newRoot = new TreeNode(node.key2);
                newRoot.left = new TreeNode(node.key1, node.left, node.middle);
                newRoot.right = new TreeNode(result.newNode.key1, result.newNode.left, result.newNode.right);
            }
            return new InsertResult(newRoot, result.inserted);
        }
    }

    private TreeNode remove(TreeNode node, int key, boolean[] removed) {
        if (node == null) {
            removed[0] = false;
            return null;
        }

        if (node.isTwoNode) {
            return handleTwoNodeRemove(node, key, removed);
        } else {
            return handleThreeNodeRemove(node, key, removed);
        }
    }

    private TreeNode handleTwoNodeRemove(TreeNode node, int key, boolean[] removed) {
        if (key == node.key1) {
            removed[0] = true;
            if (node.isLeaf()) {
                return null;
            }
            return deleteInternal(node, key);
        }

        if (key < node.key1) {
            node.left = remove(node.left, key, removed);
            return fixTwoNode(node);
        } else {
            node.right = remove(node.right, key, removed);
            return fixTwoNode(node);
        }
    }

    private TreeNode handleThreeNodeRemove(TreeNode node, int key, boolean[] removed) {
        if (key == node.key1 || key == node.key2) {
            removed[0] = true;
            if (node.isLeaf()) {
                if (key == node.key1) {
                    node.key1 = node.key2;
                }
                node.isTwoNode = true;
                return node;
            }
            return deleteInternal(node, key);
        }

        if (key < node.key1) {
            node.left = remove(node.left, key, removed);
        } else if (key < node.key2) {
            node.middle = remove(node.middle, key, removed);
        } else {
            node.right = remove(node.right, key, removed);
        }
        return fixThreeNode(node);
    }

    private TreeNode deleteInternal(TreeNode node, int key) {
        if (key == node.key1) {
            TreeNode predecessor = findMax(node.left);
            if (predecessor == null) {
                if (node.isTwoNode) {
                    return node.right;
                } else {
                    node.key1 = node.key2;
                    node.isTwoNode = true;
                    return node;
                }
            }
            int predKey = predecessor.isTwoNode ? predecessor.key1 : predecessor.key2;
            node.key1 = predKey;
            boolean[] dummy = new boolean[1];
            node.left = remove(node.left, predKey, dummy);
            return node.isTwoNode ? fixTwoNode(node) : fixThreeNode(node);
        } else {
            TreeNode successor = findMin(node.right);
            if (successor == null) {
                if (node.isTwoNode) {
                    return node.left;
                } else {
                    node.isTwoNode = true;
                    return node;
                }
            }
            node.key2 = successor.key1;
            boolean[] dummy = new boolean[1];
            node.right = remove(node.right, node.key2, dummy);
            return fixThreeNode(node);
        }
    }

    private TreeNode findMin(TreeNode node) {
        if (node == null) return null;
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private TreeNode findMax(TreeNode node) {
        if (node == null) return null;
        TreeNode current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    private TreeNode fixTwoNode(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node;
        }

        if (node.left != null && !node.left.isTwoNode) {
            TreeNode leftChild = node.left;
            node.key2 = node.key1;
            node.key1 = leftChild.key2;
            node.isTwoNode = false;
            node.middle = leftChild.right;
            leftChild.isTwoNode = true;
            leftChild.right = null;
        } else if (node.right != null && !node.right.isTwoNode) {
            TreeNode rightChild = node.right;
            node.key2 = rightChild.key1;
            node.isTwoNode = false;
            node.middle = rightChild.left;
            rightChild.key1 = rightChild.key2;
            rightChild.isTwoNode = true;
            rightChild.left = rightChild.middle;
            rightChild.middle = null;
        } else {
            if (node.left != null) {
                TreeNode newChild = new TreeNode(node.left.key1, node.key1);
                newChild.left = node.left.left;
                newChild.middle = node.left.right;
                newChild.right = node.right;
                return newChild;
            } else {
                TreeNode newChild = new TreeNode(node.key1, node.right.key1);
                newChild.left = node.left;
                newChild.middle = node.right.left;
                newChild.right = node.right.right;
                return newChild;
            }
        }
        return node;
    }

    private TreeNode fixThreeNode(TreeNode node) {
        return node;
    }

    public TreeNode getRoot() {
        return root;
    }
}