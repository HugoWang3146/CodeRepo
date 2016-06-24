package wh.code.java.algorithms;

import java.util.Arrays;
import java.util.HashMap;

public class Temp {
	
	public int[] arr;
	
//	public static void main(String[] args){
//		
//	}
	public static void quickSort(int[] a,int p,int r){
		int q=partition(a,p,r);
		quickSort(a,p,q-1);
		quickSort(a,q+1,r);
	}
	
	public static int partition(int[] a,int p,int r){
		int i=p-1;
		int x=a[r];
		int temp=0;
		for(int j=p;j<r;j++){
			if(a[j]<x){
				i++;
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
			}
		}
		i++;
		temp=a[i];
		a[i]=a[r];
		a[r]=temp;
		
		return i;
	}
	
	static class Node{
		String s="";
		int[] arr=null;
		int c=0;
		int lastIndex=0;
		public Node(String s,int[] arr,int lastIndex,int c){
			this.s=s;
			this.arr=arr;
			this.lastIndex=lastIndex;
			this.c=c;
		}
	}
	public static void main(String[] args){
		int[] a={1,2,3};
		int[][] b=new int[1][];
		b[0]=a;
		
		int[] c=b[0];
		
		c[0]=11111;
		
		System.out.println(Arrays.toString(a));
		
		
	}
	
	
	
	public void longestSubStr(String s){
		
		s="101110101001010100001111100011";
		char[] cArr=s.toCharArray();
		int preMaxOne=0;
		int preMaxZero=0;
		char current=1;
		for(int i=0;i<cArr.length-1;i++){
			if(cArr[i]==cArr[i+1]){
				current++;
			}else{
				if(cArr[i]=='1'){
					preMaxOne=preMaxOne>current?preMaxOne:current;
				}
				if(cArr[i]=='0'){
					preMaxZero=preMaxZero>current?preMaxZero:current;
				}
				current=1;
			}
		}
		int max=Math.max(preMaxZero, preMaxOne);
		
		System.out.println(max);
		
	}
	
public void SubStr(String s){
		
		s="1011101010010101010001111100011";
		char[] cArr=s.toCharArray();
		int preMax=0;
		char current=0;
		for(int i=0;i<cArr.length-1;i++){
			if(cArr[i]!=cArr[i+1]){
				current++;
			}else{
				preMax=Math.max(preMax, current);
				current=0;
			}
		}
		
		System.out.println(preMax);
		
	}
	
	
}
