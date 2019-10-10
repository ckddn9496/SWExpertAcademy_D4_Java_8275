import java.util.Arrays;
import java.util.Scanner;

class Solution
{
	private static int N = 0;
	private static int X = 0;
	private static int M = 0;
	private static int[][] data;
	private static int[] result;
	private static int maxCount = -1;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			X = sc.nextInt();
			M = sc.nextInt();
			sc.nextLine();
			
			result = new int[N];
			data = new int[M][3];
			
			for (int i = 0; i < M; i++) {
				data[i][0] = sc.nextInt();
				data[i][1] = sc.nextInt();
				data[i][2] = sc.nextInt();
				sc.nextLine();
			}

			int[] numOfHamster = new int[N];
			maxCount = -1;
			dfs(0, numOfHamster);
			StringBuilder sb = new StringBuilder("#"+test_case+" ");
			if (maxCount == -1) {
				sb.append(-1);
			} else {
				for (int i : result) {
					sb.append(i+" ");
				}
			}
			System.out.println(sb.toString());
		}
	}
	
	static void dfs(int n, int[] num) {
		if (n == N) {
			if (maxCount < Arrays.stream(num).sum() && check(num)) {
				maxCount = Arrays.stream(num).sum();
				System.arraycopy(num, 0, result, 0, N);
			}
			return;
		}
		
		for (int i = 0; i <= X; i++) {
			num[n] = i;
			dfs(n+1, num);
		}
	}

	private static boolean check(int[] num) {
		boolean isCandidate = true;
		for (int i = 0; i < M; i++) {
			int start = data[i][0];
			int end = data[i][1];
			int count = data[i][2];
			
			for (int nIdx = start-1; nIdx < end; nIdx++) {
				count -= num[nIdx];
			}
			
			if (count != 0) {
				isCandidate = false;
				break;
			}
		}
		return isCandidate;
	}
	
}