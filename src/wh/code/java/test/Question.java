package wh.code.java.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Question {

	public static void main(String[] args) {

		Question q = new Question();
		String s="2.14.24.3423";
//		String[] res=s.s
		int[] a={1,2,2,4};
		int[] b={2,2};
		int[] res=q.intersection(a,b);
//		System.out.println("\\.");
		System.out.println(Arrays.toString(res));

	}
	
	public int compareVersion(String version1, String version2) {
		String[] v1Nums = version1.split("\\.");
	    String[] v2Nums = version2.split("\\.");
	    for(int i = 0; i < v1Nums.length || i < v2Nums.length; i++) {
	        int v1Num = (i < v1Nums.length) ? Integer.parseInt(v1Nums[i]) : 0;
	        int v2Num = (i < v2Nums.length) ? Integer.parseInt(v2Nums[0]):0;
	        if(v1Num < v2Num) return -1;
	        if(v2Num < v1Num) return 1;
	    }
	    return 0;
    }
	
	public boolean isPowerOfThree(int n) {
        if(n<=0)
            return false;
        while(n%3==0)
        	n=n/3;
        return n==1;
    }

	public int[] intersection(int[] nums1, int[] nums2) {
		if(nums1.length==0||nums2.length==0)
			return null;
		HashSet<Integer> s=new HashSet<Integer>();
		HashSet<Integer> r=new HashSet<Integer>();
		for(int i:nums1){
			s.add(i);
		}
		for(int j:nums2){
			if(s.contains(j)&&!r.contains(j))
				r.add(j);
		}
		
		int[] res=new int[r.size()];
		int j=0;
		Iterator<Integer> iterator=r.iterator();
		while(iterator.hasNext()){
			res[j]=iterator.next();
			j++;
		}

		return res;
		
	}

	public int addDigits(int num) {
		while (num > 10) {
			char[] c = String.valueOf(num).toCharArray();
			num = 0;
			for (int i = 0; i < c.length; i++) {
				num += Integer.parseInt(String.valueOf(c[i]));
			}

		}
		return num;
	}

	public String reverseVowels(String s) {
		char[] c = s.toCharArray();
		int i = 0, j = s.length() - 1;
		Character[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
		ArrayList<Character> list = new ArrayList<Character>(Arrays.asList(vowels));
		for (; i < j;) {
			if (!list.contains(c[i])) {
				i++;
				continue;
			}
			if (!list.contains(c[j])) {
				j--;
				continue;
			}
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
			i++;
			j--;
		}
		return String.valueOf(c);
	}

	public String reverseString(String s) {
		char[] c = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = c.length - 1; i >= 0; i--) {
			sb.append(c[i]);
		}
		return sb.toString();
	}

	public int[] countBits(int num) {
		int[] res = new int[num + 1];

		for (int i = 0; i <= num; i++) {
			int count = 0;
			int n = i;
			while (n != 0) {
				if ((n & 1) == 1)
					count++;
				n = n >> 1;
			}
			res[i] = count;
		}

		return res;
	}

	public static void print1ToMaxDigits(int n) {
		String num = "0";
		System.out.println(num);
		while (!increment(num, n)) {

		}
	}

	public static boolean increment(String num, int n) {
		return false;

	}

	// 递归 字符串反转
	public static String stringReverse(String s) {
		if (s.length() > 0)
			return s.charAt(s.length() - 1) + stringReverse(s.substring(0, s.length() - 1));
		return "";
	}

	// 循环 字符串反转
	public static String stringReverse1(String s) {
		char[] cArr = s.toCharArray();
		String s1 = "";
		for (int i = cArr.length - 1; i >= 0; i--) {
			s1 += cArr[i];
		}
		return s1;
	}

	// 回文字符串
	public static boolean isCycleString(char[] s, int start, int end) {
		if (s == null || start < 0 || end < 0)
			return false;
		if (start < end) {
			if (s[start] == s[end])
				return isCycleString(s, start + 1, end - 1);
			return false;
		} else {
			return true;
		}
	}
	// atoi

	public static int myAtoi(String str) {
		if (str.length() == 0)
			return 0;
		char[] cArr = str.toCharArray();
		for (char c : cArr) {
			if (c < '0' || c > '9')
				return 0;
		}
		return 0;
	}

	// public static int[] countBits(int n) {
	// int[] ret = new int[n + 1];
	// int count = 0;
	// for (int i = 0; i <= n; i++) {
	// count = 0;
	// int j = i;
	// do {
	// if ((j & 1) == 1)
	// count++;
	// j = j >> 1;
	// } while (j != 0);
	//
	// ret[i] = count;
	// }
	// return ret;
	// }

	public int uniquePaths(int m, int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[j] = dp[j - 1] + dp[j];
			}
		}
		return dp[n - 1];
	}

	public int uniquePaths2(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		if (m == 0)
			return 0;
		int n = obstacleGrid[0].length;
		if (n == 0)
			return 0;
		int[][] dp = new int[m][n];
		for (int j = 0; j < n; j++) {
			if (obstacleGrid[0][j] != 1) {
				dp[0][j] = 1;
			} else {
				break;
			}
		}
		for (int i = 0; i < n; i++) {
			if (obstacleGrid[i][0] != 1) {
				dp[i][0] = 1;
			} else {
				break;
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] != 1) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				} else {
					dp[i][j] = 0;
				}
			}
		}

		return dp[m - 1][n - 1];
	}

	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int len = 0;
		int[] dp = new int[nums.length];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > dp[len]) {
				dp[++len] = nums[i];
			} else {
				int index = search(dp, len, nums[i]);
				dp[index] = nums[i];
			}
		}
		return len + 1;
	}

	public int search(int[] dp, int len, int num) {
		int start = 0;
		while (start <= len) {
			int mid = (start + len) / 2;
			if (dp[mid] == num) {
				return mid;
			} else if (dp[mid] < num) {
				start = mid + 1;
			} else {
				len = mid - 1;
			}
		}
		return start;
	}
}
