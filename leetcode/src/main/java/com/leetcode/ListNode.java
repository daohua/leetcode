package com.leetcode;
import java.util.*;
public class ListNode {
	int val;
	ListNode next;
	public ListNode(int val){
		this.val=val;
	}
	
	
	public static ListNode rotateRight(ListNode head, int k) {
        if(head==null) return null;
        int len=0;
        ListNode p = head;
        ListNode lpre = head;
        ListNode pre = head;
        while(p!=null){
            len++;
            lpre=p;
            p=p.next;
        }
        if(k>len){
            k%=len;
        }
        if(len==1) return head;
        p = head;
        int i=0;
        if(k==0 || k==len){
        	return head;
        }
        while(i<len-k){
            pre=p;
            p=p.next;
            ++i;
        }
        lpre.next=head;
        pre.next=null;
        return p;
        
    }
	
	public static ListNode buildLinkedList(int [] nums){
		ListNode head = new ListNode(nums[0]);
		ListNode p = head;
		for(int i=1;i<nums.length;i++){
			p.next = new ListNode(nums[i]);
			p=p.next;
		}
		return head;
	}
	
	public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
		
        return 0;
    }
	
//	public static int[] singleNumber(int[] nums) {
//        
//    }
	
	
	 public static String convert(String s, int numRows) {
        StringBuilder res = new StringBuilder();
        int size = 2*numRows-2;
        
        for(int i=0;i<numRows;i++){
        	
            for(int j=i;j<s.length();j+=size){
            	
                res.append(s.charAt(j));
                if(i!=0 && i!=numRows-1){
                    int temp = j+size-2*i;
                    if(temp<s.length()){
                        res.append(s.charAt(temp));
                    }
                }
            }
        }
        return res.toString();
    }
	public static void main(String [] args){
		String s = "PAYPALISHIRING";
		int nrows = 3;
		System.out.println(convert(s, nrows));
//		int [] a = {1,2};
//		ListNode head = buildLinkedList(a);
//		
//		rotateRight(head, 0);
	}

}
