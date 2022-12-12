import java.util.LinkedList;
import java.util.Queue;
/*
Allison Cheng
Binary Search Tree: binary tree data structure that works w/ Comparable type &
has one instance variable representing the root of the tree
 */

public class AllisonBST<E extends Comparable<E>> {

    private TreeNode root;

    // returns true if item is found in BST
    public boolean contains(E item) {
        return containsHelper(root, item);
    }

    // helper function of contains
    private boolean containsHelper(TreeNode r, E item) {

        if (r == null) {
            return false;
        }

        if(r.data.equals(item)) {
            return true;
        }

        if (r.data.compareTo(item) < 0 && r.left != null) {
            if (containsHelper(r.left, item)) {
                return true;
            }
        }

        if (r.data.compareTo(item) > 0 && r.right != null) {
            if (containsHelper(r.right, item)) {
                return true;
            }
        }
        return false;
    }

    // returns smallest item
    public TreeNode findMin() {
        return findMinHelper(root);
    }

    // helper function of findMin
    private TreeNode findMinHelper(TreeNode r) {

        if (r.left == null) {
            return r;
        }
        return findMinHelper(r.left);
    }

    // returns largest item
    public TreeNode findMax() {
        return findMaxHelper(root);
    }

    // helper function of findMax
    private TreeNode findMaxHelper(TreeNode r) {

        if (r.right == null) {
            return r;
        }
        return findMaxHelper(r.right);
    }

    // inserts element into tree
    public void insert(E item) {
        root = insertHelper(root, item);
    }

    // helper function of insert
    private TreeNode insertHelper(TreeNode r, E item) {

        if (r == null) {
            return new TreeNode(item, null, null);
        }

        else if (r.data.compareTo(item) > 0) {
            r.left = insertHelper(r.left, item);
        }
        else if (r.data.compareTo(item) == 0) {
            r.numOccurences++;
        }
        else {
            r.right = insertHelper(r.right, item);
        }
        return r;
    }

    // removes item from tree if found
    public void remove(E item) {
        root = removeHelper(root, item);
    }

    // helper function of remove
    private TreeNode removeHelper(TreeNode r, E item) {

        if (r == null) {
            return r;
        }

        if (r.data.compareTo(item) < 0) {
            r.right = removeHelper(r.right, item);
        }
        else if (r.data.compareTo(item) > 0) {
            r.left = removeHelper(r.left, item);
        }
        else if (r.data.compareTo(item) == 0) {
            r.numOccurences--;
        }
        else {
            r = removeNode(r);
        }
        return r;
    }

    // non-recursive helper method of remove
    private TreeNode removeNode(TreeNode toRem) {

        // if it is leaf
        if (toRem.left == null && toRem.right == null) {
            return null;
        }
        // one child
        else if (toRem.left == null) {
            return toRem.right;
        }
        else if (toRem.right == null) {
            return toRem.left;
        }
        // 2 children
        else {

            // find lowest value in this section of tree
            // & remove node that has value to swap w/ toRem
            TreeNode minNode = findMinHelper(toRem.right);
            toRem.numOccurences = minNode.numOccurences;
            toRem.data = minNode.data;
            removeHelper(toRem.right, toRem.data);
        }
        return toRem;
    }

    // prints each element from left to right 1 level at a time using Queue
    public void levelOrder() {

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        if (root == null) {
            return;
        }
        queue.add(root);

        // if queue is not empty
        while(!queue.isEmpty()) {

            TreeNode curr = queue.remove();
            System.out.print(curr.data + " ");

            // if child exists, add to queue
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    public void preOrder() {
        preOrderHelper(root);
    }

    // print before 2 recursive calls
    private void preOrderHelper(TreeNode r) {

        if (r != null) {

            for (int i = 0; i < r.numOccurences; i++) {
                System.out.print(r.data + " ");
            }
            preOrderHelper(r.left);
            preOrderHelper(r.right);
        }
    }

    public void inOrder() {
        inOrderHelper(root);
    }

    // print in between 2 recursive calls
    private void inOrderHelper(TreeNode r) {

        if (r != null) {

            inOrderHelper(r.left);
            for (int i = 0; i < r.numOccurences; i++) {
                System.out.print(r.data + " ");
            }
            inOrderHelper(r.right);
        }
    }

    public void postOrder() {
        postOrderHelper(root);
    }

    // print after 2 recursive calls
    private void postOrderHelper(TreeNode r) {

        if (r != null) {
            postOrderHelper(r.left);
            postOrderHelper(r.right);

            for (int i = 0; i < r.numOccurences; i++) {
                System.out.print(r.data + " ");
            }
        }
    }

    public class TreeNode {
        private E data;
        private TreeNode left;
        private TreeNode right;
        private int numOccurences = 1;

        public TreeNode(E d, TreeNode l, TreeNode r) {
            data = d;
            left = l;
            right = r;
        }
    }
}