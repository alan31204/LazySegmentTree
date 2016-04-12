import java.util.Arrays;
import java.util.Scanner;

import org.w3c.dom.traversal.TreeWalker;

public class Main {
	//                0 1  2  3  4  5  6   7  8  9  10 11 12  13  14 15 16  17  18 19
//	static int[] a = {3,46,4,325,45,535,8,545,46,2,564,3, 665,6,  37,32,323,757,69,6};
	static final int N = 10000,Q = 10000;
	static int[] a = new int[N];
	
	static int updateStart,updateEnd;
	static void generateRandom() {
		updateStart = (int) (Math.random()*N);
		updateEnd = (int) (Math.random()*N);
		if(updateStart > updateEnd) {
			int temp = updateStart;
			updateStart = updateEnd;
			updateEnd = temp;
		}
	}

	
	public static void main(String[] args) {
		System.out.println("Click 1 to compare random result with Segment tree and Naive Array structure, "
				+ "other number key to implement your own array for test!");
		Scanner input=new Scanner(System.in);
		if(input.nextInt()==1){
			for(int i = 0; i < N; ++i){
				a[i] = (int) (Math.random()*2000-1000);
				}
		SegmentTree tree=new SegmentTree(a);
		NaiveArray t = new NaiveArray(a);
		for(int i = 0; i < Q; ++i) {
			generateRandom();
			int v = (int) (Math.random()*2000-1000);	//random the value that we need to update
			t.update(updateStart, updateEnd, v);
			tree.update(updateStart,updateEnd,v);
			generateRandom();
			if(t.queryMax(updateStart, updateEnd) != tree.queryMax(updateStart, updateEnd))
				System.out.println("problems with max");
			if(t.queryMin(updateStart, updateEnd) != tree.queryMin(updateStart, updateEnd))
				System.out.println("problems with min");
			if(t.querySum(updateStart, updateEnd) != tree.querySum(updateStart, updateEnd))
				System.out.println("problems with sum");
		}
			System.out.println("Done!");
		}
		else {
			System.out.println("Array size with 10!");
			int [] array=new int[10];
			for(int i=0;i<10;i++){
				System.out.println("Give the number to put into the array");
				array[i]=input.nextInt();
			}
			System.out.println("Give the update range of the front, end and the value you want to update, everythings starts at index 0!");
			SegmentTree tree=new SegmentTree(array);
			int a=input.nextInt();
			int b=input.nextInt();
			int c=input.nextInt();
			tree.update(a, b, c);
			System.out.println("Now the query range of the front and end, everythings starts at index 0");
			int d=input.nextInt();
			int e=input.nextInt();
			System.out.println("Max element of the range: "+tree.queryMax(d, e));
			System.out.println("Min element of the range: "+tree.queryMin(d, e));
			System.out.println("Total Sum of the range: "+tree.querySum(d, e));
		}
		
	}
}
