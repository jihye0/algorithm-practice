//package P9935;

import java.util.*;
import java.io.*;

public class Main {
	static String str;
	static String bomb;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		str=br.readLine();
		bomb=br.readLine();
//		while(str.contains(bomb)) {
//			str=str.replaceAll(bomb, "");
//		}
//		if(str.length()==0)
//			System.out.println("FRULA");
//		else System.out.println(str);
		
		Stack<Character> stack=new Stack<>();
		for(int i=0; i<str.length(); i++) {
			stack.add(str.charAt(i));
			if(stack.size()>=bomb.length()) {
				boolean flag=true;
				for(int j=0; j<bomb.length(); j++) {
					if(stack.get(stack.size()-1-j)!=bomb.charAt(bomb.length()-1-j)) {
						flag=false;
					}
				}
				if(flag) {
					for(int j=0; j<bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		if(stack.size()==0) System.out.println("FRULA");
		else {
			StringBuilder sb=new StringBuilder();
			for(Character c: stack) {
				sb.append(c);
			}
			System.out.println(sb);
		}
		
		
	}

}
