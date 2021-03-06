package com.bgrummitt.pieces;

import com.bgrummitt.game.Player;
import com.bgrummitt.game.Type;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pawn extends Piece{

    private static final byte offsetX = -7;
    private static final byte offsetY = -6;
    private static BufferedImage whiteImg;
    private static BufferedImage blackImg;
    private int movesMade;
    public Type type;

    /**
     * Pawn constructor function
     * @param startX the starting X co-ordinate of the piece
     * @param startY the starting Y co-ordinate of the piece
     * @param player the player who owns the piece
     */
    public Pawn(int startX, int startY, int spaceSize, Player player, int arrayPos){
        super(startX, startY, spaceSize, player, arrayPos);
        type = Type.PAWN;
    }

    /**
     *Function to load the pictures in a static method and var
     */
    public static void loadPictures(){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try{
            //Get the black image
            blackImg = ImageIO.read(classloader.getResourceAsStream("pawnBlack.png"));
            //Get the white image
            whiteImg = ImageIO.read(classloader.getResourceAsStream("pawnWhite.png"));
            //If there was an IOException print it out
        }catch(IOException e){ System.out.println(e); }
    }

    /**
     * Move piece on all boards
     * @param newX new x position of piece
     * @param newY new y position of piece
     */
    public void movePiece(int newX, int newY){
        super.movePiece(newX, newY);
        movesMade++;
        //Set the piece on the board of friendly pieces
        player.onePlayerBoard[y][x] = null;
        player.onePlayerBoard[newY][newX] = type;
        //Set the piece on the whole board
        player.board.board[y][x] = null;
        player.board.board[newY][newX] = type;
    }

    /**
     * A function for painting the pieces picture
     * @param g the graphics from the JPanel's paint function
     * @param panel is the JPanel that will be painted to
     */
    public void paintPiece(Graphics g, JPanel panel){
        //If the player is black draw the black image
        if(player.colour.equals("black")){
            g.drawImage(blackImg, ((x * spaceSize) + offsetX), ((y * spaceSize) + offsetY), panel);
        }
        //Else if the player is white draw the white image
        else if(player.colour.equals("white")){
            g.drawImage(whiteImg, ((x * spaceSize) + offsetX), ((y * spaceSize) + offsetY), panel);
        }
    }

    /**
     * Function that checks if the move is valid
     * @param newX X position being moved to
     * @param newY Y position being moved to
     */
    public boolean isValidMove(byte newX, byte newY){
        //If the move is 2 spaces forward only allow it if it is the first move
        if((y - newY) == (-2 * moveForward) && newX == x && movesMade == 0){
            return true;
        }
        //If the move is 1 space forward and nothing is in that space allow it
        else if((y - newY) == -moveForward && x == newX && player.board.board[newY][newX] == null){
            return true;
        }
        //If the move is diagonal check and a player is in that space return true
        else if((y - newY) == -moveForward && Math.abs(x - newX) == 1 && player.board.board[newY][newX] != null){
            return true;
        }
        return false;
    }

    /**
     * Function to check if piece can get to end spot without
     * any friendly or opposition pieces in the way
     * @param newX x position piece is moving to
     * @param newY y position piece is moving to
     * @param board the full board where every piece is
     * @return true or false based on if path is valid
     */
    public boolean isValidPath(byte newX, byte newY, Type[][] board){
        //Pawn cannot move more than one space after first move so
        //proving validity of path is not necessary unless it is move 1
        //If it is the first move and the move is 2 space and there is not a player in between the two spaces
        return movesMade != 0 || (y - newY) != (-2 * moveForward) || player.board.board[newY - moveForward][newX] == null;
    }

}