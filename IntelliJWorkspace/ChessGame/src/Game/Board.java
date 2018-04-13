package Game;

import Pieces.*;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Board{

    public Type[][] board = new Type[8][8];
    private Player whitePlayer;
    private Player blackPlayer;
    private byte spaceSize;
    private Player turn;
    private int[] clickedCoordinates;
    private boolean squareToHighlight = false;

    /**
     * Board Constructor class
     * @param spaceSize
     */
    public Board(byte spaceSize){
        Pawn.loadPictures();

        whitePlayer = new Player("white", this, spaceSize);
        blackPlayer = new Player("black", this, spaceSize);

        turn = whitePlayer;

        this.spaceSize = spaceSize;

        printBoard();
    }

    /**
     * Temp function to print board
     * TODO Remove function
     */
    public void printBoard(){
        for(Type[] sec : board){
            for(Type i : sec){
                System.out.println(i);
            }
            System.out.println("\n");
        }
    }

    /**
     * Function to find the co-ordinated of a clicked square
     * @param clickedX x co-ordinate of the mouse click
     * @param clickedY y co-ordinate of the mouse click
     */
    public void clickedSquare(int clickedX, int clickedY){
        if(squareToHighlight && turn.isPlayerInSquare((byte)clickedCoordinates[0], (byte)clickedCoordinates[1])){
            int[] tempCo = simplifyCoordinates(clickedX, clickedY);
            System.out.println(turn.pieceInSquare(clickedCoordinates[0], clickedCoordinates[1]).isValidMove((byte)tempCo[0], (byte)tempCo[1]));
        }
        clickedCoordinates = simplifyCoordinates(clickedX, clickedY);
        if(turn.isPlayerInSquare((byte)clickedCoordinates[0], (byte)clickedCoordinates[1])){
            squareToHighlight = true;
        }else{
            squareToHighlight = false;
        }
    }

    /**
     * Function to simplify the co-ordinates into co-ordinates for an 8x8 grid
     * @param clickedX
     * @param clickedY
     * @return
     */
    public int[] simplifyCoordinates(int clickedX, int clickedY){
        //Initialise with a new array of size 2
        int[] tempCoords = new int[2];
        //Loop 0-7
        for(int i = 0; i < 8; i++){
            //If the x is greater than or equal to i * spaceSize and less that (i + 1) * spaceSize set 0 in array to i
            if(clickedX >= (i * spaceSize) && clickedX < ((i+1) * spaceSize)){
                tempCoords[0] = i;
            }
            //If the y is greater than or equal to i * spaceSize and less that (i + 1) * spaceSize set 1 in array to i
            if(clickedY >= (i * spaceSize) && clickedY < ((i + 1) * spaceSize)){
                tempCoords[1] = i;
            }
        }
        return tempCoords;
    }

    /**
     * Paint white and black pieces and Highlight spots if any
     * @param g Graphics object that will be painted on
     * @param panel The JPanel being painted on
     */
    public void paint(Graphics g, JPanel panel){
        whitePlayer.paint(g, panel);
        blackPlayer.paint(g, panel);

        if(squareToHighlight){
            g.setColor(Color.RED);
            g.drawRect((clickedCoordinates[0] * spaceSize), (clickedCoordinates[1] * spaceSize), spaceSize, spaceSize);
        }
    }

}