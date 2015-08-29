package com.leetcode;

import java.util.*;

public class Dynamic {
	
	/**
	 * only one transaction
	 * @param nums
	 * @return
	 */
	public int maxProfit(int [] nums){
		if(nums.length<=1) return 0;
		int max=0;
		int min = nums[1];
		
		for(int i=1;i<nums.length;i++){
			
			min = Integer.min(nums[i], min);
			int cur = nums[i]-min;
			if(cur>max){
				max=cur;
			}
		}
		return max;
	}
	
	/**
	 * many transaction
	 * @param nums
	 * @return
	 */
	public int maxProfitWithManyTransaction(int [] nums){
		if(nums.length<=1) return 0;
		int max=0;
		int min = nums[1];
		
		for(int i=1;i<nums.length;i++){
			
			min = Integer.min(nums[i], min);
			int cur = nums[i]-min;
			if(cur>0){
				max+=cur;
				min=nums[i];
			}
		}
		return max;
	}
	
	/**
	 * unique paths
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n){
		
//		if(m==0 || n==0) return 1;
		int [][] paths = new int[m][n];
		//initialize m=1 1*n
		for(int i=0;i<n;i++){
			paths[0][i]=1;
		}
		//initialize n=1 m*1
		for(int j=0;j<m;j++){
			paths[j][0]=1;
		}
		
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				paths[i][j]=paths[i-1][j]+paths[i][j-1];
			}
		}
		
		return paths[m-1][n-1];
	}
	
	/**
	 * unique paths with obstacles
	 * @param m
	 * @param n
	 * @return
	 */
	public static int uniquePathsWithObstacles(int[][] obstacleGrid){
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		
		obstacleGrid[0][0] = obstacleGrid[0][0]==1?0:1;
		//m=1 1*n
		for(int i=1;i<m;i++){
			obstacleGrid[i][0]=(obstacleGrid[i-1][0]==1 && obstacleGrid[i][0]==0)?1:0;
		}
		
		//n=1 m*1
		for(int j=1;j<n;j++){
			obstacleGrid[0][j]=(obstacleGrid[0][j-1]==1 && obstacleGrid[0][j]==0)?1:0;
		}
		
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				if(obstacleGrid[i][j]==1){
					obstacleGrid[i][j]=0;
				} else{
					obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
				}
			}
		}
		
		return obstacleGrid[m-1][n-1];
	}
	
	/**
	 * minimum path sum
	 * @param grid
	 * @return
	 */
	public int minPathSum(int [][] grid){
		if(grid.length==0) return 0;
		int m=grid.length;
		int n=grid[0].length;
		int [][] dp = new int[m][n];
		dp[0][0] = grid[0][0];
		
		for(int i=1;i<m;i++){
			dp[i][0]=dp[i-1][0]+grid[i][0];
		}
		
		for(int j=1;j<n;j++){
			dp[0][j]=dp[0][j-1]+grid[0][j];
		}
		
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				dp[i][j]=Integer.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
			}
		}
		return dp[m-1][n-1];
	}

	/**
	 * Maximum SubArray
	 * @param nums
	 * @return
	 */
	public int getMaxSubArray(int [] nums){
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for(int i=1;i<nums.length;i++){
			sum+=nums[i];
			Integer.max(sum,max);
			if(sum==0) sum=0;
		}
		return max;
	}
	
	/**
	 * divide and conquer
	 * @param nums
	 * @return
	 */
	public int getMaxSubArray2(int [] nums){
		
		return divide(nums, 0, nums.length-1, Integer.MIN_VALUE);
	}
	
	public int divide(int [] nums, int start, int end, int tmax){
		if(start>end) return Integer.MIN_VALUE;
		int mid = start+(end-start)/2;
		int lmax = divide(nums, start, mid-1, tmax);
		int rmax = divide(nums, mid+1, end, tmax);
		tmax = Integer.max(tmax, lmax);
		tmax= Integer.max(tmax, rmax);
		int sum = 0;
		int mlmax = 0;
		int mrmax = 0;
		for(int i=mid;i>=start;i--){
			sum+=nums[i];
			mlmax=Integer.max(mlmax, sum);
		}
		sum = 0;
		for(int j=mid+1;j<=end;j++){
			sum+=nums[j];
			mrmax=Integer.max(mrmax,sum);
		}
		
		tmax = Integer.max(tmax, mlmax+nums[mid]+mrmax);
		return   tmax;
	}
	
	/**
	 * Dynamic Equation
	 * maxDP[i+1] = max(max(maxDP[i]*nums[i], nums[i]), minDP*nums[i])
	 * minDP[i+1] = min(min(maxDP[i]*nums[i], nums[i]), minDP*nums[i])
	 * @param nums
	 * @return
	 */
	public int maxProductArray(int [] nums){
		
		int p = nums[0];
		int maxDP = nums[0];
		int minDP = nums[0];
		
		for(int i =1; i<nums.length;i++){
			int t = maxDP;
			maxDP = Integer.max(Integer.max(maxDP*nums[i], nums[i]), minDP*nums[i]);
			minDP = Integer.min(Integer.min(t*nums[i], nums[i]), minDP*nums[i]);
			p = Integer.max(maxDP, p);
		}
		return p;
	}
	
	/**
	 * Fibonacci number
	 * @param n
	 * @return
	 */
	public int climbStairs(int n){
		int f1=1;
		int f2=2;
		if(n==1) return f1;
		else if(n==2) return f2;
		
		int fn = 0 ;
		for(int i=3;i<=n;i++){
			fn = f1+f2;
			f1=f2;
			f2=fn;
		}
		return fn;
	}
	
	/**
	 * 
	 * dp[m][n] = min(dp[m+1][n],dp[m+1][n+1]) + triangle[m][n]
	 * @param triangle
	 * @return
	 */
	public static int minimumTotal(List<List<Integer>> triangle){
		int rows = triangle.size();
		int [] dp = new int[rows];
		
		for(int i=0;i<rows;i++){
			dp[i]=triangle.get(rows-1).get(i);
		}
		
		for(int i=rows-2;i>=0;i--){
			for(int j=0;j<=i;j++){
				dp[j] = triangle.get(i).get(j)+Integer.min(dp[j], dp[j+1]);
			}
		}
		return dp[0];
	}
	
	/**
	 * unique binary search tree
	 * @param n
	 * @return
	 */
	public int numTrees(int n){
		int [] dp = new int[n+1];
		dp[0]=dp[1]=1;
		for(int i=2;i<=n;i++){
			int sum = 0;
			for(int k=0;k<i;k++){
				sum+=dp[k]*dp[i-k-1];
			}
			dp[i]=sum;
		}
		return dp[n];
	}
	
	/**
	 * unique binary search tree II
	 * @param n
	 * @return
	 */
	public static List<TreeNode> generateTrees(int n){
		return generate(1, n);
	}
	
	public static List<TreeNode> generate(int start, int stop){
		List<TreeNode> ret = new ArrayList<TreeNode>();
		if(start>stop){
			ret.add(null);
			return ret;
		}
		for(int i=start;i<=stop;i++){
			
			List<TreeNode> l = new ArrayList<TreeNode>();
			List<TreeNode> r = new ArrayList<TreeNode>();
			
			for(int j=0;j<l.size();j++){
				for(int k =0;k<r.size();k++){
					
					TreeNode root = new TreeNode(i);
					root.left = l.get(j);
					root.right = r.get(k);
					ret.add(root);
				}
			}
		}
		return ret;
	}
	
	
    
	public static void main(String[] args) {
//		int [][] a = new int[8][6];
//		a[2][4]=1;
//		for(int i=0;i<8;i++){
//			for(int j=0;j<6;j++){
//				a[i][j]=0;
//			}
//		}
		
//		System.out.println(uniquePathsWithObstacles(a));

	}
	
	
	
	
	
	
	
	

}
