package com.leetcode;

public class Common {
	
	// selection sort
	public static int[] sort(int [] a){
		for(int i=0;i<a.length;i++){
			int cur = i;
			for(int j=i;j<a.length;j++){
				if(a[cur]>a[j]){
					cur=j;
				}
			}
			if(cur!=i){
				a[cur]= a[cur]+a[i];
				a[i]=a[cur]-a[i];
				a[cur]=a[cur]-a[i];
			}
		}
		return a;
	}
	//bubble sort
	
}
