package com.leetcode;

import java.util.*;

public class Tree {
	
	static HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
	static List<List<Integer>> ret = new ArrayList<List<Integer>>();
	
	/**
	 * construct binary tree from inorder and postorder traversal
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	public static TreeNode buildTree(int [] inorder, int [] postorder){
		
		for(int i=0;i<inorder.length;i++){
			map.put(inorder[i],i);
		}
		return build(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
	}
	
	public static TreeNode build(int [] inorder, int s0, int e0, int [] postorder, int s1, int e1){
		if(s0>e0 || s1>e1) return null;
		TreeNode root = new TreeNode(postorder[e1]);
		int mid = map.get(postorder[e1]);
		// left count to split inorder
		int num = mid-s0;
		
		root.left=build(inorder, s0, mid-1, postorder, s1, s1+num-1);
		root.right=build(inorder, mid+1, e0, postorder, s1+num,e1-1);
		return root;
	}
	
	/**
	 * construct binary tree from preorder and inorder traversal
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public static TreeNode buildTree2(int [] preorder, int [] inorder){
		for(int i=0;i<inorder.length;i++){
			map.put(inorder[i], i);
		}
		return build2(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
	}
	
	public static TreeNode build2(int [] preorder, int s0, int e0, int [] inorder, int s1, int e1){
		if(s0>e0 || s1>e1) return null;
		TreeNode root = new TreeNode(preorder[s0]);
		int mid = map.get(preorder[s0]);
		// left count to split inorder
		int num = mid-s1;
		
		root.left=build2( preorder, s0+1, s0+num, inorder, s1, mid-1);
		root.right=build2(preorder, s0+num+1,e0,inorder, mid+1, e1);
		return root;
	}
	
	/**
	 * Binary tree level order traversal
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrder(TreeNode root){
		int depth = getHeight(root);
		List<List<Integer>> ret =  new ArrayList<List<Integer>>(depth);
		if(depth==0) return ret;
		for(int i=0;i<depth;i++){
			ret.add(i, new ArrayList<Integer>());
		}
		DFS(root, 0);
		return ret;
	}
	
	/**
	 * Binary tree level order traversal II
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrderBottom(TreeNode root){
		int depth = getHeight(root);
		List<List<Integer>> ret =  new ArrayList<List<Integer>>(depth);
		if(depth==0) return ret;
		for(int i=0;i<depth;i++){
			ret.add(i, new ArrayList<Integer>());
		}
		DFS(root, ret, depth-1);
		return ret;
	}
	
	//DFS
	public static void DFS(TreeNode root, int level){
		if(root==null){
			return;
		}
		ret.get(level).add(root.val);
		DFS(root.left, level-1);
		DFS(root.right, level-1);
	}
	
	/**
	 * Binary Tree Zigzag Level Order Traversal
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		
		int depth = getHeight(root);
		List<List<Integer>> ret = new ArrayList<List<Integer>>(depth);
		if(depth==0) return ret;
		for(int i=0;i<depth;i++){
			ret.add(i, new ArrayList<Integer>());
		}
		DFS(root, ret, 0);
		for(int j=1;j<depth;j+=2){
			Collections.reverse(ret.get(j));
		}
		return ret;
    }
	//DFS
	public static void DFS(TreeNode root, List<List<Integer>> ret, int level){
		if(root==null) return;
		ret.get(level).add(root.val);
		DFS(root.left, ret, level+1);
		DFS(root.right, ret, level+1);
	}
	
	/**
	 * symmetric tree
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric(TreeNode root){
		if(root==null) return true;
		
		return DFS(root.left, root.right);
	}
	
	// DFS for Symmetric
	public static boolean DFS(TreeNode left, TreeNode right){
		if(left==null && right==null){
			return true;
		} else if(left==null || right==null){
			return false;
		}
		boolean cond1 = left.val==right.val;
		boolean cond2 = DFS(left.left, right.right);
		boolean cond3 = DFS(left.right, right.left);
		return cond1 && cond2 && cond3;
		
	}
	
	/**
	 * Same Tree
	 * @param p
	 * @param q
	 * @return
	 */
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if(p==null && q==null){
			return true;
		}else if(p==null || q==null){
			return false;
		}
		boolean cond1 = p.val==q.val;
		boolean cond2 = isSameTree(p.left, q.left);
		boolean cond3 = isSameTree(p.right, q.right);
		return cond1 && cond2 && cond3;
        
    }
	/**
	 * Balance Binary Tree
	 * @param root
	 * @return
	 */
	public static boolean isBalanced(TreeNode root) {
		if(root==null) return true;
		int balanced = getHeight(root);
		if(balanced!=-1) return true;
		return false;
    }
	
	//get tree height
	public static int getHeight(TreeNode node){
		if(node==null) return 0;
		
		int left = getHeight(node.left);
		if(left==-1) return -1;
		int right = getHeight(node.right);
		if(right==-1) return -1;
		int diffHeight = left>right?left-right:right-left;
		if(diffHeight>1) return -1;
		else{
			return java.lang.Math.max(left, right)+1;
		}
	}
	
	/**
	 * Path Sum
	 * @param root
	 * @param sum
	 * @return
	 */
	public static boolean hasPathSum(TreeNode root, int sum) {
		if(root==null) return false;
		return DFS(sum, 0, root);
	}
	
	public static boolean DFS(int target, int sum, TreeNode root){
		if(root==null) return false;
		sum+=root.val;
		if(root.left==null && root.right==null){
			if(sum==target){
				return true;
			}else{
				return false;
			}
		}
		boolean leftPart = DFS(target, sum, root.left);
		boolean rightPart = DFS(target, sum, root.right);
		return leftPart || rightPart;
	}
	/**
	 * Binary tree preorder traversal
	 * @param root
	 * @return
	 */
	public static List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> ret = new ArrayList<Integer>();
		if(root==null) return ret;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			ret.add(node.val);
			if(node.right!=null){
				stack.push(node.right);
			}
			if(node.left!=null){
				stack.push(node.left);
			}
		}
		return ret;
        
    }
	
	/**
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ret = new ArrayList<Integer>();
		if(root==null) return ret;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p =root;
		while(p!=null || !stack.isEmpty()){
			while(p!=null){
				stack.push(p);
				p=p.left;
			}
			if(!stack.isEmpty()){
				p=stack.pop();
				ret.add(p.val);
				p=p.right;
			}
		}
		return ret;
	}
	
	/**
	 * Binary tree postorder traversal
	 * @param root
	 * @return
	 */
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> ret = new ArrayList<Integer>();
		if(root==null) return ret;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		TreeNode pre = null;
		while(!stack.isEmpty()){
			TreeNode p = stack.peek();
			if((p.left==null && p.right==null) || (pre!=null &&(pre==p.left || pre==p.right))){
				ret.add(p.val);
				stack.pop();
				pre=p;
			} else {
				if(p.right!=null){
					stack.push(p.right);
				}
				if(p.left!=null){
					stack.push(p.left);
				}
			}
		}
		return ret;
	}
	
	/**
	 * Populating Next Right Pointers in Each Node 
	 * @param root
	 */
	public static void connect(TreeLinkNode root) {
        if(root==null) return;
        TreeLinkNode p = root;
        TreeLinkNode first = null;
        while(p!=null){
            // record next layer
            if(first==null){
                first=p.left;
            }
            
            // set left subtree next pointer
            if(p.left!=null){
                p.left.next=p.right;
            } else {
                //end
                break;
            }
            
            // set right subtree next pointer
            if(p.next!=null){
                p.right.next=p.next.left;
                p=p.next;
            } else{
                //jump next layer
                p=first;
                first=null;
            }
        }
    }
	
	/**
	 * Populating Next Right Pointers in Each Node II
	 * @param root
	 */
	public static void connect2(TreeLinkNode root) {
        if(root==null) return;
        TreeLinkNode p = root;
        TreeLinkNode first = null;
        TreeLinkNode last = null;
        
        while(p!=null){
            // record next layer
            if(first==null){
            	if(p.left!=null){
            		first=p.left;
            	} else if(p.right!=null){
            		first=p.right;
            	}
            }
            
            // set left subtree next pointer
            if(p.left!=null){
            	if(last!=null){
            		last.next=p.left;
            	}
            	last=p.left;
            }
            
            if(p.right!=null){
            	if(last!=null){
            		last.next=p.right;
            	}
            	last=p.right;
            }
            
            // set right subtree next pointer
            if(p.next!=null){
                p=p.next;
            } else{
                //jump next layer
                p=first;
                first=null;
                last=null;
            }
        }
    }
	
	/**
	 * convert sorted Singly LinkedList to Binary search tree
	 * @param head
	 * @return
	 */
	public static TreeNode sortedListToBST(ListNode head){
		return build(head, null);
	}
	
	public static TreeNode build(ListNode start, ListNode end){
		ListNode fast = start;
		ListNode slow = start;
		
		while(fast!=end && fast.next!=end){
			slow=slow.next;
			fast=fast.next.next;
		}
		
		TreeNode root = new TreeNode(slow.val);
		root.left=build(start, slow);
		root.right=build(slow.next, end);
		return root;
	}
	
	
	/**
	 * convert sorted array to binary search tree 
	 * @param nums
	 */
	public static TreeNode sortedArrayToBST(int [] nums){
		return build(nums, 0, nums.length);
	}
	
	public static TreeNode build(int [] nums, int start, int end){
		if(start>=end) return null;
		int mid = start+(end-start)/2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left=build(nums, start, mid);
		root.right=build(nums, mid+1, end);
		return root;
	}
	
	/**
	 * path sum II
	 * @param root
	 * @param sum
	 * @return
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(root == null) return ret;
        List<Integer> cur = new ArrayList<Integer>();
        DFS(root, ret, cur, sum, 0);
        return ret;
    }
    
    public void DFS(TreeNode root, List<List<Integer>> ret, List<Integer> cur, int sum, int temSum){
        if(root==null) return;
        temSum+=root.val;
        cur.add(root.val);
        if(root.left==null && root.right==null){
            if(temSum==sum){
                List<Integer> temp = new ArrayList<Integer>(cur);
                ret.add(temp);
            }
        }
        DFS(root.left, ret, cur, sum, temSum);
        DFS(root.right, ret, cur, sum, temSum);
        cur.remove(cur.size()-1);
    }
    
    /**
     * flatten binary tree to linked list
     * @param root
     */
    public static void flatten(TreeNode root){
    	if(root==null) return;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode dummy = new TreeNode(-1);
    	stack.push(root);
    	while(!stack.isEmpty()){
    		TreeNode p = stack.pop();
    		dummy.right=p;
    		dummy=p;
    		if(p.right!=null){
    			stack.push(p.right);
    			p.right=null;
    		}
    		if(p.left!=null){
    			stack.push(p.left);
    			p.left=null;
    		}
    	}
    }
	
    /**
     * Validate Binary search tree
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root){
    	return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public static boolean valid(TreeNode root, long min, long max){
    	if(root==null) return true;
    	if(root.val<=min || root.val>=max) return false;
    	return valid(root.left, min, root.val) && valid(root.right, root.val, max);
    }
    
    
	//print
	public static void printPreOrderTree(TreeNode root){
		if(root==null){
			return;
		}
		System.out.print(root.val+" ");
		printPreOrderTree(root.left);
		printPreOrderTree(root.right);
	}
	
	
	public static List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<String>();
        if(root==null) return ret;
        helper(root, ret, "");
        return ret;
    }
    public static void helper(TreeNode root, List<String> ret, String res){
        
        
        if(root.left==null && root.right==null){
        	if(res==""){
        		res=root.val+"";
        	}else{
        		res=res + "->"+root.val;
        	}
            
            ret.add(res);
            res="";
            return;
        }
        if(res==""){
        	res=root.val+"";
        } else{
        	res=res+"->"+root.val;
        }
        if(root.left!=null){
        	helper(root.left, ret, res);
        }
        if(root.right!=null){
        	helper(root.right, ret, res);
        }

    }
    
    public static boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        HashMap<String,Integer> maps = new HashMap<String,Integer>();
        HashMap<String,Integer> mapt = new HashMap<String,Integer>();
    	for(int i=0;i<s.length();i++){
    	    String keys = s.charAt(i)+"";
    	    String keyt = t.charAt(i)+"";
    	    if(!maps.containsKey(keys)){
    	        maps.put(keys,1);
    	    }else{
    	        maps.put(keys,maps.get(keys)+1);
    	    }
    	    if(!mapt.containsKey(keyt)){
    	        mapt.put(keyt,1);
    	    }else{
    	        mapt.put(keyt,mapt.get(keyt)+1);
    	    }
        }
    	for(int i=0;i<t.length();i++){
    	    String key = t.charAt(i)+"";
    		if(!maps.containsKey(key)){
    			return false;
    		}else{
    		    if((int)maps.get(key)!=(int)mapt.get(key)){
    		        return false;
    		    }
    		}
    	}
    	return true;
    }
    
    
    
    
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if(p.val>q.val){
    		return helper(root, q, p);
    	}
    	else{
    		return helper(root, p, q);
    	}
    	
    }
    
    public static TreeNode helper(TreeNode root, TreeNode p, TreeNode q){
    	if(root==null) return null;
    	TreeNode cur = root;
    	if(root.val>=p.val && root.val<=q.val){
        	return root;
        }
        if(root.val>q.val){
        	if(root.left!=null){
        		cur = lowestCommonAncestor(root.left, p, q);
        	}
        }
        if(root.val<p.val){
        	if(root.right!=null){
        		cur = lowestCommonAncestor(root.right, p, q);
        	}
        }
        return cur;
    }
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root==null) return ret;
        helper(root, ret);
        return ret;
    }
    public static void helper(TreeNode root, List<Integer> ret){
        if(root==null) return;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(root.val);
        TreeNode p = root.right;
        boolean tracking = true;
        while(p!=null && tracking){
            if(p.left==null){
            	queue.add(p.val);
                p=p.right;
            } else{
                tracking=false;
            }
        }
        if(!tracking){
            return;
        }else{
            while(!queue.isEmpty()){
                int temp = queue.remove();
                ret.add(temp);
            }
        }
        return;
    }
    
    
    public static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root==null) return ret;
        helpers(root, ret);
        return ret;
    }
    public static void helpers( TreeNode root, List<Integer> ret) {
     
        if(root == null) return;
     
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
     
        while(queue.size() > 0){
            int size = queue.size();
     
            for(int i=0; i<size; i++){
                TreeNode top = queue.remove();
     
                if(i==0){
                    ret.add(top.val);
                }

                if(top.right != null){
                    queue.add(top.right);
                }

                if(top.left != null){
                    queue.add(top.left);
                }
            }
        }
        return;
    }
    
    
    
    public static TreeNode lowestCommonAncestors(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        return helpers(root, p, q, queue);
    }
    
    public static TreeNode helpers(TreeNode root, TreeNode p, TreeNode q, Queue<TreeNode> queue){
    	if(root==null) return null;
        if(root.val==p.val || root.val==q.val){
            return root;
        }

		
		TreeNode l = helpers(root.left, p, q, queue);
		TreeNode r = helpers(root.right, p, q, queue);
        
        if(l!=null && r!=null) {
        	queue.add(root);
    		return queue.peek();
        }
        return l!=null?l:r;
    }
    
    
    public static TreeNode lowestCommonAncestorForNew(TreeNode root, TreeNode p, TreeNode q) {
    	List<TreeNode> lpaths = new ArrayList<TreeNode>();
    	List<TreeNode> rpaths = new ArrayList<TreeNode>();
    	int i = 0;
    	if(findPaths(root, p, lpaths) && findPaths(root, q, rpaths)){
    		while(i<lpaths.size() && i<rpaths.size()){
    			if(lpaths.get(i).val!=rpaths.get(i).val){
    				break;
    			}
    			++i;
    		}
    	}
    	return lpaths.get(i-1);
    }
    
    public static boolean findPaths(TreeNode root, TreeNode p, List<TreeNode> paths){
    	if(root==null) return false;
    	paths.add(root);
    	if(root.val==p.val){
    		return true;
    	}
    	
    	if((root.left!=null &&  findPaths(root.left, p, paths)) || (root.right!=null && findPaths(root.right, p, paths))){
    		return true;
    	}
    	paths.remove(paths.size()-1);
    	return false;
    }
    
    /**
     * building BT based BFS
     * @param nums
     * @return
     */
    public static TreeNode buildBT(Object [] nums){
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	int cur =0;
    	TreeNode t = new TreeNode((Integer)nums[cur]);
    	q.add(t);
    	while(!q.isEmpty()){
    		TreeNode p = q.remove();
    		cur++;
    		if(cur>=nums.length) break;
    		if(nums[cur]==null){
    			p.left=null;
    		}else{
    			p.left=new TreeNode((Integer)nums[cur]);
    			q.add(p.left);
    		}
    		cur++;
    		if(cur>=nums.length) break;
    		if(nums[cur]==null){
    			p.right=null;
    		} else{
    			p.right=new TreeNode((Integer)nums[cur]);
        		q.add(p.right);
    		}
    		
    	}
    	return t;
    }
    
    
    public static void rotate(int[] nums, int k) {
        int len =nums.length;
        int [] ret = new int[len];
        for(int i=0;i<k;i++){
            ret[i]=nums[len-k+i];
        }
        int j=0;
        for(int i=k;i<len;i++){
            ret[i]=nums[j];
            j++;
        }
        System.arraycopy(ret, 0, nums, 0, len);
        print(nums);
        return;
    }
    
    public static void print(int nums[]){
    	for(int i=0;i<nums.length;i++){
        	System.out.print(nums[i]+" ");
        }
    }
    
	public static void main(String[] args) {
		int [] nums={1,2,3,4,5,6};
		rotate(nums, 3);
//		Object [] nums = {42,10,11,60,65,50,98,66,84,35,97,-6,null,-1,73,2,61,8,20,11,21,13,50,88,89,20,59,65,66,null,
//				81,-7,12,20,-5,82,-8,96,44,58,91,31,65,29,3,93,74,null,null,10,-4,91,55,85,20,52,18,null,null,11,6,91,6,58,82,null,null,21,null,84,null,
//				7,31,null,-9,57,32,94,61,44,61,35,31,-7,54,15,75,21,-9,65,57,74,null,-2,89,24,6,95,null,null,47,null,null,79,null,36,31,79,86,9,5,86,92,
//				-4,83,76,3,24,10,1,10,72,95,43,0,null,38,41,40,-6,10,93,62,94,82,4,-3,25,91,19,36,null,95,37,67,13,15,18,39,57,13,64,50,null,null,
//				26,-3,-7,99,null,-9,77,16,91,9,null,null,null,26,null,78,83,19,76,92,74,96,46,14,null,null,8,98,null,null,26,null,
//				-7,64,39,91,79,60,80,10,3,-2,29,85,53,70,50,24,null,56,null,null,33,null,-5,71,8,62,72,35,83,null,null,null,14,85,-5,17,
//				null,5,2,14,-8,3,73,49,null,89,null,84,null,85,-3,16,8,-9,null,null,91,18,76,null,5,58,58,4,null,null,null,null,95,null,
//				-3,82,99,6,null,null,49,58,-3,54,91,63,null,12,null,26,34,64,93,null,null,null,null,25,-9,91,64,33,76,27,null,null,80,null,null,null,
//				50,68,null,null,null,10,null,null,60,null,null,50,8,null,30,35,36,5,17,22,61,38,40,null,12,98,68,65,60,48,null,20,44,20,59,78,10,91,81,8,3,27,61,
//				null,69,null,53,null,null,null,null,null,92,null,null,null,99,91,15,71,21,66,37,5,null,null,null,12,52,null,null,7,69,28,null,null,68,13,94,76,
//				null,null,null,82,-7,94,null,null,null,42,null,3,null,22,null,25,null,89,99,null,74,60,93,25,75,56,null,14,null,1,24,6,null,null,null,null,-1,
//				null,null,66,3,73,91,60,null,null,16,42,17,81,14,96,33,null,55,null,null,-9,67,4,9,53,null,null,null,null,null,null,null,null,null,42,96,null,29,16,59,
//				null,-3,56,90,null,72,null,null,87,null,null,null,null,null,null,null,null,null,75,null,null,54,null,4,39,-2,null,44,80,14,null,95,8,76,19,
//				null,null,null,null,null,null,null,66,null,68,92,94,5,8,96,null,80,null,null,null,40,52,30,-7,85,null,72,90,1,44,4,59,19,null,null,null,-9,-8,32,63,1,null,25,
//				null,21,33,37,96,-1,43,null,83,80,65,68,99,88,null,48,77,14,null,14,8,null,null,null,null,null,null,null,null,null,89,null,null,14,null,37,
//				null,null,null,null,null,null,null,20,null,null,null,61,20,null,null,null,34,50,53,null,null,51,null,98,25,42,77,59,36,18,68,4,-5,36,71,null,37,78,
//				null,null,null,null,null,null,null,null,null,null,44,74,92,null,null,3,21,76,32,79,null,59,3,86,-9,81,-4,null,null,null,31,61,32,null,null,98,null,
//				-8,5,64,null,43,32,null,78,null,36,null,null,null,48,null,null,null,78,71,null,71,80,12,null,null,null,null,null,29,52,1,83,5,95,2,56,93,65,86,95,
//				null,null,null,null,58,7,null,20,-2,84,null,-9,13,null,null,33,null,58,null,null,0,38,null,null,-9,null,88,null,78,24,null,14,null,null,9,75,53,-3,
//				null,88,71,84,76,62,85,53,null,null,null,79,36,-5,null,91,57,17,null,null,null,null,36,12,-7,51,63,-3,77,40,13,17,10,5,89,-4,72,27,53,null,83,65,
//				null,null,null,null,null,33,null,96,null,null,85,60,9,38,23,null,null,null,39,null,null,null,16,85,99,51,null,null,null,null,null,null,54,null,null,
//				62,null,null,57,90,61,99,69,2,23,71,35,null,-4,51,-1,null,30,3,null,null,17,42,null,null,77,95,39,85,null,-8,43,5,86,null,33,56,47,78,9,70,57,3,29,71,
//				null,null,null,null,null,null,null,null,47,92,85,38,-3,null,null,22,null,null,34,37,null,86,79,68,84,null,null,null,null,null,null,null,78,58,null,null,49,
//				null,null,11,null,null,null,null,null,null,null,88,65,null,50,null,52,null,55,null,13,0,null,null,null,null,30,86,60,68,48,85,null,null,null,20,null,null,null,
//				39,52,77,62,null,82,-6,null,null,60,null,71,null,null,null,39,0,53,null,null,11,null,null,null,3,85,3,78,null,78,-1,30,72,null,null,null,null,35,29,40,79,86,12,21,
//				null,48,46,70,98,62,22,93,null,null,null,null,null,null,null,45,null,null,null,null,null,-6,null,null,null,null,71,null,null,null,null,68,null,41,93,null,null,62,78,-7,14,
//				null,19,16,91,null,null,null,null,null,20,90,51,42,null,null,93,85,null,null,58,9,null,-3,27,86,42,null,null,null,null,null,17,null,null,null,null,22,-8,93,8,49,90,null,null,
//				63,19,39,null,null,null,17,4,54,8,-5,76,null,null,-9,-6,null,34,null,51,10,20,null,23,14,91,26,47,null,47,67,null,null,26,null,null,null,null,null,null,null,null,34,null,
//				15,0,85,13,3,88,86,null,80,39,33,null,52,null,null,39,null,-4,21,null,null,null,2,89,null,null,-1,-6,null,17,-1,65,null,null,null,null,null,19,null,null,63,null,null,null,null,null,
//				-1,68,null,null,null,null,null,null,null,null,17,null,null,null,93,42,null,null,null,12,null,null,null,92,85,82,8,null,null,34,18,90,50,null,99,89,null,19,null,null,78,null,74,-2,
//				null,null,null,63,null,null,38,38,null,null,null,null,null,null,null,null,null,null,74,null,8,null,null,null,49,null,null,null,21,0,null,2,60,15,36,83,59,54,null,-3,null,null,null,-8,
//				null,5,49,32,null,null,null,null,null,7,null,null,null,55,null,null,26,78,98,null,null,57,null,null,83,63,null,null,null,null,81,null,null,33,null,null,null,null,null,null,16,14,null,null,
//				-4,44,null,null,37,16,16,33,null,84,null,25,10,null,null,30,null,null,null,null,null,null,65,null,null,null,93,null,null,null,44,57,12,52,-4,67,null,49,null,null,null,null,null,null,null,
//				64,17,null,null,null,83,4,61,75,null,null,null,null,null,null,24,null,78,-7,null,-5,null,null,30,79,null,44,94,55,14,59,null,null,null,null,null,null,60,null,null,null,null,25,null,null,null,null,
//				97,34,null,null,null,null,80,67,0,null,22,null,96,null,null,null,null,null,null,null,null,47,null,null,null,null,89,null,null,null,null,43,64,null,null,9,null,null,96,37,79,null,null,null,
//				28,81,null,null,5,null,null,7,96,41,82,-6,20,null,null,null,8,null,null,null,null,null,null,null,null,null,82,8,null,null,null,null,null,null,null,null,39,6,null,null,null,null,90,59,-8,
//				null,null,null,null,null,23,null,null,null,15,89,null,null,86,50,40,70,null,null,32,null,null,null,44,-2,null,38,39,null,null,null,null,null,null,null,null,null,2,null,null,null,null,2,null,null,
//				65,47,null,null,62,27,62,38,31,27,null,null,37,null,null,null,null,null,null,null,62,74,86,-7,null,23,null,null,56,null,null,null,6,null,86,72,30,null,null,21,41,92,null,null,null,null,22,null,
//				74,96,87,null,null,null,null,null,-7,null,51,34,80,null,null,null,null,null,null,null,16,20,null,null,15,81,null,55,null,61,null,null,22,null,5,14,null,34,23,null,30,null,6,64,2,65,null,54,
//				null,null,14,null,0,null,null,null,26,null,null,78,null,49,null,null,13,null,null,null,43,null,null,-1,41,33,null,5,78,null,null,null,null,null,null,null,null,null,null,75,
//				null,null,null,null,null,null,63,70,null,null,null,null,null,40,null,null,null,66,null,null,15,null,7,43,null,60,12,null,null,null,null,null,null,null,19,null,74,-5,55,null,null,
//				null,null,52,-4,-5,null,null,null,65,null,null,67,null,3,null,null,95,null,null,36,null,81,null,null,null,null,null,88,74,null,67,null,null,null,null,68,null,null,null,null,52,null,
//				null,82,90,75,25,null,23,null,68,6,11,null,59,null,null,null,null,null,null,null,null,-2,null,null,null,61,null,null,null,32,80,null,null,null,51,6,null,91,null,null,37,null,26,null,
//				78,null,null,null,null,null,null,null,34,null,null,null,null,null,null,24,31,null,null,99,56,null,null,62,12,43,null,null,null,null,null,null,33,null,68,null,null,null,null,53,4,65,4,
//				null,86,null,46,-9,null,null,83,null,null,null,null,93,null,null,78,null,63,null,null,null,null,null,67,null,null,null,null,72,93,25,null,null,27,null,null,92,44,null,null,null,null,
//				2,9,31,null,null,73,92,62,86,null,null,-6,null,null,null,null,58,null,null,null,null,null,null,null,null,54,null,null,null,null,null,null,null,null,16,null,null,null,null,null,null,null,
//				null,null,null,null,28,null,48,null,null,null,null,null,26,null,null,null,null,null,null,37,66,
//				null,null,92,null,null,null,null,86,89,null,null,null,null,null,null,5,85,null,null,75,null,35,
//				null,null,null,null,null,null,null,27,null,null,null,null,null,null,67,84,94,44,null,null,null,
//				null,null,null,null,null,null,null,70,null,null,49,null,null,16,null,null,null,null,null,null,
//				null,87,null,-7,52,null,null,null,67,null,null,null,null,null,null,null,null,null,null,null,null,
//				null,null,80,null,76,null,25,null,91,null,12,41,null,null,26,
//				null,null,null,null,null,null,null,null,null,null,null,null,null,null,94};
//		Object [] nums={1,2,3,5,4,5,4};
//		TreeNode t = buildBT(nums);
//		
//		TreeNode tt = lowestCommonAncestorForNew( t, new TreeNode(4), new TreeNode(5) );
//		
//		System.out.println(tt.val);
		
		
//		TreeNode t = new TreeNode(37);
//		t.left=new TreeNode(-34);
//		t.right=new TreeNode(-48);
//		
//		t.left.right=new TreeNode(-100);
//		t.right.left=new TreeNode(-100);
//		t.right.right=new TreeNode(48);
//		t.right.right.left=new TreeNode(-54);
//		
//		t.right.right.left.left=new TreeNode(-71);
//		t.right.right.left.right=new TreeNode(-22);
//		t.right.right.left.right.right=new TreeNode(8);
		
//		t.right=new TreeNode(3);
//		t.left.right=new TreeNode(5);
//		t.right.right=new TreeNode(4);
//		t.right.right.left=new TreeNode(4);
		
//		t.right.left=new TreeNode(7);
//		t.right.right=new TreeNode(9);
		
//		t.left.right.left=new TreeNode(3);
//		t.left.right.right=new TreeNode(5);
//		TreeNode res = lowestCommonAncestors(t, new TreeNode(-100), new TreeNode(-71));
//		TreeNode x = lowestCommonAncestors(t, new TreeNode(5),new TreeNode(1));
//		TreeNode y = lowestCommonAncestors(t, new TreeNode(5),new TreeNode(4));
//		TreeNode z = lowestCommonAncestors(t, new TreeNode(5),new TreeNode(8));
		
//		System.out.println("5 and "+"1  root -> "+x.val);
//		System.out.println("5 and "+"4  root -> "+y.val);
//		System.out.println("-100 and "+"-71  root -> "+res.val);
		
//		List<String> ret = binaryTreePaths(t);
//		for(String str:ret){
//			System.out.println(str);
//		}
		// TODO Auto-generated method stub
//		int [] preorder = {11,7,2,8,1,3,4,5};
		
//		int [] inorder = {7,11,2,4,5,1,3,8,5,4,1};
//		TreeNode root  = buildTree2(prerder, inorder);
//		
//		List<List<Integer>> ret = pathSum(root, 22);
//		for(List<Integer> re:ret){
//			for(Integer e:re){
//				System.out.print(e+" ");
//			}
//			System.out.println();
//		}
		
//		TreeNode root = new TreeNode(1);
//		root.left=new TreeNode(2);
//		root.right=new TreeNode(3);
//		List<Integer> cur = new ArrayList<Integer>();
//		
//		dfs(root, cur);
//		
//		for(Integer i:cur){
//			System.out.print(i+" ");
//		}
//		System.out.println();
	}
	
	public static void dfs(TreeNode root, List<Integer> cur){
		if(root==null) return;
		cur.add(root.val);
		for(Integer i:cur){
			System.out.print(i+" ");
		}
		System.out.println();
		
		dfs(root.left, cur);
		dfs(root.right, cur);
		cur.remove(cur.size()-1);
	}

}
