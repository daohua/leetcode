package com.leetcode;

import java.util.*;

public class Math {
	

	/**
	 * 3 Sum
	 * @param a
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> threeSum(int[] a){
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if(a.length<=2){
			return ret;
		}
		Arrays.sort(a);
		for(int i=0;i< a.length-2;i++){
			int j=i+1;
			int k = a.length-1;
			while(j<k){
				if(a[i]+a[j]+a[k]==0){
					ArrayList<Integer> re = new ArrayList<Integer>();
					re.add(a[i]);
					re.add(a[j]);
					re.add(a[k]);
					ret.add(re);
					++j;
					--k;
					while(j<k && a[j-1]==a[j]) ++j;
					while(j<k && a[k]==a[k+1]) --k;
				} else if(a[i]+a[j]+a[k]<0){
					++j;
				} else {
					--k;
				}
			}
			while(i<a.length-2 && a[i]==a[i+1]){
				++i;
			}
		}
		return ret;
	}
	/**
	 * 3 Sum closest
	 * @param a
	 * @param target
	 * @return
	 */
	public static int threeSumClosest(int [] a, int target){
		Arrays.sort(a);
		int distance=Integer.MAX_VALUE;
		int curDistance =0;
		int ret=0;
		for(int i=0;i<a.length-2;i++){
			int j=i+1;
			int k =a.length-1;
			while(j<k){
				int tempVal = a[i]+a[j]+a[k];
				if(tempVal<target){
					curDistance=target-tempVal;
					if(curDistance<distance){
						distance=curDistance;
						ret=tempVal;
					}
					++j;
					
				}else if(tempVal>target){
					curDistance=tempVal-target;
					if(curDistance<distance){
						distance=curDistance;
						ret=tempVal;
					}
					--k;
				}else{
					return tempVal;
				}
			}
		}
		return ret;
	}
	
	/**
	 * 4 Sum
	 * @param a
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> fourSum(int [] a ,int target){
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(a);
		
		for(int i=0;i<a.length-3;i++){
			for(int j=i+1;j<a.length-2;j++){
				int k = j+1;
				int l = a.length-1;
				
				while(k<l){
					if(a[i]+a[j]+a[k]+a[l]==target){
						ArrayList<Integer> re = new ArrayList<Integer>();
						re.add(a[i]);
						re.add(a[j]);
						re.add(a[k]);
						re.add(a[l]);
						ret.add(re);
						++k;
						--l;
						while(k<l && a[k]==a[k-1]) ++k;
						while(k<l && a[l]==a[l+1]) --l;
					} else if(a[i]+a[j]+a[k]+a[l]<target){
						++k;
						
					} else{
						--l;
					}
				}
				while(j<a.length-2 && a[j]==a[j+1]){
					++j;
				}
			}
			while(i<a.length-3 && a[i]==a[i+1]){
				++i;
			}
		}
		return ret;
	}
	
	
	public static  int titleToNumber(String s) {
        int res = 0;

		int len=s.length();
		int t=0;
		int i=len-1;
		if(len==0) return 0;
		
		if(len==1){
			res= s.charAt(0)-'A'+1;
		}else if(len>1){
			while(i>=0){
				res+=(int)(s.charAt(i)-'A'+1)*java.lang.Math.pow(26, t);
				++t;
				--i;
			}
//			res+=s.charAt(len-1)-'A'+1;
			
		}
		return res;
        
    }
	
	
	public static int addDigits(int num) {
		int sum=0;
		while(num>0){
			sum+=num%10;
			num=num/10;
			
		}
		
		while(sum>=10){
			sum=sum/10+sum%10;
		}
        return sum;
        
        
    }
	public static void ugly(int n){
		SortedSet<Long> next = new TreeSet<Long>();
	    next.add((long) 1);

	    long cur = 0;
	    for (int i = 0; i < n; ++i) {
	        cur = next.first();
	        System.out.println("number " + (i + 1) + ":   " + cur);

	        next.add(cur * 2);
	        next.add(cur * 3);
	        next.add(cur * 5);
	        next.remove(cur);
	    }
	}
	
	 public static boolean isUgly(int num) {

		if(num <= 0)
	        return false;
	    while (num % 2 == 0)
			num /= 2;
		while (num % 3 == 0)
			num /= 3;
		while (num % 5 == 0)
			num /= 5;
		return num == 1 ? true : false;    
    }
	
	public static void main(String [] args){
		System.out.println(isUgly(4));
//		String s = "AAA";
//		
//		System.out.println(titleToNumber(s));
//		int [] a={102,1,2,2,3,43,37,-12,-25,45,89,3,-5,-5,12,0,22};
//		ArrayList<ArrayList<Integer>> ret = fourSum(a, 22);
//		for(ArrayList<Integer> re : ret){
//			System.out.println(re.get(0)+","+re.get(1)+","+re.get(2)+","+re.get(3));
//		}
//			int ret = threeSumClosest(a, 18);
//			System.out.println("sdfafa");

	}

}
