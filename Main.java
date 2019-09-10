import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        int boardSize = Integer.parseInt(JOptionPane.showInputDialog("Enter a Square size for the Board (10 through 20): "));

        int chance = Integer.parseInt(JOptionPane.showInputDialog("One in ___ chance for a cell to be a bomb (higher number = less bombs): "));
        //the higher the chance, the lower the amount of bombs (one in 6 chance vs one in two chance)
        Board board = new Board(boardSize, chance);


        /*	===================================**=================================
         * GAME COMPLETED
         *
         * Things to consider for optimization:
         * - Change the random bomb generation
         * - Make a user interface to change the boardSize and chance of bombs
         *
         *
         */


    }
}
