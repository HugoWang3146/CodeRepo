package wh.code.java.test;

import java.util.LinkedList;
import java.util.List;

public class Test{
	public static void main(String[] args){
		Test s = new Test();
		String res = s.getPermutation(3, 1);
		System.out.println(res);
	}
	
	public String getPermutation(int n, int k) {
	    List<Integer> nums = new LinkedList<>();
	    for (int i = 1; i <= n; i++) {
	        nums.add(i);
	    }
	    String ret = "";
	    k--;
	    while (!nums.isEmpty()) {
	        int f = fact(n-1);
	        int i = k / f;
	        ret += nums.get(i).toString();
	        nums.remove(i);
	        k -= i*f;
	        n--;
	    }
	    return ret;
	}

	private int fact(int n) {
	    int ret = 1;
	    for (int i = 1; i <= n; i++) {
	        ret *= i;
	    }
	    return ret;
	}
}

