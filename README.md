# SWExpertAcademy_D4_Java_8275

## SW Expert Academy D4 8275. 햄스터

### 1. 문제설명

출처: https://swexpertacademy.com/main/code/problem/problemList.do

input으로 `N, X, M`이 들어오고 다음 `M`개의 줄에 `li, ri, si`가 들어온다. `N`햄스터 우리의 갯수, `X`는 하나의 우리에 있는 햄스터의 최대 수, `M`는 이어서 나올 기록의 갯수이다. 기록에서 `li`는 카운트를 시작한 왼쪽 우리, `ri`는 카운트를 끝마친 오른쪽 우리이며 `si`는 `il`부터 `ri`우리 속에 있는 햄스터의 총 수이다.

위의 조건이 들어올 때 `N`개의 우리에 있는 햄스터의 경우를 아래 조건의 맞게 출력하는 문제.

[입력]
> 첫 번째 줄에 테스트 케이스의 수 `T`가 주어진다.
> 각 테스트 케이스의 첫 번째 줄에는 세 정수 `N, X, M(1 ≤ N ≤ 6, 1 ≤ X, M ≤ 10)`이 공백 하나로 구분되어 주어진다.
> 다음 `M`개의 줄의 `i`번째 줄에는 세 정수 `li, ri, si(1 ≤ li ≤ ri ≤ N, 0 ≤ si ≤ 60)`가 공백 하나로 구분되어 주어진다.
> 이는 `li`번 우리에서 `ri`번 우리까지의 햄스터 수를 세었더니 `si`마리였다는 것을 나타낸다.

[출력]
> 각 테스트 케이스마다 `#x`(`x`는 테스트케이스 번호를 의미하며 `1`부터 시작한다)를 출력하고,
> 각 테스트 케이스마다 모든 기록을 만족하는 햄스터 수 배치가 있다면,
> `1`번 우리부터 `N`번 우리의 순서대로 우리에 있는 햄스터 수를 공백 하나로 구분하여 출력한다.
> 만약 가능한 방법이 여러 가지일 경우, 햄스터 수가 가장 많은 것을 출력한다.
> 그래도 가능한 방법이 여러 가지일 경우, 사전순으로 가장 빠른 것을 출력한다.
> 만약 모든 기록을 만족하는 햄스터 배치가 없다면, `-1`을 출력하도록 한다.
 

### 2. 풀이

`DFS`을 이용하여 길이 길이 `N`의 정수형 배열을 각 우리속의 햄스터의 수를 담는다고 생각하며 생겨날 수 있는 모든 조합을 만들었다. `n`번 반복하여 배열을 채우게 되면 `M`개의 조건과 일치 여부와 햄스터의 수가 가능한 최대 경우보다 많이 존재하는지 검사한다. 이 조건을 만족하게 되면 출력해줄 `result`를 결정하고 모든 검사가 끝난다면 그 배열을 return한다. `maxCount`라는 변수를 이용하여 햄스터의 총 마리수를 세었는데 이는 `-1`로 초기화가 되어있다. 만약 한번도 가능한 조합이 생성되지 않았다면 이는 그대로 `-1`의 값을 유지하고 있었을 것이다. 이럴 경우 `-1`을 출력해 모든 기록을 만족하는 햄스터 배치가 없을때의 출력양식을 맞춰 주었다.


```java
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
```
