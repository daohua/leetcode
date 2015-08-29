package com.leetcode;

import java.util.*;

public class BackTracking {
	
	
	/**
	 * combination
	 * @param n
	 * @param k
	 * @return
	 */
	public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(k==0){
            return ret;
        }
        List<Integer> l = new ArrayList<Integer>();
        DFS(ret, l, n, k, 1);
        return ret;
    }
    
    public static void DFS(List<List<Integer>> ret, List<Integer> l, int n, int k, int level){
        if(l.size()==k){
        	//clone
        	List<Integer> re = new ArrayList<Integer>(l);
            ret.add(re);
            return;
        }
        if(l.size()>k){
            return;
        }
        for(int i=level;i<=n;i++){
            l.add(i);
            DFS(ret, l, n, k, i+1);
            l.remove(l.size()-1);
        }
    }
    
    
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> l = new ArrayList<Integer>();
        Arrays.sort(candidates);
        backTracking(ret, l, candidates, target, 0);
        return ret;
    }
    
    public static void backTracking(List<List<Integer>> ret, List<Integer> l, int [] candidates, int target, int level){
    	if(target==0){
           List<Integer> re = new ArrayList<Integer>(l);
           ret.add(re);
           return;
        }
    	if(target<0) return;
        
        for(int i=level;i<candidates.length;i++){
            target-=candidates[i];
            l.add(candidates[i]);
            backTracking(ret, l, candidates, target, i);
            l.remove(l.size()-1);
            target+=candidates[i];
        }
    }
    
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> l = new ArrayList<Integer>();
        Arrays.sort(candidates);
        backTracking2(ret, l, candidates, target, 0);
        return ret;
        
    }
    
    public static void backTracking2(List<List<Integer>> ret, List<Integer> l, int [] candidates, int target, int level){
        
        if(target==0){
            List<Integer> re = new ArrayList<Integer>(l);
            if(ret.contains(re)) return;
            ret.add(re);
            return;
        }
        
        if(target<0) return;
        
        for(int i =level;i<candidates.length;i++){
            target-=candidates[i];
            l.add(candidates[i]);
            backTracking2(ret, l, candidates, target, i+1);
            l.remove(l.size()-1);
            target+=candidates[i];
        }
    }
    
	public static List<List<Integer>> combinationSum3(int k, int n) {
	        
	        List<List<Integer>> ret = new ArrayList<List<Integer>>();
	        List<Integer> l = new ArrayList<Integer>();
	        backTracking(ret, l, k, n, 1);
	        return ret;
	    }
	    
    public static void backTracking(List<List<Integer>> ret, List<Integer> l, int k, int sum, int level){
	        
        if(sum==0 && k==0){
            List<Integer> re = new ArrayList<Integer>(l);
            ret.add(re);
            return;
        }
        
        if(sum<0) return;
        
        for(int i =level;i<=9;i++){
            sum-=i;
            k-=1;
            l.add(i);
            backTracking(ret, l, k, sum, i+1);
            l.remove(l.size()-1);
            sum+=i;
            k+=1;
        }
    }
    
    
    public static List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        if(digits.length()==0) return ret;
    	HashMap<Integer,String> map = new HashMap<Integer, String>();
        String str2 = "abc";
        String str3 = "def";
        String str4 = "ghi";
        String str5 = "jkl";
        String str6 = "mno";
        String str7 = "pqrs";
        String str8 = "tuv";
        String str9 = "wxyz";
        map.put(2,str2);
        map.put(3,str3);
        map.put(4,str4);
        map.put(5,str5);
        map.put(6,str6);
        map.put(7,str7);
        map.put(8,str8);
        map.put(9,str9);
        combination(ret, "", digits, map, 0);
        return ret;
    }
    
    public static void combination(List<String> ret, String temp, String digits, HashMap<Integer,String> map, int level){
        if(digits.length()==temp.length()){
            ret.add(temp);
            return;
        }
        int index = digits.charAt(level)-'0';
        for(int i=0;i<map.get(index).length();i++){
            temp=temp+map.get(index).charAt(i);
            combination(ret, temp, digits, map, level+1);
            temp=temp.substring(0,temp.length()-1);
        }
    }
    
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(nums.length==0) return ret;
        List<Integer> l = new ArrayList<Integer>();
        Arrays.sort(nums);
        ret.add(new ArrayList<Integer>());
        generate(ret, l, nums, 0);
        return ret;
    }
    
    public static void generate(List<List<Integer>> ret, List<Integer> l, int [] nums, int level){
    	if(level==nums.length) return;
    	
        for(int i=level;i<nums.length;i++){
            l.add(nums[i]);
            ret.add(new ArrayList<Integer>(l));
            generate(ret, l, nums, i+1);
            l.remove(l.size()-1);
        }
        
    }
    
    
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> l = new ArrayList<Integer>();
        if(nums.length==0) return ret;
        Arrays.sort(nums);
        ret.add(new ArrayList<Integer>());
        generateSubset(ret, l, nums, 0);
        return ret;
        
    }
    
    public static void  generateSubset(List<List<Integer>> ret, List<Integer> l, int [] nums, int level){
        if(level==nums.length) return;
        
        for(int i=level;i<nums.length;i++){
        	
            l.add(nums[i]);
            List<Integer> temp = new ArrayList<Integer>(l);
            ret.add(temp);
            generateSubset(ret, l, nums, i+1);
            l.remove(l.size()-1);
            while(i<nums.length-1 && nums[i]==nums[i+1]) ++i;
        }
        
    }
    
    public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	List<Integer> l = new ArrayList<Integer>();
        if(nums.length==0) return ret;
        boolean [] isVisited = new boolean[nums.length];
        permutation(ret, l, nums, isVisited);
        return ret;
    }
    
    public static void  permutation(List<List<Integer>> ret, List<Integer> l, int [] nums, boolean [] isVisited){
        if(l.size()==nums.length){
            ret.add(new ArrayList<Integer>(l));
            return;
        }
        
        for(int i=0;i<nums.length;i++){
        	if(isVisited[i]==false){
        		l.add(nums[i]);
        		isVisited[i]=true;
                permutation(ret, l, nums, isVisited);
                l.remove(l.size()-1);
                isVisited[i]=false;
        	}
        }
    }
    
    public  static List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	List<Integer> l = new ArrayList<Integer>();
        if(nums.length==0) return ret;
        boolean [] isVisited = new boolean[nums.length];
        permutation(ret, l, new HashSet<Integer>(), nums, isVisited);
        return ret;
    }
    
    public static void  permutation(List<List<Integer>> ret, List<Integer> l, HashSet<Integer> set, int [] nums, boolean [] isVisited){
        if(l.size()==nums.length){
            ret.add(new ArrayList<Integer>(l));
            return;
        }
        
        for(int i=0;i<nums.length;i++){
            
        	if(isVisited[i]==false){
        		l.add(nums[i]);
        		isVisited[i]=true;
        		set.add(nums[i]);
                permutation(ret, l, set, nums, isVisited);
                l.remove(l.size()-1);
                isVisited[i]=false;
                while(i<nums.length-1 && nums[i]==nums[i+1]) 
                	++i;
        	}
        }
        return;
    }
    
