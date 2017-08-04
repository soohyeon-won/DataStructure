package week11;

import java.util.Scanner;

public class BSTTest {
	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>();
		String command;
		int data;

		Scanner input = new Scanner(System.in);
		System.out.println("Enter a command: i(insert), ir(insert by recurion),\n"
						+ "r(emove), rr(remove by recursion),\n"
						+ "s(earch), sr(search by recursion),\n"
						+ "inorder(traverse), p(rint), or q(uit)");

		while (true) {
			System.out.print("> ");
			command = input.next();
			if (command.equals("i")) {
				data = input.nextInt();
				if (tree.insert(data)) 
					System.out.println(data + " inserted.");
				else
					System.out.println(data + " is in the tree.");				
			}
			if (command.equals("ir")) {
				data = input.nextInt();
				if (tree.insertByRecursion(data)) 
					System.out.println(data + " inserted.");
				else
					System.out.println(data + " is in the tree.");				
			}
			else if (command.equals("rr")) {
				data = input.nextInt();
				if (tree.removeByRecursion(data))

					System.out.println(data + " removed.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("r")) {
				data = input.nextInt();
				if (tree.remove(data))
					System.out.println(data + " removed.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("s")) {
				data = input.nextInt();
				if (tree.search(data))
					System.out.println(data + " is in the tree.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("sr")) {
				data = input.nextInt();
				if (tree.searchByRecursion(data))
					System.out.println(data + " is in the tree.");
				else
					System.out.println("No such " + data + "!");
			}
			else if (command.equals("inorder"))
				tree.inorderTraverse();
			else if (command.equals("p"))
				tree.print();
			else if (command.equals("q")) {
				System.out.println("Commands terminated.");
				break;
			}
		}
		input.close();
	}

}
