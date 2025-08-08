package demo;

import java.util.*;

public class InorderTraversal {
    private static int ans;

    public static void main(String[] args) {
//        Integer[] ints = new Integer[]{1, 2, 5, 3, 4, null, 6};//3, 5, 1, 6, 2, 0, 8, null, null, 7, 4
//        TreeNode root = buildTree(ints);
//        System.out.println(inorderTraversal(root));
//        System.out.println(maxDepth(root));
//        System.out.println(invertTree(root).val);
//        System.out.println(isSymmetric(root));
//        ans = 1;
//        depth(root);
//        System.out.println(ans - 1);
//        System.out.println(levelOrder(root));
//        System.out.println(kthSmallest(root, 1));
//        flatten(root);


//        int[] ints1 = new int[]{1, 3};
//        TreeNode node = sortedArrayToBST(ints1);

//        System.out.println(node.val);
//        System.out.println(isValidBST(node, Long.MIN_VALUE, Long.MAX_VALUE));
//        Integer[] ints = new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
//
//        TreeNode root = buildTree(ints);
//        TreeNode p = root.left;
//        TreeNode q = root.left.right.right;
//        System.out.println(lowestCommonAncestor(root, p, q).val);
        Integer[] ints = new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = buildTree(ints);
        long target = 8;
        System.out.println(pathSum(root, 8));
    }

    private static int pathSum(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        return rootSum(root, target) + pathSum(root.left, target) + pathSum(root.right, target);
    }

    private static int rootSum(TreeNode root, int target) {
        int ret = 0;
        if (root == null) {
            return 0;
        }
        if (target == root.val) {
            ret++;
        }
        ret += rootSum(root.left, target - root.val);
        ret += rootSum(root.right, target - root.val);
        return ret;
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode a = lowestCommonAncestor(root.left, p, q);
        TreeNode b = lowestCommonAncestor(root.right, p, q);
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return root;
    }


    private static void flatten(TreeNode root) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        preorder(root, res);
        for (int i = 1; i < res.size(); i++) {
            TreeNode pre = res.get(i - 1), cur = res.get(i);
            pre.left = null;
            pre.right = cur;
        }
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).val);
        }


    }

    private static void preorder(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        res.add(root);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    private static int kthSmallest(TreeNode root, int i) {
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            i--;
            if (i == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;

    }

    private static boolean isValidBST(TreeNode node, long minValue, long maxValue) {
        if (node == null) {
            return true;
        }
        if (node.val < minValue || node.val > maxValue) {
            return false;
        }
        return isValidBST(node.left, minValue, node.val) && isValidBST(node.right, node.val, maxValue);
    }

    private static TreeNode sortedArrayToBST(int[] nums) {
        return help(nums, 0, nums.length - 1);

    }

    private static TreeNode help(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = help(nums, 0, mid - 1);
        root.right = help(nums, mid + 1, right);
        return root;
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            List<Integer> curres = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                curres.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(curres);
        }
        return res;

    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int L = depth(root.left);
        int R = depth(root.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }

    private static boolean isSymmetric(TreeNode root) {
        return check(root.right, root.left);
    }

    private static boolean check(TreeNode right, TreeNode left) {
        if (right == null && left == null) {
            return true;
        }
        if (right == null || left == null) {
            return true;
        }
        return right.val == left.val && check(right.left, left.right) && check(left.left, right.right);
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }


    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private static void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    private static TreeNode buildTree(Integer[] ints) {
        if (ints == null || ints.length <= 0 || ints[0] == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode(ints[0]);
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.offer(treeNode);
        int index = 1;
        while (!treeNodes.isEmpty() && index < ints.length) {
            TreeNode current = treeNodes.poll();

            if (index < ints.length && ints[index] != null) {
                TreeNode left = new TreeNode(ints[index]);
                current.left = left;
                treeNodes.offer(left);
            }
            index++;
            if (index < ints.length && ints[index] != null) {
                TreeNode right = new TreeNode(ints[index]);
                current.right = right;
                treeNodes.offer(right);
            }
            index++;
        }
        return treeNode;
    }
}
