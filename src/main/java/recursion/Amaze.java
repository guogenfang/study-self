package recursion;

import java.util.Stack;

class Step{
    int x,y,d;
    public Step(int x,int y,int d) {
        this.x = x;//横坐标
        this.y = y;//纵坐标
        this.d = d;//方向
    }
}

public class Amaze {
	public static void main(String[] args) {
        // 迷宫定义
        int[][] maze = {{1,1,1,1,1,1,1,1,1,1},
                        {1,0,1,1,1,0,1,1,1,1},
                        {1,1,0,1,0,1,1,1,1,1},
                        {1,0,1,0,0,0,0,0,1,1},
                        {1,0,1,1,1,0,1,1,1,1},
                        {1,1,0,0,1,1,0,0,0,1},
                        {1,0,1,1,0,0,1,1,0,1},
                        {1,1,1,1,1,1,1,1,1,1}};
        int[][] move = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
        Stack<Step> s = new Stack<Step>();//存储
        int a = path(maze, move, s);
        //迭代显示路径
        while(!s.isEmpty()){
            Step step = s.pop();
            System.out.println(step.x+":"+step.y);
        }
    }
    public static int path(int[][] maze,int[][] move,Stack<Step> s){
        Step temp = new Step(1,1,-1); //起点
        s.push(temp);
        while(!s.isEmpty()){
            temp = s.pop();
            int x = temp.x;
            int y = temp.y;
            int d = temp.d+1;
            while(d<8){
                int i = x + move[d][0];
                int j = y + move[d][1];
                if(maze[i][j] == 0){    //该点可达
                    temp = new Step(i,j,d); //到达新点
                    s.push(temp);
                    x = i;
                    y = j;
                    maze[x][y] = -1;  //到达新点，标志已经到达
                    if(x == 6 && y == 8){
                        return 1;  //到达出口，迷宫有路，返回1
                    }else{
                        d = 0;  //重新初始化方向
                    }
                }else{
                    d++; //改变方向
                }
            }
        }
        return 0;
    }
}
