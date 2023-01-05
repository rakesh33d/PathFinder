/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rakesh
 */
public class DFS {
    public static boolean searchPath(int[][] maze,int x,int y,List<Integer>path){
        if(maze[x][y]==9)return true;//base condition
        if(maze[x][y]==0){
//            mark it visited
              maze[x][y]=2;
              int [] dx = {1,0,-1,0};
              int [] dy = {0,-1,0,1};
//              search it's neighbour's
              for(int i=0;i<4;i++){
                  int newx = x+dx[i];
                  int newy = y+dy[i];
                  if(newx>=0 && newy>=0 && newx<maze.length && newy<maze[0].length && searchPath(maze,newx,newy,path)){
                      path.add(x);
                      path.add(y);
                      System.out.println(newx+","+newy);
                      return true;
                  }
              }
              
        }
        return false;
        
    }
    public static void main(String[] args){
        
        DFS obj = new DFS();
        List<Integer> path = new ArrayList<>();
        int maze[][]= {{0,1,0},
            {0,1,9},
            {0,0,0},
        };
       
        
       boolean ans= searchPath(maze,0,0,path);
    // System.out.print(ans);
    }
    
}
