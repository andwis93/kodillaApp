package com.tictactoeapp;

public class TicTacToeApp {

    public static void main(String[] args) {

        GameMechanics gameMechanics = new GameMechanics();
        Board board = new Board();
        ScannerEntry scannerEntry = new ScannerEntry();
        Display display = new Display();
        PlayerComputer playerComputer = new PlayerComputer();
        boolean end;
        boolean validMove;
        boolean isTie;
        boolean isWinner;
        System.out.println("""
                Hi,

                You are playing Tic Tac Toe.
                """);
        board.setBoardSize(scannerEntry.setBoardSize());
        System.out.println("""

                Nice! Now select how many "O's" or "X's" you need to have to win game.
                Remember number can't be greater then board size or smaller then 3!:
                """);
        gameMechanics.setWinningNumber(board.getBoardSize());
        System.out.println("\nGreat job! Now lets set players.\n");
        gameMechanics.setPlayers();
        board.preparingBoard(board.getBoardSize());
        System.out.println("""

                Awesome! You are ready to make your first move! Select square you want to mark.
                You are doing it like this [12-34]. (If board size is grater or equal 34).
                First number is row number, second is column number.
                 """);
        playerComputer.creatingComputerMoves(board.board.size());
        do {
            isWinner = false;
            isTie = false;
            validMove = false;
            while((!isWinner) && (!isTie)) {
                gameMechanics.currentPlayer();
                while (!validMove){
                    display.displayBoard(board.board, board.getBoardSize());
                    if (gameMechanics.playersList.get(gameMechanics.whichPlayer).isPlayerIsHuman()) {
                        System.out.println("\n" + gameMechanics.playersList.get(gameMechanics.whichPlayer).getPlayerName() + ":");
                        validMove = gameMechanics.playerMove(board.board, gameMechanics.playersList.get(gameMechanics.whichPlayer)
                                , scannerEntry.squareSelection(board.getBoardSize()), board.getBoardSize(),playerComputer.getEmptySquares());
                    }else{
                        if(!gameMechanics.playersList.get(gameMechanics.whichPlayer).isPlayerIsHuman()){
                            validMove = playerComputer.computerMove(board.board, gameMechanics.playersList.get(gameMechanics.whichPlayer)
                                    ,playerComputer.getEmptySquares());
                        }
                    }
                }

                //Checking if winner Start
                isWinner = gameMechanics.checkIfWinRows(board.board, gameMechanics.playersList.get(gameMechanics.whichPlayer)
                        ,board.getBoardSize());
                if (!isWinner) {
                    isWinner = gameMechanics.checkIfWinColumns(board.board, gameMechanics.playersList.get(gameMechanics.whichPlayer)
                            ,board.getBoardSize());
                    if (!isWinner) {
                        isWinner = gameMechanics.checkIfWinDiagonallyPositive(board.board, gameMechanics.playersList.get(gameMechanics.whichPlayer)
                                ,board.getBoardSize());
                        if (!isWinner) {
                            isWinner = gameMechanics.checkIfWinDiagonallyNegative(board.board, gameMechanics.playersList.get(gameMechanics.whichPlayer)
                                    ,board.getBoardSize());
                        }
                    }
                }
                //Checking if winner End
                validMove = false;
                if (((gameMechanics.getUsedSquares() >= board.board.size()) || (playerComputer.emptySquares.size()==0)) && (!isWinner)) {
                    System.out.println("It's a tie");
                    isTie = true;
                }
            }
            if (isWinner) {
                display.displayBoard(board.board, board.getBoardSize());
                System.out.println("\n*** " + gameMechanics.playersList.get(gameMechanics.whichPlayer).getPlayerName() + " has WON. Congratulations!!! ***");
            }
            System.out.println("\nDo you want to play again");
            end = scannerEntry.selectYesOrNot();
            if ((end) || (gameMechanics.getUsedSquares() >= board.board.size())) {
                board.resetBoard();
                gameMechanics.resetUsedSquares();
                playerComputer.emptySquares.removeAll(playerComputer.emptySquares);
                playerComputer.creatingComputerMoves(board.board.size());
            }
        }
        while (end);
        System.out.println("\nGoodBuy!");
    }
}
