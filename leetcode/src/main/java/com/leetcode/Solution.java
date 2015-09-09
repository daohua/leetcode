package com.leetcode;

import java.util.*;

public class Solution {
	boolean[] colEmpty;
    boolean[] upDigEmpty;
    boolean[] downDigEmpty;
    boolean [][] queens;
    public List<List<String>> solveNQueens(int n) {
        
        List<List<String>> ret = new ArrayList<List<String>>();
        if(n==0) return ret;
        queens = new boolean[n][n]; 
        colEmpty = new boolean[n];
        upDigEmpty = new boolean[2*n];
        downDigEmpty = new boolean[2*n];
        // init
        Arrays.fill(colEmpty, true);
        Arrays.fill(upDigEmpty, true);
        Arrays.fill(downDigEmpty, true);
        dfs(ret, 0, n);
        
        return ret;
    }
    
    public void add(boolean [][] queens, List<List<String>> ret){
    	List<String> temp = new ArrayList<String>();
    	for(int i=0;i<queens.length;i++){
            
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<queens.length;j++){
                if(queens[i][j]){
                	sb.append("Q");
                }else{
                	sb.append(".");
                }
            }
            temp.add(sb.toString());
        }
    	ret.add(temp);
    }
    
    public void dfs(List<List<String>> ret, int row, int n){
        if(row==n){
        	add(queens, ret);
            return;
        }
        
        for(int col=0; col < n; col++){
            if(isSafe(row, col,n)){
                placeQueen(row, col);
                dfs(ret, row+1, n);
                removeQueen(row, col);
            }
        }
    }
    
    public boolean isSafe(int row, int col, int n){
        return (colEmpty[col] && upDigEmpty[row+col] && downDigEmpty[n-1+row-col]);
    }
    
    public void placeQueen(int row, int col){
        queens[row][col]=true;
        colEmpty[col]=false;
        upDigEmpty[row+col]=false;
        downDigEmpty[(queens.length-1)+row-col]=false;
    }
    
    public void removeQueen(int row, int col){
        queens[row][col]=false;
        colEmpty[col]=true;
        upDigEmpty[row+col]=true;
        downDigEmpty[(queens.length-1)+row-col]=true;
    }
    
	public static void main(String[] args) {
		List<List<String>> ret = new Solution().solveNQueens(8);
		System.out.println("enter the size of chessboard: 8");
		System.out.println("8-queens problem solution");
		System.out.println("total number:"+ret.size());
		for(List<String> list:ret){
			for(String str:list){
				System.out.println(str);
			}
			System.out.println();
		}
		
		

	}

}
