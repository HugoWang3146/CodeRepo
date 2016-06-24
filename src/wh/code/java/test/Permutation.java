package wh.code.java.test;

import java.util.ArrayList;

public class Permutation {
	public static void main(String[] args) {
		Permutation p=new Permutation();
		String s=p.getPermutation(5, 16);
		System.out.println(s);
	}

	public String getPermutation(int n, int k) {
		if (n <= 0 || getCount(n) < k)
			return null;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			arr.add(i + 1);
		}
		StringBuilder sb = new StringBuilder();
		k--;
		for (int i = 0; i < n; i++) {
			int c = getCount(n - 1 - i);
			int x = k / c;
			sb.append(arr.get(x));
			arr.remove(x);
			k -= x * c;
		}
		return sb.toString();

	}

	public int getCount(int n) {
		int count = 1;
		for (int i = n; i > 1; i--)
			count *= i;
		return count;
	}

}
