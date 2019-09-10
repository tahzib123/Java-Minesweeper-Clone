import javax.swing.text.Position;
import java.util.Random;

public class Cell {
    public boolean revealed;
    public boolean flagged;
    public boolean bomb;
    public int x, y, width;// x and y are for the position, width is the dimensions of the SQUARE
    public int neighbors;
    public int chance = 10;

    Cell(int x, int y, int chance){ //This constructor is going to be used in Board object when making the board
        this.chance = chance;
        revealed = false;
        flagged = false;//the cell has not been revealed yet
        this.x = x;
        this.y = y;
        width = 0; //set this later when drawing stuff is done
        bomb = false;

        //about a 50% chance for the cell to be a bomb
        Random coinFlip = new Random();
        int number = 1+coinFlip.nextInt(chance);
        if(number == 1) {
            bomb = true;

        }

    }

    public boolean getBomb() {
        return bomb;
    }

    public boolean getRevealed() {
        return revealed;
    }

    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    public int getNeighbors() {

        return neighbors;
    }

    public String getStringNeighbors() {
        String stringNeighbors = String.valueOf(neighbors);


        return stringNeighbors;
    }

    public void setRevealed(boolean b) {
        this.revealed = b;

    }

    public boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(boolean b) {
        flagged = b;
    }

}
