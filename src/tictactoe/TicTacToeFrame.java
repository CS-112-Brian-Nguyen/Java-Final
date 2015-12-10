package tictactoe;

import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
 
/**
* JFrame to hold TicTacToe board.
*/
public class TicTacToeFrame extends JFrame
{
   // Indicate whose turn it is
   private char whoseTurn = 'X';
   private boolean gameOver = false;
 
   // Create cell grid
   private Cell[][] cells = new Cell[3][3];
 
   // Create a status label
   JLabel jlblStatus = new JLabel("It is now X's turn to move.");
 
   /**
    * No-argument Constructor
    */
   public TicTacToeFrame()
   {
       // Panel to hold cells
       JPanel panel = new JPanel(new GridLayout(3, 3, 0, 0));
       for (int i = 0; i < 3; i++)
           for (int j = 0; j < 3; j++)
               panel.add(cells[i][j] = new Cell());
 
       panel.setBorder(new LineBorder(Color.red, 1));
       jlblStatus.setBorder(new LineBorder(Color.yellow, 1));
 
       //center the strings
       add(panel, BorderLayout.CENTER);
       
       //Puts label at the bottom
       add(jlblStatus, BorderLayout.SOUTH);
   }
 
   //Create a scenario for tie game.
    public boolean isFull()
    {
       for (int i = 0; i < 3; i++)
           for (int j = 0; j < 3; j++)
               if (cells[i][j].getBox() == ' ')
                   return false;
       return true;
    }
 
   // returns true if win, also checks rows and columns to see matching as well as diagonals
   public boolean isWon(char box)
   {
       // check rows
       for (int i = 0; i < 3; i++)
           if ((cells[i][0].getBox() == box)
                   && (cells[i][1].getBox() == box)
                   && (cells[i][2].getBox() == box))
           {
               return true;
           }
 
       // check columns
       for (int j = 0; j < 3; j++)
           if ((cells[0][j].getBox() == box)
               && (cells[1][j].getBox() == box)
               && (cells[2][j].getBox() == box))
           {
               return true;
           }
       // check diagonal
       if ((cells[0][0].getBox() == box)
               && (cells[1][1].getBox() == box)
               && (cells[2][2].getBox() == box))
           {
               return true;
           }
 
       if ((cells[0][2].getBox() == box)
               && (cells[1][1].getBox() == box)
               && (cells[2][0].getBox() == box))
           {
               return true;
           }
 
       return false;
   }
 

   //Creating functions of the cell seperate class!!!
    public class Cell extends JPanel
    {
       // box of the cell, instance variable for constructor
       private char box = ' ';
       public int isOccupied = 1;
 
       //Constructor
       public Cell()
       {
           setBorder(new LineBorder(Color.black, 3));
           addMouseListener(new MyMouseListener());
           isOccupied = 1;
       }       
 
       // Gets the variable box back
       public char getBox()
       {
           return box;
       }
 
       //Sets the box of the cell to be a char c
       public void setBox(char c)
       {
           box = c;
           repaint();
       }
 
       //Graphics is is the abstract base class that allows you to draw
       @Override
       protected void paintComponent(Graphics g)
       {
           super.paintComponent(g);
 
           if (box == 'X' && isOccupied == 1)
           {
               g.drawLine(0, 0, getWidth(), getHeight());
               g.drawLine(getWidth(), 0, 0, getHeight());
               isOccupied = 0;
           }
           
 
           else if (box == 'O' && isOccupied == 1)
           {
               g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
               isOccupied = 0;
           }
       }
 
       private class MyMouseListener extends MouseAdapter
       {
    	   //important to override to end the game and make it so cannot mouse click
           @Override
           public void mouseClicked(MouseEvent e)
           {
        	   //Ends the game when gameOver is true! like a while loop.
               if (gameOver)
                   return;
               
                // if the cell is empty and the game is not over, gives ability to paint again
               if (box == ' ' && whoseTurn != ' ')
                   setBox(whoseTurn);
               
               if (isWon(whoseTurn))
               {
                   jlblStatus.setText(whoseTurn + " won! Game over!");
                   whoseTurn = ' ';
                   gameOver = true;
               }
            // This is my Polymorphism
               else if (isFull())
               {
                   jlblStatus.setText("Sorry, this game is a tie.");
                   whoseTurn = ' ';
                   gameOver = true;
               }
               else if (whoseTurn == 'X' && isOccupied == 1)
            	   { 
            		   whoseTurn = 'O';
            	   }
               else if (whoseTurn == 'O' && isOccupied == 1)
            	   {
            		   whoseTurn =  'X';
            	   }
                   jlblStatus.setText(whoseTurn + "'s turn.");
               }
           }
       } 
    } 