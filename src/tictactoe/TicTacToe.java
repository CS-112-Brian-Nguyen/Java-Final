package tictactoe;
 
import javax.swing.JFrame;
 
public class TicTacToe
{
    public static void main(String[] args)
    {
        JFrame ticTacToe = new TicTacToeFrame();
        ticTacToe.setTitle("Final TicTacToe Game!");
        ticTacToe.setSize(600, 600);
        ticTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticTacToe.setVisible(true);
    }
}
