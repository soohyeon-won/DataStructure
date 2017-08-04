package week8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StackTest {
	public static void main(String[] args) throws FileNotFoundException {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		String command;
		int item;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a command: push,pop,peek,size,b(find big) "
				+ "m(test matching parenthesis), p(rint), or q(uit)");
		System.out.print("> ");
		command = in.next();
		while (!command.equals("q")) {
			if (command.equals("push")) {
				item = in.nextInt();
				stack.push(item);
			}
			else if (command.equals("pop"))
				stack.pop();
			else if (command.equals("peek")) {
				item = stack.peek();
				System.out.println("Top element is " + item);
			}
			else if (command.equals("size"))
				System.out.println("size: " + stack.size());
			else if (command.equals("p"))
				System.out.println(stack);
			else if (command.equals("m"))
				checkParenthesis();
			else if (command.equals("b")) {
				int[] data = {10, 5, 7, 2, 9, 15, 12, 3, 5, 14};
				int[] big = getLastAsBig(data);
				int i;
				System.out.print("[");
				for (i = 0; i < big.length-1; i++)
					System.out.print(big[i] + ", ");
				System.out.println(big[i] + "]");
			}
			System.out.print("> ");
			command = in.next();
		}
		System.out.println("Commands terminated.");
		in.close();
	}

	//그 원소보다 큰 원소를 왼쪽부터 찾아 해당 인덱스를 저장하는 배열
	//lastAsBic[]배열을 생성하여 출력
	//원소보다 큰 값이 없으면 -1을 저장한다.
	private static int[] getLastAsBig(int[] data) {
		int dataLangth = data.length;
		int[] lastAsBic = new int[dataLangth];
		//모든 원소를 -1로 초기화
		for(int i =0 ; i<dataLangth; i ++ ){
			lastAsBic[i] = -1;
		}
		//큰 수가 있을 때 인덱스가 들어가게함
		for(int i =0 ; i<dataLangth; i++){
			for(int j = 0 ; j<i ; j++){
				if(data[i] < data[j]){
					lastAsBic[i] = j;
				}
			}
		}
		return lastAsBic;
	}

	//괄호를 검사하는 메소드
	private static void checkParenthesis() throws FileNotFoundException {

		LinkedStack<String> stack = new LinkedStack<String>();
		while(!stack.isEmpty()){
			stack.pop();
		}
		Scanner in = new Scanner(new File("input.txt"));
		String line;
		String[] data;
		while(in.hasNextLine()){
			//스택 비우기.
			while(!stack.isEmpty()){
				stack.pop();
			}
			line = in.nextLine();
			System.out.println(line);
			data = line.split("");
			for(int i = 0 ;i <data.length; i++){
				if(data[i].equals("(")){
					stack.push("(");
				}
				else if(data[i].equals(")")){
					stack.pop();
				}
			}
			if(stack.size()==0){
				System.out.println("parentheses matched.");
			}
			else{
				System.out.println("Closing parenthesis is not matched.");
			}
		}
	}

}
