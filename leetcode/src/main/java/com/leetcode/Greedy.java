package com.leetcode;

import java.util.*;
import java.lang.*;
public class Greedy {
	
	/**
     * jump Game 
     * @param nums
     * @return
     */
    public boolean canJump(int [] nums){
    	if(nums.length<=1) return true;
    	int current = nums[0];
    	for(int i=0;i<nums.length;i++){
    		if(nums[i]==0 && i>=current){
    			return false;
    		}
    		if(nums[i]+i>current){
    			current=i+nums[i];
    		}
    		if(current+1>=nums.length){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * jump Game
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int step =0;
        int cur=0;
        int next=0;
        int n = nums.length;
        int i=0;
        while(i<n){
            if(cur>=n-1) break;
            while(i<=cur){
            	
                next = java.lang.Math.max(next, i+nums[i]);
                ++i;
            }
            ++step;
            cur=next;
        }
        return step;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
