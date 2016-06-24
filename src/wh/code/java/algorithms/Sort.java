package wh.code.java.algorithms;

public class Sort {
	
	private int[] arr;
	
	public Sort(int[] arr){
		this.arr=arr;
	}
	
	public static void quickSort(int[] arr,int p,int r){
		if(p<r){
			int q=partition(arr,p,r);
			quickSort(arr,p,q-1);
			quickSort(arr,q+1,r);
		}
	}
	public static int partition(int[] arr,int p,int r){
		int i=p-1;
		int x=arr[r];
		int temp=0;
		for(int j=p;j<r;j++){
			if(arr[j]<x){
				i++;
				temp=arr[j];
				arr[j]=arr[i];
				arr[i]=temp;
			}
		}
		i++;
		temp=arr[r];
		arr[r]=arr[i];
		arr[i]=temp;
		return i;
	}
	
	public void print(){
		for(int i:arr){
			System.out.println(i);
		}
	}
	
	public static int search(int[] a,int left,int right,int value){
		if(a.length==0||left>right)
			return -1;
		int mid=(right+left)/2;
		if(a[mid]==value){
			return mid;
		}
		else if(a[mid]>value){
			left=mid+1;
		}else{
			right=mid-1;
		}
		return search(a,left,right,value);
	}
	
	public static void main(String[] args){
		int[] arr={3,4,3,2,1};
		Sort s=new Sort(arr);
		Sort.quickSort(s.arr,0,s.arr.length-1);
		s.print();
	}
}
