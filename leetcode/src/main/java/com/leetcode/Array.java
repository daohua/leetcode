package com.leetcode;


public class Array {
	
	/**
	 * Merge Sorted Array
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[] mergeSortedArray(int [] a,int [] b){
		int len = a.length+b.length;
		int [] c = new int[len];
//		int min = a.length<b.length?a.length:b.length;
		int i=0,aindex=0,bindex=0;
		while(aindex<a.length && bindex<b.length){
			if(a[aindex]<b[bindex]){
				c[i]=a[aindex];
				aindex++;
				i++;
				
			}else{
				c[i]=b[bindex];
				bindex++;
				i++;
			}	
		}
		
		while(aindex<a.length){
			c[i]=a[aindex];
			aindex++;
			i++;
		}
		
		while(bindex<b.length){
			c[i]=b[bindex];
			bindex++;
			i++;
		}
		return c;
	}
	
	/**
	 * Find Minimum in Rotated Sorted Array
	 * you may assume this array has no duplicate element
	 * @param a
	 * @return
	 */
	public static int findMin(int [] a){
		
		if(a.length==0) return 0;
		else if(a.length==1) return a[0];
		else if(a.length==2) return java.lang.Math.min(a[0],a[1]);
		
		int start=0;
		int stop =a.length-1;
		
		while(start<stop-1){
			if(a[start]<a[stop]){
				return a[start];
			}
			
			int mid = start+(stop-start)/2;
			if(a[mid]>a[start]){
				start=mid;
			} else {
				stop=mid;
			}
		}
		return java.lang.Math.min(a[start], a[stop]);
	}
	
	/**
	 * find minimum rotated sorted array
	 * you may contains duplicates in the array
	 * @param a
	 * @return
	 */
	public static int findMinForDuplicates(int [] a){
		if(a.length==0){
			return 0;
		}
		else if(a.length==1){
			return a[0];
		}
		else if(a.length==2){
			return java.lang.Math.min(a[0], a[1]);
		}
		
		int start =0;
		int stop=a.length-1;
		while(start<stop-1){
			if(a[start]<a[stop]){
				return a[start];
			}
			int mid = start+(stop-start)/2;
			if(a[mid]>a[start]){
				start=mid;
			} else if(a[mid]<a[start]){
				stop=mid;
			} else{
				++start;
			}
		}
		return java.lang.Math.min(a[start], a[stop]);
		
	}
	
	/**
	 * search in rotated sorted array
	 * you may assume no duplicate exists in the array
	 * @param a
	 * @param target
	 * @return
	 */
	public static int search(int [] a, int target){
		int start=0;
		int stop=a.length-1;
		while(start<=stop){
			int mid = start+(stop-start)/2;
			if(a[mid]==target){
				return mid;
			}
			if(a[mid]>=a[start]){
				if(a[start]<=target && target<=a[mid]){
					stop=mid-1;
				} else{
					start=mid+1;
				}
				
			}else {
				if(a[start]<=target && target<=a[stop]){
					start=mid+1;
				}else{
					stop=mid-1;
				}
			}
		}
		return -1;
	}
	
	/**
	 * find median sorted arrays
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
        return 0;
    }
	
	
	
	public static void main(String [] args){
		int [] a = {3,1};
		System.out.println(search(a,1));
	}
	
	

}
