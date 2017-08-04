import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <미로에서 가능한 모든 경로 찾기>
 * 주어진 미로의 시작 위치에서 탈출 위치까지 여러 개의 경로가 존재할 때 
 * 사이클을 형성하지 않는 가능한 모든 경로를 찾는다.
 * 주어진 파일의 첫째 줄은 사각형으로 주어진 미로의 행과 열의 수
 * 다음은 미로, 미로에서
 * # : 통과할 수 없음
 * ' '(스페이스) : 통과 가능
 * S : 시작점
 * E : 탈출점
 * @author 원수현
 *
 */
public class Maze {

	//필드
	private String[][] maze;	// 미로
	private int row;	//행
	private int col;	//열
	private int[] start = new int[2]; //스타트 지점을 저장하는 배열
	private int[] exit = new int[2];  //출구 지점을 저장하는 배열

	int current_X;	//현재 위치 x좌표
	int current_Y;	//현재 위치 y좌표

	private int distance;	//지나온 길의 개수.
	private int paths=0;	//경로의 개수. (number of paths)
	private ArrayList<String> coordinateOfPaths = new ArrayList<String>();	//지나온 길의 좌표들을 저장하는 list ex)(1,2)

	//메소드
	/**
	 * 미로에서 경로를 찾아 찾은 경로를 printMaze를 이용해 보여주고
	 * 이동한 좌표와 이동횟수를출력해준다. (재귀 함수)
	 * @param current_X 현재 X좌표
	 * @param current_Y 현재 Y좌표
	 */
	public void findMaze(int current_X, int current_Y){

		//출구를 찾았을 때.
		//경로의 개수를 저장.(path++)
		//list에 있는 좌표들(지나온 길)을 출력해 준다.
		if(!coordinateOfPaths.isEmpty()) {
			if(current_X == exit[0] && current_Y == exit[1]){	//출구를 찾았을 때.
				this.paths++;	//경로 개수 추가.
				getDistance();	//이동 횟수와 이동 좌표들을 출력.
				maze[exit[0]][exit[1]]="E"; //.으로 표시되어있을 출구를 E로 만들어준다.
				printMaze();				//미로 출력
			}
		}

		//시작위치가 아니라면 '.'값을 넣어준다.
		if(!maze[current_X][current_Y].equals("S")){
			maze[current_X][current_Y] = ".";
			//지나간 좌표 저장
			coordinateOfPaths.add("("+(current_X+1)+","+(current_Y+1)+")"+" ");
		}
		// 길이 있으면 이동하는 부분
		//상
		if(current_X>=1 && current_X < row && canMove(current_X-1, current_Y))
				findMaze(current_X-1, current_Y);
		//하
		if(current_X>=0 && current_X < row-1 && canMove(current_X+1, current_Y))
				findMaze(current_X+1, current_Y);
		//좌
		if(current_Y>=1 && current_Y<col && canMove(current_X, current_Y-1))
				findMaze(current_X, current_Y-1);
		//우
		if(current_Y>=0 && current_Y<col-1 && canMove(current_X, current_Y+1))
				findMaze(current_X, current_Y+1);

		//길이 없어서 뒤로 돌아왔을 때.
		maze[current_X][current_Y] = " "; //뒤로 돌아오면 그 장소를 다시 공백으로 만들어준다.
		if(!coordinateOfPaths.isEmpty())  
			coordinateOfPaths.remove(coordinateOfPaths.size()-1);//지나온 길들의 좌표에서도 없애줌
	}

	/**
	 * 미로찾기를 수행하고 경로를 찾을 때마다 경로의 개수(path)증가 시켜준다. (재귀 함수)
	 * @param current_X 현재 X좌표
	 * @param current_Y 현재 Y좌표
	 */
	public void findPaths(int current_X, int current_Y){
		//시작위치가 아니라면 '.'값을 넣어준다.
		if(!maze[current_X][current_Y].equals("S")){
			maze[current_X][current_Y] = "."; 
		}
		
		//출구를 찾았을 때.
		//출구를 찾으면 경로 저장.
		if(current_X == exit[0] && current_Y == exit[1]){
			this.paths++;
			maze[exit[0]][exit[1]]="E"; //.으로 표시되어있을 출구를 E로 만들어준다.
		}
		// 길이 있으면 이동하는 부분
		//상
		if(current_X>=1 && current_X < row && canMove(current_X-1, current_Y))
				findPaths(current_X-1, current_Y);
		//하
		if(current_X>=0 && current_X < row-1 && canMove(current_X+1, current_Y))
				findPaths(current_X+1, current_Y);
		//좌
		if(current_Y>=1 && current_Y<col && canMove(current_X, current_Y-1))
				findPaths(current_X, current_Y-1);
		//우
		if(current_Y>=0 && current_Y<col-1 && canMove(current_X, current_Y+1))
				findPaths(current_X, current_Y+1);

		//길이 없어서 뒤로 돌아왔을 때.
		maze[current_X][current_Y] = " ";	//뒤로 돌아오면 그 장소를 다시 공백으로 만들어준다.
	}
	
	/**
	 * 미로에서 이동할 수 있는 부분(공백과 E)인지 아닌지를 결정해주는 메소드
	 * @param current_X 이동하려고 하는 X좌표
	 * @param current_Y 이동하려고 하는 Y좌표
	 * @return 이동할수 있으면 true, 아니면 false
	 */
	private boolean canMove(int current_X, int current_Y){
		if(maze[current_X][current_Y].equals(" ") || maze[current_X][current_Y].equals("E"))
			return true;
		return false;
	}

	//파일에서 미로를 받아와서 미로(maze)를 만들어주는 메소드.
	public void makeMaze(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		row = in.nextInt();
		col = in.nextInt();
		maze = new String[row][col];
		int i=0;
		String line;
		line = in.nextLine();
		while(in.hasNextLine()){
			line = in.nextLine();
			this.maze[i]=line.split("");
			i++;
		}
		in.close();
	}

	//미로를 출력하는 메소드
	public void printMaze(){
		for(int i=0; i<row; i++){
			for(int j =0; j<col; j++){
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	//시작 지점과 출구지점을 알아내는 메소드
	public void StartAndExit(){
		for(int i = 0 ; i<row; i++){
			for(int j = 0; j<col; j++){
				if(maze[i][j].equals("S")){
					this.start[0]=i;
					this.start[1]=j;
				}
				if(maze[i][j].equals("E")){
					this.exit[0]=i;
					this.exit[1]=j;
				}
			}
		}
		//현재 위치에 첫 시작점을 지정해 준다.
		this.current_X = start[0];	
		this.current_Y = start[1];
	}

	//경로의 개수를 출력하는 메소드
	public void getPath(){
		System.out.println("number of Paths : "+ paths);
		System.out.println();
	}

	//지나온 좌표들과 (coordinateOfPaths)
	//와 이동횟수(distance)를 출력하는 메소드
	public void getDistance(){
		distance = coordinateOfPaths.size();
		System.out.print("Distance : "+ distance+"\t");	//이동횟수 출력
		for(int i = 0; i<coordinateOfPaths.size(); i++)
			System.out.print(coordinateOfPaths.get(i)+" ");	// 지나온 좌표 출력
		System.out.println();
	}
}
