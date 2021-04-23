package boj.m4w4_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {
	
	static int L, C;
	static StringBuilder sb;
	static char[] cand;
	static char last;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cand = new char[C];
		int[] count = new int[26];
		
		String input = br.readLine();
		for (int i = 0; i < C; i++) {
			int a = (int) (input.charAt(2*i) - 'a');
			count[a] = 1;
		}
		
		int idx = 0;
		for (int i = 0; i < 26; i++) {
			if(count[i] == 1) {
				cand[idx++] = (char) ('a' + i);
				if(i==0 || i==4 || i==8 || i==14 || i==20) last = (char) ('a' + i);
			}
		}
		
		select(0, 0, 0, 0, 0);
		System.out.print(sb.toString());
	}
	
	static void select(int cnt, int now, int selected, int a, int b) {		
		if(cnt == L) {
			if(a >= 1 && b >= 2) {
				for (int i = 0; i < C; i++) {
					if((selected & 1 << i) > 0) sb.append(cand[i]);
				}
				sb.append("\n");
			}
			return;
		}
		if(now == C) return;
		if(cand[now] > last && a < 1) return;
		
		// 선택
		selected |= 1 << now;
		int ch = cand[now];
		if(ch == 'a' || ch == 'i' || ch == 'e' || ch=='o' || ch=='u') {
			select(cnt+1, now+1, selected, a+1, b);
		}
		else select(cnt+1, now+1, selected, a, b+1);
		
		// 미선택
		selected ^= 1 << now;
		select(cnt, now+1, selected, a, b);
		
	}

}
