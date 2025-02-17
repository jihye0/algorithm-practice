//package P10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String pipe=br.readLine();
		Stack<Character> stack =new Stack<>();
		int result=0;
		for(int i=0; i<pipe.length(); i++) {
			if(pipe.charAt(i)=='(') {
				stack.push('(');
			}else if(pipe.charAt(i)==')') {
				stack.pop();
			}
			if(i>0) {
				if(pipe.charAt(i-1)=='(' && pipe.charAt(i)==')') {
					result+=stack.size();
				}else if(pipe.charAt(i-1)==')' && pipe.charAt(i)==')') {
					result++;
				}
			}
		}
		System.out.println(result);
	}

}
