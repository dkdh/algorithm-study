package boj.m4w4_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20055_컨테이너벨트위의로봇 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] durability = new int[2*N];
		int up = 0;
		int down = N-1;
		boolean[] robots = new boolean[2*N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 2*N; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}
		
		int broken = 0;
		int phase = 0;
		while(broken < K) {
			phase++;
			
			// 1. 벨트 한 칸 회전
			robots[down] = false;
			up = up > 0 ? up-1 : 2*N-1;
			down = down > 0 ? down-1 : 2*N-1;
			robots[down] = false;
			
			// 2. 로봇 이동
			int next = down;
			int cur = down - 1;
			for (int i = 0; i < N-1; ++i) {
				if(cur < 0) cur = 2*N - 1;
				if(robots[cur] && !robots[next] 
						&& durability[next] > 0) {
					robots[cur] = false;
					robots[next] = true;
					durability[next] -= 1;
					if(durability[next] == 0) broken++;
				}
				next = cur;
				cur--;
			}
			
			// 3. 로봇 올리기
			if(!robots[up] && durability[up] > 0) {
				robots[up] = true;
				durability[up] -= 1;
				if(durability[up] == 0) broken++;
			}
		}
		
		System.out.print(phase);
	}

}
