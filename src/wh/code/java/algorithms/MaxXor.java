package wh.code.java.algorithms;

import java.util.Scanner;

public class MaxXor {
	
	/*
	 * PROBLEM:
	 * maximum xor with prefix and suffix
	 * 
	 * METHOD:
	 * TireTree,xor,search
	 * 
	 * EXPLANATION:
	 * To find the maximum xor with prefix and suffix,which means the binary bits of the xor result of prefix array and the xor result of suffix array should be different  as many as possible.
	 * So use a tiretree structure to store the prefix's binary bits ,each path is a prefix value.
	 * Then find the path which is most different from suffix,get the max result.
	 */

	public static int N = 0;	//the length of array
	
	private static int index = 1;	//the index of node in tree
	
	//a data structure of TireNode
	static class TireNode {
		int[] next = { -1, -1 };
	}
	
	//function:insert a node to tree
	public static void insert(TireNode[] tree, long num) {
		int m, n = 0;
		for (int i = 40; i >= 0; i--) {
			if ((((1L) << i) & num) != 0) {
				m = 1;
			} else {
				m = 0;
			}
			if (tree[n].next[m] == -1) {
				tree[index] = new TireNode();
				tree[n].next[m] = index++;
			}
			n = tree[n].next[m];
		}
	}
	
	//function:find the max xor result with this suffix(num)
	public static long find(TireNode[] tree, long num) {
		int m, n = 0;
		long res = 0;
		for (int i = 40; i >= 0; i--) {
			if ((((1L) << i) & num) != 0) {
				m = 0;
			} else {
				m = 1;
			}
			if (tree[n].next[m] == -1)
				m ^= 1;
			res = (res << 1) + m;
			n = tree[n].next[m];
		}
		return res;
	}
	
	public static void main(String[] args) {
		//get input data
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		sc.close();
		N = Integer.valueOf(str1);
		String[] sArr = str2.split(" ");
		long[] lArr = new long[N];
		long cur = 0L;
		for (int i = 0; i < N; i++) {
			lArr[i] = Long.valueOf(sArr[i]);
			cur ^= lArr[i];
		}
		//init tree
		TireNode[] tree = new TireNode[N * 41];
		tree[0] = new TireNode();
		insert(tree, 0L);
		//find the answer
		Long tmp = 0L, ans = 0L;
		for (int i = 0; i < N; i++) {
			tmp ^= lArr[i];
			cur ^= lArr[i];
			insert(tree, tmp);
			ans = Math.max(ans, find(tree, cur) ^ cur);
		}
		//print result
		System.out.println(ans);
	}
}
