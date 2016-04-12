/* Author: Po-Chun Chiu */

public class SegmentTree {
	int size;
	private int[] max;
	private int[] min;
	private int[] sum;

	private int[] lazy;


	public SegmentTree(int[] array){
		size = array.length;
		max=new int[array.length*4];
		min=new int[array.length*4];
		sum=new int[array.length*4];
		lazy=new int[array.length*4];
		buildTree(array,1,0,size-1);
	}

	private void buildTree(int[] array, int position, int front, int end){
		//Basis case for returning the elements itself
		if(front==end){
			max[position]=array[front];
			min[position]=array[front];
			sum[position]=array[front];
			return;
		}
		//Building trees by dividing into two subtrees
		buildTree(array, position*2,front,(front+end)/2);
		buildTree(array, position*2+1,(front+end)/2+1,end);

		recalc(position,front,end);
	}

	private void recalc(int position, int front, int end) {
		max[position]=Math.max(getMax(position*2),getMax(position*2+1) );
		min[position]=Math.min(getMin(position*2), getMin(position*2+1));
		sum[position]=getSum(position*2,front,(front+end)/2)+getSum(position*2+1,(front+end)/2+1,end);
	}

	private int getMax(int position){
		return max[position]+lazy[position];
	}

	private int getMin(int position){
		return min[position]+lazy[position];
	}

	private int getSum(int position,int front,int end){
		return sum[position]+lazy[position]*(end-front+1);
	}

	public int queryMax(int queryFront, int queryEnd) {
		return queryMax(1,0,size-1,queryFront,queryEnd);
	}

	private int queryMax(int position,int front,int end, int queryFront, int queryEnd){
		//Case for entirely inclusive
		if(front>=queryFront&&queryEnd>=end){
			return getMax(position);
		}
		//Case for entirely exclusive
		if(end<queryFront||queryEnd<front){
			return Integer.MIN_VALUE;
		}
		propagate(position,front,end);
		int leftAns=queryMax(position*2,front,(front+end)/2,queryFront,queryEnd);
		int rightAns=queryMax(position*2+1,(front+end)/2+1,end,queryFront,queryEnd);
		return Math.max(leftAns, rightAns);
	}

	public int queryMin(int queryFront, int queryEnd) {
		return queryMin(1,0,size-1,queryFront,queryEnd);
	}

	private int queryMin(int position,int front,int end, int queryFront, int queryEnd){
		//Case for entirely inclusive
		if(front>=queryFront&&queryEnd>=end){
			return getMin(position);
		}
		//Case for entirely exclusive
		if(end<queryFront||queryEnd<front){
			return Integer.MAX_VALUE;
		}
		propagate(position,front,end);
		int leftAns=queryMin(position*2,front,(front+end)/2,queryFront,queryEnd);
		int rightAns=queryMin(position*2+1,(front+end)/2+1,end,queryFront,queryEnd);
		return Math.min(leftAns, rightAns);
	}
	public int querySum(int queryFront, int queryEnd) {
		return querySum(1,0,size-1,queryFront,queryEnd);
	}

	private int querySum(int position,int front,int end, int queryFront, int queryEnd){
		//Case for entirely inclusive
		if(front>=queryFront&&queryEnd>=end){
			return getSum(position,front,end);
		}
		//Case for entirely exclusive
		if(end<queryFront||queryEnd<front){
			return 0;
		}
		propagate(position,front,end);
		int leftAns=querySum(position*2,front,(front+end)/2,queryFront,queryEnd);
		int rightAns=querySum(position*2+1,(front+end)/2+1,end,queryFront,queryEnd);
		return leftAns+rightAns;
	}


	private void propagate(int position,int front, int end){
		lazy[position*2]+=lazy[position];
		lazy[position*2+1]+=lazy[position];
		max[position]=getMax(position);
		min[position]=getMin(position);
		sum[position]=getSum(position,front,end);
		lazy[position]=0;
		//Set the propagation to 0 so that it will not propagate the value again and again
	}

	public void update(int updateFront, int updateEnd, int value){
		update(1,0,size-1,updateFront,updateEnd,value);
	}

	private void update(int position, int front, int end, int updateFront,int updateEnd,int value){
		//Case for entirely inclusive
		if(front>=updateFront&&updateEnd>=end){
			lazy[position]+=value;
			return;
		}
		//Case for entirely exclusive
		if(end<updateFront||updateEnd<front){
			return;
		}
		propagate(position,front,end);
		update(position*2,front,(front+end)/2,updateFront,updateEnd,value);
		update(position*2+1,(front+end)/2+1,end,updateFront,updateEnd,value);
		recalc(position,front,end);
	}
}
