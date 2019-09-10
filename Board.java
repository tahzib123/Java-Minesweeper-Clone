import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Board	extends JFrame {
    public Cell [][]arr;
    public int boardSize;
    private JButton[][] squares;
    public int w;
    public int chance;
    public int bombCount;

    Board(int boardSize, int chance){
        this.chance = chance;
        this.boardSize = boardSize;
        Cell[][] arr = new Cell[boardSize][boardSize];
        int neighbors = 0;
        w = 20;
        bombCount = 0;
        squares = new JButton[boardSize][boardSize];


        Container contents = getContentPane();
        contents.setLayout(new GridLayout(boardSize, boardSize));

        //create the two dimensional array of cells
        for(int x = 0; x < boardSize; x++) {
            for(int y = 0; y < boardSize; y++) {
                squares[x][y] = new JButton();
                arr[x][y] = new Cell(x*w,y*w, chance);
                squares[x][y].setBackground(new Color(240,240,241));

                squares[x][y].setBorder(BorderFactory.createRaisedSoftBevelBorder());
                contents.add(squares[x][y]);
                if(arr[x][y].getBomb() == true) bombCount++;
                final Integer innerx = new Integer(x);
                final Integer innery = new Integer(y);
                squares[x][y].addMouseListener(new MouseAdapter() {

                    public void openCell(int innerx, int innery) {
                        if(arr[innerx][innery].getFlagged() == false) {
                            if(arr[innerx][innery].getBomb() == true) {
                                squares[innerx][innery].setBackground(Color.RED);
                                squares[innerx][innery].setText("B");



                                JOptionPane.showMessageDialog(contents, "You lose!");

                                System.exit(0);
                            }



                            if((arr[innerx][innery].getBomb() == false) && (arr[innerx][innery].getNeighbors() == 0)) {
                                squares[innerx][innery].setBackground(Color.LIGHT_GRAY);
                                squares[innerx][innery].setText(" ");
                                arr[innerx][innery].setRevealed(true);
                                for(int i = -1; i <= 1; i++) { //then check the 8 cells around it
                                    for(int j = -1; j <= 1; j++) { // cont.
                                        int p = innerx + i;
                                        int k = innery + j;
                                        if((p != -1 && p < boardSize) && (k != -1 && k < boardSize)) {
                                            if(arr[p][k].getNeighbors() != 0)
                                                squares[p][k].setText(arr[p][k].getStringNeighbors());

                                            if(arr[p][k].getNeighbors() == 0) {
                                                if(arr[p][k].getRevealed() == false) openCell(p,k);

                                            }
                                        }
                                    }
                                }
                            }
                            if((arr[innerx][innery].getBomb() == false) && (arr[innerx][innery].getNeighbors() != 0)) {
                                squares[innerx][innery].setText(arr[innerx][innery].getStringNeighbors());
                                arr[innerx][innery].setRevealed(true);
                            }
                        }
                    }


                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //arr[x][y].setRevealed(true);


                        if (e.getButton() == 1)  { //1 means left, 3 means right
                            openCell(innerx, innery);
                        }

                        if	(e.getButton() == 3) {
                            if(arr[innerx][innery].getFlagged() == false) {
                                arr[innerx][innery].setFlagged(true);
                                squares[innerx][innery].setForeground(Color.MAGENTA);
                                squares[innerx][innery].setText("F");
                                if(arr[innerx][innery].getBomb() == true) bombCount--;

                                if(bombCount == 0) {
                                    JOptionPane.showMessageDialog(contents, "You win!");
                                    System.exit(0);
                                }

                            }else {
                                arr[innerx][innery].setFlagged(false);
                                squares[innerx][innery].setText(" ");
                                if(arr[innerx][innery].getBomb() == true) bombCount++;
                            }





                        }


                    }


                });

            }
        } //end of for loop


        //check the neighbors and setNeighbors
        for(int x = 0; x < boardSize; x++) {
            for(int y = 0; y < boardSize; y++) {


                if((arr[x][y].getBomb() == false) ){ //IF the cell at x,y is not a bomb
                    for(int i = -1; i <= 1; i++) { //then check the 8 cells around it
                        for(int j = -1; j <= 1; j++) { // cont.
                            int p = x + i;
                            int k = y + j;
                            if((p != -1 && p < boardSize) && (k != -1 && k < boardSize)) {
                                if(arr[p][k].getBomb() == true) neighbors++; // if they are a bomb,
                                //then increment neighbors
                            }
                            arr[x][y].setNeighbors(neighbors);

                            //set the cell's neighbors
                            //amount equal to that neigbbors
                        }

                    }
                }
                neighbors = 0;
            }
        }// end of that madness



        int tempSize = boardSize * 45;
        setSize(tempSize,tempSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}
