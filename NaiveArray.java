
public class NaiveArray {
	int[] a;
	
	public NaiveArray(int[] array) {
		a = array;
	}
	
	public int queryMax(int queryFront, int queryEnd) {
		int ans = Integer.MIN_VALUE;
		for(int i = queryFront; i <= queryEnd; ++i)
			ans = Math.max(ans, a[i]);
		return ans;
	}
	
	public int queryMin(int queryFront, int queryEnd) {
		int ans = Integer.MAX_VALUE;
		for(int i = queryFront; i <= queryEnd; ++i)
			ans = Math.min(ans, a[i]);
		return ans;
	}
	
	public int querySum(int queryFront, int queryEnd) {
		int ans = 0;
		for(int i = queryFront; i <= queryEnd; ++i)
			ans += a[i];
		return ans;
	}
	
	public void update(int c, int b, int v) {
		for(int i = c; i <= b; ++i)
			a[i] += v; 
	}
}
