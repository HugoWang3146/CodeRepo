package wh.code.java.algorithms;

import java.util.ArrayList;
import java.util.Scanner;

public class CharacterRecognition {

	/*
	 * PROBLEM:
	 * character recognition->matrix recognition with minimum elements
	 * 
	 * METHOD:
	 * DP(dynamic programming) with memory, Search
	 * 
	 * EXPLANATION:
	 * The element (i,j) can divide matrices to two parts according the value of the element(0 or 1).
	 * What we want to do is find a set of element which can divide each matrix from another matrix.
	 * K matrices ->combination=(K-1)*K/2.
	 * Using binary array to represent if a set of element can distinguish the two of matrices，using dp with memory to find the minimum answer.
	 */
	public static int N = 0;	//matrix row number
	public static int M = 0;	//matrix column number
	public static int K = 0;	//matrix number
	public static int Combination = 0; //combination

	//a data structure to store a set of elements' info
	static class Node {
		int[] arr = null;
		int lastIndex = 0;

		public Node(int[] arr, int lastIndex) {
			this.arr = arr;
			this.lastIndex = lastIndex;
		}
	}

	public static void main(String[] args) {
		int i = 0, j = 0;
		//get input data
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String[] str1Arr = str1.split(" ");

		N = Integer.valueOf(str1Arr[0]);
		M = Integer.valueOf(str1Arr[1]);
		K = Integer.valueOf(str1Arr[2]);
		Combination = (K - 1) * K / 2;
		
		//store matrix data to a two-dimensional array:int[][] blocks
		int[][] blocks = new int[K][];
		for (i = 0; i < K; i++) {
			blocks[i] = new int[N * M];
		}
		String strTemp = "";
		for (i = 0; i < K;) {
			String s = sc.nextLine();
			if (s.isEmpty()) {
				for (j = 0; j < N; j++) {
					strTemp += sc.nextLine();
				}
				for (int m = 0; m < N * M; m++) {
					blocks[i][m] = Integer.valueOf(String.valueOf(strTemp.charAt(m)));
				}
				strTemp = "";
				i++;
			}
		}
		sc.close();
		//store each element's combination info to a array:int[][] dps
		int[][] dps = new int[N * M][];
		for (i = 0; i < N * M; i++) {
			dps[i] = new int[Combination];
		}
		int count = 0;
		for (i = 0; i < N * M; i++) {
			for (j = 0; j < (2 ^ Combination); j++) {
				for (int m = j + 1; m < K; m++) {
					dps[i][count] = blocks[j][i] ^ blocks[m][i];
					count++;
				}
			}
			count = 0;
		}
		//dp,search for the best solution
		ArrayList<Node> currentList = new ArrayList<Node>();
		for (i = 0; i < N * M; i++) {
			int[] dpTemp = dps[i];
			int res = 0;
			for (int num : dpTemp) {
				res += num;
			}
			if (res == Combination) {
				System.out.println(1);//print result
				return;
			}
			currentList.add(new Node(dpTemp, i));
		}
		ArrayList<Node> tempList = new ArrayList<Node>();
		tempList.addAll(currentList);
		currentList.clear();
		for (i = 2; i <= N * M; i++) {
			for (Node node : tempList) {

				for (j = node.lastIndex + 1; j < N * M; j++) {
					int[] dpTemp = dps[j].clone();
					int res = 0;
					for (int m = 0; m < Combination; m++) {
						dpTemp[m] = node.arr[m] | dpTemp[m];
						res += dpTemp[m];
					}
					if (res == Combination) {
						System.out.println(i);//print result
						return;
					}
					currentList.add(new Node(dpTemp, j));
				}
			}
			tempList.clear();
			tempList.addAll(currentList);
			currentList.clear();
		}
	}

}
