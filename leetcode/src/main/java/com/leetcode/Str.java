package com.leetcode;

public class Str {
	/**
	 * add Binary
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addBinary(String a, String b) {
        int m = a.length()-1;
        int n = b.length()-1;
        if(a==null || m+1==0) return b;
        if(b==null || n+1==0) return a;
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        
        while(m>=0 || n>=0){
        	int pa=0;
            int pb=0;
        	if(m>=0){
        		pa = a.charAt(m)=='0'?0:1;
        		--m;
        	}
        	if(n>=0){
        		pb = b.charAt(n)=='0'?0:1;
        		--n;
        	}

            int sum = pa+pb+flag;
            if(sum>=2){
                sb.append(String.valueOf(sum-2));
                flag=1;
            }else{
                sb.append(String.valueOf(sum));
                flag=0;
            }
        }
        
        if(flag==1){
        	sb.append(flag);
        }
        return sb.reverse().toString();
    }
	
	/**
	 * longest palindrome
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		if (s.isEmpty()) {
			return null;
		}
		if (s.length() == 1) {
			return s;
		}
		String longest = s.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			String tmp = helper(s, i, i);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
			tmp = helper(s, i, i + 1);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
		}
		return longest;
	}
	 
	public static String helper(String s, int begin, int end) {
		while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)){
			begin--;
			end++;
		}
		return s.substring(begin + 1, end);
	}
	
	public static int[] productExceptSelf(int[] nums) {
		int len=nums.length;
		int[] res = new int[len];
		res[len-1]=1;
		for(int i=nums.length-2;i>=0;i--){
			res[i]=res[i+1]*nums[i+1];
		}
		int left=1;
		for(int i=0;i<len;i++){
			res[i]*=left;
			left*=nums[i];
		}
		return res;
    }
	
	/**
	 * ZigZag pattern
	 * @param s
	 * @param numRows
	 * @return
	 */
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] nums = {1,0};
		int [] ret = productExceptSelf(nums);
		for(int i=0;i<ret.length;i++){
			System.out.print(ret[i]+" ");
		}
	}
}
