package com.leetcode;

import java.util.HashMap;

public class Hash {
	/**
	 * Two Sum
	 * @param a
	 * @param sum
	 * @return
	 */
	public static int [] twoSum(int [] a, int sum){
		int [] indexs = new int [2];
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<a.length;i++){
			if(map.containsKey(sum-a[i])){
				int m = map.get(sum-a[i]);
				indexs[0]=m+1;
				indexs[1]=i+1;
				return indexs;
			}else{
				map.put(a[i],i);
			}
		}
		return null;
	}

}
