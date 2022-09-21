package com.tictactoeapp;

import java.util.ArrayList;
import java.util.List;

public class GameMechanics extends Board{

    List<Player> playersList = new ArrayList<>();
    int usedSquares = 0;
    public int winningNumber = 5;
    boolean ifWin = false;
    int whichPlayer = 1;

    public int getUsedSquares() {
        return usedSquares;
    }

    public void setUsedSquares() {
        this.usedSquares++;
    }

    public void resetUsedSquares() {
        this.usedSquares = 0;
    }

    public boolean checkIfWinRows(List<Character> board, Player player, int boardSize) {
        ifWin = false;
        int j=0;
        int counter; // counting how many 'O' or 'X' you need diagonally. If 5 it counts up to =<5
        //Moving row
        while ((j <= board.size()-1) && (!ifWin)) {
            int i=j;
            //move in a row one at time to the size of selected boardSize reduced by winningNumber. There is no point to
            // go to the very end because if remaining numbers of squares in a row are smaller than winningNumber you will
            // not win anyway in this row
            while ((i <= (boardSize + j - winningNumber)) && (!ifWin)) {
                if (board.get(i) == player.getPlayerSymbol()) {
                    int ii = i;
                    ifWin = true;
                    counter = 1;
                    //after fining 'O' or 'X' (player.getPlayerSymbol()) it will check in line if there is another matching symbol in sequence up to
                    // winningNumber
                    while (counter<=winningNumber) {
                        if (board.get(ii) != player.getPlayerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii++;
                        counter++;
                    }
                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }

    public boolean checkIfWinColumns(List<Character> board, Player player, int boardSize) {
        ifWin = false;
        int j=0;
        int counter;// counting how many 'O' or 'X' you need diagonally. If 5 it counts up to =<5
        //Moving row
        while ((j <= (board.size() - (boardSize * winningNumber))) && (!ifWin)) {
            int i=j;
            //move in a row one at time to the size of selected boardSize.
            while ((i < (boardSize + j)) && (!ifWin)) {
                if (board.get(i) == player.getPlayerSymbol()) {
                    int ii = i;
                    ifWin = true;
                    counter = 1;
                    //after fining 'O' or 'X' (player.getPlayerSymbol()) it will check by column (ii+=boardSize) if there is another matching symbol in sequence up to
                    // winningNumber
                    while (counter<=winningNumber) {
                        if (board.get(ii) != player.getPlayerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii += boardSize;
                        counter++;
                    }

                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }


    public boolean checkIfWinDiagonallyPositive(List<Character> board, Player player, int boardSize){
        ifWin = false;
        int j=0;
        int counter;// counting how many 'O' or 'X' you need diagonally. If 5 it counts up to =<5
        //Moving row
        while ((j <= board.size()- (boardSize * winningNumber)) && (!ifWin)) {
            int i=j;
            //move in a row one at time to the size of selected boardSize reduced by winningNumber. There is no point to
            // go to the very end because if remaining numbers of squares in a row are smaller than winningNumber you will
            // not win anyway in this row
            while ((i <= (boardSize + j - winningNumber)) && (!ifWin)) {
                if (board.get(i) == player.getPlayerSymbol()) {
                    int ii = i;
                    ifWin = true;
                    counter = 1;
                    //after fining 'O' or 'X' (player.getPlayerSymbol()) it will check diagonally (ii+=boardSize + 1) if there is another matching symbol in sequence up to
                    // winningNumber
                    while (counter<=winningNumber) {
                        if (board.get(ii) != player.getPlayerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii +=boardSize + 1 ;
                        counter++;
                    }
                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }


    public boolean checkIfWinDiagonallyNegative(List<Character> board, Player player, int boardSize){
        ifWin = false;
        int j=0;
        int counter=0;// counting how many 'O' or 'X' you need diagonally. If 5 it counts up to =<5
        //Moving row
        while ((j <= (board.size()) - (boardSize * winningNumber)) && (!ifWin)) {
            int i=j+winningNumber-1;
            //you start each row + winningNumber because it is negative diagonal so 'O' or 'X'
            //in first row will be the farthest from left
            //move in a row one at time to the size of selected boardSize.
            while ((i <= (boardSize + j-1)) && (!ifWin)) {
                if (board.get(i) == player.getPlayerSymbol()) {
                    int ii = i+boardSize-1;
                    ifWin = true;
                    counter = 2;
                    //after fining 'O' or 'X' (player.getPlayerSymbol()) it will check diagonally (ii+=boardSize - 1) if there is
                    // another matching symbol in sequence up to winningNumber
                    while (counter<=winningNumber) {
                        if (board.get(ii) != player.getPlayerSymbol()) {
                            ifWin = false;
                            break;
                        }
                        ii +=boardSize - 1 ;
                        counter++;
                    }
                }
                i++;
            }
            j += boardSize;
        }
        return ifWin ;
    }

    public boolean playerMove(List<Character> board, Player player, List<Integer> squareSelection, int boardSize, List<Integer> emptySquares ){
        boolean squareEmpty = false;
        int row = 0;
        int column = 0;
        int square=0;

        row = squareSelection.get(0)-1;
        column = squareSelection.get(1)-1;
        if (row == 0){
            square = column;
        }else {
            square = (row*boardSize)+column;
        }
        if ((board.get(square) != 'O') && (board.get(square) !='X')) {
            board.set(square, player.getPlayerSymbol());
            squareEmpty = true;
            for (int i=0; i<=emptySquares.size();i++){
                if(emptySquares.get(i) == square){
                    emptySquares.remove(i);
                    break;
                }
            }
            usedSquares++;
        } else {
            System.out.println("Selected square is already taken. Select another square:");
        }
        return  squareEmpty;
    }

    public void currentPlayer(){
        if (whichPlayer == 0) {
            whichPlayer = 1;
        } else {
            whichPlayer = 0;
        }
    }

    public void setPlayers(){
        ScannerEntry scannerEntry = new ScannerEntry();
        String name;
        boolean isHuman;

        playersList.add(new Player("Player#1",true,'O'));
        playersList.add(new Player("Player#2",true,'X'));


        System.out.println("Enter name for player#1: ");
        name = scannerEntry.getStringEntry();
        System.out.println("Is player#1 human? [y]-yes, [n]-no:");
        isHuman = scannerEntry.selectYesOrNot();
        playersList.set(0,new Player(name,isHuman,'O'));

        System.out.println("Enter name for player#2: ");
        name = scannerEntry.getStringEntry();
        System.out.println("Is player#2 human? [y]-yes, [n]-no:");
        isHuman = scannerEntry.selectYesOrNot();
        playersList.set(1,new Player(name,isHuman,'X'));
    }
    public void setWinningNumber(int boardSize){
        ScannerEntry scannerEntry = new ScannerEntry();
        boolean numberValidation=false;
        while (!numberValidation) {
            System.out.println("Please enter number 3-" + boardSize + ":");
            try {
                winningNumber = scannerEntry.getIntegerEntry();
                if((winningNumber<=boardSize) &&(winningNumber>2))
                    numberValidation=true;
            } catch (WrongValueEnteredException ignored) {
            }

        }
    }
}
