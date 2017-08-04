import java.io.File;
import java.io.FileNotFoundException;
/**
 * Maze크래스틑 Test하는 메소드
 * @author 원수현
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
	 * Maze클래스를 이용하여 미로를 찾는 메소드
	 * 미로를 출력하여 미로의 이동을 표시
	 * 지나온 좌표와 지나온 길의 개수를 표시해주고
	 * 총 경로의 개수를 표시해준다.
	 * @param file	입력받을 파일(미로가 저장된)
	 * @throws FileNotFoundException
	 */
	public static void getDistanceAndPathOfmaze(File file) throws FileNotFoundException{
		Maze maze = new Maze();
		maze.makeMaze(file);		//map을 만들고
		maze.StartAndExit();		//시작위치를 읽어온다.
		maze.findMaze(maze.current_X, maze.current_Y);	//미로 찾기 수행
		maze.getPath();				//경로의 갯수 출력
	}
	
	/**
	 * Maze클래스를 이용하여 미로를 찾는 메소드
	 * 총 경로의 개수를 표시해준다.
	 * @param file	//입력받을 파일(미로가 저장된)
	 * @throws FileNotFoundException
	 */
	public static void getPathOfMaze(File file) throws FileNotFoundException{
		Maze maze2 = new Maze();
		maze2.makeMaze(file);		//map을 만들고
		maze2.printMaze();			//map을 출력한다.
		maze2.StartAndExit();		//시작위치를 읽어온다.
		maze2.findPaths(maze2.current_X, maze2.current_Y);	//미로 찾기를 수행
		maze2.getPath();	//경로의 갯수 출력
	}
	
}
