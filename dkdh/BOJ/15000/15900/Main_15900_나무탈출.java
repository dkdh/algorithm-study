package boj.m4w4_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15900_나무탈출 {
	
	static class Node {
		int x;
		Node next;
		
		public Node(int x) {
			super();
			this.x = x;
		}
	}
	
	static int parity;
	static Node[] tree;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		tree = new Node[N+1];
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Node newNode = new Node(b);
			newNode.next = tree[a];
			tree[a] = newNode;
			
			newNode = new Node(a);
			newNode.next = tree[b];
			tree[b] = newNode;
		}
		
		parity = 0;
		visited = new boolean[N+1];
		
		dfs(1, 0);
		
		String result = parity==1 ? "Yes" : "No";
		System.out.print(result);
	}
	
	static void dfs(int x, int depth) {
		visited[x] = true;
		
		int cnt = 0;
		for (Node temp = tree[x]; temp != null; temp = temp.next) {
			cnt++;
			if(!visited[temp.x]) dfs(temp.x, depth+1);
		}
		
		if(cnt==1) {
			parity += depth;
			parity %= 2;
			return;
		}
		
	}

}
