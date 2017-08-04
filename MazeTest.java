import java.io.File;
import java.io.FileNotFoundException;
/**
 * Mazeũ�����z Test�ϴ� �޼ҵ�
 * @author ������
 *
 */
public class MazeTest {
	public static void main(String[] args) throws FileNotFoundException {

		File file1 = new File("maze1.txt");
		getDistanceAndPathOfmaze(file1);
		
		File file2 = new File("maze2.txt");
		getPathOfMaze(file2);
		
		File file3 = new File("maze3.txt");
		getPathOfMaze(file3);
		
		File file4 = new File("maze4.txt");	
		getPathOfMaze(file4);
	}
	
	/**
	 * MazeŬ������ �̿��Ͽ� �̷θ� ã�� �޼ҵ�
	 * �̷θ� ����Ͽ� �̷��� �̵��� ǥ��
	 * ������ ��ǥ�� ������ ���� ������ ǥ�����ְ�
	 * �� ����� ������ ǥ�����ش�.
	 * @param file	�Է¹��� ����(�̷ΰ� �����)
	 * @throws FileNotFoundException
	 */
	public static void getDistanceAndPathOfmaze(File file) throws FileNotFoundException{
		Maze maze = new Maze();
		maze.makeMaze(file);		//map�� �����
		maze.StartAndExit();		//������ġ�� �о�´�.
		maze.findMaze(maze.current_X, maze.current_Y);	//�̷� ã�� ����
		maze.getPath();				//����� ���� ���
	}
	
	/**
	 * MazeŬ������ �̿��Ͽ� �̷θ� ã�� �޼ҵ�
	 * �� ����� ������ ǥ�����ش�.
	 * @param file	//�Է¹��� ����(�̷ΰ� �����)
	 * @throws FileNotFoundException
	 */
	public static void getPathOfMaze(File file) throws FileNotFoundException{
		Maze maze2 = new Maze();
		maze2.makeMaze(file);		//map�� �����
		maze2.printMaze();			//map�� ����Ѵ�.
		maze2.StartAndExit();		//������ġ�� �о�´�.
		maze2.findPaths(maze2.current_X, maze2.current_Y);	//�̷� ã�⸦ ����
		maze2.getPath();	//����� ���� ���
	}
	
}