//    public static boolean wordBreak(String s, List<String> wordDict) {
//        if(s.length()==0) return true;
//        
//        return wordBreakHelper(s, wordDict, 0);
//    }
//    
//    public static boolean wordBreakHelper(String s, List<String> wordDict, int start){
//        if(start==s.length()) return true;
//        for(String str:wordDict){
//            int len = str.length();
//            int end = start+len;
//            if(end>s.length()) break;
//            if(s.substring(start, end).equals(str)){
//                if(wordBreakHelper(s, wordDict, end)){
//                    return true;
//                }
//            }
//        }
//        return false;
//        
//    }
    
    
    public static boolean wordBreak(String s, Set<String> wordDict) {
        if(s.length()==0) return true;
        
        return wordBreakHelper(s, wordDict, 0);
    }
    
    public static boolean wordBreakHelper(String s, Set<String> wordDict, int start){
        if(start==s.length()) return true;
        for(String str:wordDict){
            int len = str.length();
            int end = start+len;
            
            if(end>s.length()) break;
            
            if(s.substring(start, end).equals(str)){
                if(wordBreakHelper(s, wordDict, end)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean wordBreakDP(String s, Set<String> wordDict) {
        int length = s.length();
        boolean [] dp = new boolean [length+1];
        dp[0] = true;
        for(int i=0;i<length;i++){
            if(!dp[i]) continue;
            for(String str:wordDict){
                int len = str.length();
                int end = i+len;
                if(end>s.length()) continue;
                if(dp[end]) continue;
                if(s.substring(i, end).equals(str)){
                    dp[end]=true;
                }
            }
        }
        return dp[length];
    }
    
    public static List<String> wordBreakTwo(String s, Set<String> wordDict) {
        List<String> ret = new ArrayList<String>();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int length = s.length(); 
        boolean [] dp = new boolean[length+1];
        
        dp[0]=true;
        
        for(int i=0;i<length;i++){
        	if(!dp[i]) continue;
        	for(String str:wordDict){
        		int len = str.length();
        		int end = len+i;
        		if(end > s.length()) continue;
        		if(dp[end]) continue;
        		if(s.substring(i, end).equals(str)){
        			dp[end]=true;
        			if(i==0)
        				map.put(str, end);
        		}
        	}
        }
        for(String ss:map.keySet()){
        	String part = generate(length, wordDict, dp, s, map.get(ss));
        	ret.add(ss+" "+part);
        }
        
        return ret;
    }
    
    public static String generate(int length, Set<String> wordDict, boolean [] dp, String s, int start){
    	String temp="";
    	int curIndex=start;
    	for(int i=start;i<length;i++){
        	if(!dp[i]) continue;
        	for(String str:wordDict){
        		int len = str.length();
        		int end = len+i;
        		if(end > s.length()) continue;
//        		if(dp[end]) continue;
        		if(s.substring(i, end).equals(str)){
        			dp[end]=true;
        			if(end==curIndex+len){
        				temp+=str+" ";
    					curIndex=end;
        			}
        		}
        	}
        }
    	return temp.substring(0,temp.length()-1);
    }
    
    
    public static int lengthOfLastWord(String s) {
    	int len = s.length();
    	if(s==null || len==0) return 0;
    	
    	boolean sign=false;
    	int result=0;
        for(int i=len-1;i>=0;i--){
        	
        	if((s.charAt(i)>='a' && s.charAt(i)<='z') || (s.charAt(i)>='A' && s.charAt(i)<='Z')) {
        		sign=true;
        		result++;
			} else if(sign){
				return result;
			}
        }
        return result;
    }
    
    public static List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<String>();
        if(n==0) return ret;
        dfs(ret, "", n, n);
        return ret;
    }
    
    public static void dfs(List<String> ret, String temp, int left, int right){
    	if(left>right) return;
        if(left==0 && right==0){
            ret.add(temp);
            return;
        }
        if(left>0){
        	dfs(ret, temp+"(", left-1, right);
        }
        if(right>0){
        	dfs(ret, temp+")", left, right-1);
        }
    }
    
	public static void main(String[] args) {

		List<String> ret = generateParenthesis(2);
		for(String s:ret){
			System.out.println(s);
		}
		
		
		
//		String s = " a ";
//		System.out.println(lengthOfLastWord(s));
		// TODO Auto-generated method stub
//		int [] can = {1,1,2};
//		List<List<Integer>> ret = combinationSum2(can, 3);
//		List<List<Integer>> ret = permuteUnique(can);
//		for(List<Integer> r:ret){
//			for(Integer i:r){
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
//		String strDig = "8";
//		List<String> re = letterCombinations(strDig);
//		for(String str:re){
//			System.out.print(str+" ");
//		}
		
//		Set<String> set = new HashSet<String>();	
//		String s = "catsanddog";
//		set.add("cat");
//		set.add("cats");
//		set.add("and");
//		set.add("sand");
//		set.add("dog");
//		set.add("sand");
		
//		"applepie", ["pie","pear","apple","peach"]
//		set.add("code");
		
//		for(String str:wordBreakTwo(s, set)){
//			System.out.println(str);
//		}
		
		
	}

}
