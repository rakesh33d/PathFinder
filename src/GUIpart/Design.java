/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIpart;


import Algorithms.DFS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rakesh
 */
public class Design extends JFrame implements ActionListener,MouseListener{
    
//    values : 0-> not visited
//             1-> blocked
//             2-> visited
//             9-> end point or target
    
       private int[][] maze = 
        {    
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,1,1,1,0,1},
        {1,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,1,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,0,0,1,0,1},
        {1,0,1,0,1,1,1,1,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,9,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
       private int[] target = {8,11};
       private List<Integer> path = new ArrayList<>();
//       declare buttons
       JButton submitButton;
       JButton clearButton;
       JButton exitButton;
       
    
    
//    constructor
    public Design(){
        this.setTitle("Path Finder");
        this.setSize(520,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
        
        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(this);
        submitButton.setBounds(120,400,80,30);
        
        clearButton = new JButton("CLEAR");
        clearButton.addActionListener(this);
        clearButton.setBounds(220,400,80,30);
        
        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        exitButton.setBounds(320,400,80,30);
        
        this.addMouseListener(this);
        this.add(submitButton);
        this.add(exitButton);
        this.add(clearButton);
        
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(int row=0; row<maze.length; row++){
            for(int cols=0; cols<maze[0].length; cols++){
                Color color;
                switch(maze[row][cols]){
                    case 1: color = Color.BLACK;break;
                    case 9: color = Color.red;break;
                    default : color = Color.white;break;
                }
                
                g.setColor(color);
                g.fillRect(cols*40, row*40, 40, 40);
                g.setColor(Color.BLACK);
                g.drawRect(cols*40, row*40, 40, 40);
            }
        }
        
//        for painting the path
for(int i=0;i<path.size();i+=2){
    int pathx = path.get(i);
    int pathy = path.get(i+1);
    g.setColor(Color.green);
    g.fillRect(40*pathy, 40*pathx, 40, 40);
}
    }
      @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()== submitButton){
            try{
              DFS.searchPath(maze, 1, 1, path);
              this.repaint();
            }
            catch(Exception excp){
                JOptionPane.showMessageDialog(null, excp.toString());
            }
            
        }
        
        if(e.getSource()== exitButton){
            int flag= JOptionPane.showConfirmDialog(null, "Are you sure you want to exit", "Submit", JOptionPane.YES_NO_OPTION);
            if(flag==0){
                System.exit(0);
            }
        }
        
        if(e.getSource()== clearButton){
            path.clear();
            for(int row=0;row<maze.length;row++){
                for(int col=0;col<maze[0].length;col++){
//                    make visited places unvisited again
                    if(maze[row][col]==2){
                        maze[row][col]=0;
                    }
                }
            }
            this.repaint();
        }
    }
    
       @Override
    public void mouseClicked(MouseEvent e) {
//        check if mouse is clicked inside maze
       if(e.getX() >=0 && e.getX()<=maze[0].length*40 && e.getY() >=0 && e.getY()<=maze.length*40){
           int row = e.getY()/40;
           int col = e.getX()/40;
           if(maze[row][col]==1)return;
           
           Graphics g = getGraphics();//gets the graphics used above
           g.setColor(Color.white);
           g.fillRect(target[1]*40, target[0]*40, 40, 40);
           g.setColor(Color.red);
           g.fillRect(col*40, row*40, 40, 40);
           maze[target[0]] [target[1]]=0;
           maze[row][col]=9;
           target[0]=row;
           target[1]=col;
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    public static void main(String[] args){
        
        Design gui = new Design();
//        by default the JFrame is set to be in invisible state.
//        so make it visible to user
        gui.setVisible(true);
    } 
}
